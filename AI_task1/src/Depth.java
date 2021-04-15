
public class Depth extends Algorithm{
	
	
	public Depth(int[][] puzzle,int rows,int collumns) {
		
		super(puzzle,rows,collumns);
		
		
		startTime = System.currentTimeMillis();
		
		initiateTree();
		
		do {
			findChildren(); 

			if(checkIfIsSolution(frontier.getFirst())) {
				
				endTime = System.currentTimeMillis();
				
				timeElapsed = (endTime - startTime) / 1000F;
				timeElapsed = Math.round(timeElapsed * 100.0) / 100.0;
				
				System.out.println(timeElapsed);
				
				solutionSteps(frontier.getFirst());
			}
			
		} while(moves.isEmpty());
		
		
	}
	
	

	
	
	
	
}
