package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AdvancedCalcActivity extends AppCompatActivity {

    CalculatorButtonConfigurator calculatorButtonConfigurator;
    /* Backup for screen rotation */
    private String tempResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calc);

        this.calculatorButtonConfigurator = new CalculatorButtonConfigurator();

        calculatorButtonConfigurator.configureSimpleOperators(this);
        calculatorButtonConfigurator.configureNumberButtons(this);
        calculatorButtonConfigurator.configureResultButton(this);
        calculatorButtonConfigurator.configureAdvancedOperators(this);

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
