package masters_test;

import java.util.Scanner;
public class Step_1 {
	public static char[] rightPush(char[] word, int pushNum, char direction) {
		char temp = 'a';
		for (int i = 0; i < pushNum; i++) {
			temp = word[word.length - 1];
			for (int j = word.length - 1; j >= 1; j--) {
				word[j] = word[j - 1];
			}
			word[0] = temp;
		}
		return word;
	}
	
	public static char[] leftPush(char[] word, int pushNum, char direction) {
		char temp = 'a';
		for (int i = 0; i < pushNum; i++) {
			temp = word[0];
			for (int j = 0; j < word.length - 1; j++) {
				word[j] = word[j + 1];
			}
			word[word.length - 1] = temp;
		}
		return word;
	}

	public static char[] push(char[] word, int pushNum, char direction) {

		if (direction == 'R' | direction =='r') {
			if (pushNum >= 0) {
				rightPush(word, pushNum, direction);
			} else {
				leftPush(word, -pushNum, direction);
			}

		} else if (direction == 'L' | direction == 'l') {
			if (pushNum >= 0) {
				leftPush(word, pushNum, direction);
			} else {
				rightPush(word, -pushNum, direction);
			}
		}
		return word;
	}

	public static void print(char[] word) {
		for (int i = 0; i < word.length; i++) {
			System.out.printf("%c", word[i]);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("> ");
		String input = scanner.nextLine();
		// 명령어 분류
		String[] splitInput = input.split(" ");
		char[] word = splitInput[0].toCharArray();
		int pushNum = Integer.parseInt(splitInput[1]);
		char direction = splitInput[2].charAt(0);

		if (pushNum >= -100 | pushNum < 100) {
			push(word, pushNum, direction);
			print(word);
		}
		scanner.close();
	}

	}
