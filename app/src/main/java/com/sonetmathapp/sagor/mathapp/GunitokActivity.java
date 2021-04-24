package com.sonetmathapp.sagor.mathapp;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.sqrt;

public class GunitokActivity extends AppCompatActivity {

    Button btngunitokadd, btngunitokrange, btngunoniokadd, btnclear, btngunitok, btngunoniok;
    EditText etgunitok, etgunitokrange, etgunoniok;
    int gunoniok, gunitok, gunitokrange, a = 0, b = 0,isnumberentered1=0,isnumberentered2=0;
    ArrayList<Integer> arrayListGunoniok = new ArrayList<Integer>();
    ArrayList<Integer> arrayListGunitok = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.factor);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gunitok);

        btngunitokadd = findViewById(R.id.btnGunitokIdadd);
        btngunitokrange = findViewById(R.id.btnGunitokrangeId);
        btnclear = findViewById(R.id.btngunitokClearId);
        btngunoniokadd = findViewById(R.id.btnGunoniokIdadd);
        etgunitok = findViewById(R.id.editTextGunitokId);
        etgunitokrange = findViewById(R.id.editTextGunitokRangeId);
        etgunoniok = findViewById(R.id.editTextGunoniokId);
        btngunoniok = findViewById(R.id.btngunoniokid);
        btngunitok = findViewById(R.id.btngunitokid);

        btngunoniokadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    gunoniok = Integer.parseInt(etgunoniok.getText().toString());
                    isnumberentered1++;
                    etgunoniok.setEnabled(false);
                    btngunoniokadd.setEnabled(false);
                } catch (Exception e) {
                    Toast.makeText(GunitokActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btngunitokadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    gunitok = Integer.parseInt(etgunitok.getText().toString());
                    isnumberentered2++;
                    etgunitok.setEnabled(false);
                    btngunitokadd.setEnabled(false);
                } catch (Exception e) {
                    Toast.makeText(GunitokActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btngunitokrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    gunitokrange = Integer.parseInt(etgunitokrange.getText().toString());
                    if(gunitokrange>gunitok){
                        isnumberentered2++;
                        etgunitokrange.setEnabled(false);
                        btngunitokrange.setEnabled(false);
                    }else {
                        Toast.makeText(GunitokActivity.this, "Enter Number greater than "+gunitok, Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    Toast.makeText(GunitokActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btngunoniok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a == 0) {
                    if(isnumberentered1==1){
                    try {
                        if (gunoniok % 2 == 0) {
                            for (int i = 1; i <= sqrt(gunoniok); i++) {

                                if (gunoniok % i == 0) {
                                    if (arrayListGunoniok.contains(i)) {
                                    } else {
                                        arrayListGunoniok.add(i);
                                    }
                                    int vagfol = gunoniok / i;
                                    if (arrayListGunoniok.contains(vagfol)) {
                                    } else {

                                        arrayListGunoniok.add(vagfol);
                                    }
                                }
                            }
                        } else {
                            for (int i = 1; i <= sqrt(gunoniok); i += 2) {
                                if (gunoniok % i == 0) {
                                    if (arrayListGunoniok.contains(i)) {
                                    } else {
                                        arrayListGunoniok.add(i);
                                    }
                                    int vagfol = gunoniok / i;
                                    if (arrayListGunoniok.contains(vagfol)) {
                                    } else {

                                        arrayListGunoniok.add(vagfol);
                                    }

                                }
                            }
                        }

                        Collections.sort(arrayListGunoniok);
                        Intent intent = new Intent(GunitokActivity.this, ShowGunoniokActivity.class);
                        intent.putExtra("arraylistgunoniok", arrayListGunoniok);
                        a++;
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(GunitokActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                        Toast.makeText(GunitokActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Intent intent = new Intent(GunitokActivity.this, ShowGunoniokActivity.class);
                    intent.putExtra("arraylistgunoniok", arrayListGunoniok);
                    startActivity(intent);
                }
            }
        });

        btngunitok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b == 0) {
                    if(isnumberentered2==2){
                    try {
                        int loop = gunitokrange / gunitok;
                        for (int i = 1; i <= loop; i++) {
                            int result = gunitok * i;
                            if (result != gunitokrange) {
                                arrayListGunitok.add(result);
                            }
                        }
                        Intent intent1 = new Intent(GunitokActivity.this, ShowGunitokActivity.class);
                        intent1.putExtra("arraylistgunitok", arrayListGunitok);
                        b++;
                        startActivity(intent1);
                    } catch (Exception e) {
                        Toast.makeText(GunitokActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                        Toast.makeText(GunitokActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Intent intent1 = new Intent(GunitokActivity.this, ShowGunitokActivity.class);
                    intent1.putExtra("arraylistgunitok", arrayListGunitok);
                    startActivity(intent1);
                }
            }
        });


        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                etgunoniok.requestFocus();
                etgunoniok.setText("");
                etgunitok.setText("");
                etgunitokrange.setText("");
                a=0;
                b=0;
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
