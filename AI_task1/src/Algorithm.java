import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Algorithm {
	
	int[][] puzzle;
	int rows;
	int collumns;
	ArrayList<SearchTreeNode> tree = new ArrayList<SearchTreeNode>();
	LinkedList<SearchTreeNode> frontier = new LinkedList<SearchTreeNode>();
	ArrayList<String> moves = new ArrayList<String>();
	long startTime = 0;
	long endTime = 0;
	double timeElapsed = 0;
	static final int TIME_LIMIT = 60000;
	
	
	public Algorithm(int[][] puzzle,int rows,int collumns) {
		
		this.rows = rows;
		this.collumns = collumns;
		this.puzzle = new int[rows][collumns];
		for(int i=0; i<this.puzzle.length; i++)
			  for(int j=0; j<this.puzzle[i].length; j++)
				  this.puzzle[i][j]=puzzle[i][j];
		
	}
	
	//Συνάρτηση όπου δημιουργούνται το δέντρο και το μέτωπο αναζήτησης.
	void initiateTree() {
		
		int[][] root_state = new int[rows][collumns];
		
		for(int i=0; i<root_state.length; i++)
			  for(int j=0; j<root_state[i].length; j++)
				  root_state[i][j]=puzzle[i][j];
		
		SearchTreeNode root = new SearchTreeNode(root_state,null,null,null,null,rows,collumns);
		root.h = 0;
		
		tree.add(root);
		frontier.addFirst(new SearchTreeNode(root_state,null,null,null,root,rows,collumns));
	}
	
	//Συνάρτηση που ελέγχεται εάν στην τελική κατάσταση έχει απομείνει ένας πάσαλος.
	boolean checkIfIsSolution(SearchTreeNode node) {
		
		int k = 0;
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<collumns; j++) {
				if(node.state[i][j] == 1)
					k++;
			}
		}
		
		return k == 1;
	}
	
	//Συνάρτηση όπου διαγράφεται ένας κόμβος που δεν έχει παιδιά και δεν αποτελεί λύση.Επίσης ελέγχονται αναδρομικά και οι πιο πάνω κόμβοι για την διαγραφή υποδέντρων που δε παράγουν λύση.
	void removeNode(SearchTreeNode node) {
		
		tree.remove(node);
		
		node = node.parent;
		
		node.children = node.children - 1;
		
		if(node.children == 0) {
			
			removeNode(node);
		}

	}
	
	//Συνάρτηση που αποθηκεύει τα βήματα της λύσης σε μια λίστα.
	void solutionSteps(SearchTreeNode solution) {
		
		Stack<SearchTreeNode> moves_stack = new Stack<SearchTreeNode>();
		
		solution = solution.leaf;
		
		while(solution != null) {
			
			moves_stack.add(solution);
			
			solution = solution.parent;
		}

		while(!moves_stack.isEmpty()) {
			if(moves_stack.lastElement().move != null)
				moves.add(moves_stack.lastElement().move);
			
			moves_stack.pop();
		}
		
	}
	
	//Συνάρτηση εκτύπωσης της κατάστασης ενός στιγμιοτύπου του παζλ.
	void printState(int[][] state) {
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<collumns; j++) {
				
				System.out.print(state[i][j] + " ");
			}
			System.out.println("");
		}
		
		System.out.println();
	}
	
	
	
}
