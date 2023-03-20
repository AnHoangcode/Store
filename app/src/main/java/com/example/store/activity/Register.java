package com.example.store.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.store.R;
import com.example.store.dao.UserDAO;
import com.example.store.db.AppDatabase;
import com.example.store.model.User;

public class Register extends AppCompatActivity {
    EditText et_username, et_password, et_fullname, et_gender, et_email;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        et_fullname = findViewById(R.id.fullname);
        et_gender = findViewById(R.id.gender);
        et_email = findViewById(R.id.email);
        btn_register = findViewById(R.id.registerButton);

        btn_register.setOnClickListener(v -> {
            String accountText = et_username.getText().toString();
            String passwordText = et_password.getText().toString();
            String fullnameText = et_fullname.getText().toString();
            String genderText = et_gender.getText().toString();
            String emailText = et_email.getText().toString();
            if (accountText.isEmpty() || passwordText.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please, fill the required content", Toast.LENGTH_SHORT).show();
            }else {
                //Perform Query
                AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
                UserDAO userDAO = db.userDAO();
                User currentUser = userDAO.authenticate(accountText, passwordText);
                if (currentUser == null){
                    User newUser = new User(accountText,passwordText,fullnameText,genderText,emailText);
                    userDAO.insert(newUser);
                    Toast.makeText(getApplicationContext(), "Register successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Login.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Existed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}