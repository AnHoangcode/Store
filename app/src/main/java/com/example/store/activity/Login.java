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

public class Login extends AppCompatActivity {

    EditText et_username, et_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        btn_login = findViewById(R.id.loginButton);

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
                    startActivity(new Intent(Login.this,HomeScreen.class).putExtra("name",currentUser.getName()));
                }else{
                    Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}