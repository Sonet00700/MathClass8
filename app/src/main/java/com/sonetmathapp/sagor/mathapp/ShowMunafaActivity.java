package com.sonetmathapp.sagor.mathapp;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ShowMunafaActivity extends AppCompatActivity {

    TextView txtVSI,txtVCP,txtVCI,txtVDOI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("মুনাফা");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_munafa);

        txtVSI=findViewById(R.id.txtvsimpleInterestId);
        txtVCP=findViewById(R.id.txtvcomplexpcplId);
        txtVCI=findViewById(R.id.txtvcomplexInterestId);
        txtVDOI=findViewById(R.id.txtvdifferenceofInterestId);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            try{
                String simpleinterest = bundle.getString("simpleinterest");
                String complexpcpl = bundle.getString("complexpcpl");
                String complexinterest = bundle.getString("complexinterest");
                String differenceofinterest = bundle.getString("differenceofinterest");

                txtVSI.setText(simpleinterest);
                txtVCP.setText(complexpcpl);
                txtVCI.setText(complexinterest);
                txtVDOI.setText(differenceofinterest);
            }catch (Exception e){
                Toast.makeText(ShowMunafaActivity.this, "Null Problem", Toast.LENGTH_SHORT).show();
            }

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
                String s="munafa";
                Intent i = new Intent(ShowMunafaActivity.this, RulesActivity.class);
                i.putExtra("s",s);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
