package com.example.androidfinalproject;

import static com.example.androidfinalproject.Utils.showInfoDialog;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
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
import android.widget.Button;
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
    private Button button;
    private boolean yearly = false;
    private double calc;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        toolbar =setupToolbar();
        setupFAB();
        setupfields();
        button = (Button)findViewById(R.id.resources_button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                showResources();
            }
        });


    }

    private void showResources() {
        Intent intent = new Intent(this, Resources.class);
        startActivity(intent);
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
            if(yearly){
                msg = msg +" for the year";
            }
        snackbar.setText(msg);
        snackbar.show();

        inflationCalc.setRate(0);
        inflationCalc.setMoney(0);
        inflationCalc.setTotal(0);
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
        calc = inflationCalc.calculate();
        if(yearly){
            calc=calc*12;
        }

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
        if (id == R.id.yearly) {
            toggleMenuItem(item);
            yearly=item.isChecked();
        }
        if (id == R.id.monthly) {
            toggleMenuItem(item);
        }
        if (id == R.id.action_about) {
            showAbout();
        }

        return super.onOptionsItemSelected(item);
    }
    private void showAbout() {
        if (snackbar.isShown()) {
            snackbar.dismiss();
        }
        showInfoDialog(MainActivity.this, getString(R.string.about_title),
                getString(R.string.about_txt));
    }
    private void toggleMenuItem(MenuItem item) {
        item.setChecked(!item.isChecked());
    }

}