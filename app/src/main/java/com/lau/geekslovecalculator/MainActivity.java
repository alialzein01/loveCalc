package com.lau.geekslovecalculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView results;
    EditText name;
    Spinner sp;
    TableLayout table;
    private ArrayList<List<String>> array;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        results = findViewById(R.id.resTxt);
        name = findViewById(R.id.nameTxt);
        sp = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lang, R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);
        table = findViewById(R.id.table1);
        array = new ArrayList<List<String>>();
    }

    public void showRes(View v) {
        List<String> tempArr = new ArrayList<String>();
        closeKeyboard();
        if (name.getText().toString().isEmpty()) {
            results.setText("Please Enter Your Name.");
        } else {
            Random rand = new Random();
            int res = rand.nextInt(101);
            String text = sp.getSelectedItem().toString();
            String n = name.getText().toString();
            tempArr.add(sp.getSelectedItem().toString());
            tempArr.add(Integer.toString(res)+"%");
            array.add(tempArr);
            if (res < 26) {
                results.setText(n + " + " + text + " = " + res + "% \n\" Run away from it :( \"");
            } else if (res <= 50) {
                results.setText(n + " + " + text + " = " + res + "% \n\" You don't love it :\\ \"");
            } else if (res < 75) {
                results.setText(n + " + " + text + " = " + res + "% \n\" Not so good :/ \"");
            } else {
                results.setText(n + " + " + text + " = " + res + "% \n\" There's chemistry :) \"");
            }
        }
        insertTab();
    }

    private void closeKeyboard() {
        View v = this.getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private void insertTab() {
        for (int i = counter; i < array.size(); i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < array.get(i).size(); j++) {
                TextView t = new TextView(this);
                t.setText(array.get(i).get(j).toString());
                t.setPadding(50,20,20,20);
                t.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                t.setTextSize(18);
                row.addView(t);
            }
            table.addView(row);
        }
        counter++;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}