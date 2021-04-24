package com.sonetmathapp.sagor.mathapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.renderscript.Sampler;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    //Initialize Button/textView/EditText/ListView/Arraylist/Variables
    public Button btnMode, btnMedian, btnMean, btnClear, btnAdd, btnEnter, btnShreniBabodhan;
    private TextView textViewRemainderNo;
    private EditText editTextTotalNo, editTextAllNo, editTextShreniBabodhan;
    boolean language;

    ArrayList<Integer> arrayListIntValue = new ArrayList<Integer>();
    ArrayList<Integer> arrayListCount = new ArrayList<Integer>();
    ArrayList<Integer> arrayListElement = new ArrayList<Integer>();

    private Double avg, median, sum = 0.0, mid1, mid2, value = 0.0, fixi, fi = 0.0, xi, subtotal = 0.0, gor, minVal, maxV, minV, porishor, shreniShongkha, minimum, maximum, shreniBabodhan;
    private String r;
    private int count = 0, isNumberEntered = 0, n, remain, i, minVa, j, k, a = 0, b = 0, c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //logo set korer jonno
        //getSupportActionBar().setLogo(R.drawable.icon_math_class_8_4);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.information);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find Button/textView/EditText/ListView/Arraylist
        btnMean = findViewById(R.id.btnIdMean);
        btnMedian = findViewById(R.id.btnIdMedian);
        btnMode = findViewById(R.id.btnIdMode);
        btnAdd = findViewById(R.id.btnIdAddElement);
        btnEnter = findViewById(R.id.btnIdNumber);
        btnShreniBabodhan = findViewById(R.id.btnIdShreniBabodhan);
        textViewRemainderNo = findViewById(R.id.textViewIdRemainNoOfElements);
        editTextTotalNo = findViewById(R.id.editTextIdNumber);
        editTextAllNo = findViewById(R.id.editTextIdAllElements);
        editTextShreniBabodhan = findViewById(R.id.editTextIdShreniBabodhan);
        btnClear = findViewById(R.id.btnClear);

        //set onclickListener to button Enter
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get value from edit text and convert into Integer

                try {
                    n = Integer.parseInt(editTextTotalNo.getText().toString());
                    isNumberEntered++;
                    //disable edit text and button after enter the value
                    btnEnter.setEnabled(false);
                    editTextTotalNo.setEnabled(false);

                    remain = n;
                    r = Integer.toString(remain);

                    //set value to the text view
                    textViewRemainderNo.setText(r);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }


            }
        });
        //set onclickListener to button Shrenibabodhan
        btnShreniBabodhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    shreniBabodhan = Double.valueOf(editTextShreniBabodhan.getText().toString());
                    isNumberEntered++;
                    btnShreniBabodhan.setEnabled(false);
                    editTextShreniBabodhan.setEnabled(false);

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //set onclickListener to button AddElements
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int getInput = Integer.parseInt(editTextAllNo.getText().toString());
                    arrayListIntValue.add(getInput);

                    //set edit text empty
                    editTextAllNo.setText("");

                    count++;
                    remain--;
                    r = Integer.toString(remain);

                    //set value to text view
                    textViewRemainderNo.setText(r);

                    //check and count entry of the elements if all the elements are in then edit text and button get disable
                    if (count == n) {
                        isNumberEntered++;
                        editTextAllNo.setEnabled(false);
                        btnAdd.setEnabled(false);
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //set onclickListener to button Mean
        btnMean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //calculating mean

                if (a == 0) {
                    try {
                        for (i = 0; i < arrayListIntValue.size(); i++) {
                            value = Double.valueOf(arrayListIntValue.get(i));
                            sum = sum + value;
                            avg = sum / n;
                        }
                        //convert value of mean/avarage and set to the button
                        String s = Double.toString(avg);

                        //code for ganitic Gor
                        Collections.sort(arrayListIntValue);
                        maxV = Double.valueOf(arrayListIntValue.get(n - 1));
                        minV = Double.valueOf(arrayListIntValue.get(0));
                        minVa = minV.intValue();
                        minimum = minV;
                        maximum = maxV;
                        porishor = (maxV - minV) + 1;
                        shreniShongkha = Math.ceil(porishor / shreniBabodhan);

                        // total calculation
                        for (i = 0; i < shreniShongkha; i++) {
                            minVal = minVa + shreniBabodhan;
                            for (j = minVa; j < minVal; j++) {
                                for (k = 0; k < n; k++) {
                                    if (j == arrayListIntValue.get(k)) {
                                        fi++;
                                    }
                                }
                            }
                            xi = (minVal + minVa - 1) / 2.0;
                            fixi = fi * xi;
                            subtotal = subtotal + fixi;
                            fi = 0.0;
                            xi = 0.0;
                            minVa = minVal.intValue();

                        }

                        gor = subtotal / n;

                        //data pathanor jonno
                        Intent intent = new Intent(MainActivity.this, MeanActivity.class);
                        intent.putExtra("min", minimum.toString());
                        intent.putExtra("max", maximum.toString());
                        intent.putExtra("porishor", porishor.toString());
                        intent.putExtra("shrenishongkha", shreniShongkha.toString());
                        intent.putExtra("subtotal", subtotal.toString());
                        intent.putExtra("shrenibabodhan", shreniBabodhan.toString());
                        intent.putExtra("sorasorigor", avg.toString());
                        intent.putExtra("gor", gor.toString());

                        a++;
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, MeanActivity.class);
                    intent.putExtra("min", minimum.toString());
                    intent.putExtra("max", maximum.toString());
                    intent.putExtra("porishor", porishor.toString());
                    intent.putExtra("shrenishongkha", shreniShongkha.toString());
                    intent.putExtra("subtotal", subtotal.toString());
                    intent.putExtra("shrenibabodhan", shreniBabodhan.toString());
                    intent.putExtra("sorasorigor", avg.toString());
                    intent.putExtra("gor", gor.toString());
                    startActivity(intent);
                }
            }
        });

        btnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b == 0) {
                    if (isNumberEntered == 3) {
                        try {
                            Collections.sort(arrayListIntValue);
                            boolean visited[] = new boolean[n];
                            Arrays.fill(visited, false);

                            for (i = 0; i < n; i++) {
                                if (visited[i])
                                    continue;

                                int count1 = 1;
                                for (j = i + 1; j < n; j++) {
                                    if (arrayListIntValue.get(i) == arrayListIntValue.get(j)) {
                                        visited[j] = true;
                                        count1++;
                                    }
                                }
                                arrayListCount.add(count1);
                                arrayListElement.add(arrayListIntValue.get(i));
                            }
                            b++;

                            Intent intent1 = new Intent(MainActivity.this, ModeActivity.class);
                            intent1.putExtra("arraylistmode", arrayListElement);
                            intent1.putExtra("arraylistmodecount", arrayListCount);
                            startActivity(intent1);

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent intent1 = new Intent(MainActivity.this, ModeActivity.class);
                    intent1.putExtra("arraylistmode", arrayListElement);
                    intent1.putExtra("arraylistmodecount", arrayListCount);
                    startActivity(intent1);
                }
            }
        });

        btnMedian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c == 0) {
                    try {
                        Collections.sort(arrayListIntValue);
                        if (n % 2 == 1) {
                            median = Double.valueOf(arrayListIntValue.get(((n + 1) / 2) - 1));
                        } else {
                            mid1 = Double.valueOf(arrayListIntValue.get((n / 2) - 1));
                            mid2 = Double.valueOf(arrayListIntValue.get(n / 2));
                            median = (mid1 + mid2) / 2.0;

                        }
                        c++;
                        Intent intent2 = new Intent(MainActivity.this, MedianActivity.class);
                        intent2.putExtra("median", median.toString());
                        startActivity(intent2);

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent intent2 = new Intent(MainActivity.this, MedianActivity.class);
                    intent2.putExtra("median", median.toString());
                    startActivity(intent2);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                editTextTotalNo.requestFocus();
                editTextTotalNo.setText("");
                editTextShreniBabodhan.setText("");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
