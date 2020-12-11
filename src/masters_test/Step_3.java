package masters_test;

import java.util.Scanner;

public class Step_3 {
	static final int B = 0, W = 1, O = 2, G = 3, Y = 4, R = 5;

	static char[][][] initCube(char cube[][][]) {
		for (int j = 0; j < 3; j++) {
			for (int j2 = 0; j2 < 3; j2++) {
				cube[B][j][j2] = 'B';
				cube[W][j][j2] = 'W';
				cube[O][j][j2] = 'O';
				cube[G][j][j2] = 'G';
				cube[Y][j][j2] = 'Y';
				cube[R][j][j2] = 'R';
			}
		}
		return cube;
	}

	static void standardColor(char[][][] cube, int c) {
		char[] temp = new char[3];
		for (int i = 0; i < 2; i++) {
			temp[i] = cube[c][0][i];
			cube[c][0][i] = cube[c][2 - i][0];
			cube[c][2 - i][0] = cube[c][2][2 - i];
			cube[c][2][2 - i] = cube[c][i][2];
			cube[c][i][2] = temp[i];
		}
	}

	static void rotateF(char[][][] cube) {
		char[] temp = new char[3];
		standardColor(cube, O);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[B][2][i];
			cube[B][2][i] = cube[W][2 - i][2];
			cube[W][2 - i][2] = cube[R][0][2 - i];
			cube[R][0][2 - i] = cube[G][i][0];
			cube[G][i][0] = temp[i];
		}
	}

	static void rotateR(char[][][] cube) {
		char[] temp = new char[3];
		standardColor(cube, G);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[B][2 - i][2];
			cube[B][2 - i][2] = cube[O][2 - i][2];
			cube[O][2 - i][2] = cube[R][2 - i][2];
			cube[R][2 - i][2] = cube[Y][i][0];
			cube[Y][i][0] = temp[i];
		}
	}

	static void rotateL(char[][][] cube) {
		char[] temp = new char[3];
		standardColor(cube, W);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[B][i][0];
			cube[B][i][0] = cube[Y][2 - i][2];
			cube[Y][2 - i][2] = cube[R][i][0];
			cube[R][i][0] = cube[O][i][0];
			cube[O][i][0] = temp[i];
		}
	}

	static void rotateU(char[][][] cube) {
		char[] temp = new char[3];
		standardColor(cube, B);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[Y][0][2 - i];
			cube[Y][0][2 - i] = cube[W][0][2 - i];
			cube[W][0][2 - i] = cube[O][0][2 - i];
			cube[O][0][2 - i] = cube[G][0][2 - i];
			cube[G][0][2 - i] = temp[i];
		}
	}

	static void rotateB(char[][][] cube) {
		char[] temp = new char[3];
		standardColor(cube, Y);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[B][0][2 - i];
			cube[B][0][2 - i] = cube[G][2 - i][2];
			cube[G][2 - i][2] = cube[R][2][i];
			cube[R][2][i] = cube[W][i][0];
			cube[W][i][0] = temp[i];
		}
	}

	static void rotateD(char[][][] cube) {
		char[] temp = new char[3];
		standardColor(cube, R);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[O][2][i];
			cube[O][2][i] = cube[W][2][i];
			cube[W][2][i] = cube[Y][2][i];
			cube[Y][2][i] = cube[G][2][i];
			cube[G][2][i] = temp[i];
		}
	}

	static void commandTheCube(String command, char[][][] cube) {
		switch (command) {
		case "U":
			rotateU(cube);
			break;
		case "L":
			rotateL(cube);
			break;
		case "F":
			rotateF(cube);
			break;
		case "R":
			rotateR(cube);
			break;
		case "B":
			rotateB(cube);
			break;
		case "D":
			rotateD(cube);
			break;
		}
	}

	static void print(char cube[][][]) {
		for (int j = 0; j < 3; j++) {
			System.out.print("      ");
			for (int j2 = 0; j2 < 3; j2++) {
				System.out.print(cube[0][j][j2]);
			}
			System.out.println();
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 1; j < 5; j++) {
				for (int j2 = 0; j2 < 3; j2++) {
					System.out.print(cube[j][i][j2]);
				}
				System.out.print("   ");
			}
			System.out.println();
		}
		for (int j = 0; j < 3; j++) {
			System.out.print("      ");
			for (int j2 = 0; j2 < 3; j2++) {
				System.out.print(cube[5][j][j2]);
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
		char[][][] cube = new char[6][3][3];
		int n = 0;
		cube = initCube(cube);
		print(cube);
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.printf("CUBE> ");
			String input = scanner.nextLine();
			String[] command = getCommand(input);
			if (command[0].equals("Q")) {
				System.out.println("조작갯수: " + n);
				System.out.println("이용해주셔서 감사합니다!");
				break;
			}
			for (int i = 0; i < command.length; i++) {
				if (!command[i].equals("'")) {
					System.out.println(command[i]);
					commandTheCube(command[i],cube);
					print(cube);
					System.out.println();
					n++;
				}
			}

		}
		scanner.close();
	}

}
