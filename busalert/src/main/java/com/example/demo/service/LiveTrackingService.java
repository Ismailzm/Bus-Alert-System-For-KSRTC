package com.example.demo.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.config.KarnatakaDistricts;
import com.example.demo.dto.LiveTrackingResponse;
import com.example.demo.entity.BusTrip;
import com.example.demo.graph.KarnatakaRoadNetwork;
import com.example.demo.repository.BusTripRepository;
import com.example.demo.util.GeoUtils;
import java.util.Optional;

import com.example.demo.entity.DelayReport;
import com.example.demo.enums.DelayStatus;
import com.example.demo.repository.DelayReportRepository;


@Service
public class LiveTrackingService {

    private final BusTripRepository busTripRepository;
    private final RouteService routeService;
    private final DelayReportRepository delayReportRepository;
    private static final int SIMULATION_SPEED = 3;

    public LiveTrackingService(
            BusTripRepository busTripRepository,
            RouteService routeService,
            DelayReportRepository delayReportRepository) {

        this.busTripRepository = busTripRepository;
        this.routeService = routeService;
        this.delayReportRepository = delayReportRepository;

    }

    public LiveTrackingResponse trackBus(String busNumber) {

        // Find active trip
        BusTrip trip = busTripRepository
                .findByBusNumberAndActiveTrue(busNumber)
                .orElseThrow(() ->
                        new RuntimeException("No active trip found"));

        // Find route
        List<String> route = routeService.findRoute(
                trip.getSource(),
                trip.getDestination());

        if (route.isEmpty()) {
            throw new RuntimeException("No route found");
        }

        // Total travel time (minutes)
        int totalMinutes =
                routeService.calculateTotalTravelTime(route);

        // ===============================
        // Simulation Speed (3x)
        // ===============================

        

        long elapsedMinutes = Duration.between(
                trip.getTripStartTime(),
                LocalDateTime.now())
                .toMinutes();

        elapsedMinutes *= SIMULATION_SPEED;

        // Prevent negative values
        if (elapsedMinutes < 0) {
            elapsedMinutes = 0;
        }

        // Prevent overflow
        if (elapsedMinutes > totalMinutes) {
            elapsedMinutes = totalMinutes;
        }

        // Journey progress
        double progress = 0.0;

        if (totalMinutes > 0) {
            progress = (double) elapsedMinutes / totalMinutes;
        }

        return buildTrackingResponse(
                trip,
                route,
                totalMinutes,
                elapsedMinutes,
                progress);
    }

    private LiveTrackingResponse buildTrackingResponse(
            BusTrip trip,
            List<String> route,
            int totalMinutes,
            long elapsedMinutes,
            double progress) {

        // ==========================
        // Journey Completed
        // ==========================

        if (progress >= 1.0) {

            double[] destinationCoordinates =
                    KarnatakaDistricts.DISTRICTS.get(
                            trip.getDestination());

            LiveTrackingResponse response =
                    new LiveTrackingResponse();

            response.setBusNumber(trip.getBusNumber());

            response.setSource(trip.getSource());

            response.setDestination(trip.getDestination());

            response.setCurrentDistrict(trip.getDestination());

            response.setNextDistrict("Journey Completed");

            response.setLatitude(destinationCoordinates[0]);

            response.setLongitude(destinationCoordinates[1]);

            response.setEtaMinutes(0);

            response.setStatus("ARRIVED");

            response.setProgress(100.0);

            response.setDelayed(false);

            response.setDelayReason("");

            response.setDelayMinutes(0);

            return response;

            
        }

        // ==========================
        // Find Current Segment
        // ==========================

        int travelledMinutes = 0;

        String currentDistrict = route.get(0);

        String nextDistrict =
                route.size() > 1
                        ? route.get(1)
                        : route.get(0);

        double segmentProgress = 0.0;

        for (int i = 0; i < route.size() - 1; i++) {

            String from = route.get(i);

            String to = route.get(i + 1);

            int segmentTime = getTravelTime(from, to);

            if (elapsedMinutes <= travelledMinutes + segmentTime) {

                currentDistrict = from;

                nextDistrict = to;

                if (segmentTime > 0) {

                    segmentProgress =
                            (double) (elapsedMinutes - travelledMinutes)
                                    / segmentTime;

                }

                break;

            }

            travelledMinutes += segmentTime;

        }

        // ==========================
        // Coordinates
        // ==========================

        double[] start =
                KarnatakaDistricts.DISTRICTS.get(currentDistrict);

        double[] end =
                KarnatakaDistricts.DISTRICTS.get(nextDistrict);

        if (start == null || end == null) {

            throw new RuntimeException(
                    "District coordinates not found.");

        }

        // ==========================
        // Interpolate Position
        // ==========================

        double[] currentPosition =
                GeoUtils.interpolate(
                        start[0],
                        start[1],
                        end[0],
                        end[1],
                        segmentProgress);

        // ==========================
        // ETA
        // ==========================

        int remainingMinutes =
                totalMinutes - (int) elapsedMinutes;

        if (remainingMinutes < 0) {
            remainingMinutes = 0;
        }

        // ==========================
        // Status
        // ==========================

        String status;

        if (progress >= 0.90) {

            status = "REACHING_DESTINATION";

        } else {

            status = "ON_TIME";

        }

        // ==========================
        // Build Response
        // ==========================

        LiveTrackingResponse response =
                new LiveTrackingResponse();

        response.setBusNumber(trip.getBusNumber());

        response.setSource(trip.getSource());

        response.setDestination(trip.getDestination());

        response.setCurrentDistrict(currentDistrict);

        response.setNextDistrict(nextDistrict);

        response.setLatitude(currentPosition[0]);

        response.setLongitude(currentPosition[1]);

        response.setEtaMinutes(remainingMinutes);

        response.setStatus(status);

        response.setProgress(progress * 100);

        // =============================
        // Check Approved Delay
        // =============================

        Optional<DelayReport> approvedDelay =
                delayReportRepository
                .findFirstByBusNumberAndStatusOrderByReportedAtDesc(

                        trip.getBusNumber(),

                        DelayStatus.APPROVED

                );

        if(approvedDelay.isPresent()){

            DelayReport report = approvedDelay.get();

            response.setDelayed(true);

            response.setDelayReason(report.getReason());

            response.setDelayMinutes(report.getDelayMinutes());

            response.setStatus("DELAYED");

            response.setEtaMinutes(
                    response.getEtaMinutes()
                    + report.getDelayMinutes()
            );

        }
        else{

            response.setDelayed(false);

            response.setDelayReason("");

            response.setDelayMinutes(0);

        }
        return response;
    }

    private int getTravelTime(String from,
                              String to) {

        var graph = KarnatakaRoadNetwork.getGraph();

        if (!graph.containsKey(from)) {
            return 0;
        }

        for (var road : graph.get(from)) {

            if (road.getDestination().equals(to)) {

                return road.getTravelMinutes();

            }

        }

        return 0;
    }

}