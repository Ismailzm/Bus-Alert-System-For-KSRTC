package com.example.demo.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.graph.KarnatakaRoadNetwork;
import com.example.demo.graph.KarnatakaRoadNetwork.Road;

@Service
public class RouteService {

    /**
     * Finds the fastest route using Dijkstra's Algorithm
     */
    public List<String> findRoute(String source, String destination) {

        Map<String, List<Road>> graph = KarnatakaRoadNetwork.getGraph();

        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        PriorityQueue<Node> pq =
                new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));

        // Initialize distances
        for (String district : graph.keySet()) {
            distance.put(district, Integer.MAX_VALUE);
        }

        distance.put(source, 0);
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            if (current.getDistrict().equals(destination)) {
                break;
            }

            List<Road> neighbours = graph.get(current.getDistrict());

            if (neighbours == null)
                continue;

            for (Road road : neighbours) {

                int newDistance =
                        distance.get(current.getDistrict())
                                + road.getTravelMinutes();

                if (newDistance < distance.get(road.getDestination())) {

                    distance.put(road.getDestination(), newDistance);

                    previous.put(road.getDestination(),
                            current.getDistrict());

                    pq.add(new Node(road.getDestination(), newDistance));
                }
            }
        }

        List<String> route = new ArrayList<>();

        if (!distance.containsKey(destination)
                || distance.get(destination) == Integer.MAX_VALUE) {

            return route;
        }

        String current = destination;

        while (current != null) {

            route.add(current);

            current = previous.get(current);

        }

        Collections.reverse(route);

        return route;
    }

    /**
     * Returns total travel time (minutes)
     */
    public int calculateTotalTravelTime(List<String> route) {

        Map<String, List<Road>> graph = KarnatakaRoadNetwork.getGraph();

        int total = 0;

        for (int i = 0; i < route.size() - 1; i++) {

            String from = route.get(i);
            String to = route.get(i + 1);

            for (Road road : graph.get(from)) {

                if (road.getDestination().equals(to)) {

                    total += road.getTravelMinutes();

                    break;
                }
            }
        }

        return total;
    }

    private static class Node {

        private final String district;
        private final int distance;

        public Node(String district, int distance) {

            this.district = district;
            this.distance = distance;

        }

        public String getDistrict() {
            return district;
        }

        public int getDistance() {
            return distance;
        }

    }

}