public class FindShortestPath {
	
	public static void main (String[] args) {
		
		try {
			if (args.length < 1) {
				throw new Exception("No input file.");
			} // if
			
				String dungeonFileName = args[0];
		        
				// creating priority queue and dungeon
				DLPriorityQueue <Hexagon> dungeonQ = new DLPriorityQueue<>();
				Dungeon dungeon = new Dungeon(dungeonFileName);
				// get, enqueue, and mark the starting chamber
			    Hexagon dungeonStart = dungeon.getStart();
			    
				dungeonQ.add(dungeonStart, 0);
				dungeonStart.markEnqueued();
				
				boolean exitFound = false;
				
				// while exit isnt found and while queue is full
				while (!dungeonQ.isEmpty()  && !exitFound) {
				    // remove lowest node
					Hexagon current = dungeonQ.removeMin();
					current.markDequeued();

		            // exit is found, leave loop
		            if (current.isExit()) {
		            	exitFound = true;
		                break;
		            } // if
					
		            
					for (int i = 0; i < 6; i++) {  
			        	Hexagon neighbor = current.getNeighbour(i);
			        	// makes sure the neighbor chamber isnt a wall, null, near a dragon, or already dequeued
			        	if (neighbor !=  null && !neighbor.isWall() && !neighbor.isMarkedDequeued() && !nearDragon(neighbor)) {
			        		
			        		// the distance from the start of the dungeon to the current chamber
			        		int startToNeighbor = 1 + current.getDistanceToStart();
			        		
			        		// corrects the distance value of the neighbors chamber to the start
			        		if (neighbor.getDistanceToStart() > startToNeighbor) {
			        		    neighbor.setDistanceToStart(startToNeighbor);
			        		    neighbor.setPredecessor(current);	
			        		} // if
			        		
			        		// if the neighbor is already queued, update its priority
		                    if (neighbor.isMarkedEnqueued()) {
		                    	double newPriority = neighbor.getDistanceToStart() + neighbor.getDistanceToExit(dungeon);
		                        dungeonQ.updatePriority(neighbor, newPriority);
		                    } // if
		                    
		                    // else update priority and add it to the queue
		                    else {
		                    	double newPriority = neighbor.getDistanceToStart() + neighbor.getDistanceToExit(dungeon);
		                    	dungeonQ.add(neighbor, newPriority);
		                    	neighbor.markEnqueued();
		                    } // else
			    		        	
			        	} // if
			        		
			        } // for
				} // while
				
				// chamber counter
				if (exitFound) {
				    System.out.println("Best path: " + (dungeon.getExit().getDistanceToStart() + 1));
				} // if
				
				else {
				    System.out.println("No possible paths found.");
				} // else
				
			} // try
		
		catch (Exception e){
			
			// going through each possible given exception 
		    if (e instanceof InvalidDungeonCharacterException) {
		        System.out.println("Invalid dungeon character: " + e.getMessage());   
		    } // if
		    else if (e instanceof EmptyPriorityQueueException) {
		        System.out.println("Priority queue is empty: " + e.getMessage());
		    } // else if
		    else if (e instanceof InvalidElementException) {
		        System.out.println("Invalid element in queue: " + e.getMessage());
		    } // else if
		    else if (e instanceof InvalidNeighbourIndexException) {
		        System.out.println("Invalid neighbour index: " + e.getMessage());
		    } // else if
		    else {
		        System.out.println("An error occurred: " + e.getMessage());
		    } // else 
		} // catch
		
	} // method main
	
	
	// helper method to see if the current, or any neighboring chambers hold a dragon
	static private boolean nearDragon(Hexagon current) {
		
		// if dragon
        if (current.isDragon()) {
			return true;
		} // if
        
        // checking the 6 neighbors for dragons
		for (int i = 0; i < 6; i++) {  
			Hexagon neighbor = current.getNeighbour(i);
			
			if (neighbor !=  null && !neighbor.isWall() && !neighbor.isMarkedDequeued()) {
				if (neighbor.isDragon()) {
					return true;
				} // if
			} // if			
		} // for
		
		return false;
	} // method nearDragon
	
	
} // class FindShortestPath
