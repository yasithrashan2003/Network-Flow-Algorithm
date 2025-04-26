package network.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Public FlowNetwork class
 */
public class FlowNetwork {

    //  Create Data Structures

    private final List<Node> nodes;
    private final List<Edge> edges;
    private final Map<Integer, Node> nodeMap;
    private final Map<Integer, List<Edge>> outgoingEdges;
    private final Map<Integer, List<Edge>> incomingEdges;
    private Node source;
    private Node target;

    /**
     * Default Constructor
     * Creates new empty flow network.
     */
    public FlowNetwork() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        nodeMap = new HashMap<>();
        outgoingEdges = new HashMap<>();
        incomingEdges = new HashMap<>();
    }

    /**
     * Adds a node to the network.
     *
     * @param id The ID of the node
     * @return The created node
     */
    public Node addNode(int id) {
        Node node = new Node(id);
        nodes.add(node);
        nodeMap.put(id, node);
        outgoingEdges.put(id, new ArrayList<>());
        incomingEdges.put(id, new ArrayList<>());
        return node;
    }

    /**
     * Sets the source and target
     *
     * @param sourceId of the source node
     * @param targetId of the target node
     */
    public void setSourceAndTarget(int sourceId, int targetId) {

        //  Set the source
        this.source = nodeMap.get(sourceId);
        if (source != null) {
            source.setAsSource();
        }

        //  Set the target
        this.target = nodeMap.get(targetId);
        if (target != null) {
            target.setAsTarget();
        }
    }

    /**
     * Adds edge to the network
     *
     * @param fromId of the source node
     * @param toId of the destination node
     * @param capacity capacity of the edge
     * @return edge
     */
    public Edge addEdge(int fromId, int toId, int capacity) {

        //  Get the node by ID
        Node from = nodeMap.get(fromId);
        Node to = nodeMap.get(toId);

        //  Validation for node exist
        if (from == null || to == null) {
            throw new IllegalArgumentException("Nodes must exist before creating an edge");
        }

        //  Create a new edge
        Edge edge = new Edge(from, to, capacity);
        edges.add(edge);
        outgoingEdges.get(fromId).add(edge);
        incomingEdges.get(toId).add(edge);

        return edge;
    }

    /**
     * Gets a node by ID.
     *
     * @param id of the node
     * @return The node, or null if not found
     */
    public Node getNode(int id) {
        return nodeMap.get(id);
    }

    /**
     * @return source node
     */
    public Node getSource() {
        return source;
    }

    /**
     * @return target node
     */
    public Node getTarget() {
        return target;
    }

    /**
     * @return All the nodes
     */
    public List<Node> getNodes() {
        return new ArrayList<>(nodes);
    }

    /**
     * @return All the edges
     */
    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    /**
     * Gets all outgoing edges from a node.
     *
     * @param nodeId of the node
     * @return A list of outgoing edges
     */
    public List<Edge> getOutgoingEdges(int nodeId) {
        return new ArrayList<>(outgoingEdges.getOrDefault(nodeId, new ArrayList<>()));
    }

    /**
     * Gets all incoming edges to a node.
     *
     * @param nodeId of the node
     * @return A list of incoming edges
     */
    public List<Edge> getIncomingEdges(int nodeId) {
        return new ArrayList<>(incomingEdges.getOrDefault(nodeId, new ArrayList<>()));
    }

    /**
     * Calculates the total flow out
     * Sum of the flow of all edges
     *
     * @return total flow value
     */
    public int calculateFlowValue() {
        int flowValue = 0;
        for (Edge edge : getOutgoingEdges(source.getId())) {
            flowValue += edge.getFlow();
        }
        return flowValue;
    }

    /**
     * String representation  of the Flow Network object
     *
     * @return network state
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Flow Network:\n");

        sb.append("Nodes: ");
        for (Node node : nodes) {
            sb.append(node.getId()).append(" ");
        }
        sb.append("\n");

        sb.append("Edges:\n");
        for (Edge edge : edges) {
            sb.append("  ").append(edge).append("\n");
        }

        return sb.toString();
    }
}


