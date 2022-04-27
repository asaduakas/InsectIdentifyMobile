package com.example.insectidentify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DataActivity extends AppCompatActivity implements View.OnClickListener {

    final Calendar myCalendar = Calendar.getInstance();
    EditText editTextDate;
    EditText eTextTime;
    ImageButton checkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        checkBtn =  findViewById(R.id.checkButton);
        checkBtn.setOnClickListener(this);

        //TOOLBAR SETUP
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_foreground);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(R.string.dataTitle);

        //Trapping dropdown
        String[] type = getResources().getStringArray(R.array.trapMethods);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_item,
                        type);

        AutoCompleteTextView editTextFilledExposedDropdown =
                findViewById(R.id.trapDropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);

        //Catching time dropdown
        String[] type1 = getResources().getStringArray(R.array.catchTime);
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_item,
                        type1);

        AutoCompleteTextView editTextFilledExposedDropdown1 =
                findViewById(R.id.catchDropdown);
        editTextFilledExposedDropdown1.setAdapter(adapter1);

        String[] type2 = getResources().getStringArray(R.array.field);
        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_item,
                        type2);

        AutoCompleteTextView editTextFilledExposedDropdown2 =
                findViewById(R.id.fieldDropdown);
        editTextFilledExposedDropdown2.setAdapter(adapter2);

        editTextDate = (EditText) findViewById(R.id.startDate);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        editTextDate.setOnClickListener(view ->
                new DatePickerDialog(DataActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        //TIME PICKER
        eTextTime = (EditText) findViewById(R.id.startTime);
        eTextTime.setInputType(InputType.TYPE_NULL);
        eTextTime.setOnClickListener(view -> {
            final Calendar cldr = Calendar.getInstance();
            int hour = cldr.get(Calendar.HOUR_OF_DAY);
            int minutes = cldr.get(Calendar.MINUTE);
            TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if (view.isShown()) {
                        cldr.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cldr.set(Calendar.MINUTE, minute);
                        eTextTime.setText(hourOfDay + ":" + minute);
                    }
                }
            };
            TimePickerDialog timePickerDialog = new TimePickerDialog(DataActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minutes, true);
            timePickerDialog.setTitle("Choose time:");
            timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            timePickerDialog.show();
        });
    }

    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.GERMANY);
        editTextDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(this,ActivityFragmentViewPager.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dicho, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home) {// User chose the "Home" item, go to main page
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }else// If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);
    }

}