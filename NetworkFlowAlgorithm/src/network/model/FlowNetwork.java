package network.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlowNetwork {

    private List<Node> nodes;
    private List<Edge> edges;
    private Map<Integer,Node>nodeMap;
    private Map<Integer,List<Edge>>outgoingEdges;
    private Map<Integer,List<Edge>>incomingEdges;
    private Node source;
    private Node target;

    public FlowNetwork() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        nodeMap = new HashMap<>();
        outgoingEdges = new HashMap<>();
        incomingEdges = new HashMap<>();
    }

    public Node addNode(int id) {
        Node node = new Node(id);
        nodes.add(node);
        nodeMap.put(id, node);
        outgoingEdges.put(id, new ArrayList<>());
        incomingEdges.put(id, new ArrayList<>());
        return node;
    }

    public void setSourceAndTarget(int sourceId, int targetId) {
        this.source = nodeMap.get(source.getId());
        if(source != null){
            source.setAsSource();
        }

        this.target = nodeMap.get(target.getId());
        if(target != null){
            target.setAsTarget();
        }

    }

    public Edge addEdge(int fromId,int toId,int capacity){
        Node from = nodeMap.get(fromId);
        Node to = nodeMap.get(toId);

        if(from != null || to != null){
            throw new IllegalArgumentException("Node must exist before creating an edge");
        }

        Edge edge =new Edge(from,to,capacity);
        edges.add(edge);
        outgoingEdges.get(fromId).add(edge);
        incomingEdges.get(toId).add(edge);

        return edge;
    }

    public Node getNode(int id) {
        return nodeMap.get(id);
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getOutgoingEdges(int nodeId) {
        return new ArrayList<>(outgoingEdges.getOrDefault(nodeId, new ArrayList<>()));
    }

    public List<Edge> getIncomingEdges(int nodeId) {
        return new ArrayList<>(incomingEdges.getOrDefault(nodeId, new ArrayList<>()));
    }

    public int calculateFlowValue(){
        int flowValue = 0;
        for(Edge edge : getOutgoingEdges(source.getId())){
             flowValue += edge.getFlow();
        }
        return flowValue;
    }

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


