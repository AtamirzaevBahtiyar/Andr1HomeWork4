package com.example.andr1homework4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityTwo extends AppCompatActivity {

    private EditText etText;
    private Button btnResult;
    Title title;
    public static final String KEY2 = "key2";
    private CalendarView calendarView;
    String selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;
                selectedDate = new StringBuilder().append(mMonth + 1)
                        .append("-").append(mDay).append("-").append(mYear)
                        .append(" ").toString();
                Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();
            }
        });

        etText = findViewById(R.id.etText);
        btnResult = findViewById(R.id.btnResult);
        getData();
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                title = new Title(etText.getText().toString());
                title.data = selectedDate;
                intent.putExtra(KEY2,title);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void getData() {
        if (getIntent() != null){
            title = (Title) getIntent().getSerializableExtra(MainActivity.KEY);
            etText.setText(title.name);
        }
    }
}