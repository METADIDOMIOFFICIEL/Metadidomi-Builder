package com.example.counterapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.util.Log;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "CounterApp";
    private int counter = 0;
    private TextView counterDisplay;
    private Button incrementBtn;
    private Button decrementBtn;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity créée");

        counterDisplay = (TextView) findViewById(R.id.counterDisplay);
        incrementBtn = (Button) findViewById(R.id.incrementBtn);
        decrementBtn = (Button) findViewById(R.id.decrementBtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);

        updateDisplay();

        incrementBtn.setOnClickListener(this);
        decrementBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.incrementBtn) {
            counter++;
            Log.d(TAG, "Compteur incrémenté: " + counter);
        } else if (id == R.id.decrementBtn) {
            counter--;
            Log.d(TAG, "Compteur décrémenté: " + counter);
        } else if (id == R.id.resetBtn) {
            counter = 0;
            Log.d(TAG, "Compteur réinitialisé");
        }
        updateDisplay();
    }

    private void updateDisplay() {
        counterDisplay.setText(String.valueOf(counter));
    }
}
