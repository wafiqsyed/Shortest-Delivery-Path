import java.io.FileNotFoundException;
import java.io.IOException;

public class FindShortestPath {

	private Map cityMap;
	
	public FindShortestPath(String filename){
		try {
			cityMap = new Map(filename);
		}catch (FileNotFoundException e){System.out.println("Sorry, the file is not found. Please select a valid file.");
		}catch (IOException e){System.out.println("Sorry, the file could not be opened. Please select a valid file.");}
	}
	
	public static void main (String [] args) {
		if(args.length < 1) {																//Checks to make sure that the file entered is valid 
			throw new IllegalArgumentException(
				"Provide a valid filename with the input map");
		}	
		String mapFileName = args[0];
		FindShortestPath map = new FindShortestPath(mapFileName);	
		
		
		DLPriorityQueue<MapCell> path = new DLPriorityQueue<MapCell>();						//Create an empty priority queue 
		MapCell UWO = map.cityMap.getUWOstore();											//Gets first cell - UWO bookstore
		path.enqueue(UWO, 0);																//Adds UWO cell to queue with priority of 0
		UWO.markEnqueued();																	//Marks the UWO bookstore as enqueued. 
		MapCell currentCell = UWO;															//C represents the current cell 
		int count = 0;	
		MapCell smallestPriorityCell = null;														//Removes cell with smallest priority queue
		while(!path.isEmpty() && !currentCell.isCustomer()) {									//Initializes loop, when the path queue isn't empty and the C isn't customer
			smallestPriorityCell = path.getSmallest();
			smallestPriorityCell.markDequeued();												//Marks the smallest priority cell as dequeued. 
			if(smallestPriorityCell.isCustomer()) {
				count = smallestPriorityCell.getDistanceToStart() + 1;
				break;}									//If the smallest priority cell is the customer, exit the loop.
			if(smallestPriorityCell.isTower() || interference(smallestPriorityCell)) {continue;}						//If the smallest priority cell is a tower cell or is adjacent to a tower cell, return to beginning of loop 
			
			try{
				for(int neighbour = 0; neighbour < 6; neighbour++) {							//Loop through neighbouring cells
					MapCell currentCellNeighbour = smallestPriorityCell.getNeighbour(neighbour);
					if (currentCellNeighbour != null && !currentCellNeighbour.isNoFlying() && !currentCellNeighbour.isMarkedDequeued()){		//Check if neighbour is not null, no flying zone, or maarked dequeue.
						int distanceToUWO = 1 + smallestPriorityCell.getDistanceToStart();				//Distance from smallest priority cell to initial UWO store
						if(currentCellNeighbour.getDistanceToStart() > distanceToUWO) {							//If the distance from current cell to the inital cell is greater than the distance to initial cell.
							currentCellNeighbour.setDistanceToStart(distanceToUWO);								//Set the distance of the current cell to UWO as the distance 
							currentCellNeighbour.setPredecessor(smallestPriorityCell);
						}
						double priority = currentCellNeighbour.getDistanceToStart() + currentCell.euclideanDistToDest(map.cityMap);
						if(currentCellNeighbour.isMarkedEnqueued() && priority < path.getPriority(currentCellNeighbour)) {
							path.changePriority(currentCellNeighbour, priority);
						}
						if(!currentCellNeighbour.isMarkedEnqueued()) {//							double currentCellPriority = currentCellNeighbour.getDistanceToStart() + currentCellNeighbour.euclideanDistToDest(map.cityMap);
							path.enqueue(currentCellNeighbour, priority);
						}
					}
				}
				
			}catch (Exception e) {System.out.println("ERROR Occurred: Unable to identify neighbouring cells. " + e.getMessage());}					
		}
		

		if(currentCell.isInitial() && smallestPriorityCell.isCustomer()) {							//Make sure that the path of the queue includes the customer as end, and UWO store as initial
			System.out.println("The shortest path from UWO to the customer's house is found. Number of cells travelled by the drone: " + count);
		}else {
			System.out.println("There is no available path from UWO to the customer's house.");
		}
		
	}
	
	/*
	 * Method checks if any of current cell's neighbours are tower cells
	 * @param cell is the cell for which we will check its neighbours
	 * returns true if any of the current cell's neighbours is a tower cell
	 * returns false if none of the neighbours are tower cells 
	 */
	private static boolean interference(MapCell cell) {
		MapCell currentCell = cell;
		
		try{
			for(int neighbour = 0; neighbour < 6; neighbour++) {		//Loop through neighbouring cells
				if (currentCell.getNeighbour(neighbour) != null) {
				if(currentCell.getNeighbour(neighbour).isTower()) {return true;}
				}
			}
		}catch (Exception e) {System.out.println("ERROR Occurred: Unable to identify if the adjacent cell is a tower cell. " + e.getMessage());}
		
		return false;
	}
}
	

