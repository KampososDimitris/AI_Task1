
public class Bfs extends Algorithm{

	int first_in_frontier_index;
	
	public Bfs(int[][] puzzle,int rows,int collumns) {
		
		super(puzzle,rows,collumns);
		
		
		startTime = System.currentTimeMillis();
		
		initiateTree();
		
		first_in_frontier_index = findSmallestHVal();
		
		do {
			findChildren(); 
			
			//anazhthsh
			
			first_in_frontier_index = findSmallestHVal();
			
			if(checkIfIsSolution(frontier.get(first_in_frontier_index))) {
				
				endTime = System.currentTimeMillis();
				
				timeElapsed = (endTime - startTime) / 1000F;
				timeElapsed = Math.round(timeElapsed * 100.0) / 100.0;
				
				System.out.println(timeElapsed);
				
				solutionSteps(frontier.get(first_in_frontier_index));
			}
			
		} while(moves.isEmpty());
		
		
		
		
	}
	
	
	
	int findSmallestHVal() {
		
		int h = frontier.get(0).h;
		int i = 0;
		
		for(int j=0; j<frontier.size(); j++ ) {
			
			if(frontier.get(j).h < h) {
				
				h = frontier.get(j).h;
				i = j;
			}	
		}
		
		return i;
	}
	
	int findHVal(int[][] state) {
		
		int isolated = 0;
		
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<collumns; j++) {
				
				
				
				//panw grammh
				if(i-1 < 0) {
					
					if(j-1 < 0) {
						
						if( (state[i][j] == 1) && (state[i][j+1] != 1) && (state[i+1][j+1] != 1) && (state[i+1][j] != 1) ) 
							isolated++;
					}
					else if(j+1 >= collumns) {
						
						if( (state[i][j] == 1) && (state[i][j-1] != 1) && (state[i+1][j-1] != 1) && (state[i+1][j] != 1) ) 
							isolated++;
					}
					else {
						
						if( (state[i][j] == 1) && (state[i][j+1] != 1) && (state[i+1][j+1] != 1) && (state[i+1][j] != 1) && (state[i][j-1] != 1) && (state[i+1][j-1] != 1) ) 
							isolated++;
					}
				}
				//katw grammh
				else if(i+1 >= rows) {
					
					if(j-1 < 0) {
						
						if( (state[i][j] == 1) && (state[i-1][j] != 1) && (state[i-1][j+1] != 1) && (state[i][j+1] != 1) ) 
							isolated++;
					}
					else if(j+1 >= collumns) {
						
						if( (state[i][j] == 1) && (state[i][j-1] != 1) && (state[i-1][j-1] != 1) && (state[i-1][j] != 1) ) 
							isolated++;
					}
					else {
						
						if( (state[i][j] == 1) && (state[i][j-1] != 1) && (state[i-1][j-1] != 1) && (state[i-1][j] != 1) && (state[i-1][j+1] != 1) && (state[i][j+1] != 1) ) 
							isolated++;
					}
				}
				//endiamesa
				else {
					
					if(j-1 < 0) {
						
						if( (state[i][j] == 1) && (state[i-1][j] != 1) && (state[i-1][j+1] != 1) && (state[i][j+1] != 1) && (state[i+1][j+1] != 1) && (state[i+1][j] != 1) ) 
							isolated++;
					}
					else if(j+1 >= collumns) {
						
						if( (state[i][j] == 1) && (state[i-1][j] != 1) && (state[i-1][j-1] != 1) && (state[i][j-1] != 1) && (state[i+1][j-1] != 1) && (state[i+1][j] != 1) ) 
							isolated++;
					}
					else {
						
						if( (state[i][j] == 1) && (state[i-1][j-1] != 1) && (state[i-1][j] != 1) && (state[i-1][j+1] != 1) && (state[i+1][j-1] != 1) && (state[i+1][j] != 1) && (state[i+1][j+1] != 1)
							&& (state[i][j-1] != 1) && (state[i][j+1] != 1) ) 
							isolated++;
					}
				}	
			}
		}
		
		
		return isolated;
	}
	
	void findChildren() {
		
		SearchTreeNode current = frontier.get(first_in_frontier_index);
		int[][] current_state = new int[rows][collumns];
		int[][] child_state = new int[rows][collumns];
		int children_found = 0;
		
		for(int i=0; i<current_state.length; i++)
			  for(int j=0; j<current_state[i].length; j++)
				  current_state[i][j]=current.state[i][j];
		
		frontier.remove(first_in_frontier_index);
		
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
					
					child.h = findHVal(current_state);
					
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
					
					child.h = findHVal(current_state);
					
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
					
					child.h = findHVal(current_state);
					
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
					
					child.h = findHVal(current_state);
					
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
	}
	
}
