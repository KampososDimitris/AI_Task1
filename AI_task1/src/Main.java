import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		int[][] puzzle = null;
		String algorithm = null;
		String input_file = null;
		String output_file = null;
		int rows = 0;
		int collumns = 0;
		ArrayList<String> solution = new ArrayList<String>();
		
		
		//Είσοδος από τον χρήστη.
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter 'algorithm' 'Read file' 'Write file':");
		
		System.out.println("Algorithm: ");
		algorithm = s.nextLine();
		
		System.out.println("Input file: ");
		input_file = s.nextLine();
		
		System.out.println("Output file: ");
		output_file = s.nextLine();
		
		s.close();
		
		
		//Ανάγνωση του παζλ από τον φάκελο εισόδου.
		try {
			File inTxt = new File(input_file);
			Scanner fileReader = new Scanner(inTxt);
			
			String first_row = fileReader.nextLine();
			rows = Integer.parseInt(first_row.substring(0,1));
			collumns = Integer.parseInt(first_row.substring(2,3));
			
			puzzle = new int[rows][collumns];
			
			String row_data = null;
			for(int i=0; i<rows; i++) {
				row_data = fileReader.nextLine();
				
				int k = 0;
				int l = 1;
				for(int j=0; j<collumns; j++) {
					puzzle[i][j] = Integer.parseInt(row_data.substring(k,l));
					k += 2;
					l += 2;
				}	
			}	
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}
		
		
		
		search(puzzle,algorithm,rows,collumns,solution);
		
		/*for(String i: solution) {
			
			System.out.println(i);
		}*/
		
		
		try {
			
			FileWriter writer = new FileWriter(output_file);
			
			writer.write(String.valueOf(solution.size()) + "\n");
			
			for(String i: solution) {
				
				writer.write(i + "\n"); 
			}
			
			writer.close();
		}
		catch(IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	static void search(int[][] puzzle,String algorithm,int rows,int collumns,ArrayList<String> solution) {
		
		if(algorithm.contentEquals("depth")) {
				
			Depth run = new Depth(puzzle,rows,collumns);
				
			if(!run.moves.isEmpty()) {
				
				for(String i: run.moves) {
					
					solution.add(i);
				}
			}
			else {
				
				System.out.println("Problem occured!");
				System.exit(0);			
			}
		}
		else if(algorithm.contentEquals("best")) {
				
			Bfs run = new Bfs(puzzle,rows,collumns);
			
			if(!run.moves.isEmpty()) {
				
				for(String i: run.moves) {
					
					solution.add(i);
				}
			}
			else {
				
				System.out.println("Problem occured!");
				System.exit(0);			
			}
				
		}
		else {
			System.out.println("Enter a valid algorithm!");
		}
		
		
		
	}

}