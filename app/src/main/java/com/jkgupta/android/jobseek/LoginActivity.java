package com.jkgupta.android.jobseek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editText_login_email);
        editTextPassword = findViewById(R.id.editText_login_password);
        Button LoginButton = findViewById(R.id.button_login_login);
        Button SignupButton = findViewById(R.id.button_login_signup);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbCreater dbCreater = new DbCreater(getBaseContext());
                LoginDataHolder.setEmail(editTextEmail.getText().toString());
                LoginDataHolder.setPassword(editTextPassword.getText().toString());
                Log.v("jobseek_loginactivity", "Email:" + editTextEmail.getText().toString() + "/" + "Password:" + editTextPassword.getText().toString());
                UserModel userModel=dbCreater.getUser(LoginDataHolder);
                if(userModel.isUser_in_Db()){

                    String user_type_rec="Recruiter";
                    String user_type_user = "Candidate";
                    Log.v("jobseek_loginactivity", "check type req" + userModel.getUser_Type().equals(user_type_rec));
                    Log.v("jobseek_loginactivity", "check type candidate" + userModel.getUser_Type().equals(user_type_user));

                    if(userModel.getUser_Type().equals(user_type_rec))

                    {
                        Intent intent = new Intent(getBaseContext(), RecruiterActivity.class);
                        Bundle b = new Bundle();
                        b.putString("user_type", String.valueOf(userModel.getUser_Type()));
                        b.putString("user_id", String.valueOf(userModel.getUser_Id()));
                        b.putString("email", userModel.getEmail());
                        b.putString("name", String.valueOf(userModel.getName()));


                        intent.putExtras(b);
                        startActivity(intent);
                    } else if(userModel.getUser_Type().equals(user_type_user)) {
                        Log.v("candidate",userModel.getUser_Type().equals(user_type_user)+"");
                        Intent intent = new Intent(getBaseContext(), CandidateActivity.class);
                        Bundle b = new Bundle();
                        b.putString("user_type", String.valueOf(userModel.getUser_Type()));
                        b.putString("user_id", String.valueOf(userModel.getUser_Id()));
                        b.putString("email", userModel.getEmail());
                        b.putString("name", String.valueOf(userModel.getName()));


                        intent.putExtras(b);
                        startActivity(intent);

                    }

                    Log.v("jobseek_loginactivity", userModel.getName());
                    Log.v("jobseek_loginactivity", userModel.getUser_Id());


                } else {
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

    @Override
    public void onBackPressed() {

    }
}
