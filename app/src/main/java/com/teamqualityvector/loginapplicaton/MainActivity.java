package com.teamqualityvector.loginapplicaton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText etEmail=(EditText)findViewById(R.id.etEmail);
        final EditText etPassword=(EditText)findViewById(R.id.etPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        TextView tvNot_reg = (TextView) findViewById(R.id.tvNot_reg);

        Intent intent=getIntent();
        String val=intent.getStringExtra("toMain");
        etEmail.setText(val);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etEmail.getText().toString();
                String password =etPassword.getText().toString();


                String checkPassword;
                SharedPreferences sharedPreferences=getSharedPreferences("MYPREFERRENCES", MODE_PRIVATE);

                if(username.equals("") || password.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Access Denied!!, Fields should not be empty",Toast.LENGTH_LONG).show();
                }
                else {
                    if (sharedPreferences.contains(username)) {
                        checkPassword = sharedPreferences.getString(username, "");
                        if (checkPassword.equals(password))
                        {
                                Intent tent = new Intent(MainActivity.this, NextActivity.class);
                                tent.putExtra("UserName", username);
                            Toast.makeText(MainActivity.this,"Sucessfully Logged In",Toast.LENGTH_LONG).show();
                                startActivity(tent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Access Denied!! Incorrect Username or Password",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Access Denied!! Incorrect Username or Password",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        tvNot_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, RegistrationActivity.class);
                i.putExtra("toRegister",etEmail.getText().toString());
                startActivity(i);
            }
        });

    }

}

