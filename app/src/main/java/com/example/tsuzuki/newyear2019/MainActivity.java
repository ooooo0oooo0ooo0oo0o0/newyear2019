package com.example.tsuzuki.newyear2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView msgText = this.findViewById(R.id.messageView);
        msgText.setText("お年玉なしも有り得るよ。覚悟しろよー！");
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
}
