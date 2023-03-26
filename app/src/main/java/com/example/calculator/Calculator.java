package com.example.calculator;
import org.mariuszgromada.math.mxparser.*;

public class Calculator {
    public String result;

    public Calculator() {
        this.result = "";
        License.iConfirmNonCommercialUse("Marek");
    }

    public double evaluate() {
        Expression expression = new Expression(result);

        return expression.calculate();
    }


}
