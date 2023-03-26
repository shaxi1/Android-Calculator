package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class SimpleCalcActivity extends AppCompatActivity {
    Calculator calculator;
    CalculatorButtonConfigurator calculatorButtonConfigurator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);

        this.calculatorButtonConfigurator = new CalculatorButtonConfigurator();

        calculatorButtonConfigurator.configureSimpleOperators(this);
        calculatorButtonConfigurator.configureNumberButtons(this);
        calculatorButtonConfigurator.configureResultButton(this);
    }

}