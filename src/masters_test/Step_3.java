package masters_test;

import java.util.Scanner;

public class Step_3 {
// Q 입력시 강제종료
	static boolean command_Q(String command, long start, int n) {
		if (command.equals("Q")) {
			long end = System.currentTimeMillis();
			System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
			System.out.println("조작갯수: " + n);
			System.out.println("이용해주셔서 감사합니다!");
			return false;
		} else {
			return true;
		}
	}
// 명령어 분류
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
		char[][][] cube = new char[6][3][3];
		char[][][] initCube = new char[6][3][3];
		int n = 0;
		int num = 0;
		boolean b = true;
		long start = System.currentTimeMillis(); // 시작하는 시점 계산
		Cube.getCube(initCube);
		Cube.getCube(cube);
		System.out.print("큐브를 몇번 섞으시겠습니까?");
		num = scanner.nextInt();
		Cube.shuffle(cube, num); // 큐브 섞기
		scanner.nextLine();
		Cube.print(cube);
		while (b) {
			System.out.printf("CUBE> "); // 프롬프트
			String input = scanner.nextLine();
			String[] command = getCommand(input); // 명령어 분류
			b = command_Q(command[0], start, n);// Q입력시 종료
			if (b != false) {
				Cube.commandTheCube(command, initCube, cube, b, start, n); // 회전 명령
			}
		};
		scanner.close();
	}
}
