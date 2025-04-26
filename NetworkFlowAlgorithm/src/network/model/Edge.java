package network.model;

/**
 * Public Node Class
 * Represent a directed edge in the flow network with a capacity and flow.
 */
public class Edge {
    private Node from;
    private Node to;
    private int capacity;
    private int flow;

    /**
     * Create a constructor
     * @param from the source node
     * @param to the destination node
     * @param capacity of maximum capacity of this edge
     */
    public Edge(Node from, Node to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    /**
     * The source node of the edge
     * @return from
     */
    public Node getFrom() {
        return from;
    }

    /**
     * The destination node of this edge
     * @return to
     */
    public Node getTo() {
        return to;
    }

    /**
     * The capacity of the edge
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * The current flow through the flow
     * @return flow
     */
    public int getFlow() {
        return flow;
    }

    /**
     * The residual capacity of the edge
     * @return capacity
     */
    public int getResidualCapacity() {
        return capacity - flow;
    }

    /**
     * Add the amount to the Flow
     * @param amount to add
     */
    public void addFlow(int amount) {
        flow += amount;
    }

    /**
     * String representation of the Edge
     * @return 'from', 'to', 'capacity', flow
     */
    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", capacity=" + capacity +
                ", flow=" + flow +
                '}';
    }
}


