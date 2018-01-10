package com.jkgupta.android.jobseek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jkgupta.android.jobseek.database.DbCreater;

public class MainActivity extends AppCompatActivity {
    RegistrationModal registrationModal = new RegistrationModal();
    EditText name, email, password;
    RadioButton rb;
    private RadioGroup radioGroup;
    private DbCreater dbCreater = new DbCreater(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        Log.v("jobseek_mainactivity", "" + radioGroup);
        Log.v("jobseek_mainactivity", "" + rb);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb = group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button reg_Button = findViewById(R.id.bt_reg_signup);
        Button login_Button = findViewById(R.id.bt_reg_login);
        name = findViewById(R.id.et_reg_name);
        email = findViewById(R.id.et_reg_email);
        password = findViewById(R.id.et_reg_pass);
        //rb = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());

        reg_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkValidation = validation();
                Log.v("jobseek_recactivity", "" + checkValidation);
                if (checkValidation) {

                registrationModal.setName(name.getText().toString());
                registrationModal.setEmail(email.getText().toString());
                registrationModal.setPassword(password.getText().toString());
                registrationModal.setUser_type(rb.getText().toString());
                    Log.v("jobseek_mainactivity", registrationModal.getName());
                    Log.v("jobseek_mainactivity", registrationModal.getEmail());
                    Log.v("jobseek_mainactivity", registrationModal.getPassword());
                    Log.v("jobseek_mainactivity", registrationModal.getUser_type());
                dbCreater.addUser(registrationModal);
                Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    public boolean validation() {
        String checkName = name.getText().toString();
        String checkEmail = email.getText().toString();
        String checkPassword = password.getText().toString();
        boolean validationStatus = true;

        if (checkName.matches("")) {
            validationStatus = false;
            Toast.makeText(MainActivity.this, "Fill Your Name", Toast.LENGTH_SHORT).show();
        } else if (checkEmail.matches("")) {
            validationStatus = false;
            Toast.makeText(MainActivity.this, "Fill Your Email", Toast.LENGTH_SHORT).show();
        } else if (checkPassword.matches("")) {
            validationStatus = false;
            Toast.makeText(MainActivity.this, "Fill Your Password", Toast.LENGTH_SHORT).show();
        } else if (null == rb) {
            validationStatus = false;
            Toast.makeText(MainActivity.this, "Select Any Type", Toast.LENGTH_SHORT).show();
        }
        return validationStatus;
    }


}
