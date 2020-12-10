package masters_test;

import java.util.Scanner;

public class Step_2 {
	static char[][] rotate(char[][] cube, String command) {
		switch (command) {
		case "U":
			upperLeft(cube, command);
			break;
		case "U'":
			upperRight(cube, command);
			break;
		case "R":
			rightUp(cube, command);
			break;
		case "R'":
			rightDown(cube, command);
			break;
		case "L":
			leftDown(cube, command);
			break;
		case "L'":
			leftUp(cube, command);
			break;
		case "B":
			bottomRight(cube, command);
			break;
		case "B'":
			bottomLeft(cube, command);
			break;
		}
		return cube;
	}

	static char[][] upperRight(char[][] cube, String command) {
		char temp = cube[0][2];
		for (int i = 2; i >= 1; i--) {
			cube[0][i] = cube[0][i - 1];
		}
		cube[0][0] = temp;
		return cube;
	}

	static char[][] upperLeft(char[][] cube, String command) {
		char temp = cube[0][0];
		for (int i = 0; i <= 1; i++) {
			cube[0][i] = cube[0][i + 1];
		}
		cube[0][2] = temp;
		return cube;
	}

	static char[][] bottomRight(char[][] cube, String command) {
		char temp = cube[2][2];
		for (int i = 2; i >= 1; i--) {
			cube[2][i] = cube[2][i - 1];
		}
		cube[2][0] = temp;
		return cube;
	}

	static char[][] bottomLeft(char[][] cube, String command) {
		char temp = cube[2][0];
		for (int i = 0; i <= 1; i++) {
			cube[2][i] = cube[2][i + 1];
		}
		cube[2][2] = temp;
		return cube;
	}

	static char[][] rightUp(char[][] cube, String command) {
		char temp = cube[0][2];
		for (int i = 0; i <= 1; i++) {
			cube[i][2] = cube[i + 1][2];
		}
		cube[2][2] = temp;
		return cube;
	}

	static char[][] rightDown(char[][] cube, String command) {
		char temp = cube[2][2];
		for (int i = 2; i >= 1; i--) {
			cube[i][2] = cube[i - 1][2];
		}
		cube[0][2] = temp;
		return cube;
	}

	static char[][] leftUp(char[][] cube, String command) {
		char temp = cube[0][0];
		for (int i = 0; i <= 1; i++) {
			cube[i][0] = cube[i + 1][0];
		}
		cube[2][0] = temp;
		return cube;
	}

	static char[][] leftDown(char[][] cube, String command) {
		char temp = cube[2][0];
		for (int i = 2; i >= 1; i--) {
			cube[i][0] = cube[i - 1][0];
		}
		cube[0][0] = temp;
		return cube;
	}

	static void print(char[][] cube) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.printf("%c ", cube[i][j]);
			}
			System.out.println();
		}
	}

	static String[] getCommand(String input) {
		String[] inputSplit = input.split("(?!^)");
		String[] command = new String[inputSplit.length];
		for (int i = 0; i < command.length; i++) {
			if (i == command.length - 1) {
				command[i] = inputSplit[i];
			} else {
				if (inputSplit[i + 1].equals("'")) {
					command[i] = inputSplit[i] + inputSplit[i + 1];
				} else {
					command[i] = inputSplit[i];
				}
			}
		}
		return command;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[][] cubeState = { { 'R', 'R', 'W' }, { 'G', 'C', 'W' }, { 'G', 'B', 'B' } };
		print(cubeState);
		while (true) {
			System.out.printf("CUBE> ");
			String input = scanner.nextLine();
			String[] command = getCommand(input);
			if (command[0].equals("Q")) {
				System.out.println("Bye~");
				break;
			}
			for (int i = 0; i < command.length; i++) {
				if (!command[i].equals("'")) {
					System.out.println(command[i]);
					cubeState = rotate(cubeState, command[i]);
					print(cubeState);
					System.out.println();
				}
			}

		}
		scanner.close();
	}
}
