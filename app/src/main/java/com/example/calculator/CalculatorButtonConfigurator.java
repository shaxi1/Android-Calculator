package com.example.calculator;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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
                    if (!Character.isDigit(lastChar))
                        return;
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
                if (!Character.isDigit(lastChar))
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

    }
}
