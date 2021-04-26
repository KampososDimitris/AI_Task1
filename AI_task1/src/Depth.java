
public class Depth extends Algorithm{
	
	
	public Depth(int[][] puzzle,int rows,int collumns) {
		
		super(puzzle,rows,collumns);
		
		
		startTime = System.currentTimeMillis();
		
		initiateTree();
		
		//������� ��� ������ ����� �� ������ ���� ��� �� ��������� � �� ���������� �� ������� ������� ���� ��� ���� �����.
		do {
			findChildren(); 
			
			long currentTime;
			currentTime = System.currentTimeMillis();
			timeElapsed = (currentTime - startTime) / 1000F;
			timeElapsed = Math.round(timeElapsed * 100.0) / 100.0;
			
			if(checkIfIsSolution(frontier.getFirst())) {
				
				endTime = System.currentTimeMillis();
				
				timeElapsed = (endTime - startTime) / 1000F;
				timeElapsed = Math.round(timeElapsed * 100.0) / 100.0;
				
				solutionSteps(frontier.getFirst());
			}
			
		} while(moves.isEmpty() && (timeElapsed < TIME_LIMIT));
		
		
	}
	
	//��������� ��� ������� �� ������ ���� ������ ��� �� ��������� ��� ������ ��� ��� ������ ���������� �� ���� ��� ��������� "����� �� �����".
	void findChildren() {
		
		//���������� � ������ ��� ��������� ������ ��� ������ ����������.
		SearchTreeNode current = frontier.getFirst();
		
		int[][] current_state = new int[rows][collumns];
		int[][] child_state = new int[rows][collumns];
		int children_found = 0;
		
		for(int i=0; i<current_state.length; i++)
			  for(int j=0; j<current_state[i].length; j++)
				  current_state[i][j]=current.state[i][j];
		
		//����������� � ������ ��� ��������� ��� �� ������ ���������� ������ ��� �������� ����(���� ��� ���� �������� ��� ������ ���������).
		frontier.removeFirst();
		
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
