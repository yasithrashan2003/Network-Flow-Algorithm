package network.model;

public class Edge {
    private Node from;
    private Node to;
    private int capacity;
    private int flow;


    public Edge(Node from, Node to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }


    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public int getResidualCapacity() {
        return capacity - flow;
    }

    public void addFlow(int amount) {
        flow += amount;
    }

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


