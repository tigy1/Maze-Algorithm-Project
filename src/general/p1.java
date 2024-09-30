package general;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;
import java.util.Stack;
public class p1 {
	static int rows = 0;
	static int columns = 0;
	static int levels = 0;
	static int it = 0;
	static String line = "";
	
	public static void main(String[] args) throws Exception {
		
		//command line - content of args
		
		//example - not real/actual logic
		/*if(args.length <= 1) {
			//throw the appropriate exception if you encounter
			//an error from list
			throw new IllegalCommandLineInputsException();
		}*/
		try {
			File file = new File("test6.txt");
		    Scanner scanner = new Scanner(file);
		    String[][] arr = mapT(file, scanner, 1);
		    stackMaze(arr, true);
		    queueMaze(arr, true);
		    optimal(arr, true);
		} catch (FileNotFoundException e) {
			System.out.println("file not exist!!!");
		}
	}
	
	public static String[][] mapT(File map, Scanner scanner, int text) {
		while(scanner.hasNext()) {
			line = scanner.next();
			if(it == 0) {
				rows = Integer.parseInt(line);
			}
			if(it == 1) {
				columns = Integer.parseInt(line);
			}
			if(it == 2) {
				levels = Integer.parseInt(line);
				break;
			}
			it++;
		}
		if(text == 0) {
			String[][] arr2 = new String[rows][columns];
			for(int r = 0; r < rows; r++) {
				line = scanner.next();
				for(int c = 0; c < columns; c++) {
					arr2[r][c] = line.substring(c, c+1);
				}
			}
			return arr2;
		}
		if(text == 1) {
			String[][] arr2 = new String[rows][columns];
			while(scanner.hasNext()) {
				String[] row = new String[4];
				for(int i = 0; i < 4; i++) {
					row[i] = scanner.next();
				}
				arr2[Integer.parseInt(row[1])][Integer.parseInt(row[2])] = row[0];
			}
			return arr2;
		}
		else {
			return null;
		}
	}
	
	public static void queueMaze(String[][] arr, boolean time){
		int[] start = new int[2];
		String[][] arr2 = new String[arr.length][arr[0].length];
		for(int r = 0; r < arr.length; r++) {
			for(int c = 0; c < arr[0].length; c++) {
				arr2[r][c] = arr[r][c];
				if(arr2[r][c].equals("W")) {
					start[0] = r;
					start[1] = c;
				}
			}
		}
		long tStart = System.nanoTime();
		Queue<int[]> toBeVisited = new LinkedList<int[]>();
		Queue<int[]> visited = new LinkedList<int[]>();
		toBeVisited.add(start);
		boolean whatever = true;
		//visited.size() == 0 || !(arr[visited.peek()[1]][visited.peek()[0]].equals("$"))

		while(whatever) {
			visited.add(toBeVisited.peek());
			toBeVisited.remove();
//			System.out.println(flip(visited)[0]);
			if(arr[flip(visited)[0]][flip(visited)[1]].equals("$")) {
				whatever = false;
			}
			if(flip(visited)[0]-1 >= 0 && (arr[flip(visited)[0]-1][flip(visited)[1]].equals(".")
					|| arr[flip(visited)[0]-1][flip(visited)[1]].equals("$"))) {
				int[] north = {flip(visited)[0]-1, flip(visited)[1]};
				if(queueContains(visited, north) && queueContains(toBeVisited, north)) {
					toBeVisited.add(north);
				}
			}
			if(flip(visited)[0]+1 < arr.length && (arr[flip(visited)[0]+1][flip(visited)[1]].equals(".")
					|| arr[flip(visited)[0]+1][flip(visited)[1]].equals("$"))) {
				int[] south = {flip(visited)[0]+1, flip(visited)[1]};
				if(queueContains(visited, south) && queueContains(toBeVisited, south)) {
					toBeVisited.add(south);
				}
			}
			if(flip(visited)[1]+1 < arr[0].length && (arr[flip(visited)[0]][flip(visited)[1]+1].equals(".")
					|| arr[flip(visited)[0]][flip(visited)[1]+1].equals("$"))) {
				int[] east = {flip(visited)[0], flip(visited)[1]+1};
				if(queueContains(visited, east) && queueContains(toBeVisited, east)) {
					toBeVisited.add(east);
				}
			}
			if(flip(visited)[1]-1 >= 0 && (arr[flip(visited)[0]][flip(visited)[1]-1].equals(".")
					|| arr[flip(visited)[0]][flip(visited)[1]-1].equals("$"))) {
				int[] west = {flip(visited)[0], flip(visited)[1]-1};
				if(queueContains(visited, west) && queueContains(toBeVisited, west)) {
					toBeVisited.add(west);
				}
			}
	
		}
		
		for(int[] i: visited) {
			arr2[i[0]][i[1]] = "+";
		}
		long tEnd = System.nanoTime();
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0; j < arr2[0].length; j++) {
				System.out.print(arr2[i][j]);
			}
			System.out.println();
		}
		if(time) {
			double runtime = tEnd - tStart;
			System.out.println("Total Runtime: " + runtime/1000000000 + " seconds");
		}
	}
	public static int[] flip(Queue<int[]> bro) { //for queues
		int[] old = new int[2];
		for(int[] i: bro) {
			old = i;
		}
		return old;
	}
	
	public static boolean queueContains(Queue<int[]> visited, int[] pos) {
		for(int[] i: visited) {
			if(Arrays.equals(i, pos)){
				return false;
			}
		}
		return true;
	}
	
	public static void stackMaze(String[][] arr, boolean time){
		int[] start = new int[2];
		String[][] arr2 = new String[arr.length][arr[0].length];
		for(int r = 0; r < arr.length; r++) {
			for(int c = 0; c < arr[0].length; c++) {
				arr2[r][c] = arr[r][c];
				if(arr2[r][c].equals("W")) {
					start[0] = r;
					start[1] = c;
				}
			}
		}
		long tStart = System.nanoTime();
		Stack<int[]> toBeVisited = new Stack<int[]>();
		Stack<int[]> visited = new Stack<int[]>();
		toBeVisited.push(start);
		boolean whatever = true;
		while(whatever) {
			if(!visited.isEmpty() && arr[visited.peek()[0]][visited.peek()[1]].equals("$")) {
				whatever = false;
				break;
			}
				visited.push(toBeVisited.peek());
				toBeVisited.pop();
			if(visited.peek()[1]-1 >= 0 && (arr[visited.peek()[0]][visited.peek()[1]-1].equals(".")
					|| arr[visited.peek()[0]][visited.peek()[1]-1].equals("$"))) {
				int[] west = {visited.peek()[0], visited.peek()[1]-1};
				if(stackContains(visited, west) && stackContains(toBeVisited, west)) {
					toBeVisited.push(west);
				}
			}
			if(visited.peek()[1]+1 < arr[0].length && (arr[visited.peek()[0]][visited.peek()[1]+1].equals(".")
					|| arr[visited.peek()[0]][visited.peek()[1]+1].equals("$"))) {
				int[] east = {visited.peek()[0], visited.peek()[1]+1};
				if(stackContains(visited, east) && stackContains(toBeVisited, east)) {
					toBeVisited.push(east);
				}
			}
			if(visited.peek()[0]+1 < arr.length && (arr[visited.peek()[0]+1][visited.peek()[1]].equals(".")
					|| arr[visited.peek()[0]+1][visited.peek()[1]].equals("$"))) {
				int[] south = {visited.peek()[0]+1, visited.peek()[1]};
				if(stackContains(visited, south) && stackContains(toBeVisited, south)) {
					toBeVisited.push(south);
				}
			}
			if(visited.peek()[0]-1 >= 0 && (arr[visited.peek()[0]-1][visited.peek()[1]].equals(".")
					|| arr[visited.peek()[0]-1][visited.peek()[1]].equals("$"))) {
				int[] north = {visited.peek()[0]-1, visited.peek()[1]};
				if(stackContains(visited, north) && stackContains(toBeVisited, north)) {
					toBeVisited.push(north);
				}
			}
		}
		for(int[] i: visited) {
			arr2[i[0]][i[1]] = "+";
		}
		long tEnd = System.nanoTime();
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0; j < arr2[0].length; j++) {
				System.out.print(arr2[i][j]);
			}
			System.out.println();
		}
		if(time) {
			double runtime = tEnd - tStart;
			System.out.println("Total Runtime: " + runtime/1000000000 + " seconds");
		}
	}
	public static boolean stackContains(Stack<int[]> visited, int[] pos) {
		for(int[] i: visited) {
			if(Arrays.equals(i, pos)){
				return false;
			}
		}
		return true;
	}
	public static int[] sFlip(Stack<int[]> bro) { //to add the oldest element of stack
		int[] old = new int[2];
		for(int[] i: bro) {
			old = i;
		}
		return old;
	}
	
	public static Stack<int[]> removeLast(Stack<int[]> real){
		Stack<int[]> temp = new Stack<int[]>();
		while (real.size() > 1) {
			temp.push(real.pop());
		}
		real.pop();
		while (!temp.isEmpty()) {
			real.push(temp.pop());
		}
		return real;
	}
	
	public static void optimal(String[][] pos, boolean time){
		int xFinal = 0;
		int yFinal = 0;
		Position start2 = null;
		Position[][] arr2 = new Position[pos.length][pos[0].length];
		for(int r = 0; r < pos.length; r++) {
			for(int c = 0; c < pos[0].length; c++) {
				arr2[r][c] = new Position(c, r, pos[r][c]);
				if(pos[r][c].equals("W")) {
					start2 = arr2[r][c];
				}
			}
		}
		long tStart = System.nanoTime();
		Queue<Position> toBeVisited = new LinkedList<Position>();
		Queue<Position> visited = new LinkedList<Position>();
		toBeVisited.add(start2);
		boolean whatever = true;
		while(whatever) {
			Position current = toBeVisited.peek();
			visited.add(toBeVisited.remove());
			if(current.getVal().equals("$")) {
				xFinal = current.getX();
				yFinal = current.getY();
				whatever = false;
				break;
			}
			if(current.getY()-1 >= 0 && (arr2[current.getY()-1][current.getX()].getVal().equals(".")
					|| arr2[current.getY()-1][current.getX()].getVal().equals("$"))) {
				Position north = arr2[current.getY()-1][current.getX()];
				if(optCont(visited, north) && optCont(toBeVisited, north)) {
					north.setPrev(current);
					toBeVisited.add(north);
				}
			}
			if(current.getY()+1 < arr2.length && (arr2[current.getY()+1][current.getX()].getVal().equals(".")
					|| arr2[current.getY()+1][current.getX()].getVal().equals("$"))) {
				Position south = arr2[current.getY()+1][current.getX()];
				if(optCont(visited, south) && optCont(toBeVisited, south)) {
					south.setPrev(current);
					toBeVisited.add(south);
				}
			}
			if(current.getX()+1 < arr2[0].length && (arr2[current.getY()][current.getX()+1].getVal().equals(".")
					|| arr2[current.getY()][current.getX()+1].getVal().equals("$"))) {
				Position east = arr2[current.getY()][current.getX()+1];
				if(optCont(visited, east) && optCont(toBeVisited, east)) {
					east.setPrev(current);
					toBeVisited.add(east);
				}
			}
			if(current.getX()-1 >= 0 && (arr2[current.getY()][current.getX()-1].getVal().equals(".")
					|| arr2[current.getY()][current.getX()-1].getVal().equals("$"))) {
				Position west = arr2[current.getY()][current.getX()-1];
				if(optCont(visited, west) && optCont(toBeVisited, west)) {
					west.setPrev(current);
					toBeVisited.add(west);
				}
			}
		}
		Position current = arr2[yFinal][xFinal];
		while(current.getPrev() != null){
			arr2[current.getY()][current.getX()].setVal("+");
			current = current.getPrev();
		}
		long tEnd = System.nanoTime();
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0; j < arr2[0].length; j++) {
				System.out.print(arr2[i][j].getVal());
			}
			System.out.println();
		}
		if(time) {
			double runtime = tEnd - tStart;
			System.out.println("Total Runtime: " + runtime/1000000000 + " seconds");
		}
	}
	public static boolean optCont(Queue<Position> visited, Position pos) {
		for(Position i: visited) {
			if(pos.equals(i)){
				return false;
			}
		}
		return true;
	} 
}


