package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class SimpleCalcActivity extends AppCompatActivity {
    CalculatorButtonConfigurator calculatorButtonConfigurator;
    /* Backup for screen rotation */
    private String tempResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);

        this.calculatorButtonConfigurator = new CalculatorButtonConfigurator();

        calculatorButtonConfigurator.configureSimpleOperators(this);
        calculatorButtonConfigurator.configureNumberButtons(this);
        calculatorButtonConfigurator.configureResultButton(this);

        if (savedInstanceState != null) {
            tempResult = savedInstanceState.getString("result");
            calculatorButtonConfigurator.calculator.result = tempResult;
            calculatorButtonConfigurator.refreshResult(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result", calculatorButtonConfigurator.calculator.result);
    }
}