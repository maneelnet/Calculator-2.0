package com.maneelnet;

import java.util.ArrayList;
import java.util.Collections;

public class Calculator {
    final private String arithmeticOperation;
    private int result = 0;
    private int index = 1;
    private static ArrayList<Integer> numbers = new ArrayList<>();
    private static ArrayList<String> operands = new ArrayList<>();
    private String[] operationArray;

    public Calculator(String arithmeticOperation) {
        this.arithmeticOperation = arithmeticOperation;
    }

    //Evaluation of an arithmetic operation
    public void calculate() {
        result = numbers.get(0);
        for (String operand: operands) {
            if (operand.equals("-")) {
                result = result - numbers.get(index);
            } else if (operand.equals("+")) {
                result = result + numbers.get(index);
            } else if (operand.equals("*")) {
                result = result * numbers.get(index);
            } else if (operand.equals("/")) {
                result = result / numbers.get(index);
            }
            index++;
        }
        System.out.println(result);
    }

    //Filling arrays of numbers and operands with data
    private static void fillArrays(String[] operationArray) {
        for (int i = 0; i < operationArray.length; i++) {
            if (i % 2 == 0) {
                numbers.add(Integer.parseInt(operationArray[i]));
            } else {
                operands.add(operationArray[i]);
            }
        }
    }

    //Order of evaluation of an arithmetic operation
    private static void calculationOrder() {
        if (operands.size() > 1) {
            if (operands.get(0).equals("+")
                    && (operands.get(1).equals("*") || operands.get(1).equals("/"))) {
                Collections.swap(operands, 0, 1);
                Collections.swap(numbers, 0, 2);
            } else if (operands.get(0).equals("-")
                    && (operands.get(1).equals("*") || operands.get(1).equals("/"))) {
                operands.set(0,"+");
                numbers.set(2,numbers.get(2) * -1);
                Collections.swap(operands, 0, 1);
                Collections.swap(numbers, 0, 2);
            }
        }
    }

    //Checking the correctness of the entered data
    public boolean isValidate() {
        if (isArithmeticCorrect(arithmeticOperation)) {
            this.operationArray = arithmeticOperation.trim().split(" ");
            fillArrays(operationArray);
            calculationOrder();
        } else {
            return false;
        }
        if (isAmountOperationsCorrect(operands) && isNumbersCorrect(numbers)) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean isArithmeticCorrect(String arithmetic) {
        String str = arithmetic.replaceAll("[0-9*+-/ ]+", "");
        if (str.length() > 0) {
            System.out.println("Your arithmetic operation isn't correct. Use only numbers and symbols (+, -, *, /).");
            return false;
        }
        return true;
    }
    private static boolean isAmountOperationsCorrect(ArrayList<String> operands) {
        if (operands.size() > 2) {
            System.out.println("Number of operations should not exceed 2!");
            return false;
        }
        return true;
    }
    private static boolean isNumbersCorrect (ArrayList<Integer> numbers) {
        if (numbers.size() > 3) {
            return false;
        }
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) > 10 || numbers.get(i) < -10) {
                System.out.println("Numbers can not be bigger than 10 and lower than -10");
                return false;
            }
        }
        return true;
    }
}
