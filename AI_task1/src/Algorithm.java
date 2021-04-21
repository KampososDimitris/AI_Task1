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
	
	
	public Algorithm(int[][] puzzle,int rows,int collumns) {
		
		this.rows = rows;
		this.collumns = collumns;
		this.puzzle = new int[rows][collumns];
		for(int i=0; i<this.puzzle.length; i++)
			  for(int j=0; j<this.puzzle[i].length; j++)
				  this.puzzle[i][j]=puzzle[i][j];
		
	}
	
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

	void removeNode(SearchTreeNode node) {
		
		tree.remove(node);
		
		node = node.parent;
		
		node.children = node.children - 1;
		
		if(node.children == 0) {
			
			removeNode(node);
		}

	}
	
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
	
	void printState(int[][] state) {
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<collumns; j++) {
				
				System.out.print(state[i][j] + " ");
			}
			System.out.println("");
		}
		
		System.out.println();
	}
	
	void printList(LinkedList<SearchTreeNode> f) {
		
		System.out.println("Start of frontier print\n");
		
		for(SearchTreeNode i: f) {
			
			printState(i.state);
			System.out.println(i.direction);
			System.out.println(i.g);
			System.out.println(i.move);
		}
		
		System.out.println("End of frontier print\n");
		
	}
	
	void printTree(ArrayList<SearchTreeNode> t) {
		
		System.out.println("Start of tree print\n");
		
		for(SearchTreeNode i: t) {
			
			printState(i.state);
			System.out.println(i.direction);
			System.out.println(i.g);
			System.out.println(i.move);
		}
		
		System.out.println("End of tree print\n");
	}
	
}
