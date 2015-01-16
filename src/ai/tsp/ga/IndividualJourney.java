package ai.tsp.ga;


import java.util.ArrayList;
import java.util.Collections;

public class IndividualJourney{

   
	//  stores the all  journey 
    private ArrayList journey = new ArrayList<Node>();
    // Cache
    private double fitness = 0;
    private int distance = 0;
    
    // Constructs a blank tour
    public IndividualJourney(){
        for (int i = 0; i < JourneyManager.totalNodes(); i++) {
        	journey.add(null);
        }
    }
    
    public IndividualJourney(ArrayList joureny){
        this.journey = joureny;
    }

    // Creates a random individual
    public void createJourney() {
        // Loop through all our destination cities and add them to our tour
        for (int nodeIndex = 0; nodeIndex < JourneyManager.totalNodes(); nodeIndex++) {
          setNode(nodeIndex, JourneyManager.getNode(nodeIndex));
        }
        // Randomly reorder the tour
        Collections.shuffle(journey);
    }

    // Gets a city from the tour
    public Node getNode(int tourPosition) {
        return (Node)journey.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setNode(int nodeIndex, Node node) {
    	journey.set(nodeIndex, node);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }
    
    // Gets the tours fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }
    
    // Gets the total distance of the tour
    public int getDistance()
    {
        if (distance == 0) {
            int totalJourneyDistance = 0;
            Node sourceNodeInCurrentJourney=null,lastNodeInCurrentJourney=null;
            // Loop through our tour's cities
            for (int nodeIndex=0; nodeIndex < totalNoOfCitiesInJourney(); nodeIndex++) {
                // Get city we're travelling from
            	if(nodeIndex==0)
            		sourceNodeInCurrentJourney = getNode(nodeIndex);
            	else if(nodeIndex==(totalNoOfCitiesInJourney()-1))
            		lastNodeInCurrentJourney= getNode(nodeIndex);
                Node currentNode = getNode(nodeIndex);
                // City we're travelling to
                Node intermediateNode;
                // Check we're not on our tour's last city, if we are set our 
                // tour's final destination city to our starting city
                if(nodeIndex+1 < totalNoOfCitiesInJourney())
                {
                	intermediateNode = getNode(nodeIndex+1);
                }
                else
                {
                	intermediateNode = getNode(0);
                }
                // Get the distance between the two cities
                totalJourneyDistance += currentNode.getInterNodeDistance(intermediateNode);
            }
            try
            {
            int endDistance=(int)sourceNodeInCurrentJourney.getInterNodeDistance(lastNodeInCurrentJourney);
            }
            catch(Exception exe)
            {
            	
            }
            distance = totalJourneyDistance;
        }
        return distance;
    }

    // Get number of cities on our tour
    public int totalNoOfCitiesInJourney() {
        return journey.size();
    }
    
    // Check if the tour contains a city
    public boolean isJouneyContainsNode(Node node){
        return journey.contains(node);
    }
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < totalNoOfCitiesInJourney(); i++) {
            geneString += getNode(i)+"|";
        }
        return geneString;
    }
}
