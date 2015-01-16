package ai.tsp.ga;



//  Main class which Implements Genetic Algorithm concepts
public class GeneticAlgoImplementor {

    /* Genetic Algorithm variables*/
	
    private static final double mutationVairable = 0.015;
    private static final int evolutionStages = 20;
    

    // Evolves a population over one generation
    public  StateSpacePopulation generateBetterPopulation(StateSpacePopulation pop) {
    	StateSpacePopulation nextGeneration = new StateSpacePopulation(pop.populationSize(), false);

        

           // select fittest(Min distance tour) tours from the current population for crossover
        		
            for (int i = 0; i < nextGeneration.populationSize(); i++) {
               // choose two parent  
        	IndividualJourney A = selectToursFromCurrentPopulation(pop);
        	IndividualJourney B = selectToursFromCurrentPopulation(pop);
            
        	 // Combine the two parent two generate fit journey
        	IndividualJourney childJourney = generateChildJourney(A, B);
              
        	// Save the current journey  for next iteration 
        	nextGeneration.saveJourneyToArray(i, childJourney);
        }

        
            // apply mutation to get diversity in current population
        for (int i = 0; i < nextGeneration.populationSize(); i++) {
        	applyMutation(nextGeneration.getJourneyFromArray(i));
        }

        return nextGeneration;
    }

    //  Mutation function swaps the nodes to achieve diversity
    private static void applyMutation(IndividualJourney journey) {
      
    	// iterate over nodes 
        for(int n=0; n < journey.totalNoOfCitiesInJourney(); n++){
           
            if(Math.random() < mutationVairable){
               
                int n2 = (int) (journey.totalNoOfCitiesInJourney() * Math.random());

                
                Node a = journey.getNode(n);
                Node b = journey.getNode(n2);

                
                journey.setNode(n2, a);
                journey.setNode(n, b);
            }
        }
    }
    
     
    private static IndividualJourney selectToursFromCurrentPopulation(StateSpacePopulation pop) {
        
    	
    	//  generate tournament population
    	
    	StateSpacePopulation tournament = new StateSpacePopulation(evolutionStages, false);
       
        for (int i = 0; i < evolutionStages; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveJourneyToArray(i, pop.getJourneyFromArray(randomId));
        }
        
        // Get the Strongest  tour
        IndividualJourney fittest = tournament.getStongestFromPopulation();
        return fittest;
    }

    
    // choose random cities from both the parents to create child journey
    public static IndividualJourney generateChildJourney(IndividualJourney a, IndividualJourney b) {
        
    	
    	
    	IndividualJourney childJoureny = new IndividualJourney();

        
    	
        int startingIndexOfParentA = (int) (Math.random() * a.totalNoOfCitiesInJourney());
        int endIndexOfParentA = (int) (Math.random() * a.totalNoOfCitiesInJourney());
        
         if(endIndexOfParentA<startingIndexOfParentA)
         {
        	 int tempIndex=startingIndexOfParentA;
        	 startingIndexOfParentA=endIndexOfParentA;
        	 endIndexOfParentA=tempIndex;
        	 
         }
        
         
        for (int i = 0; i < childJoureny.totalNoOfCitiesInJourney(); i++) {
            
        	// check whether startIndex is less that endIndex
        	
            if (startingIndexOfParentA < endIndexOfParentA && i > startingIndexOfParentA && i < endIndexOfParentA) {
            	childJoureny.setNode(i, a.getNode(i));
            } 
            
        }

      
        for (int j = 0; j < b.totalNoOfCitiesInJourney(); j++) {
            
        	// If child doesn't have the Node  add it 
            if (!childJoureny.isJouneyContainsNode(b.getNode(j))) {
                
                for (int k = 0; k < childJoureny.totalNoOfCitiesInJourney(); k++) {
                    
                    if (childJoureny.getNode(k) == null) {
                    	childJoureny.setNode(k, b.getNode(j));
                        break;
                    }
                }
            }
        }
        return childJoureny;
    }


}