package com.sonetmathapp.sagor.mathapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    Button btninfo, btnsubset, btnmunafa, btnlcm, btnprime, btnfactor,btnlanguage;
    public static String PKG_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_welcome);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        btnfactor = findViewById(R.id.btnFactor);
        btnprime = findViewById(R.id.btnPrimeNumber);
        btnlcm = findViewById(R.id.btnLCM);
        btnmunafa = findViewById(R.id.btnInterest);
        btnsubset = findViewById(R.id.btnPorimap);
        btninfo = findViewById(R.id.btnDataAndInformation);
        btnlanguage=findViewById(R.id.btnLanguage);
        PKG_NAME = BuildConfig.APPLICATION_ID;

        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnprime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, PrimeNumberActivity.class);
                startActivity(intent);
            }
        });
        btnlcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LCMActivity.class);
                startActivity(intent);
            }
        });
        btnmunafa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MunafaActivity.class);
                startActivity(intent);
            }
        });
        btnsubset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, SubsetActivity.class);
                startActivity(intent);
            }
        });
        btnfactor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, GunitokActivity.class);
                startActivity(intent);
            }
        });
       btnlanguage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showLanguageChangeDialog();
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutUs:
                Intent i = new Intent(WelcomeActivity.this, AboutUs.class);
                startActivity(i);
                return true;

            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String value = "https://play.google.com/store/apps/details?id=" + PKG_NAME;
                intent.putExtra(Intent.EXTRA_TEXT, value);
                startActivity(Intent.createChooser(intent, "Share Via "));
                return true;

            case R.id.rating:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + PKG_NAME)));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + PKG_NAME)));
                }
                return true;

            case R.id.otherApps:
                Toast.makeText(WelcomeActivity.this, "Coming Soon....", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.checkForUpdate:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + PKG_NAME)));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + PKG_NAME)));
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void showLanguageChangeDialog(){
        final String[] listitems = {"English", "Bangla"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(WelcomeActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setLocale("en");
                    recreate();
                } else if (i == 1) {
                    setLocale("bn");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


    private void setLocale(String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences prefs=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String languages=prefs.getString("My_Lang","");
        setLocale(languages);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do You want to Exit?");
        builder.setTitle("EXIT");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.exit_red);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                WelcomeActivity.super.onBackPressed();
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
    }
}
