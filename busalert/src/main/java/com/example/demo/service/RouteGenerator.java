package com.example.demo.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class RouteGenerator {

    private final Map<String, List<String>> routes = new HashMap<>();

    public RouteGenerator() {

        // Bengaluru → Mysuru
        routes.put("Bengaluru-Mysuru",
                List.of("Bengaluru",
                        "Ramanagara",
                        "Mandya",
                        "Mysuru"));

        // Mysuru → Bengaluru
        routes.put("Mysuru-Bengaluru",
                List.of("Mysuru",
                        "Mandya",
                        "Ramanagara",
                        "Bengaluru"));

        // Bengaluru → Hubballi
        routes.put("Bengaluru-Hubballi",
                List.of("Bengaluru",
                        "Tumakuru",
                        "Chitradurga",
                        "Davanagere",
                        "Haveri",
                        "Hubballi"));

        // Hubballi → Bengaluru
        routes.put("Hubballi-Bengaluru",
                List.of("Hubballi",
                        "Haveri",
                        "Davanagere",
                        "Chitradurga",
                        "Tumakuru",
                        "Bengaluru"));

        // Bengaluru → Belagavi
        routes.put("Bengaluru-Belagavi",
                List.of("Bengaluru",
                        "Tumakuru",
                        "Chitradurga",
                        "Davanagere",
                        "Haveri",
                        "Hubballi",
                        "Dharwad",
                        "Belagavi"));

        // Belagavi → Bengaluru
        routes.put("Belagavi-Bengaluru",
                List.of("Belagavi",
                        "Dharwad",
                        "Hubballi",
                        "Haveri",
                        "Davanagere",
                        "Chitradurga",
                        "Tumakuru",
                        "Bengaluru"));

        // Bengaluru → Mangaluru
        routes.put("Bengaluru-Dakshina Kannada",
                List.of("Bengaluru",
                        "Ramanagara",
                        "Mandya",
                        "Hassan",
                        "Sakleshpur",
                        "Dakshina Kannada"));

        // Bengaluru → Shivamogga
        routes.put("Bengaluru-Shivamogga",
                List.of("Bengaluru",
                        "Tumakuru",
                        "Tiptur",
                        "Arasikere",
                        "Shivamogga"));

    }

    public List<String> getRoute(String source, String destination){

        String key = source + "-" + destination;

        return routes.getOrDefault(key,new ArrayList<>());

    }

}