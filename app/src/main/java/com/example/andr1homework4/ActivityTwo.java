package com.example.andr1homework4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity {

    private EditText etText;
    private Button btnResult;
    Title title;
    public static final String KEY2 = "key2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        etText = findViewById(R.id.etText);
        btnResult = findViewById(R.id.btnResult);
        getData();
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                title = new Title(etText.getText().toString());
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