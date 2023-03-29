package com.example.calculator;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorButtonConfigurator {
    Calculator calculator;
    public CalculatorButtonConfigurator () {
        this.calculator = new Calculator();
    }

    void configureSimpleOperators(Activity activity) {
        Button plus = (Button) activity.findViewById(R.id.btn_add);
        Button minus = (Button) activity.findViewById(R.id.btn_subtract);
        Button divide = (Button) activity.findViewById(R.id.btn_divide);
        Button multiply = (Button) activity.findViewById(R.id.btn_multiply);


        View.OnClickListener addOperator = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = ((Button) v).getText().toString();

                char lastChar;
                if (calculator.result.length() != 0) {
                    lastChar = calculator.result.charAt(calculator.result.length() - 1);
                    if (lastChar == '-' && buttonText.equals("-")) {
                        calculator.result = calculator.result.substring(0, calculator.result.length() - 1);
                        calculator.result += "+";
                        refreshResult(activity);
                        return;
                    }
//                    if (!Character.isDigit(lastChar) && lastChar != ')')
//                        return;
                } else if (!buttonText.equals("-")) {
                    return;
                }
                if (calculator.result.endsWith(buttonText))
                    return;

                calculator.result += buttonText;
                refreshResult(activity);
            }
        };
        plus.setOnClickListener(addOperator);
        minus.setOnClickListener(addOperator);
        divide.setOnClickListener(addOperator);
        multiply.setOnClickListener(addOperator);

        Button dot = (Button) activity.findViewById(R.id.btn_decimal);
        View.OnClickListener addDot = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculator.result.length() == 0)
                    return;

                if (canAddDecimal(calculator.result))
                    calculator.result += ".";
                else
                    return;

                refreshResult(activity);
            }
        };
        dot.setOnClickListener(addDot);

        Button clear = (Button) activity.findViewById(R.id.btn_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.result = "";
                refreshResult(activity);
            }
        });

        Button backspace = (Button) activity.findViewById(R.id.btn_backspace);
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculator.result.length() == 0)
                    return;
                calculator.result = calculator.result.substring(0, calculator.result.length() - 1);
                refreshResult(activity);
            }
        });
    }

    void refreshResult(Activity activity) {
        TextView result = (TextView) activity.findViewById(R.id.tv_result);
        result.setText(calculator.result);
    }

    boolean canAddDecimal(String expression) {
        if (expression.length() == 0)
            return false;
        char lastChar = expression.charAt(expression.length() - 1);
        if (!Character.isDigit(lastChar))
            return false;

        boolean foundDecimal = false;
        for (int i = expression.length() - 1; i >= 0; i--) {
            char currentChar = expression.charAt(i);
            if (currentChar == '.')
                foundDecimal = true;
            /* 1.2+10 */
            if (!Character.isDigit(currentChar))
                return !foundDecimal;
        }

        return true;
    }


    void configureResultButton(Activity activity) {
        Button result = (Button) activity.findViewById(R.id.btn_equals);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculator.result.length() == 0)
                    return;

                char lastChar = calculator.result.charAt(calculator.result.length() - 1);
                if (!Character.isDigit(lastChar) && lastChar != ')')
                    return;

                calculator.result = String.valueOf(calculator.evaluate());
                refreshResult(activity);
            }
        });
    }

    void configureNumberButtons(Activity activity) {
        Button zero = (Button) activity.findViewById(R.id.btn_0);
        Button one = (Button) activity.findViewById(R.id.btn_1);
        Button two = (Button) activity.findViewById(R.id.btn_2);
        Button three = (Button) activity.findViewById(R.id.btn_3);
        Button four = (Button) activity.findViewById(R.id.btn_4);
        Button five = (Button) activity.findViewById(R.id.btn_5);
        Button six = (Button) activity.findViewById(R.id.btn_6);
        Button seven = (Button) activity.findViewById(R.id.btn_7);
        Button eight = (Button) activity.findViewById(R.id.btn_8);
        Button nine = (Button) activity.findViewById(R.id.btn_9);

        View.OnClickListener addNumber = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = ((Button) v).getText().toString();
                calculator.result += buttonText;
                refreshResult(activity);
            }
        };
        zero.setOnClickListener(addNumber);
        one.setOnClickListener(addNumber);
        two.setOnClickListener(addNumber);
        three.setOnClickListener(addNumber);
        four.setOnClickListener(addNumber);
        five.setOnClickListener(addNumber);
        six.setOnClickListener(addNumber);
        seven.setOnClickListener(addNumber);
        eight.setOnClickListener(addNumber);
        nine.setOnClickListener(addNumber);
    }

    void configureAdvancedOperators(Activity activity) {
        Button squareRoot = (Button) activity.findViewById(R.id.btn_square);
        Button square = (Button) activity.findViewById(R.id.btn_expo);
        Button expo_spec = (Button) activity.findViewById(R.id.btn_spec_expo);
        Button log = (Button) activity.findViewById(R.id.btn_log);
        Button ln = (Button) activity.findViewById(R.id.btn_ln);
        Button sin = (Button) activity.findViewById(R.id.btn_sin);
        Button cos = (Button) activity.findViewById(R.id.btn_cos);
        Button tan = (Button) activity.findViewById(R.id.btn_tan);

        View.OnClickListener addOperator = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculator.result.length() == 0)
                    return;

                String lastNumber = getAndRemoveLastNumber(calculator.result);
                if (lastNumber == null)
                    return;

                String buttonText = ((Button) v).getText().toString();
                if (!buttonText.equals("^"))
                    calculator.result = calculator.result.substring(0, calculator.result.length() - lastNumber.length());

                if (buttonText.equals("x²")) {
                    calculator.result += lastNumber + "^2";
                } else if (buttonText.equals("^")) {
                    calculator.result += "^";
                } else {
                    calculator.result += buttonText + "(" + lastNumber + ")";
                }

                refreshResult(activity);
            }
        };
        squareRoot.setOnClickListener(addOperator);
        square.setOnClickListener(addOperator);
        expo_spec.setOnClickListener(addOperator);
        log.setOnClickListener(addOperator);
        ln.setOnClickListener(addOperator);
        sin.setOnClickListener(addOperator);
        cos.setOnClickListener(addOperator);
        tan.setOnClickListener(addOperator);
    }

    String getAndRemoveLastNumber(String expression) {
        if (expression.length() == 0)
            return "";

        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(expression);
        String lastNumber = null;

        while (matcher.find())
            lastNumber = matcher.group();

        return lastNumber;
    }
}
