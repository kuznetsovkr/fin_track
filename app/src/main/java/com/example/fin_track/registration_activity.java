package com.example.fin_track;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class registration_activity extends AppCompatActivity {
    DatabaseHelper db;
    EditText user_email, user_password,user_confirm_password,user_firstname,user_secondname;
    Button button_register;
    ImageButton button_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DatabaseHelper(this);
        user_email = (EditText)findViewById(R.id.editTextTextEmailAddress);
        user_password = (EditText)findViewById(R.id.editTextTextPassword);
        user_confirm_password = (EditText)findViewById(R.id.editTextTextConfirmPassword);
        user_firstname = (EditText)findViewById(R.id.editTextTextPersonFirstName);
        user_secondname = (EditText)findViewById(R.id.editTextTextPersonSecondName);
        button_register = (Button)findViewById(R.id.button_register);
        button_back = (ImageButton)findViewById(R.id.img_button_back);


        button_back.setOnClickListener(view -> {
            Intent to_signin = new Intent(registration_activity.this, MainActivity.class);
            startActivity(to_signin);
        });

        button_register.setOnClickListener(v -> {
            String s1 = user_email.getText().toString();
            String s2 = user_password.getText().toString();
            String s3 = user_confirm_password.getText().toString();
            String s4= user_firstname.getText().toString();
            String s5 = user_secondname.getText().toString();


            if (s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){
                Toast.makeText(getApplicationContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            }
            else{
                if(s2.equals(s3)){
                    Boolean chkemail = db.chkemail(s1);
                    if (chkemail==true){
                        Boolean insert = db.insert(s1,s2,s4,s5);
                        if (insert==true){
                            Toast.makeText(getApplicationContext(), "Успешная регистрация", Toast.LENGTH_SHORT).show();
                            Intent to_signin = new Intent(registration_activity.this, MainActivity.class);
                            startActivity(to_signin);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Аккаунт с такием email уже зарегистрирован", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}