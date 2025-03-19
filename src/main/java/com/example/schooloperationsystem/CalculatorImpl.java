package com.example.schooloperationsystem;


public class CalculatorImpl implements Calculator {
    @Override
    public String sum(String a, String b) {
        try {
            int num1 = Integer.parseInt(a);
            int num2 = Integer.parseInt(b);
            return Integer.toString(num1 + num2);
        } catch (Exception e) {
            return "NaN";
        }
    }
}
