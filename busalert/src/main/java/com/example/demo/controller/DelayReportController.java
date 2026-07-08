package com.example.demo.controller;



import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.DelayReport;
import com.example.demo.service.DelayReportService;

@RestController
@RequestMapping("/api/delay")
@CrossOrigin(origins = "*")
public class DelayReportController {

    private final DelayReportService delayReportService;

    public DelayReportController(
            DelayReportService delayReportService) {

        this.delayReportService = delayReportService;

    }

    // ======================================
    // Submit Delay Report
    // ======================================

    @PostMapping
    public DelayReport submitReport(
            @RequestBody DelayReport report){

        return delayReportService.submitReport(report);

    }

    // ======================================
    // Get All Reports
    // ======================================

    @GetMapping
    public List<DelayReport> getAllReports(){

        return delayReportService.getAllReports();

    }

    // ======================================
    // Get Pending Reports
    // ======================================

    @GetMapping("/pending")
    public List<DelayReport> getPendingReports(){

        return delayReportService.getPendingReports();

    }

    // ======================================
    // Approve Report
    // ======================================

    @PutMapping("/approve/{id}")
    public DelayReport approveReport(
            @PathVariable Long id){

        return delayReportService.approveReport(id);

    }

    // ======================================
    // Reject Report
    // ======================================

    @PutMapping("/reject/{id}")
    public DelayReport rejectReport(
            @PathVariable Long id){

        return delayReportService.rejectReport(id);

    }

}