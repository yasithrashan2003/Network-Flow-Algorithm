package network.algorithm;


import network.model.Edge;
import network.model.FlowNetwork;
import network.model.Node;

import java.util.*;

/**
 * Create MaxFlowAlgorithm class for find the maximum flow
 */
public class MaxFlowAlgorithm {

    private final FlowNetwork network;
    private StringBuilder executionLog;
    private int iterations;

    /**
     * Creates a new instance for the given network.
     *
     * @param network flow network
     */
    public MaxFlowAlgorithm(FlowNetwork network) {
        this.network = network;
        this.executionLog = new StringBuilder();
        this.iterations = 0;
    }

    /**
     * Execute Ford-Fulkerson algorithm with Edmonds Karp improvement
     * Using BFS to find augmenting paths
     *
     * @return The maximum flow value
     */
    public int findMaxFlow() {
        //  Reset the algorithm
        executionLog = new StringBuilder();
        iterations = 0;

        //  Reset all flows to zero 0
        for (Edge edge : network.getEdges()) {
            edge.addFlow(-edge.getFlow()); // Reset to 0
        }

        //  Get the source and target nodes
        Node source = network.getSource();
        Node target = network.getTarget();

        if (source == null || target == null) {
            throw new IllegalStateException("Source or target node not set");
        }

        //  Start the algorithm
        executionLog.append("Starting Ford-Fulkerson algorithm with Edmonds-Karp improvement\n");
        executionLog.append("Source node: ").append(source.getId()).append("\n");
        executionLog.append("Target node: ").append(target.getId()).append("\n\n");

        //  Find all the augmenting paths
        int totalFlow = 0;
        List<Edge> path;

        while ((path = findAugmentingPath(source, target)) != null) {
            iterations++;
            executionLog.append("Iteration ").append(iterations).append(":\n");

            //  Find the bottleneck capacity
            int bottleneck = findBottleneckCapacity(path);
            executionLog.append("  Found augmenting path with bottleneck capacity ").append(bottleneck).append(":\n");

            //  Print the path
            for (Edge edge : path) {
                executionLog.append("    Edge from ").append(edge.getFrom().getId())
                        .append(" to ").append(edge.getTo().getId())
                        .append(" (flow: ").append(edge.getFlow())
                        .append("/").append(edge.getCapacity()).append(")\n");
            }

            //  Augment the flow along the path
            for (Edge edge : path) {
                edge.addFlow(bottleneck);
            }

            totalFlow += bottleneck;
            executionLog.append("  Augmented flow by ").append(bottleneck)
                    .append(", total flow is now ").append(totalFlow).append("\n\n");
        }

        executionLog.append("No more augmenting paths found.\n");
        executionLog.append("Maximum flow: ").append(totalFlow).append("\n");

        return totalFlow;
    }

    /**
     * Finds an augmenting path from the source to the target using BFS.
     *
     * @param source source node
     * @param target target node
     * @return A list of edges of augmenting path
     */
    private List<Edge> findAugmentingPath(Node source, Node target) {
        //  Maps each node to the edge that was used to reach it
        Map<Integer, Edge> parentEdge = new HashMap<>();

        //  Queue for BFS
        Queue<Node> queue = new LinkedList<>();
        queue.add(source);

        //  Keep memory for visited path
        Set<Integer> visited = new HashSet<>();
        visited.add(source.getId());

        //  BFS to find a path
        while (!queue.isEmpty() && !visited.contains(target.getId())) {
            Node current = queue.poll();

            //  Try all edges
            for (Edge edge : network.getOutgoingEdges(current.getId())) {
                Node next = edge.getTo();

                //  Check the more paths
                if (!visited.contains(next.getId()) && edge.getResidualCapacity() > 0) {
                    parentEdge.put(next.getId(), edge);
                    visited.add(next.getId());
                    queue.add(next);

                    //  If reached all target then break
                    if (next.getId() == target.getId()) {
                        break;
                    }
                }
            }
        }

        //  Check if the no augmenting path exists
        if (!visited.contains(target.getId())) {
            return null;
        }

        //  Reconstruct the path
        List<Edge> path = new ArrayList<>();
        Node current = target;

        while (current.getId() != source.getId()) {
            Edge edge = parentEdge.get(current.getId());
            path.add(0, edge); // Add to front to maintain order
            current = edge.getFrom();
        }

        return path;
    }

    /**
     * Finds the bottleneck capacity
     *
     * @param path augmenting path
     * @return minimum residual capacity
     */
    private int findBottleneckCapacity(List<Edge> path) {
        int bottleneck = Integer.MAX_VALUE;

        for (Edge edge : path) {
            bottleneck = Math.min(bottleneck, edge.getResidualCapacity());
        }

        return bottleneck;
    }

    /**
     * Gets the execution log of the algorithm.
     *
     * @return The execution log
     */
    public String getExecutionLog() {
        return executionLog.toString();
    }

    /**
     * Gets the number of iterations
     *
     * @return number of iterations
     */
    public int getIterations() {
        return iterations;
    }
}



