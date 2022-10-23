package com.example.fin_track;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText user_email, user_password;
    Button button_signin,button_register;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        user_email = (EditText)findViewById(R.id.editTextTextEmailAddress2);
        user_password = (EditText)findViewById(R.id.editTextTextPassword2);
        button_signin = (Button)findViewById(R.id.sign_in_button);
        button_register = (Button)findViewById(R.id.button_register_main);

        button_signin.setOnClickListener(v -> {
            String email = user_email.getText().toString();
            String password = user_password.getText().toString();
            Boolean Chkemailpass = db.emailpassword(email, password);
            if (Chkemailpass==true) {
                Toast.makeText(getApplicationContext(), "Успешная авторизация", Toast.LENGTH_SHORT).show();
                Intent to_empty = new Intent(MainActivity.this, EmptyActivity.class);
                //здесь переход на следующую активити при успешной авторизации
                //где EmptyActivity это новый джава класс
                startActivity(to_empty);
            }
            else
                Toast.makeText(getApplicationContext(), "Неправильный email или пароль", Toast.LENGTH_SHORT).show();
        });

        button_register.setOnClickListener(v -> {
            Intent to_registration = new Intent(MainActivity.this, registration_activity.class);
            startActivity(to_registration);
        });
    }
}