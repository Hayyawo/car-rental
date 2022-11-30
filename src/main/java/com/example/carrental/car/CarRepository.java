package com.example.carrental.car;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("""
            SELECT c FROM car c
            WHERE (:#{#carFilter.name} IS NULL OR c.name = :#{#carFilter.name})
            """)
    Page<Car> findByFiler(@Param("carFilter") CarFilter carFilter, Pageable pageable);
}
