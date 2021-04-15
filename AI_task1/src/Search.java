import java.util.ArrayList;

public class Search {
	
	int[][] puzzle;
	String algorithm;
	int rows;
	int collumns;
	ArrayList<SearchTreeNode> solution = new ArrayList<SearchTreeNode>();
	String[] direction = null;
	
	public Search(int[][] puzzle,String algorithm,int rows,int collumns) {
		
		this.algorithm = algorithm;
		this.rows = rows;
		this.collumns = collumns;
		this.puzzle = new int[rows][collumns];
		this.puzzle = puzzle;
	
		search(puzzle,algorithm,rows,collumns,solution);
		
	}
	
	
	
	
	//Αναζητά λύση στο παζλ με βάση τον αλγόριθμο που δώθηκε από τον χρήστη.
	void search(int[][] puzzle,String algorithm,int rows,int collumns,
					   ArrayList<SearchTreeNode> solution) {
		
		if(algorithm.contentEquals("depth")) {
			
			Depth run = new Depth(puzzle,rows,collumns);
			
			if(!run.moves.isEmpty()) {
				solution = (ArrayList<SearchTreeNode>) run.moves.clone();
				
				
			}
			else {
				System.out.println("Problem occured!");
				System.exit(0);
			}
			
		}
		else if(algorithm.contentEquals("bfs")) {
			
			Bfs run = new Bfs(puzzle,rows,collumns);
			
		}
		else {
			System.out.println("Enter a valid algorithm!");
		}
		
		
		
	}
	
	
}
