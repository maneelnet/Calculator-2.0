package com.maneelnet;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        String arithmeticOperation = scanner.nextLine();
        Calculator calculator = new Calculator(arithmeticOperation);
        if (!calculator.isValidate()) {
            return;
        }
        calculator.calculate();
    }

}
