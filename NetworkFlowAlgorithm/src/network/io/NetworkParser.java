package network.io;

import network.model.Edge;
import network.model.FlowNetwork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NetworkParser {


    public static FlowNetwork parseFromFile(String filename) throws IOException {
        FlowNetwork network = new FlowNetwork();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Read the number of nodes

            String line = reader.readLine();
            int numNodes = Integer.parseInt(line.trim());

            // Create all nodes
            for(int i = 0; i < numNodes; i++) {
                network.addNode(i);
            }

            // Set source and target
            network.setSourceAndTarget(0,numNodes-1);

            while ( (line = reader.readLine()) != null ) {
                line = line.trim();
                if(line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if(parts.length != 3) {
                    throw new IOException("Invalid edge format in file "+line);
                }

                int fromId = Integer.parseInt(parts[0]);
                int toId = Integer.parseInt(parts[1]);
                int capacity = Integer.parseInt(parts[2]);

                network.addEdge(fromId, toId, capacity);
            }
        }
        return network;

    }

    public static void printNetworkInfo(FlowNetwork network) {
        System.out.println("Network Information");
        System.out.println("Number of nodes: "+network.getNodes().size());
        System.out.println("Number of edges: "+network.getEdges().size());
        System.out.println("Source node: "+network.getSource().getId());
        System.out.println("Target node: "+network.getTarget().getId());
        System.out.println("/nEdges :");

        for(Edge edge : network.getEdges()) {
            System.out.print(" From " + edge.getFrom().getId() +
                    " to " + edge.getTo().getId() +
                    " (capacity: " + edge.getCapacity() + ")");
        }


    }


}
