package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private display displayFragment;
    double firstNum;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayFragment = (display) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);

        initializeButtons();
    }

    private void initializeButtons() {
        Button num0 = findViewById(R.id.zero);
        Button num1 = findViewById(R.id.one);
        Button num2 = findViewById(R.id.two);
        Button num3 = findViewById(R.id.three);
        Button num4 = findViewById(R.id.four);
        Button num5 = findViewById(R.id.five);
        Button num6 = findViewById(R.id.six);
        Button num7 = findViewById(R.id.seven);
        Button num8 = findViewById(R.id.eight);
        Button num9 = findViewById(R.id.nine);
        Button zeroes = findViewById(R.id.zeroes);

        Button AC = findViewById(R.id.AC);
        Button clear = findViewById(R.id.clear);
        Button divide = findViewById(R.id.divide);
        Button multiply = findViewById(R.id.multiply);
        Button minus = findViewById(R.id.minus);
        Button plus = findViewById(R.id.plus);
        Button equal = findViewById(R.id.equal);
        Button dot = findViewById(R.id.dot);
        Button modulus = findViewById(R.id.modulus);

        AC.setOnClickListener(view -> {
            firstNum = 0;
            operation = null;
            displayFragment.clearText();
        });

        num0.setOnClickListener(view -> {
            String expression = displayFragment.getText();

            Pattern pattern = Pattern.compile("[+\\-*/%]");
            String[] parts = pattern.split(expression);

            if (parts.length == 2) {
                if (!parts[1].startsWith("0") && !parts[1].startsWith("00")) {
                    displayFragment.appendText("0");
                }
            } else if (parts.length == 1) {
                if (!parts[0].startsWith("0") && !parts[0].startsWith("00")) {
                    displayFragment.appendText("0");
                }
            }
        });

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        for(Button b:nums){
            b.setOnClickListener(view->{
                displayFragment.appendText(b.getText().toString());
            });
        }

        ArrayList<Button> oper = new ArrayList<>();
        oper.add(divide);
        oper.add(multiply);
        oper.add(minus);
        oper.add(plus);
        oper.add(modulus);

        for(Button b:oper){
            b.setOnClickListener(view->{
                firstNum = Double.parseDouble(displayFragment.getText().toString());
                operation = b.getText().toString();
                displayFragment.setExpression(displayFragment.getText()+operation);
            });
        }

        equal.setOnClickListener(view->{
            Log.i("initializeButtons: ", displayFragment.getText());
            String expression = displayFragment.getText();
            if (expression.isEmpty()) {
                return; // No expression to evaluate
            }

            Pattern pattern = Pattern.compile("[+\\-*/%]");
            String[] parts = pattern.split(expression);

//            Log.i("initializeButtons: ", Arrays.toString(parts));
            double secondNum = Double.parseDouble(parts[1]);

            double result = calculateResult(firstNum, secondNum, operation);
            displayFragment.setResult(String.valueOf(result));
            firstNum = result;
        });


        dot.setOnClickListener(view -> {
            String expression = displayFragment.getText();

            Pattern pattern = Pattern.compile("[+\\-*/%]");
            String[] parts = pattern.split(expression);

            if (parts.length == 2) {
                if (!parts[1].contains(".")) {
                    displayFragment.appendText(".");
                }
            } else if (parts.length == 1) {
                if (!parts[0].contains(".")) {
                    displayFragment.appendText(".");
                }
            }
        });

        zeroes.setOnClickListener(view -> {
            String expression = displayFragment.getText();

            Pattern pattern = Pattern.compile("[+\\-*/%]");
            String[] parts = pattern.split(expression);

            if (parts.length == 2) {
                if (!parts[1].startsWith("0") && !parts[1].startsWith("00")) {
                    displayFragment.appendText("00");
                }
            } else if (parts.length == 1) {
                if (!parts[0].startsWith("0") && !parts[0].startsWith("00")) {
                    displayFragment.appendText("00");
                }
            }
        });

        clear.setOnClickListener(view->{
            String num=displayFragment.getText().toString();
            if(num.length()>1){
                displayFragment.setText(num.substring(0,num.length()-1));
            }else if(num.length() == 1 && !num.equals("0")){
                displayFragment.setText("0");
            }
        });
    }

    private double calculateResult(double num1, double num2, String operation) {
        switch (operation) {
            case "/":
                return num1 / num2;
            case "X":
                return num1 * num2;
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "%":
                return (num1 / 100) * num2;
            default:
                return 0;
        }
    }
}
