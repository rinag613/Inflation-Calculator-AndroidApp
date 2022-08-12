package com.example.androidfinalproject;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Pair;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.androidfinalproject.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    Toolbar toolbar;
    private InflationCalc inflationCalc;
    private EditText spending, rate;
    private Snackbar snackbar;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        toolbar =setupToolbar();
        setupFAB();
        setupfields();
    }

    private void setupfields() {
        spending = findViewById(R.id.edit_txt_spending_amount);
        rate = findViewById(R.id.inflat_edit);
        View layoutMain = findViewById(R.id.main_activity);
        snackbar = Snackbar.make(layoutMain, "",
                Snackbar.LENGTH_INDEFINITE);
    }

    private void handleFABClick() {
              String spnd=  spending.getText().toString();
               String rt= rate.getText().toString();
        setFieldsTo(spnd, rt);

            String msg = getNumber();
        snackbar.setText(msg);
        snackbar.show();
    }

    private void setFieldsTo(String spnd, String rt) {

        double money = Double.parseDouble(spnd);
        double theRate = Double.parseDouble(rt);

        if ( inflationCalc== null)
            inflationCalc = new InflationCalc(money, theRate);
        else {
            inflationCalc.setMoney(money);
            inflationCalc.setRate(theRate);
        }
    }

    private String getNumber() {
        final double calc;
        calc = inflationCalc.calculate();

        String total = calc+"";
        return total;
    }
    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleFABClick();
            }
        });
    }
    private Toolbar setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}