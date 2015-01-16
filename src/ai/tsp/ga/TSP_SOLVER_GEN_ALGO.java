package ai.tsp.ga;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;



// Main class that starts Implementation


public class TSP_SOLVER_GEN_ALGO {

	
	 public static Dictionary aStarHeuristics = new Hashtable();
	 public static  int totalHeuristicDistance =0;
    public static void main(String[] args) {

        
    	
    	
    	int [] heuristicDistanceArray=new int[2000];
       
    	// Generat the Random nodes  with X and Y coordinates  
        for(int nodeIndex=0;nodeIndex<1000;nodeIndex++)
        	
        {
        	 String string="Node_" +nodeIndex;
        	Node dummyNode= new Node((int) (Math.random() * 200), (int) (Math.random() * 200),string);
            JourneyManager.addNode(dummyNode);
        }
        
       ArrayList tspNodesList=new ArrayList();
       for(int i=0; i<JourneyManager.totalNodes();i++)
        {  
        	 
        	Node tspNode = JourneyManager.getNode(i);
        	tspNodesList.add(tspNode);
        	
        	
        }
       
       
       //calculate the running time of A* Star ALgo
       long startTime = System.nanoTime();
       
         ArrayList currentJourneyArray=new ArrayList();
         for(int i=0; i<tspNodesList.size();i++)
         {         
            //int z=0;
        	 totalHeuristicDistance =0;
    	     ArrayList currentJourneyList=new ArrayList();
    	    //ArrayList tempTspList=new 
        	 Node startNode = (Node)tspNodesList.get(i);
       	     tspNodesList.remove(i);
       	     currentJourneyList.add(startNode);       	
       	     apply_A_StarAlgo(tspNodesList,startNode,0,currentJourneyList);
        	 heuristicDistanceArray[i] =totalHeuristicDistance ;
       	     currentJourneyArray.add(currentJourneyList);
       	    
       	    for(int x=0; x<JourneyManager.totalNodes();x++)
            {  
         	 
         	 Node tspNode = JourneyManager.getNode(x);
         	 tspNodesList.add(tspNode);
         	
            }
         } 
         int minDistance=heuristicDistanceArray[0];
         int indexOfOptimalJounrey=0;
         for(int i=0;i<heuristicDistanceArray.length;i++)
         {
        	 if(minDistance<=heuristicDistanceArray[i])
        	 {
        		 
        		 minDistance=heuristicDistanceArray[i];
        		 indexOfOptimalJounrey=i;
        	 }
         }
         // A* execution time finished
         long stopTime = System.nanoTime();
         System.out.println("Time taken by A * Algorithm \n :"+((stopTime - startTime)/10000));
        
         System.out.println("Optimal journey by A * ALgo \n :" +indexOfOptimalJounrey);
       //  System.out.println(currentJourneyArray.get(indexOfOptimalJounrey));
         
    	 //for(int z=0;z<tspCities.size();z++)
    	 System.out.println("Total distance covered by A * Algo  :"+ heuristicDistanceArray[indexOfOptimalJounrey]);
         
               
        

        
        // Genetic Algorithm implementation
        // Initialize population
    	 //begin start time
    	 startTime = System.nanoTime();
        StateSpacePopulation population = new StateSpacePopulation(50, true);
        System.out.println("Total Distance before Applying Genetic Algorith: " + population.getStongestFromPopulation().getDistance());

        // Evolve population for 100 generations
        GeneticAlgoImplementor genAlgoImple=new GeneticAlgoImplementor();
        population = genAlgoImple.generateBetterPopulation(population);
        for (int i = 0; i < 100; i++) {
        	population = genAlgoImple.generateBetterPopulation(population);
        }

        // Print final results

        stopTime = System.nanoTime();
        System.out.println("Time taken by Genetic Algorithm * :"+((stopTime - startTime)/10000));
        
        System.out.println("Total Distance After Applying Genetic Algorith:" + population.getStongestFromPopulation().getDistance());
       // System.out.println("The Optimal tour for Travelling Salesman Problem is by Genetic Algo \n:");
      //  System.out.println(population.getStongestFromPopulation());
    }
    
    private static  void apply_A_StarAlgo(ArrayList  tspCities, Node currentStartNode, int  heuristicDistance ,ArrayList currentJourney)
    {
    	
    	int i=0;
    	boolean nodeAlreadyInJourney=false;
    	
    	int currentMinimumDistance=(int)currentStartNode.getInterNodeDistance((Node)tspCities.get(i));;
    	int tempDistance=1; 	
    	Node nextNodeInJourney=null;
    	for(int k=0;k<tspCities.size();k++)
    	{
    	    tempDistance=(int) currentStartNode.getInterNodeDistance((Node)tspCities.get(k));
    		if(tempDistance<=currentMinimumDistance)
    		{
    			currentMinimumDistance=tempDistance;
    			nextNodeInJourney=(Node)tspCities.get(k);    
    			i=k;
    		}
    		//tspCities.remove(i);
    		     		
    	} 
    	//if(!currentJourney.isJouneyContainsNode())
    	 // add the next node to be visited to the journey in
    	currentJourney.add(nextNodeInJourney);  
    	// add remove the current visited node from the remaining nodes 
    	tspCities.remove(i);
    	
    	// till there are more nodes present apply A * star algo method recursively  till all the node gets visited 
    	if(tspCities.size()>0)
    	{
    		// add the cost of visiting current node to the Heuristic Distance 
    		heuristicDistance=heuristicDistance+currentMinimumDistance+currentMinimumDistance;;
    		apply_A_StarAlgo(tspCities,nextNodeInJourney,heuristicDistance,currentJourney);
    	}  
    	else
    	{
    		totalHeuristicDistance=heuristicDistance;
    	        // System.out.println(currentJourney);
    	        //for(int z=0;z<tspCities.size();z++)
    	        //System.out.println(heuristicDistance);
    	        // aStarHeuristics.put(heuristicDistance,currentJourney);
    	}
        	//return heuristicDistance;
     }
}
