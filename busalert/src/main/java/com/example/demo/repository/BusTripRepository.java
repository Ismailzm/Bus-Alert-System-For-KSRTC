package com.example.demo.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BusTrip;

@Repository
public interface BusTripRepository extends JpaRepository<BusTrip, Long>{

    Optional<BusTrip> findByBusNumberAndActiveTrue(String busNumber);

}