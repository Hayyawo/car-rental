package com.example.carrental.reservation;

import com.example.carrental.accessories.Accessory;
import com.example.carrental.accessories.AccessoryRepository;
import com.example.carrental.car.Car;
import com.example.carrental.car.CarRepository;
import com.example.carrental.exceptions.AccessoryDoesNotExists;
import com.example.carrental.exceptions.CarDoesNotExists;
import com.example.carrental.exceptions.ReservationDoesNotExists;
import com.example.carrental.exceptions.WrongDateException;
import com.example.carrental.price.priceforrent.PriceForRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final CarRepository carRepository;
    private final PriceForRentService priceForRentService;
    private final AccessoryRepository accessoryRepository;

    public ReservationResponse save(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.mapFromDto(reservationRequest);
        double price = priceForRentService.calculatePriceForReservation(reservation.getCar().getId(), reservation.getDateTo().compareTo(reservation.getDateFrom()));
        reservation.setTotalPrice(price);

        if (checkDate(reservation)) {
            throw new WrongDateException();
        }
        addAccessoriesToReservation(reservationRequest, reservation);

        reservationRepository.save(reservation);
        return reservationMapper.mapToDto(reservation);
    }

    public List<ReservationResponse> reservationList(Long carId) {
        if (carId == null) {
            return reservationRepository.findAll()
                    .stream()
                    .map(reservationMapper::mapToDto).toList();
        } else {
            return reservationRepository.findByCar_Id(carId)
                    .stream()
                    .map(reservationMapper::mapToDto).toList();
        }
    }

    @PostConstruct
    public void deleteReservationIfTimePassed() {
        List<Reservation> allReservations = reservationRepository.findAll();
        allReservations.stream()
                .filter(reservation -> reservation.getDateTo().isBefore(LocalDate.now()))
                .forEach(reservationRepository::delete);
    }

    public ReservationResponse editReservation(ReservationRequest reservationRequest) {
        Reservation reservation = reservationRepository.findById(reservationRequest.getId())
                .orElseThrow(ReservationDoesNotExists::new);
        Car car = carRepository.findById(reservationRequest.getCarId())
                .orElseThrow(CarDoesNotExists::new);

        reservation.setDateTo(reservationRequest.getDateTo());
        reservation.setDateFrom(reservationRequest.getDateTo());
        reservation.setCar(car);

        reservationRepository.save(reservation);
        return reservationMapper.mapToDto(reservation);
    }

    public void delete(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationDoesNotExists::new);
        reservationRepository.delete(reservation);
    }

    //todo nie dziala to poprawnie daty sie moga zazebiac i nie wywala bledu

    private boolean checkDate(Reservation reservation) {
        List<Reservation> allReservations = reservationRepository.findAll();
        for (Reservation r : allReservations) {
            return reservation.getDateFrom().isBefore(LocalDate.now()) ||
                    reservation.getDateFrom().isEqual(r.getDateTo()) ||
                    reservation.getDateFrom().isEqual(r.getDateFrom()) ||
                    reservation.getDateTo().isEqual(r.getDateTo()) ||
                    reservation.getDateTo().isEqual(r.getDateFrom()) ||
                    reservation.getDateFrom().isBefore(r.getDateTo()) ||
                    reservation.getDateTo().isBefore(r.getDateFrom()) ||
                    (reservation.getDateFrom().isEqual(r.getDateFrom()) && reservation.getDateTo().isEqual(r.getDateTo()));
        }
        return false;
    }
    private void addAccessoriesToReservation(ReservationRequest reservationRequest, Reservation reservation) {
        List<Accessory> accessories = new ArrayList<>();
        List<Integer> accessoriesIds = reservationRequest.getAccessoriesIds();
        for (Integer accessory : accessoriesIds) {
            accessories.add(accessoryRepository.findById(accessory.longValue()).orElseThrow(AccessoryDoesNotExists::new));
        }
        reservation.setAccessories(accessories);
    }
}
