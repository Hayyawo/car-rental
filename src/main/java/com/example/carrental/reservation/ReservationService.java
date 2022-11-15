package com.example.carrental.reservation;

import com.example.carrental.car.Car;
import com.example.carrental.car.CarRepository;
import com.example.carrental.exceptions.CarDoesNotExists;
import com.example.carrental.exceptions.WrongDateException;
import com.example.carrental.price.PriceService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final CarRepository carRepository;
    private final PriceService priceService;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper, CarRepository carRepository, PriceService priceService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.carRepository = carRepository;
        this.priceService = priceService;
    }

    public ReservationResponse save(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.mapFromDto(reservationRequest);
        double price = priceService.calculatePriceForReservation(reservation.getCar().getId(), reservation.getDateTo().compareTo(reservation.getDateFrom()));
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
}
