package com.example.demo.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TrackedBus;

public interface TrackedBusRepository
        extends JpaRepository<TrackedBus,Long>{

    List<TrackedBus> findByBusNumber(String busNumber);

    Optional<TrackedBus> findByUserIdAndBusNumber(
            Long userId,
            String busNumber);

}
