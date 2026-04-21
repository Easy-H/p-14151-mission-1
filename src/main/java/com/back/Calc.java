package com.back;

public class Calc {
    public static int run(String exp) {
        int startAt = 0;
        return calc(exp, startAt);
    }

    private static int calc(String exp, Integer startAt) {

        int[] nums = new int[3];
        int numTop = 0;
        char[] chars = new char[2];
        char charTop = 0;
        boolean beforeChar = true;
        boolean isMinus = false;

        System.out.print(exp);

        for (int i = startAt; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (c == ' ') continue;
            if (c >= '0' && c <= '9') {

                int num = c - '0';
                if (beforeChar) {
                    nums[numTop] = 0;
                }
                nums[numTop] *= 10;
                nums[numTop] += num;
                beforeChar = false;
                continue;
            }
            if (beforeChar && c == '-') {
                nums[numTop] = 0;
                beforeChar = false;
                isMinus = true;
                continue;
            }
            if (isMinus) {
                nums[numTop] *= -1;
                isMinus = false;
            }

            if (charTop > 1) {
                nums[0] = calc(nums[0], chars[0], nums[1]);
                nums[1] = nums[2];
                chars[0] = chars[1];
                numTop--;
                charTop--;
            }

            numTop++;
            chars[charTop++] = c;
            beforeChar = true;
        }
        
        if (isMinus) {
            nums[numTop] *= -1;
        }

        while (charTop > 0) {
            nums[0] = calc(nums[0], chars[0], nums[1]);
            nums[1] = nums[2];
            chars[0] = chars[1];
            charTop--;
        }

        return nums[0];
    }

    private static int calc(int num1, char op, int num2) {
        if (op == '+') return num1 + num2;
        if (op == '-') return num1 - num2;
        if (op == '*') return num1 * num2;
        return num1 / num2;
    }
}
