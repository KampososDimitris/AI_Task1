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
		double time = 0;
		
		
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
			System.exit(0);
		}
		
		
		//Εκτέλεση αλγορίθμου.
		time = search(puzzle,algorithm,rows,collumns,solution);
		
		//Έλεγχος λύσης.
		checkSolution(puzzle,solution);
		
		System.out.println("Time elapsed: " + time + "s");
		
		//Εγγραφή λύσης στο αρχείο εξόδου.
		try {
			
			FileWriter writer = new FileWriter(output_file);
			
			writer.write(String.valueOf(solution.size()) + "\n");
			
			for(String i: solution) {
				
				writer.write(i + "\n"); 
			}
			
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Could not write to file!");
			System.exit(0);
		}
		
		
	}
	
	
	//Συνάρτηση όπου επιλέγεται ο αλγόριθμος επίλυσης του παζλ.
	static double search(int[][] puzzle,String algorithm,int rows,int collumns,ArrayList<String> solution) {
		
		double time = 0;
		
		if(algorithm.contentEquals("depth")) {
				
			Depth run = new Depth(puzzle,rows,collumns);
				
			if(!run.moves.isEmpty()) {
				
				for(String i: run.moves) {
					
					solution.add(i);
				}
				time = run.timeElapsed;
			}
			else {
				
				System.out.println("Time out!");
				System.exit(0);			
			}
		}
		else if(algorithm.contentEquals("best")) {
				
			Bfs run = new Bfs(puzzle,rows,collumns);
			
			if(!run.moves.isEmpty()) {
				
				for(String i: run.moves) {
					
					solution.add(i);
				}
				time = run.timeElapsed;
			}
			else {
				
				System.out.println("Time out!");
				System.exit(0);			
			}
				
		}
		else {
			System.out.println("Enter a valid algorithm!");
		}
		
		return time;
		
	}
	
	//Συνάρτηση όπου ελέγχεται η εγκυρότητα της λύσης που βρέθηκε απο το πρόγραμμα.
	static void checkSolution(int[][] puzzle,ArrayList<String> solution) {
		
		System.out.println();
		printState(puzzle);
		
		for(String move: solution) {
			
			int i1 = Integer.parseInt(move.substring(0,1)) - 1;
			int j1 = Integer.parseInt(move.substring(2,3)) - 1;
			int i2 = Integer.parseInt(move.substring(4,5)) - 1;
			int j2 = Integer.parseInt(move.substring(6,7)) - 1;
			int i3 = 0;
			int j3 = 0;
			
			
			if(j2 == j1) {
				
				if(i2>i1)
					i3 = i1 + 1;
				else if(i1>i2)
					i3 = i1 - 1;
				else {
					System.out.println("Invalid move!");
					System.exit(0);
				}
				j3 = j1;
			}
			else if(i2 == i1) {
				
				if(j2>j1)
					j3 = j1 + 1;
				else if(j1>j2)
					j3 = j1 - 1;
				else {
					System.out.println("Invalid move!");
					System.exit(0);
				}
				i3 = i1;
			}
			else {
				System.out.println("Invalid move!");
				System.out.println("Incorrect solution!");
				System.exit(0);
			}
			
			
			if( (puzzle[i1][j1] == 1) && (puzzle[i2][j2] == 2) && (puzzle[i3][j3] == 1) ) {
				
				puzzle[i1][j1] = 2;
				puzzle[i2][j2] = 1;
				puzzle[i3][j3] = 2;
			}
			else {
				
				System.out.println("Incorrect solution!");
				System.exit(0);
			}
			
			printState(puzzle);
		}
		
		
		if(checkIfIsSolution(puzzle)) {
			
			System.out.println("Solution correct!");
		}
		else {
			
			System.out.println("Solution incorrect!");
			System.exit(0);
		}
		
	}
	
	//Συνάρτηση εκτύπωσης της κατάστασης ενός στιγμιοτύπου του παζλ.
	static void printState(int[][] state) {
		
		for(int i=0; i<state.length; i++) {
			for(int j=0; j<state[i].length; j++) {
				
				System.out.print(state[i][j] + " ");
			}
			System.out.println("");
		}
		
		System.out.println();
	}
	
	//Συνάρτηση που ελέγχεται εάν στην τελική κατάσταση έχει απομείνει ένας πάσαλος.
	static boolean checkIfIsSolution(int[][] state) {
		
		int k = 0;
		
		for(int i=0; i<state.length; i++) {
			for(int j=0; j<state[i].length; j++) {
				if(state[i][j] == 1)
					k++;
			}
		}
		
		return k == 1;
	}
	
}