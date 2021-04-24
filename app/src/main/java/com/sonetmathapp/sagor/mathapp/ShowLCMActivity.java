package com.sonetmathapp.sagor.mathapp;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ShowLCMActivity extends AppCompatActivity {

    TextView hcf,lcm;
    String p,s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.lcmandhcf);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lcm);
        hcf=findViewById(R.id.txtVidHCF);
        lcm=findViewById(R.id.txtVidLCM);

        Bundle bundle = getIntent().getExtras();
        try {
            if (bundle != null) {
                p = bundle.getString("lcm");
                s=bundle.getString("hcf");
                hcf.setText("গ.সা.গু = "+s);
                lcm.setText("ল.সা.গু = "+p);
            }
        }catch (Exception e){
            Toast.makeText(ShowLCMActivity.this, "Null Problem", Toast.LENGTH_SHORT).show();
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
                String s="lcm";
                Intent i = new Intent(ShowLCMActivity.this, RulesActivity.class);
                i.putExtra("s",s);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
