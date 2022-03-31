package com.example.libraryoverdue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    EditText edtStuName, edtStuNo;
    TextView txtIssueDate, txtDueDate;
    Button btnSubmit;
    Spinner spnBook;
    int nYear, nMonth, nDay = 0;

    String[] Books = {"Discrete Mathematics", "Operating Systems", "Theory of Computation",
            "Computer-Networks", "Cryptography and Network Security", "Object Oriented Systems",
            "Compilers", "Software Engineering", "Image Processing", "Artificial Intelligence"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        txtIssueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Books);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spnBook.setAdapter(aa);

        spnBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),Books[position] , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void openDatePicker() {
        Calendar cal = Calendar.getInstance();
        int mYear = cal.get(Calendar.YEAR);
        int mMonth = cal.get(Calendar.MONTH);
        int mDay = cal.get(Calendar.DAY_OF_MONTH);



        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        int uMonth = month+1;
                        nYear = year;
                        nMonth = month;
                        nDay = day;
                        txtIssueDate.setText("Issue Date: "+ year +"-"+uMonth +"-"+day );
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

        Date createdDate = new GregorianCalendar(nYear, nMonth - 1, nDay).getTime();
        Date newDate = addDays(createdDate, 15);
        txtDueDate.setText("Due Date: "+newDate);

    }
    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    private void initialize() {
        edtStuName = findViewById(R.id.edtStuName);
        edtStuNo = findViewById(R.id.edtStuEnrollment);
        txtIssueDate = findViewById(R.id.txtIssueDate);
        txtDueDate = findViewById(R.id.txtDueDate);
        btnSubmit = findViewById(R.id.btnSubmit);
        spnBook = findViewById(R.id.spnBook);
    }
}