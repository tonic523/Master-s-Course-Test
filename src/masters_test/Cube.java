package masters_test;

import java.util.Objects;
import java.util.Random;

public class Cube {
	static final int B = 0, W = 1, O = 2, G = 3, Y = 4, R = 5;

	// 큐브 생성 함수
	static char[][][] getCube(char cube[][][]) {
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

	// 큐브 front 방향 회전
	static void rotateFrontColor(char[][][] cube, int c) {
		char[] temp = new char[3];
		for (int i = 0; i < 2; i++) {
			temp[i] = cube[c][0][i];
			cube[c][0][i] = cube[c][2 - i][0];
			cube[c][2 - i][0] = cube[c][2][2 - i];
			cube[c][2][2 - i] = cube[c][i][2];
			cube[c][i][2] = temp[i];
		}
	}

	// 큐브 섞기
	static void shuffle(char[][][] cube, int num) {
		Random random = new Random();
		int ranNum = random.nextInt(5) + 1;
		for (int i = 0; i < num; i++) {
			switch (ranNum % 7) {
			case 0:
				rotateU(cube);
				break;
			case 1:
				rotateL(cube);
				break;
			case 2:
				rotateF(cube);
				break;
			case 3:
				rotateR(cube);
				break;
			case 4:
				rotateB(cube);
				break;
			case 5:
				rotateD(cube);
				break;
			}
			ranNum = random.nextInt(5) + 1;
		}
	}

	// F명령어 회전
	static void rotateF(char[][][] cube) {
		char[] temp = new char[3];
		rotateFrontColor(cube, O);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[B][2][i];
			cube[B][2][i] = cube[W][2 - i][2];
			cube[W][2 - i][2] = cube[R][0][2 - i];
			cube[R][0][2 - i] = cube[G][i][0];
			cube[G][i][0] = temp[i];
		}
	}

	// R명령어 회전
	static void rotateR(char[][][] cube) {
		char[] temp = new char[3];
		rotateFrontColor(cube, G);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[B][2 - i][2];
			cube[B][2 - i][2] = cube[O][2 - i][2];
			cube[O][2 - i][2] = cube[R][2 - i][2];
			cube[R][2 - i][2] = cube[Y][i][0];
			cube[Y][i][0] = temp[i];
		}
	}

	// L명령어 회전
	static void rotateL(char[][][] cube) {
		char[] temp = new char[3];
		rotateFrontColor(cube, W);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[B][i][0];
			cube[B][i][0] = cube[Y][2 - i][2];
			cube[Y][2 - i][2] = cube[R][i][0];
			cube[R][i][0] = cube[O][i][0];
			cube[O][i][0] = temp[i];
		}
	}

	// U명령어 회전
	static void rotateU(char[][][] cube) {
		char[] temp = new char[3];
		rotateFrontColor(cube, B);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[Y][0][2 - i];
			cube[Y][0][2 - i] = cube[W][0][2 - i];
			cube[W][0][2 - i] = cube[O][0][2 - i];
			cube[O][0][2 - i] = cube[G][0][2 - i];
			cube[G][0][2 - i] = temp[i];
		}
	}

	// B명령어 회전
	static void rotateB(char[][][] cube) {
		char[] temp = new char[3];
		rotateFrontColor(cube, Y);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[B][0][2 - i];
			cube[B][0][2 - i] = cube[G][2 - i][2];
			cube[G][2 - i][2] = cube[R][2][i];
			cube[R][2][i] = cube[W][i][0];
			cube[W][i][0] = temp[i];
		}
	}

	// D명령어 회전
	static void rotateD(char[][][] cube) {
		char[] temp = new char[3];
		rotateFrontColor(cube, R);
		for (int i = 0; i < temp.length; i++) {
			temp[i] = cube[O][2][i];
			cube[O][2][i] = cube[W][2][i];
			cube[W][2][i] = cube[Y][2][i];
			cube[Y][2][i] = cube[G][2][i];
			cube[G][2][i] = temp[i];
		}
	}

	// 회전 명령어 분류
	static void rotate(String command, char[][][] cube) {
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

	// 역방향 명령어 포함
	static void commandFunction(String command, char[][][] cube) {
		String[] revSplit = command.split("(?!^)");
		if (revSplit.length == 1) {
			rotate(revSplit[0], cube);
		} else {
			for (int i = 0; i < 3; i++) {
				rotate(revSplit[0], cube);
			}
		}
	}

	// 프로그램 종료
	static boolean complete(char initCube[][][], char cube[][][], String command, long start, int n) {
		if (Objects.deepEquals(cube, initCube)) { // 큐브를 맞췄을 때 축하메시지와 함께 종료
			long end = System.currentTimeMillis();
			System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
			System.out.println("조작갯수: " + n);
			System.out.println("모든 면을 맞췄습니다!! 축하드려요^^");
			return false;
		} else {
			return true;
		}
	}

	// 큐브에 명령어 입력 수행
	static void commandTheCube(String[] command, char[][][] initCube, char[][][] cube, boolean b, long start, int n) {
		for (int i = 0; i < command.length; i++) {
			if (!command[i].equals("'")) {
				System.out.println(command[i]);
				commandFunction(command[i], cube); // 회전 명령어 실행
				print(cube);
				System.out.println();
				n++;
				b = complete(initCube, cube, command[i], start, n); // 모든 면을 맞췄는지 확인
			}
		}
	}

	// 큐브 출력
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
}
