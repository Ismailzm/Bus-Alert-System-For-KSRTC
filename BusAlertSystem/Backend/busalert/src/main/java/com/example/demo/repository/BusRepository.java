package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.enums.BusType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    Optional<Bus> findByBusNumber(String busNumber);

    boolean existsByBusNumber(String busNumber);

    List<Bus> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);
    
    List<Bus> findByBusType(BusType busType);
    
    List<Bus> findBySourceIgnoreCaseAndDestinationIgnoreCaseAndBusType(
            String source,
            String destination,
            BusType busType);

}