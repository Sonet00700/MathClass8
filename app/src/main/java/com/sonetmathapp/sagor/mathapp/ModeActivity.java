package com.sonetmathapp.sagor.mathapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ModeActivity extends AppCompatActivity {

    ListView listView1;
    TextView txtv1, txtv2;
    String s1, s2;
    int i;

    ArrayList<ModeCount> modecount = new ArrayList<>();

    ArrayList<Integer> arrayList1, arrayList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.mode);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        listView1 = findViewById(R.id.listViewIdMode);
        txtv1 = findViewById(R.id.txtVmodeid1);
        txtv2 = findViewById(R.id.txtVmodeid2);

        Bundle bundle = getIntent().getExtras();
        try {
            if (bundle != null) {

                arrayList1 = bundle.getIntegerArrayList("arraylistmode");
                arrayList2 = bundle.getIntegerArrayList("arraylistmodecount");

                ModeCount m;
                for (i = 0; i < arrayList1.size(); i++) {

                    s1 = arrayList1.get(i).toString();
                    s2 = arrayList2.get(i).toString();
                    m = new ModeCount(s1, s2);
                    modecount.add(m);
                }
                CustomAdapter adapterC = new CustomAdapter(ModeActivity.this, modecount);
                listView1.setAdapter(adapterC);

            }
        } catch (Exception e) {
            Toast.makeText(ModeActivity.this, "Null Problem", Toast.LENGTH_SHORT).show();
        }

    }

    //back button code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        switch (item.getItemId()) {
            case R.id.exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do You want to Exit?");
                builder.setTitle("EXIT");
                builder.setCancelable(false);
                builder.setIcon(R.drawable.exit_red);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        moveTaskToBack(true);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;

            case R.id.rules:
                String s="mode";
                Intent i = new Intent(ModeActivity.this, RulesActivity.class);
                i.putExtra("s",s);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
