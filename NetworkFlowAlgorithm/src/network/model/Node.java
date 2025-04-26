package network.model;

/**
 * Public Node Class
 * Represents node in the flow network
 */
public class Node {
    private int id;
    private boolean isSource;
    private boolean isTarget;

    /**
     * Create a constructor
     * Create a new node with given ID
     * @param id
     */
    public Node(int id) {
        this.id = id;
        this.isSource = false;
        this.isTarget = false;
    }

    /**
     * Getters for getId
     * @return getId
     */
    public int getId() {
        return id;
    }

    /**
     * Set Node as a source node
     */
    public void setAsSource() {
        isSource = true;
    }

    /**
     * Set node as a target node
     */
    public void setAsTarget() {
        isTarget = true;
    }

    /**
     * Return true if this node is the source
     * @return isSource
     */
    public boolean isSource() {
        return isSource;
    }

    /**
     * Return true if the node is the target
     * @return isTarget
     */
    public boolean isTarget() {
        return isTarget;
    }

}


