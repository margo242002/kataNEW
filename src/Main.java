import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение (например, 1 + 2): ");
        String input = scanner.nextLine();

        String[] parts = input.split(" ");

        if (parts.length != 3) {
            System.out.println("Неверный формат ввода");
            return;
        }
        if ((isRoman(parts[0]) && !isRoman(parts[2])) || (!isRoman(parts[0]) && isRoman(parts[2]))) {
            System.out.println("Используются одновременно разные системы счисления");
            return;
        }
        int num1;
        int num2;
        num1 = parseOperand(parts[0]);
        num2 = parseOperand(parts[2]);

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10){
            System.out.println("Числа должны быть от 1 до 10");
            return;}

        String operator = parts[1];
        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                System.out.println("Неверный оператор");
                return;
        }
        if (isRoman(parts[0]) && isRoman(parts[2]) && result < 0) {
            System.out.println("В римской системе нет отрицательных чисел");
            return;
        }
        System.out.println("Результат: " + result);
    }

    private static int parseOperand(String operand) {
        if (isRoman(operand)) {
            return romanToArabic(operand);
        } else {
            return Integer.parseInt(operand);
        }
    }

    private static boolean isRoman(String str) {
        return str.matches("^[IVXLC]+$");
    }

    private static int romanToArabic(String roman) {
        Map<Character, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);
        romanNumerals.put('C', 100);

        int result = 0;
        int prevValue = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanNumerals.get(roman.charAt(i));
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }
        return result;
    }
}