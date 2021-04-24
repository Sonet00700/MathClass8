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

public class MeanActivity extends AppCompatActivity {

    TextView txtmax, txtmin, txtsorasorigor, txtporishor, txtshreniShonkha, txtshrenibabodhan, txtsubtotal, txtgor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.mean);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mean);

        txtmin = findViewById(R.id.txtVIdminimumValue);
        txtmax = findViewById(R.id.txtVIdmaximumValue);
        txtporishor = findViewById(R.id.txtVIdporishor);
        txtshreniShonkha = findViewById(R.id.txtVIdshreniShongkha);
        txtshrenibabodhan = findViewById(R.id.txtVIdshrenibabodhan);
        txtsubtotal = findViewById(R.id.txtVIdsubtotal);
        txtgor = findViewById(R.id.txtVIdgor);
        txtsorasorigor = findViewById(R.id.txtVidsorasorigor);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String min = bundle.getString("min");
            String max = bundle.getString("max");
            String porishor = bundle.getString("porishor");
            String shrenishongkha = bundle.getString("shrenishongkha");
            String shrenibabodhan = bundle.getString("shrenibabodhan");
            String subtotal = bundle.getString("subtotal");
            String sorasorigor = bundle.getString("sorasorigor");
            String gor = bundle.getString("gor");

            txtmin.setText(min);
            txtmax.setText(max);
            txtporishor.setText(porishor);
            txtshreniShonkha.setText(shrenishongkha);
            txtshrenibabodhan.setText(shrenibabodhan);
            txtsubtotal.setText(subtotal);
            txtsorasorigor.setText(sorasorigor);
            txtgor.setText(gor);
        }
    }


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
                String s="mean";
                Intent i = new Intent(MeanActivity.this, RulesActivity.class);
                i.putExtra("s",s);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
