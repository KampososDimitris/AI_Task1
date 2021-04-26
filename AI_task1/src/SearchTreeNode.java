
public class SearchTreeNode {
	
	//Η κατάσταση του παζλ στον κόμβο αυτό.
	int[][] state;
	//Η τιμή της ευρετικής συνάρτησης.
	int h;
	//Το βάθος του κόμβου στο δέντρο αναζήτησης.
	int g = 0;
	//Ο αριθμός των παιδιών του κόμβου που δεν έχουν ελεγχθεί.
	int children;
	//Η κατεύθυνση της κίνησης που έγινε για να οδηγηθεί το παζλ σε αυτή την κατάσταση.
	String direction;
	//Οι συντεταγμένες της θέσης που βρισκόταν ο πάσαλος που κινήθηκε και οι συντεταγμένες της θέσης στην οποία κατέληξε για να οδηγηθεί το παζλ σε αυτή την κατάσταση.
	String move;
	//Δείκτης προς τον κόμβο-γονέα του κόμβου αυτού.
	SearchTreeNode parent;
	//Δείκτης προς τον κόμβο-φύλλο του δέντρου τον οποίο αντιπρωσοπεύει στο μέτωπο αναζήτησης αυτός ο κόμβος.
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
