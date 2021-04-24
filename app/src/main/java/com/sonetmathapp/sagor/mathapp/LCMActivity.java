package com.sonetmathapp.sagor.mathapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class LCMActivity extends AppCompatActivity {

    EditText etlcmnumber,etlcm;
    Button btnlcmnumber,btnlcm,btnResult,btnClear;
    int number,element,hcf,vajjo,isentered=0,count=0;
    long lcm;
    String p,s;
    ArrayList<Integer> arrayListLCM=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.lcmandhcf);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcm);

        etlcmnumber=findViewById(R.id.edittextidlcmnumber);
        etlcm=findViewById(R.id.edittextidlcm);
        btnlcmnumber=findViewById(R.id.btnidlcmnumber);
        btnlcm=findViewById(R.id.btnidlcm);
        btnResult=findViewById(R.id.btnResultLCM);
        btnClear=findViewById(R.id.btnClearLCM);

        btnlcmnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    number = Integer.parseInt(etlcmnumber.getText().toString());
                    isentered++;
                    etlcmnumber.setEnabled(false);
                    btnlcmnumber.setEnabled(false);
                }catch (Exception e){
                    Toast.makeText(LCMActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnlcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                element=Integer.parseInt(etlcm.getText().toString());
                etlcm.setText("");
                arrayListLCM.add(element);
                if(number==arrayListLCM.size()){
                    isentered++;
                    etlcm.setEnabled(false);
                    btnlcm.setEnabled(false);
                }
                }catch (Exception e){
                    Toast.makeText(LCMActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    if (isentered == 2) {
                        try {
                            if (arrayListLCM.size() == number) {
                                Collections.sort(arrayListLCM);
                                lcm = arrayListLCM.get(number - 1);

                                Long result = Long.valueOf(arrayListLCM.get(0));
                                for (int i = 0; i < number; i++) {
                                    result = lcm(result, arrayListLCM.get(i));
                                }
                                 p = String.valueOf(result);

                                hcf = arrayListLCM.get(0);
                                for (int i = 0; i < number - 1; i++) {
                                    vajjo = arrayListLCM.get(i + 1);
                                    while (vajjo % hcf != 0) {
                                        int temp = hcf;
                                        hcf = vajjo % hcf;
                                        vajjo = temp;
                                    }
                                }
                                 s = String.valueOf(hcf);
                                Intent intent = new Intent(LCMActivity.this, ShowLCMActivity.class);
                                intent.putExtra("lcm", p);
                                intent.putExtra("hcf", s);
                                count++;
                                startActivity(intent);

                            }
                        } catch (Exception e) {
                            Toast.makeText(LCMActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LCMActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Intent intent = new Intent(LCMActivity.this, ShowLCMActivity.class);
                    intent.putExtra("lcm", p);
                    intent.putExtra("hcf", s);
                    startActivity(intent);
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                etlcmnumber.requestFocus();
                etlcmnumber.setText("");
                etlcm.setText("");
            }
        });
    }
    private static long lcm(long a,long b){
        long c,d;
        c=a;
        d=b;
        while (d>0){
          long temp=d;
          d=c%d;
          c=temp;
        }

          long re=(a*b)/c;
        return re;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
