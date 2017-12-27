package com.jkgupta.android.jobseek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jkgupta.android.jobseek.database.DbCreater;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private LoginModel LoginDataHolder = new LoginModel();
    private DbCreater dbCreater = new DbCreater(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editText_login_email);
        editTextPassword = (EditText) findViewById(R.id.editText_login_password);
        Button LoginButton = (Button) findViewById(R.id.button_login_login);
        Button SignupButton = (Button) findViewById(R.id.button_login_signup);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginDataHolder.setEmail(editTextEmail.getText().toString());
                LoginDataHolder.setPassword(editTextPassword.getText().toString());
                UserModel userModel=dbCreater.getUser(LoginDataHolder);
                if(userModel.isUser_in_Db()){

                    Log.v("Pune",userModel.getName()+"Hi");
                    Log.v("Pune",userModel.getUser_Id()+"Hi");
                    Toast.makeText(LoginActivity.this,userModel.getName(), Toast.LENGTH_SHORT).show();

                }
                else
                    {
                        Toast.makeText(LoginActivity.this,"Detail Not Match", Toast.LENGTH_SHORT).show();
                    }



            }
        });

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}