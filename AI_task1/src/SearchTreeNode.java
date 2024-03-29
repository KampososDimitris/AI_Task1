
public class SearchTreeNode {
	
	//� ��������� ��� ���� ���� ����� ����.
	int[][] state;
	//� ���� ��� ��������� ����������.
	int h;
	//�� ����� ��� ������ ��� ������ ����������.
	int g = 0;
	//� ������� ��� ������� ��� ������ ��� ��� ����� ��������.
	int children;
	//� ���������� ��� ������� ��� ����� ��� �� �������� �� ���� �� ���� ��� ���������.
	String direction;
	//�� ������������� ��� ����� ��� ��������� � ������� ��� �������� ��� �� ������������� ��� ����� ���� ����� �������� ��� �� �������� �� ���� �� ���� ��� ���������.
	String move;
	//������� ���� ��� �����-����� ��� ������ �����.
	SearchTreeNode parent;
	//������� ���� ��� �����-����� ��� ������� ��� ����� �������������� ��� ������ ���������� ����� � ������.
	SearchTreeNode leaf;
	
	public SearchTreeNode(int[][] state,SearchTreeNode parent,String direction,String move,SearchTreeNode leaf,int rows,int collumns) {
		
		this.state = new int[rows][collumns];
		for(int i=0; i<this.state.length; i++)
			  for(int j=0; j<this.state[i].length; j++)
				  this.state[i][j]=state[i][j];
		this.direction = direction;
		this.move = move;
		this.parent = parent;
		this.leaf = leaf;
		
		if(this.parent != null) 
			this.g = parent.g + 1;
	}	
	
}
