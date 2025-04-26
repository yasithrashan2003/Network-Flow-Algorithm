package network;

import network.algorithm.MaxFlowAlgorithm;
import network.io.NetworkParser;
import network.model.Edge;
import network.model.FlowNetwork;
import java.io.IOException;

public class Main {

    /**
     *  Application entry point
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        String filePath;
        if (args.length < 1) {

            //  Path to the file
            filePath = "src/data/data.txt";
            System.out.println("No file path provided, using default: " + filePath);
        } else {
            filePath = args[0];
        }

        try {
            //  Parse the network from the file
            System.out.println("Parsing network from file: " + filePath);
            FlowNetwork network = NetworkParser.parseFromFile(filePath);

            //  Print information about the network
            NetworkParser.printNetworkInfo(network);

            //  Run the max flow algorithm
            System.out.println("\nCalculating maximum flow...");
            MaxFlowAlgorithm algorithm = new MaxFlowAlgorithm(network);
            int maxFlow = algorithm.findMaxFlow();

            //  Print the results
            System.out.println("\nResults:");
            System.out.println("Maximum flow: " + maxFlow);
            System.out.println("\nDetailed execution log:");
            System.out.println(algorithm.getExecutionLog());

            //  Print the final network state
            System.out.println("\nFinal network state:");
            for (Edge edge : network.getEdges()) {
                System.out.println("  Edge from " + edge.getFrom().getId() +
                        " to " + edge.getTo().getId() +
                        " (flow: " + edge.getFlow() + "/" +
                        edge.getCapacity() + ")");
            }

        } catch (IOException e) {

            //  Handle IO exceptions
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {

            //  Handles other exceptions
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

