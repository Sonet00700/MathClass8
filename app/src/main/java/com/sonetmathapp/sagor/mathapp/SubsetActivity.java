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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.pow;

public class SubsetActivity extends AppCompatActivity {

    EditText etsetnumber, etsetelement;
    Button btnsubset,btnsetnumber,btnsetelement,btnclearset;
    int number, count = 0;
    int isentered=0;
    String s="",frequency,elementString;
    ArrayList<String> arrayListString2 = new ArrayList<>();
    ArrayList<String> arrayListSubset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.subset);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subset);

        etsetelement = findViewById(R.id.edittextsetelementid);
        etsetnumber = findViewById(R.id.edittextsetnumberid);
        btnsubset = findViewById(R.id.btnidsubset);
        btnsetnumber=findViewById(R.id.btnsubsetnumberid);
        btnsetelement=findViewById(R.id.btnsubsetelementid);
        btnclearset=findViewById(R.id.btnsubsetClear);

        btnsetnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    number = Integer.parseInt(etsetnumber.getText().toString());
                    etsetnumber.setEnabled(false);
                    btnsetnumber.setEnabled(false);
                    count++;
                }catch (Exception e){
                    Toast.makeText(SubsetActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnsetelement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==1){
                    try{

                        elementString = etsetelement.getText().toString();//string value er jonno
                        arrayListString2.add(elementString);//string value er jonno
                        etsetelement.setText("");

                        if(number==arrayListString2.size()){////string value er jonno String2
                            etsetelement.setEnabled(false);
                            btnsetelement.setEnabled(false);
                            count++;
                        }
                    }catch (Exception e){
                        Toast.makeText(SubsetActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(SubsetActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnsubset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if( isentered==0){
                   if(count==2){
                       try{
                           ArrayList<ArrayList<String>> r2 = new ArrayList<ArrayList<String>>();////string value er jonno
                           r2 = subsets(arrayListString2);
                           for (int i = 0; i < r2.size(); i++) {
                               for (int j = 0; j < r2.get(i).size(); j++) {
                                   String result2 = r2.get(i).get(j);
                                   s = s + " " + result2;
                               }
                               arrayListSubset.add(s);
                               s = "";
                           }
                           frequency= String.valueOf(arrayListSubset.size());

                           Intent intent = new Intent(SubsetActivity.this, ShowSubsetActivity.class);
                           intent.putExtra("arraylistsubsetnumber", arrayListSubset);
                           intent.putExtra("subsetnumbercount", frequency);
                           isentered++;
                           startActivity(intent);
                       }catch (Exception e){
                           Toast.makeText(SubsetActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                       }
                   }else{
                       Toast.makeText(SubsetActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                   }
               }else{
                   Intent intent = new Intent(SubsetActivity.this, ShowSubsetActivity.class);
                   intent.putExtra("arraylistsubsetnumber", arrayListSubset);
                   intent.putExtra("subsetnumbercount", frequency);
                   startActivity(intent);
               }
            }
        });

        btnclearset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                etsetnumber.requestFocus();
                etsetnumber.setText("");
                etsetelement.setText("");
            }
        });
    }

    public ArrayList<ArrayList<String>> subsets(ArrayList<String> S) {
        if (S == null)
            return null;

        Collections.sort(S);

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < S.size(); i++) {
            ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();

            //get sets that are already in result
            for (ArrayList<String> a : result) {
                temp.add(new ArrayList<String>(a));
            }

            //add S[i] to existing sets
            for (ArrayList<String> a : temp) {
                a.add(S.get(i));
            }

            //add S[i] only as a set
            ArrayList<String> single = new ArrayList<String>();
            single.add(S.get(i));
            temp.add(single);

            result.addAll(temp);
        }

        //add empty set
        result.add(new ArrayList<String>());

        return result;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
