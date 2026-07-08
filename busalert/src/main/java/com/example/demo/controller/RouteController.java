package com.example.demo.controller;



import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.service.RouteService;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<String> getRoute(
            @RequestParam String source,
            @RequestParam String destination) {

        return routeService.findRoute(source, destination);

    }

}
