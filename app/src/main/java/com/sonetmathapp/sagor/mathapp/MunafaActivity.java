package com.sonetmathapp.sagor.mathapp;

import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MunafaActivity extends AppCompatActivity {

    Button btnmunafa,btnclear,btnP,btnT,btnR;
    EditText etpcpl,ettime,etrateofinterest;
    Double simplemunafa;
    Double complexmunafa,complexPcpl,difference;
    Double pcpl;
    Double time;
    String diff,si,cp,ci;
    Double rate;
    int co=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.munafa);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_munafa);

        btnmunafa=findViewById(R.id.btnmunafa);
        etpcpl=findViewById(R.id.edittextprincipalid);
        ettime=findViewById(R.id.edittexttimeid);
        etrateofinterest=findViewById(R.id.edittextrateofinterestid);
        btnclear=findViewById(R.id.btnmunafaClear);
        btnP=findViewById(R.id.btnmuldhonid);
        btnR=findViewById(R.id.btnrateid);
        btnT=findViewById(R.id.btnsomoyid);

        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    pcpl=Double.valueOf(etpcpl.getText().toString());
                    etpcpl.setEnabled(false);
                    btnP.setEnabled(false);
                }catch (Exception e){
                    Toast.makeText(MunafaActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    time=Double.valueOf(ettime.getText().toString());
                    ettime.setEnabled(false);
                    btnT.setEnabled(false);
                }catch (Exception e){
                    Toast.makeText(MunafaActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    rate=Double.valueOf(etrateofinterest.getText().toString());
                    etrateofinterest.setEnabled(false);
                    btnR.setEnabled(false);
                }catch (Exception e){
                    Toast.makeText(MunafaActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnmunafa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(co==0){
                    if(etpcpl!=null&&ettime!=null&&etrateofinterest!=null){
                        try{
                            Double crate=1.0;
                            rate=rate*0.01;
                            simplemunafa=pcpl*time*rate;

                            rate=rate+1;
                            for(int i=1;i<=time;i++){
                                crate=crate*rate;
                            }
                            complexPcpl=pcpl*crate;
                            complexmunafa=complexPcpl-pcpl;
                            difference=complexmunafa-simplemunafa;
                            si=(new DecimalFormat("##.##").format(simplemunafa));
                            cp=(new DecimalFormat("##.##").format(complexPcpl));
                            ci=(new DecimalFormat("##.##").format(complexmunafa));
                            diff=(new DecimalFormat("##.##").format(difference));

                            Intent intent = new Intent(MunafaActivity.this, ShowMunafaActivity.class);
                            intent.putExtra("simpleinterest", si);
                            intent.putExtra("complexpcpl", cp);
                            intent.putExtra("complexinterest", ci);
                            intent.putExtra("differenceofinterest", diff);
                            co++;
                            startActivity(intent);
                        }catch (Exception e){
                            Toast.makeText(MunafaActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MunafaActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Intent intent = new Intent(MunafaActivity.this, ShowMunafaActivity.class);
                    intent.putExtra("simpleinterest", si);
                    intent.putExtra("complexpcpl", cp);
                    intent.putExtra("complexinterest", ci);
                    intent.putExtra("differenceofinterest", diff);
                    startActivity(intent);
                }
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                etpcpl.requestFocus();
                etpcpl.setText("");
                ettime.setText("");
                etrateofinterest.setText("");
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
