package network.model;

public class Node{
    private int id;
    private boolean isSource;
    private boolean isTarget;



    public Node(int id){
        this.id = id;
        this.isSource = false;
        this.isTarget = false;
    }

    public int getId(){
        return id;
    }

    public void setAsSource(){
        isSource = true;
    }

    public void setAsTarget(){
        isTarget = true;
    }

    public boolean isSource(){
        return isSource;
    }

    public boolean isTarget(){
        return isTarget;
    }

}


