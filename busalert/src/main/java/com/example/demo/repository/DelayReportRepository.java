package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.DelayReport;
import com.example.demo.enums.DelayStatus;

@Repository
public interface DelayReportRepository extends JpaRepository<DelayReport, Long> {

    List<DelayReport> findByStatus(DelayStatus status);

    Optional<DelayReport> findFirstByBusNumberAndStatusOrderByReportedAtDesc(
            String busNumber,
            DelayStatus status);

    @Transactional
    @Modifying
    void deleteByBusNumber(String busNumber);

}