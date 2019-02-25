package com.teamqualityvector.loginapplicaton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText regEmail=(EditText)findViewById(R.id.etRegEmail);
        final EditText regPassword1=(EditText)findViewById(R.id.etRegPassword1);
        final EditText regPassword2=(EditText)findViewById(R.id.etRegPassword2);
        Button regButton=(Button)findViewById(R.id.btnSignup);

        Intent intent=getIntent();
        String value=intent.getStringExtra("toRegister");
        regEmail.setText(value);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("MYPREFERRENCES", MODE_PRIVATE);
                String reg_mail = regEmail.getText().toString();
                String reg_password1 = regPassword1.getText().toString();
                String reg_password12 = regPassword2.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (reg_mail.length()>0 && regPassword1.length()>0 && regPassword2.length()>0)
                {
                    if (regPassword1.getText().toString().equals(regPassword2.getText().toString()))
                    {
                        if (sharedPreferences.contains(reg_mail))
                        {
                            Toast.makeText(RegistrationActivity.this, "User Already Exist", Toast.LENGTH_LONG).show();
                        } else {
                            editor.putString(reg_mail, reg_password1);
                            editor.commit();

                            Intent it = new Intent(RegistrationActivity.this, MainActivity.class);
                            it.putExtra("toMain", reg_mail);
                            startActivity(it);
                        }
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Password Mismatched", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {

                    Toast.makeText(RegistrationActivity.this,"Fields should not be empty",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
