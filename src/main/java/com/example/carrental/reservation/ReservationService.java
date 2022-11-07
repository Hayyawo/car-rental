package com.example.carrental.reservation;

import com.example.carrental.exceptions.WrongDateException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public boolean save(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.mapFromDto(reservationRequest);
        checkDate(reservation);
        return true;
    }

    public List<ReservationResponse> reservationList(Long carId) {
        return reservationRepository.findByCar_Id(carId)
                .stream()
                .map(reservationMapper::mapToDto).toList();
    }

    @PostConstruct
    public boolean deleteReservationIfTimePassed() {
        List<Reservation> allReservations = reservationRepository.findAll();
        allReservations.stream()
                .filter(reservation -> reservation.getDateTo().isBefore(LocalDate.now()))
                .forEach(reservationRepository::delete);
        return true;
    }

    private void checkDate(Reservation reservation) {
        List<Reservation> listOfReservations = reservationRepository.findByCar_Id(reservation.getCar().getId());
        boolean checkIfDateIsAlreadyBooked = listOfReservations.stream()
                .anyMatch(reservations -> reservation.getDateFrom().isAfter(reservations.getDateFrom())
                        && reservation.getDateTo().isBefore(reservations.getDateTo()));

        if (reservation.getDateFrom().isBefore(LocalDate.now()) || reservation.getDateTo().isBefore(LocalDate.now())) {
            throw new WrongDateException();
        } else if (reservation.getDateTo().isEqual(reservation.getDateFrom()) || checkIfDateIsAlreadyBooked) {
            throw new WrongDateException();
        } else {
            reservationRepository.save(reservation);
        }
    }
}
