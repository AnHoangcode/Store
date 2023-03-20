package com.example.store.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.store.R;
import com.example.store.dao.UserDAO;
import com.example.store.db.AppDatabase;
import com.example.store.model.User;

public class Login extends AppCompatActivity {

    EditText et_username, et_password;
    Button btn_login;
    TextView text_register;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        btn_login = findViewById(R.id.loginButton);
        text_register = findViewById(R.id.registerText);

        text_register.setOnClickListener(v -> {
            startActivity(new Intent(Login.this,Register.class));
        });

        btn_login.setOnClickListener(v -> {
            String accountText = et_username.getText().toString();
            String passwordText = et_password.getText().toString();
            if (accountText.isEmpty() || passwordText.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please, fill the required content", Toast.LENGTH_SHORT).show();
            }else{
                //Perform Query
                AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
                UserDAO userDAO = db.userDAO();
                User currentUser = userDAO.authenticate(accountText,passwordText);
                if (currentUser != null){
                    if (!currentUser.getAccount().equals("admin")){
                        SharedPreferences userPre = getApplicationContext().getSharedPreferences("userPre", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = userPre.edit();
                        editor.putInt("USER_ID", currentUser.getId());
                        editor.apply();
                        startActivity(new Intent(Login.this,HomeScreen.class));
                        Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}