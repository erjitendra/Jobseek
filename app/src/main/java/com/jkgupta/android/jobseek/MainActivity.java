package com.jkgupta.android.jobseek;

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
    EditText name, email, password, user_type;
    RadioButton rb;
    private RadioGroup radioGroup;
    private DbCreater dbCreater = new DbCreater(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button reg_Button = findViewById(R.id.bt_reg_signup);
        name = findViewById(R.id.et_reg_name);
        email = findViewById(R.id.et_reg_email);
        password = findViewById(R.id.et_reg_pass);
        //rb = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());

        reg_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                registrationModal.setName(name.getText().toString());
                registrationModal.setEmail(email.getText().toString());
                registrationModal.setPassword(password.getText().toString());
                registrationModal.setUser_type(rb.getText().toString());
                Log.v("Pune", registrationModal.getPassword());dbCreater.addUser(registrationModal);


                Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
