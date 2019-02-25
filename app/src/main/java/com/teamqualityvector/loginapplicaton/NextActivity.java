package com.teamqualityvector.loginapplicaton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Button btnLogout = (Button) findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NextActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        SharedPreferences sharedPreferences=getSharedPreferences("MYPREFERRENCES",MODE_PRIVATE);
        String display=sharedPreferences.getString("display", "");

        TextView displayTv=(TextView)findViewById(R.id.tvDisplay);

        Intent intent=getIntent();
        displayTv.setText(intent.getStringExtra("UserName"));
    }
}
