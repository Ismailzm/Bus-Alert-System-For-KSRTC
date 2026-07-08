package com.example.demo.service;

import com.example.demo.entity.Notification;
import com.example.demo.entity.TrackedBus;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.TrackedBusRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DelayReport;
import com.example.demo.enums.DelayStatus;
import com.example.demo.repository.DelayReportRepository;

@Service
public class DelayReportService {

	private final DelayReportRepository delayReportRepository;

	private final TrackedBusRepository trackedBusRepository;

	private final NotificationRepository notificationRepository;

	public DelayReportService(

	        DelayReportRepository delayReportRepository,

	        TrackedBusRepository trackedBusRepository,

	        NotificationRepository notificationRepository) {

	    this.delayReportRepository = delayReportRepository;

	    this.trackedBusRepository = trackedBusRepository;

	    this.notificationRepository = notificationRepository;

	}

    // ======================================
    // Submit Delay Report
    // ======================================

    public DelayReport submitReport(DelayReport report){

        report.setReportedAt(LocalDateTime.now());

        report.setStatus(DelayStatus.PENDING);

        return delayReportRepository.save(report);

    }

    // ======================================
    // Pending Reports
    // ======================================

    public List<DelayReport> getPendingReports(){

        return delayReportRepository.findByStatus(
                DelayStatus.PENDING);

    }

    // ======================================
    // All Reports
    // ======================================

    public List<DelayReport> getAllReports(){

        return delayReportRepository.findAll();

    }

    // ======================================
    // Approve Report
    // ======================================

 // ======================================
 // Approve Report
 // ======================================

 public DelayReport approveReport(Long id){

     DelayReport report = delayReportRepository
             .findById(id)
             .orElseThrow(() ->
                     new RuntimeException("Report Not Found"));

     report.setStatus(DelayStatus.APPROVED);

     delayReportRepository.save(report);

     // ===================================
     // Notify All Users Tracking This Bus
     // ===================================

     List<TrackedBus> trackedUsers =
             trackedBusRepository.findByBusNumber(
                     report.getBusNumber());

     for(TrackedBus trackedBus : trackedUsers){

         Notification notification =
                 new Notification();

         notification.setUserId(
                 trackedBus.getUserId());

         notification.setMessage(

                 "🚌 " +

                 report.getBusNumber() +

                 " has been delayed.\n\nReason : " +

                 report.getReason() +

                 "\nExpected Delay : " +

                 report.getDelayMinutes() +

                 " Minutes."

         );

         notification.setRead(false);

         notificationRepository.save(notification);

     }

     return report;

 }

    // ======================================
    // Reject Report
    // ======================================

    public DelayReport rejectReport(Long id){

        DelayReport report = delayReportRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Report Not Found"));

        report.setStatus(DelayStatus.REJECTED);

        return delayReportRepository.save(report);

    }

}