package com.example.demo.graph;

import java.util.*;

public class KarnatakaRoadNetwork {

    public static class Road {

        private final String destination;
        private final int travelMinutes;

        public Road(String destination, int travelMinutes) {
            this.destination = destination;
            this.travelMinutes = travelMinutes;
        }

        public String getDestination() {
            return destination;
        }

        public int getTravelMinutes() {
            return travelMinutes;
        }
    }

    private static final Map<String, List<Road>> graph = new HashMap<>();

    static {

        // --------------------------
        // Bengaluru Region
        // --------------------------

        connect("Bengaluru","Ramanagara",35);
        connect("Bengaluru","Tumakuru",70);
        connect("Bengaluru","Kolar",90);
        connect("Bengaluru","Bengaluru Rural",40);
        connect("Bengaluru","Chikkaballapur",70);

        // --------------------------
        // South Karnataka
        // --------------------------

        connect("Ramanagara","Mandya",40);
        connect("Mandya","Mysuru",45);
        connect("Mysuru","Kodagu",120);
        connect("Mysuru","Chamarajanagar",60);
        connect("Mandya","Hassan",120);
        connect("Hassan","Kodagu",90);
        connect("Hassan","Chikkamagaluru",90);
        connect("Kodagu","Dakshina Kannada",150);

        // --------------------------
        // Central Karnataka
        // --------------------------

        connect("Tumakuru","Chitradurga",120);
        connect("Chitradurga","Davanagere",75);
        connect("Davanagere","Haveri",75);
        connect("Davanagere","Shivamogga",90);
        connect("Shivamogga","Udupi",120);
        connect("Shivamogga","Chikkamagaluru",90);

        // --------------------------
        // North Karnataka
        // --------------------------

        connect("Haveri","Gadag",50);
        connect("Haveri","Dharwad",90);
        connect("Dharwad","Hubballi",20);
        connect("Dharwad","Belagavi",90);
        connect("Gadag","Koppal",70);
        connect("Koppal","Ballari",80);
        connect("Koppal","Raichur",120);
        connect("Raichur","Yadgir",60);
        connect("Yadgir","Kalaburagi",60);
        connect("Kalaburagi","Bidar",120);
        connect("Gadag","Bagalkote",90);
        connect("Bagalkote","Vijayapura",70);

        // --------------------------
        // Coastal Karnataka
        // --------------------------

        connect("Udupi","Dakshina Kannada",60);
        connect("Udupi","Uttara Kannada",180);
        connect("Uttara Kannada","Belagavi",180);

    }

    private static void connect(String from,
                                String to,
                                int travelMinutes) {

        graph.computeIfAbsent(from, k -> new ArrayList<>())
                .add(new Road(to, travelMinutes));

        graph.computeIfAbsent(to, k -> new ArrayList<>())
                .add(new Road(from, travelMinutes));

    }

    public static Map<String, List<Road>> getGraph() {
        return graph;
    }

}