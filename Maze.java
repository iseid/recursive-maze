import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Maze{

	//reads the input file and stores the maze in a 2D array
	public char[][] readFile(String filename) {
		try {
			Scanner keyboard = new Scanner(new File(filename));
			int i = keyboard.nextInt();
			int j = keyboard.nextInt();
			keyboard.nextLine();
			char [][] temp = new char [i][j];
			int first = 0;

			while(keyboard.hasNextLine()) {
				String line = keyboard.nextLine();
				for (int col = 0; col < j; col++) {
					temp[first][col] = line.charAt(col);
				}
				first +=1;
			}	
			keyboard.close();
			return temp;
		} 	
		catch(IOException e) {
			System.exit(0);
			return null;
		}
	}

	//find the starting point in the maze as designated by an 'S'
	public int[] findStart(char[][] maze) {
		int [] temp = {0,0};
		for(int i=0; i<maze.length; i++) {
			for(int j=0; j<maze[0].length; j++) {
				if(maze[i][j] == 'S') {
					temp[0] = i;
					temp[1] = j;
				}
			}
		}
		if(temp[0] == 0 && temp[1] == 0) {
			return null;
		}
		return temp;
	}

	//uses a depth first search method to solve the maze.
	//maze is solved when 'G" is returned
	public String findPath(char[][] maze, int[] startPosition) {
		// TODO Auto-generated method stub
		int r = startPosition[0];
		int c = startPosition[1];
		String s = recPath(maze, r ,c);
		maze[r][c] = 'S';
		return s;
	}

	//Helper method to solve maze. Leaves a trail of .'s to represent that solution path and prints move letter.
	private String recPath(char[][] maze, int r, int c) {
		String string = "";
		if(r > maze.length || r > maze[0].length || r < 0 || c < 0 ) {
			return "";
		}
		if(maze[r][c] == '#' || maze[r][c] == '.') {
			return "";
		}
		if(maze[r][c] == 'G') {
			return "G";
		}
		else {
			maze[r][c] = '.';
			string = recPath(maze, r-1, c);
			if(string.contains("G")) {
				return "U" + string;
			}
			string = recPath(maze, r, c+1);
			if(string.contains("G")) {
				return "R" + string;
			}
			string = recPath(maze, r+1, c);
			if(string.contains("G")) {
				return "D" + string;
			}
			string = recPath(maze, r, c-1);
			if(string.contains("G")) {
				return "L" + string;
			}
			else {
				maze[r][c] = ' ';
				return "";
			}
		}
	}

	//prints the maze design
	public String printMaze(char[][] maze) {
		String s = "";
		for(int i=0; i<maze.length; i++) {
			for(int j=0; j<maze[0].length; j++) {
				s += maze[i][j];
				if(j == maze[0].length-1) {
					s+= "\n";
				}
			}
		}
		return s;
	}

	public static void main(String[] args) {
		Maze maze = new Maze();
		char [][] temp = maze.readFile("Maze.txt");
		System.out.println(maze.printMaze(temp));
		int[] startPoint = maze.findStart(temp);
		System.out.println(("Starting point is: " + (Arrays.toString(startPoint) + "\n")));
		System.out.println("The solution for the maze is: " + maze.findPath(temp, startPoint) + "\n");
		System.out.println(maze.printMaze(temp));
	}
}