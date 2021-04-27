
public class Bfs extends Algorithm{

	int first_in_frontier_index;
	
	public Bfs(int[][] puzzle,int rows,int collumns) {
		
		super(puzzle,rows,collumns);
		
		startTime = System.currentTimeMillis();
		
		initiateTree();
		
		//��������� � ������� ���� �� ������ ����������,��� ������-����� �� �� ��������� h.
		first_in_frontier_index = findSmallestHVal();
		
		//������� ��� ������ ����� �� ������ ���� ��� �� ��������� � �� ���������� �� ������� ������� ���� ��� ���� �����.
		do {
			findChildren(); 
			
			first_in_frontier_index = findSmallestHVal();
			
			long currentTime;
			currentTime = System.currentTimeMillis();
			timeElapsed = (currentTime - startTime) / 1000F;
			timeElapsed = Math.round(timeElapsed * 100.0) / 100.0;
			
			if(checkIfIsSolution(frontier.get(first_in_frontier_index))) {
				
				endTime = System.currentTimeMillis();
				
				timeElapsed = (endTime - startTime) / 1000F;
				timeElapsed = Math.round(timeElapsed * 100.0) / 100.0;
				
				solutionSteps(frontier.get(first_in_frontier_index));
			}
			
		} while(moves.isEmpty() && (timeElapsed < TIME_LIMIT));
		
	}
	
	
	//��������� ��� ������� ��� ���������� �� ������ ���� �� ������ ���������� ��� ������-����� �� �� ��������� ���� h.
	int findSmallestHVal() {
		
		int h = frontier.get(0).leaf.h;
		int i = 0;
		
		for(int j=0; j<frontier.size(); j++ ) {
			
			if(frontier.get(j).leaf.h < h) {
				
				h = frontier.get(j).leaf.h;
				i = j;
			}	
		}
		
		return i;
	}
	
	//��������� ��� ������� ��� ���������� ��� ��� �����-����� �� ������ ��� ���������� Manhattan ���� ������� ��� ���� ������ (�������� ��� ��� �������� ��������� ��� ����������).
	int findManSumH(int[][] state) {
		
		int sum = 0;
		
		
		for(int i=0; i<state.length; i++) {
			for(int j=0; j<state[i].length; j++) {
				
				if(state[i][j] == 1) {
					
					for(int k=0; k<state.length; k++) {
						for(int l=0; l<state[k].length; l++) {
							
							if(state[k][l] == 1) {
								sum += manhattanDistance(i,j,k,l);
							}
						}
					}
				}
			}
		}
		
		return sum;
	}
	
	//��������� ��� ������� ��� ���������� ��� �������� Manhattan ��� �������.
	int manhattanDistance(int i,int j,int k,int l) {
		
		int sum = 0;
		
		sum =( (Math.abs(i-k)) + (Math.abs(j-l)) );
		//System.out.println(sum);
		return sum;
	}
	
	//��������� ��� ������� �� ������ ���� ������ ��� �� ��������� ��� ������ ��� ��� ������ ���������� �� ���� ��� ��������� "��������� ����� ��� ��������".
	void findChildren() {
		
		//���������� � ������ ��� ���� �� ��������� ���� h (h: ���������� ��������� ����������).
		SearchTreeNode current = frontier.get(first_in_frontier_index);
		
		int[][] current_state = new int[rows][collumns];
		int[][] child_state = new int[rows][collumns];
		int children_found = 0;
		
		for(int i=0; i<current_state.length; i++)
			  for(int j=0; j<current_state[i].length; j++)
				  current_state[i][j]=current.state[i][j];
		
		//����������� � ������ ��� ��������� ��� �� ������ ���������� ������ ��� �������� ����(���� ��� ���� �������� ��� ������ ���������).
		frontier.remove(first_in_frontier_index);
		
		//���������� �� ���� ��� �������� ���� �� ���� ��� ������������� �� ������ ��� �� ������ ����������.
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
					
					child.h = findManSumH(child_state);
					
					tree.add(child);
					frontier.addFirst(new SearchTreeNode(child_state,null,"up",move,child,rows,collumns));
					
					children_found++;
				}		
			}
		}
		
		
		//���������� �� ���� ��� �������� ���� �� ���� ��� ������������� �� ������ ��� �� ������ ����������.
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
					
					child.h = findManSumH(child_state);
					
					tree.add(child);
					frontier.addFirst(new SearchTreeNode(child_state,null,"down",move,child,rows,collumns));
					
					children_found++;
				}		
			}
		}
		
		//���������� �� ���� ��� �������� ���� �� ����� ��� ������������� �� ������ ��� �� ������ ����������.
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
					
					child.h = findManSumH(child_state);
					
					tree.add(child);
					frontier.addFirst(new SearchTreeNode(child_state,null,"right",move,child,rows,collumns));
					
					children_found++;
				}		
			}
		}
		
		//���������� �� ���� ��� �������� ���� �� �������� ��� ������������� �� ������ ��� �� ������ ����������.
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
					
					child.h = findManSumH(child_state);
					
					tree.add(child);
					frontier.addFirst(new SearchTreeNode(child_state,null,"left",move,child,rows,collumns));
					
					children_found++;
				}		
			}
		}
		
		current.leaf.children = children_found;
		
		//��������� ��� � ������ ��� ���������� ����� �� ���������.
		if(current.leaf.children == 0)
			removeNode(current.leaf);
		
	}
	
}
