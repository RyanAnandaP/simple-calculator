package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    // Variables to hold the operands
    private Double operand1 = null;
    private String pendingOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        //Store the button by id
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);

        Button buttonminus = (Button) findViewById(R.id.buttonminus);
        Button buttonPlus = (Button) findViewById(R.id.buttonplus);
        Button buttondivide = (Button) findViewById(R.id.buttondivide);
        Button buttonTimes = (Button) findViewById(R.id.buttontimes);
        Button buttondot = (Button) findViewById(R.id.buttondot);
        Button buttonEquals = (Button) findViewById(R.id.buttonequal);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttondot.setOnClickListener(listener);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    Double doublevalue = Double.valueOf(value);
                    performOperation(doublevalue,op);
                }catch (NumberFormatException e){
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };
        buttonEquals.setOnClickListener(opListener);
        buttondivide.setOnClickListener(opListener);
        buttonTimes.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);
        buttonminus.setOnClickListener(opListener);
    }
    private void performOperation(Double value, String operation){
        if(null == operand1){
            operand1 = value;
        }
        else {
            if(pendingOperation.equals("=")){
                pendingOperation = operation;
            }
            switch(pendingOperation){
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if(value == 0){
                        operand1 = 0.0;
                    }
                    else{
                        operand1 /= value;
                    }
                    break;

                case "*":
                    operand1 *= value;
                    break;

                case "-":
                    operand1 -= value;
                    break;

                case "+":
                    operand1 += value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }

}