

package ai.tsp.ga;

public class Node {
   
	String nodeName;
	int x,y;

    
    
    // Constructs a randomly placed city
    public Node(){
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
        
    }
    
    
    // Constructs a city at chosen x, y location
    public Node(int x, int y, String nodeName){
        this.x = x;
        this.y = y;
        this.nodeName=nodeName;
    }
    
    // set Node name 
    
    public String getnodeName()
    {
    	
    	 return this.nodeName;
    }
    // returns Nodes x co-ordinate 
    public int getXCoordinate(){
        return this.x;
    }
    
    // returns Nodes  y  co-ordinate 
    public int getYCordinate(){
        return this.y;
    }
    
    // Gets the distance to given city
    public double getInterNodeDistance(Node node){
        int xDistance = Math.abs(getXCoordinate() - node.getXCoordinate());
        int yDistance = Math.abs(getYCordinate() - node.getYCordinate());
       // Using  pythagorus therom calculate distance between two Nodes
         double  interNodeDistance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        
        return interNodeDistance;
    }
    
    @Override
    public String toString(){
        return getnodeName()+ " -: " +" X: "+  getXCoordinate()+" Y : "+getYCordinate() ;
    }
}