package com.example.carrental.reservation;

import com.example.carrental.car.Car;
import com.example.carrental.car.CarRepository;
import com.example.carrental.exceptions.CarDoesNotExists;
import com.example.carrental.exceptions.WrongDateException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.carRepository = carRepository;
    }

    public void save(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.mapFromDto(reservationRequest);
        double price = calculatePriceForReservation(reservation);
        reservation.setPriceForReservation(price);
        reservationRequest.setPriceForReservation(price);
        if(checkDate(reservation)){
            throw new WrongDateException();
        }
        reservationRepository.save(reservation);
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

        changeCarAvailability(allReservations);
        return true;
    }

    private double calculatePriceForReservation(Reservation reservation) {
        int numberOfDays = reservation.getDateTo().compareTo(reservation.getDateFrom());
        System.out.println(numberOfDays);
        double priceForDay = reservation.getCar().getPriceForDay();
        priceForDay = discountCondition(numberOfDays, priceForDay);
        double priceForAllReservation = 0;

        for (int i = 0; i <= numberOfDays; i++) {
            priceForAllReservation += priceForDay;
        }

        return priceForAllReservation;
    }

    private static double discountCondition(int numberOfDays, double priceForReservation) {
        if (numberOfDays > 5 && numberOfDays < 10) {
            priceForReservation = priceForReservation - (priceForReservation * 0.10);
            return priceForReservation;
        } else if (numberOfDays > 10 && numberOfDays < 20) {
            priceForReservation = priceForReservation - (priceForReservation * 0.20);
            return priceForReservation;
        } else if (numberOfDays > 20 && numberOfDays < 30) {
            priceForReservation = priceForReservation - (priceForReservation * 0.30);
            return priceForReservation;
        } else {
            priceForReservation = priceForReservation - (priceForReservation * 0.40);
            return priceForReservation;
        }

    }

    private void changeCarAvailability(List<Reservation> allReservations) {
        for (Reservation reservation : allReservations) {
            if (reservation.getDateTo().isBefore(LocalDate.now())) {
                Car car = carRepository.findById(reservation.getCar().getId())
                        .orElseThrow(CarDoesNotExists::new);
                car.setCarFreeNow(true);
                carRepository.save(car);
            }
        }
    }

//    private void checkDate(Reservation reservation) {
//        List<Reservation> listOfReservations = reservationRepository.findByCar_Id(reservation.getCar().getId());
//        boolean checkIfDateIsAlreadyBooked = isDateAlreadyBooked(reservation, listOfReservations);
//
//        if (reservation.getDateFrom().isBefore(LocalDate.now()) || reservation.getDateTo().isBefore(LocalDate.now())) {
//            throw new WrongDateException();
//        } else if (reservation.getDateTo().isEqual(reservation.getDateFrom()) || checkIfDateIsAlreadyBooked) {
//            throw new WrongDateException();
//        }
//    }

    private boolean checkDate(Reservation reservation) {
        List<Reservation> listOfReservations = reservationRepository.findByCar_Id(reservation.getCar().getId());

        if (reservation.getDateFrom().isBefore(reservation.getDateTo()) && !reservation.getDateTo().equals(LocalDate.now()) && !reservation.getDateFrom().isBefore(LocalDate.now())) {
            for (int i = 0; i < listOfReservations.size(); i++) {
                for (Reservation listOfReservation : listOfReservations) {
                    if (reservation.getDateFrom().isEqual(listOfReservations.get(i).getDateTo()) && reservation.getDateTo().isAfter(listOfReservations.get(i).getDateTo())) {
                        return false;
                    } else if (reservation.getDateFrom().isEqual(listOfReservations.get(i).getDateTo()) && reservation.getDateTo().isEqual(listOfReservation.getDateFrom())) {
                        return false;
                    } else if (reservation.getDateFrom().isAfter(listOfReservations.get(i).getDateFrom()) && reservation.getDateTo().isAfter(listOfReservations.get(i).getDateTo())) {
                        return false;
                    } else {
                        return true;
                    }

                }
            }
        } else {
            return true;
        }
        return false;
//        return listOfReservations.stream()
//                .anyMatch(reservations ->
//                           (reservation.getDateFrom().isBefore(reservations.getDateFrom())
//                        && reservation.getDateTo().isAfter(reservations.getDateFrom()))
//
//                        ||
//                        (reservation.getDateFrom().isBefore(reservations.getDateTo())
//                                && reservation.getDateTo().isAfter(reservations.getDateTo()))
//                        ||
//
//                        (reservation.getDateFrom().isBefore(reservations.getDateFrom())
//                                && reservation.getDateTo().isAfter(reservations.getDateTo()))
//                        ||
//                                   (reservation.getDateFrom().isAfter(reservations.getDateFrom())
//                                           && reservation.getDateTo().isBefore(reservations.getDateTo())));
    }

}
