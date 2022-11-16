package com.example.carrental.reservation;

import com.example.carrental.car.Car;
import com.example.carrental.car.CarRepository;
import com.example.carrental.exceptions.CarDoesNotExists;
import com.example.carrental.exceptions.ReservationDoesNotExists;
import com.example.carrental.exceptions.WrongDateException;
import com.example.carrental.price.priceforrent.PriceForRentService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final CarRepository carRepository;
    private final PriceForRentService priceForRentService;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper, CarRepository carRepository, PriceForRentService priceForRentService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.carRepository = carRepository;
        this.priceForRentService = priceForRentService;
    }

    public ReservationResponse save(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.mapFromDto(reservationRequest);
        double price = priceForRentService.calculatePriceForReservation(reservation.getCar().getId(), reservation.getDateTo().compareTo(reservation.getDateFrom()));
        reservation.setTotalPrice(price);
        reservationRepository.save(reservation);

        if (checkDate(reservation)) {
            throw new WrongDateException();
        }
        return reservationMapper.mapToDto(reservation);
    }

    public List<ReservationResponse> reservationList(Long carId) {
        return reservationRepository.findByCar_Id(carId)
                .stream()
                .map(reservationMapper::mapToDto).toList();
    }

    public boolean checkDate(Reservation reservation) {
        List<Reservation> allReservations = reservationRepository.findAll();
        for (Reservation r : allReservations) {
            if (    reservation.getDateFrom().isBefore(LocalDate.now()) ||
                    reservation.getDateFrom().isEqual(reservation.getDateTo()) ||
                    reservation.getDateFrom().isEqual(r.getDateFrom()) ||
                    reservation.getDateTo().isEqual(r.getDateTo()) ||
                    reservation.getDateFrom().isBefore(r.getDateTo()) ||
                    reservation.getDateTo().isAfter(r.getDateFrom())) {
                return true;
            }
        }
        return false;
    }

    @PostConstruct
    public boolean deleteReservationIfTimePassed() {
        List<Reservation> allReservations = reservationRepository.findAll();
        allReservations.stream()
                .filter(reservation -> reservation.getDateTo().isBefore(LocalDate.now()))
                .forEach(reservationRepository::delete);
        return true;
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
}
