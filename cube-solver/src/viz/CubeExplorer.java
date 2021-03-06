package viz;

import java.util.*;

import cube.Algorithm;
import cube.Cube;
import cube.Move;

/**
 * Used to run the CubeSolver through terminal
 * @author Brad Hodkinson
 */
public class CubeExplorer {

	private static void displayCubeUsageMessage() {
		System.out.println("\nType \"scramble\" to mix the cube up");
		System.out.println("Type \"solve\" to solve the cube");
		System.out.println("Type \"new\" to generate a new cube");
		System.out.println("Type \"size\" to change the size of the cube");
		System.out.println("Type \"usage\" to view usage message");
		System.out.println("Type \"quit\" to quit Cube Explorer\n");
		
		
		System.out.println("Enter valid notation to twist the cube. Notation listed below:");
		String[] faces = new String[]{"up", "right", "front", "down", "left", "back"};
		for(String face: faces) {
			System.out.print(face.toUpperCase().charAt(0) + " - rotate " + face + " face clockwise\t\t");
			System.out.println(face.toUpperCase().charAt(0) + "\' - rotate " + face + " face counter-clockwise");
		}
		System.out.println();
	}
	
	private static int getUsersCubeSize(Scanner keyBoard ) {
		String input = "";
		boolean validInput = false;
		while(!validInput) {
			System.out.print("Enter a cube size: ");
			input = keyBoard.nextLine();
			validInput = isValidCubeSize(input);
			if(!validInput) {
				System.out.println("Invalid input. Please enter a number between 1 and 17");
			}
		}
		return Integer.parseInt(input);
		
	}
	
	
	
	private static boolean isValidCubeSize(String input) {
		System.out.println(input);
		try {
			int cubeSize = Integer.parseInt(input);	
			return cubeSize >= 1 || cubeSize <= 17;

		} catch (NumberFormatException ex) {
			return  false;
		}
	}
	
	
	
	private static Cube scrambleCube(Cube cube) {
		int cubeSize = cube.getSize();
		int scrambleLength = 20;
		Algorithm scramble = new Algorithm();
		scramble.generateScramble(cubeSize, scrambleLength);
		cube.applyAlgorithm(scramble);
		return cube;
	}
	
	
	/**
	 * Takes user input and 
	 * @param cube
	 */
	private static void startExplorer(Cube cube) {
		Scanner keyBoard = new Scanner(System.in);
		String input = keyBoard.nextLine();
		boolean cubeChanged = false;
		
		while(!input.equals("quit")) {
			if(input.equals("scramble")) {
				cube = scrambleCube(cube);
				cubeChanged = true;
			}
			else if(input.equals("solve")) {
				System.out.println("Sorry, this feature is not yet available");
				System.out.println("To generate a new cube type \"new\"\n");
				
			}
			else if(input.equals("new")) {
				int cubeSize = cube.getSize();
				cube = new Cube(cubeSize);
				cubeChanged = false;
			}
			else if(input.equals("size")) {
				int cubeSize = getUsersCubeSize(keyBoard);
				cube = new Cube(cubeSize);
				cubeChanged = false;
			}
			else if(input.equals("usage")) {
				displayCubeUsageMessage();
			}
			
			else {
				boolean isValidMove = Move.isValidMove(input, cube.getSize());
				if(isValidMove) {
					cube.rotate(new Move(input));
					cubeChanged = true;
				}
				else {
					System.out.println("Not a valid move. Type \"usage\" to view program usage");
				}
				
			}
			cubeChanged = didSolve(cube, cubeChanged);
			System.out.println(cube);
			input = keyBoard.nextLine();
		}
		
		System.out.println("User Quit");
	}
	
	
	
	public static boolean didSolve(Cube cube, boolean cubeChanged) {
		if(cubeChanged) {
			if(cube.isSolved()) {
				System.out.println("CONGRATS, YOU SOLVED THE CUBE");
				cubeChanged =false;
			}
		}
		return cubeChanged;
	}
}
