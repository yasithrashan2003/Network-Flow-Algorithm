package network.algorithm;


import network.model.Edge;
import network.model.FlowNetwork;
import network.model.Node;

import java.util.*;

public class MaxFlowAlgorithm {

    private FlowNetwork network;
    private StringBuilder executionLog;
    private int iterations;


    public MaxFlowAlgorithm(FlowNetwork network) {
        this.network = network;
        this.executionLog = new StringBuilder();
        this.iterations = 0;
    }

    public int findMaxFlow() {
        //Reset the algorithm state
        executionLog=new StringBuilder();
        iterations=0;

        // reset all the flows to 0
        for(Edge edge : network.getEdges()) {
            edge.addFlow(-edge.getFlow());  // reset to 0
        }

        // Get the source and target nodes
        Node source = network.getSource();
        Node target = network.getTarget();

        if (source == null || target == null) {
            throw new IllegalStateException("Source or target node not set");
        }

        // Start the algorithm
        executionLog.append("Starting Ford-Fulkerson algorithm with Edmonds-Karp improvement\n");
        executionLog.append("Source node: ").append(source.getId()).append("\n");
        executionLog.append("Target node: ").append(target.getId()).append("\n\n");

        // Find augmenting paths and push flow until no more path exist
        int totalFlow=0;
        List<Edge> path;

        while ((path = findAugmentingPath(source, target)) != null){
            iterations++;
            executionLog.append("Iteration ").append(iterations).append("\n");

            // Find the bottleneck capacity (minimum residual capacity along the path)

            int bottleneck = findBottleneckCapacity(path);
            executionLog.append("Find augmenting path with Bottleneck: ").append(bottleneck).append("\n");

            // Print the path
            for (Edge edge : path) {
                executionLog.append("    Edge from ").append(edge.getFrom().getId())
                        .append(" to ").append(edge.getTo().getId())
                        .append(" (flow: ").append(edge.getFlow())
                        .append("/").append(edge.getCapacity()).append(")\n");
            }

            // Augment the flow along the path
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

        private List<Edge> findAugmentingPath(Node source, Node target){
            // Maps each node to the edge that was used to reach it

            Map<Integer,Edge> parentEdges = new HashMap<>();

            // Queue for BFS
            Queue<Node> queue = new LinkedList<>();
            queue.add(source);

            // Keep track of visited node for avoid cycles
            Set<Integer> visited = new HashSet<>();
            visited.add(source.getId());


            // BFS to find a path
            while(!queue.isEmpty() && !visited.contains(source.getId())){
                Node current = queue.poll();

                // Try all outgoing edges
                for(Edge edge : network.getOutgoingEdges(current.getId())){
                    Node next=edge.getTo();

                    // Check if we can push more flow throw this edge
                    if(!visited.contains(next.getId()) && edge.getResidualCapacity() >0){
                        parentEdges.put(next.getId(),edge);
                        visited.add(next.getId());
                        queue.add(next);


                        // if we reached the target, done
                        if(next.getId()==target.getId()){
                            break;
                        }
                    }

                }



            }

            // If we didn't reach the target, no augmenting path exists
            if (!visited.contains(target.getId())) {
                return null;
            }

            // Reconstruct the path
            List<Edge> path = new ArrayList<>();
            Node current = target;

            while (current.getId() != source.getId()) {
                Edge edge = parentEdges.get(current.getId());
                path.add(edge);
                current = edge.getTo();
            }

            return path;

        }

        // Find the bottleneck capacity
    private int findBottleneckCapacity(List<Edge> path){
        int bottleneck= Integer.MAX_VALUE;

        for(Edge edge : path){
            bottleneck = Math.min(bottleneck,edge.getResidualCapacity());
        }

        return bottleneck;
    }

    // get execution log
    public String getExecutionLog() {
        return executionLog.toString();
    }

    public int getIterations() {
        return iterations;
    }









    }


