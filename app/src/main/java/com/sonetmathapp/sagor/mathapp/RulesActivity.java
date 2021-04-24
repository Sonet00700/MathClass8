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

public class RulesActivity extends AppCompatActivity {

    TextView textViewRules;
    String S;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Rules");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        textViewRules=findViewById(R.id.rules_text_id);
        final String s_gunitok=getResources().getString(R.string.show_gunitok);
        final String s_gunoniok=getResources().getString(R.string.show_gunoniok);
        final String s_lcm=getResources().getString(R.string.show_lcm);
        final String s_mean=getResources().getString(R.string.show_mean);
        final String s_median=getResources().getString(R.string.show_median);
        final String s_mode=getResources().getString(R.string.show_mode);
        final String s_munafa=getResources().getString(R.string.show_munafa);
        final String s_prime_number=getResources().getString(R.string.show_prime_number);
        final String s_subset=getResources().getString(R.string.show_subset);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            try {
                S = bundle.getString("s");

                if(S.equals("mean")){
                    textViewRules.setText(s_mean);
                }
                if(S.equals("median")){
                    textViewRules.setText(s_median);
                }
                if(S.equals("mode")){
                    textViewRules.setText(s_mode);
                }
                if(S.equals("munafa")){
                    textViewRules.setText(s_munafa);
                }
                if(S.equals("prime_number")){
                    textViewRules.setText(s_prime_number);
                }
                if(S.equals("gunitok")){
                    textViewRules.setText(s_gunitok);
                }
                if(S.equals("gunoniok")){
                    textViewRules.setText(s_gunoniok);
                }
                if(S.equals("lcm")){
                    textViewRules.setText(s_lcm);
                }
                if(S.equals("subset")){
                    textViewRules.setText(s_subset);
                }
            }catch (Exception e){
                Toast.makeText(RulesActivity.this, "Null Problem", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //back button code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_exit_menu, menu);
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

            case R.id.home:
                Intent i = new Intent(RulesActivity.this, WelcomeActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
