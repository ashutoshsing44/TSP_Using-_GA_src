package ai.tsp.ga;


//class which creates the population for state space
public class StateSpacePopulation {

 
	IndividualJourney[]  jounreyArray;


    public StateSpacePopulation(int populationSize, boolean initialise) {
    	jounreyArray = new IndividualJourney[populationSize];
        
        if (initialise) {
            
            for (int i = 0; i < populationSize(); i++) {
            	IndividualJourney journey = new IndividualJourney();
            	journey.createJourney();
            	saveJourneyToArray(i, journey);
            }
        }
    }
    
    
    public void saveJourneyToArray(int index, IndividualJourney journey) {
    	jounreyArray[index] = journey;
    }
    
    
    public IndividualJourney getJourneyFromArray(int index) {
        return jounreyArray[index];
    }

    
    public IndividualJourney getStongestFromPopulation() {
    	IndividualJourney strongest = jounreyArray[0];
        
        for (int i = 1; i < populationSize(); i++) {
            if (strongest.getFitness() <= getJourneyFromArray(i).getFitness()) {
            	strongest = getJourneyFromArray(i);
            }
        }
        return strongest;
    }

    
    public int populationSize() {
        return jounreyArray.length;
    }
}