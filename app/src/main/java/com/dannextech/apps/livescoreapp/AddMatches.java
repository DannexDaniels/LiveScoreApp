package com.dannextech.apps.livescoreapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddMatches extends AppCompatActivity {

    EditText etHome, etAway, etGround, etDate,etTime;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matches);

        etAway = (EditText) findViewById(R.id.etAwayTeam);
        etHome = (EditText) findViewById(R.id.etHomeTeam);
        etDate = (EditText) findViewById(R.id.etGameDate);
        etTime = (EditText) findViewById(R.id.etGameTime);
        etGround = (EditText) findViewById(R.id.etGameGround);
        btSubmit = (Button) findViewById(R.id.btGameSubmit);

        final LiveScoresQueries query = new LiveScoresQueries(this);

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFieldsFilled(v)){
                    if (query.saveMatch(etHome.getText().toString(),etAway.getText().toString(),etDate.getText().toString(),etTime.getText().toString(),etGround.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Match has been saved successfully",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Matches.class));
                    }else
                        Snackbar.make(v,"Something's wrong. Please try again.",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                }
            }
        });
    }

    private void showDatePicker() {
        int mYear,mMonth,mDay;
        Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddMatches.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Toast.makeText(AddEvent.this,year+"/"+(month+1)+"/"+dayOfMonth,Toast.LENGTH_SHORT).show();
                setDate(year,month,dayOfMonth);
            }
        },mYear,mMonth,mDay);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }

    private void setDate(int year, int month, int dayOfMonth) {
        etDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
    }

    private void showTimePicker() {
        int mHour, mMinute;
        final Calendar cal = Calendar.getInstance();
        mHour = cal.get(Calendar.HOUR);
        mMinute = cal.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(AddMatches.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String hr=String.valueOf(hourOfDay),min=String.valueOf(minute);
                if (hourOfDay<10)
                    hr = "0"+String.valueOf(hourOfDay);
                if (minute<10)
                    min = "0"+String.valueOf(minute);

                setTime(hr,min);
            }
        },mHour,mMinute,true);
        timePickerDialog.show();
    }

    private void setTime(String hourOfDay, String minute) {
        etTime.setText(hourOfDay+":"+minute);
    }

    private Boolean checkFieldsFilled(View v) {
        if (etAway.getText().toString().isEmpty() || etHome.getText().toString().isEmpty() || etTime.getText().toString().isEmpty() || etDate.getText().toString().isEmpty() || etGround.getText().toString().isEmpty()){
            Snackbar.make(v,"All fields must be filled",Snackbar.LENGTH_LONG).setAction("Action",null).show();
            return false;
        }else
            return true;
    }
}
