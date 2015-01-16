package ai.tsp.ga;



import java.util.ArrayList;

public class JourneyManager {

    // Holds our cities
    private static ArrayList tspcities = new ArrayList<Node>();

    // Adds a destination city
    public static void addNode(Node node) {
    	tspcities.add(node);
    }
    
    // Get a city
    public static Node getNode(int index){
        return (Node)tspcities.get(index);
    }
    
    // Get the number of destination cities
    public static int totalNodes(){
        return tspcities.size();
    }
}