package com.sonetmathapp.sagor.mathapp;

import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PrimeNumberActivity extends AppCompatActivity {

    EditText etLR,etUR;

    Button btnUR,btnLR,btnprimeNumber,btnclear;
    int lr,ur,n,c=0,count,isentered=0;
    ArrayList<Integer> arrayListPrimeN =new ArrayList<>();
    String countString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.primenumber);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_number);
        etLR=findViewById(R.id.etlowerrange);
        etUR=findViewById(R.id.etupperrange);
        btnLR=findViewById(R.id.btnlowerrange);
        btnUR=findViewById(R.id.btnupperrange);
        btnprimeNumber=findViewById(R.id.btnprimeNumberid);
        btnclear=findViewById(R.id.btnprimenumberClearId);

        final String[] primeNumber=getResources().getStringArray(R.array.prime_number);
        btnLR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    lr = Integer.parseInt(etLR.getText().toString());
                    if(lr>=1&&lr<=100000){
                        isentered++;
                        etLR.setEnabled(false);
                        btnLR.setEnabled(false);
                    }else{
                        Toast.makeText(PrimeNumberActivity.this, "Enter Number between 1 to 100000", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(PrimeNumberActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ur = Integer.parseInt(etUR.getText().toString());
                    if(ur<=100000&&ur>=1){
                        if(ur>lr){
                            isentered++;
                            etUR.setEnabled(false);
                            btnUR.setEnabled(false);
                        }else {
                            Toast.makeText(PrimeNumberActivity.this, "Enter Number greater than "+lr, Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(PrimeNumberActivity.this, "Enter Number between 1 to 100000", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(PrimeNumberActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnprimeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c == 0) {
                    if(isentered==2){
                        try {
                            n = primeNumber.length;
                            for (int i = 0; i < n; i++) {
                                int lr2 = Integer.parseInt(primeNumber[i]);
                                int ur2 = Integer.parseInt(primeNumber[i]);
                                if (lr <= lr2 && ur>=ur2) {
                                    arrayListPrimeN.add(Integer.parseInt(primeNumber[i]));
                                }
                            }

                            count = arrayListPrimeN.size();
                            countString= String.valueOf(count);

                            Intent intent = new Intent(PrimeNumberActivity.this, ShowPrimeNumberActivity.class);
                            intent.putExtra("arraylistprimenumber", arrayListPrimeN);
                            intent.putExtra("primenumbercount", countString);
                            c++;
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(PrimeNumberActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(PrimeNumberActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Intent intent = new Intent(PrimeNumberActivity.this, ShowPrimeNumberActivity.class);
                    intent.putExtra("arraylistprimenumber", arrayListPrimeN);
                    intent.putExtra("primenumbercount", countString);
                    startActivity(intent);
                }

            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                etLR.requestFocus();
                etLR.setText("");
                etUR.setText("");
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
