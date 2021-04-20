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
	
	/*void findChildren() {
		
		SearchTreeNode current = frontier.getFirst();
		int[][] current_state = new int[rows][collumns];
		int[][] child_state = new int[rows][collumns];
		int children_found = 0;
		
		for(int i=0; i<current_state.length; i++)
			  for(int j=0; j<current_state[i].length; j++)
				  current_state[i][j]=current.state[i][j];
		
		frontier.removeFirst();
		
		//Εξετάζεται το παζλ για κινήσεις προς τα πάνω.
		for(int i=0; i<rows; i++) {
			for(int j=0; j<collumns; j++) {
				if((i-1 >= 0) && (i-2 >= 0) && 
				  (current_state[i][j] == 1) && (current_state[i-1][j] == 1) && (current_state[i-2][j] == 2)) {
					
					for(int k=0; k<rows; k++)
						  for(int l=0; l<collumns; l++)
							  child_state[k][l]=current_state[k][l];
					child_state[i][j] = 2;
					child_state[i-1][j] = 2;
					child_state[i-2][j] = 1;
					
					String move = String.valueOf(i+1) + " " + String.valueOf(j+1) + " " + String.valueOf(i-1) + " " + String.valueOf(j+1);
					
					SearchTreeNode child = new SearchTreeNode(child_state,current.leaf,"up",move,null,rows,collumns);
					
					tree.add(child);
					//tree.get(tree.indexOf(child)).index = tree.indexOf(child);
					frontier.addFirst(new SearchTreeNode(child_state,null,"up",move,child,rows,collumns));
					
					children_found++;
				}		
			}
		}
		
		
		//Εξετάζεται το παζλ για κινήσεις προς τα κάτω.
		for(int i=0; i<rows; i++) {
			for(int j=0; j<collumns; j++) {
				
				if((i+1 < rows) && (i+2 < rows) &&
				  (current_state[i][j] == 1) && (current_state[i+1][j] == 1) && (current_state[i+2][j] == 2)) {
					
					for(int k=0; k<rows; k++)
						  for(int l=0; l<collumns; l++)
							  child_state[k][l]=current_state[k][l];
					child_state[i][j] = 2;
					child_state[i+1][j] = 2;
					child_state[i+2][j] = 1;
					
					String move = String.valueOf(i+1) + " " + String.valueOf(j+1) + " " + String.valueOf(i+3) + " " + String.valueOf(j+1);
					
					SearchTreeNode child = new SearchTreeNode(child_state,current.leaf,"down",move,null,rows,collumns);
					
					tree.add(child);
					//tree.get(tree.indexOf(child)).index = tree.indexOf(child);
					frontier.addFirst(new SearchTreeNode(child_state,null,"down",move,child,rows,collumns));
					
					children_found++;
				}		
			}
		}
		
		//Εξετάζεται το παζλ για κινήσεις προς τα δεξιά.
		for(int i=0; i<rows; i++) {
			for(int j=0; j<collumns; j++) {
				
				if((j+1 < collumns) && (j+2 < collumns) && 
				  (current_state[i][j] == 1) && (current_state[i][j+1] == 1) && (current_state[i][j+2] == 2)) {
					
					for(int k=0; k<rows; k++)
						  for(int l=0; l<collumns; l++)
							  child_state[k][l]=current_state[k][l];
					child_state[i][j] = 2;
					child_state[i][j+1] = 2;
					child_state[i][j+2] = 1;
					
					String move = String.valueOf(i+1) + " " + String.valueOf(j+1) + " " + String.valueOf(i+1) + " " + String.valueOf(j+3);
					
					SearchTreeNode child = new SearchTreeNode(child_state,current.leaf,"right",move,null,rows,collumns);
					
					tree.add(child);
					//tree.get(tree.indexOf(child)).index = tree.indexOf(child);
					frontier.addFirst(new SearchTreeNode(child_state,null,"right",move,child,rows,collumns));
					
					children_found++;
				}		
			}
		}
		
		//Εξετάζεται το παζλ για κινήσεις προς τα αριστερά.
		for(int i=0; i<rows; i++) {
			for(int j=0; j<collumns; j++) {
				
				if((j-1 >= 0) && (j-2 >= 0) && 
				  (current_state[i][j] == 1) && (current_state[i][j-1] == 1) && (current_state[i][j-2] == 2)) {
					
					for(int k=0; k<rows; k++)
						  for(int l=0; l<collumns; l++)
							  child_state[k][l]=current_state[k][l];
					child_state[i][j] = 2;
					child_state[i][j-1] = 2;
					child_state[i][j-2] = 1;
					
					String move = String.valueOf(i+1) + " " + String.valueOf(j+1) + " " + String.valueOf(i+1) + " " + String.valueOf(j-1);
					
					SearchTreeNode child = new SearchTreeNode(child_state,current.leaf,"left",move,null,rows,collumns);
					
					tree.add(child);
					//tree.get(tree.indexOf(child)).index = tree.indexOf(child);
					frontier.addFirst(new SearchTreeNode(child_state,null,"left",move,child,rows,collumns));
					
					children_found++;
				}		
			}
		}
		//System.out.println(children_found);
		
		current.leaf.children = children_found;
		
		if(current.leaf.children == 0) {
			
			removeNode(current.leaf);
		}
		
		System.out.println(tree.size());
	}*/
	
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
			
			//printState(moves_stack.lastElement().state);
			
			moves_stack.pop();
		}
		
		/*for(String i: moves) {
			System.out.println(i);
		}*/
	}
	
	void printState(int[][] state) {
		System.out.println("PRINT STATEEEEE");
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
