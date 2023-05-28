package com.example.teest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class MainActivity5 extends AppCompatActivity {

    Intent i;
    TextView email;
    TextView password;
    TextView password2;
    Button butonel;
    Button butonel2;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        butonel = findViewById(R.id.button);
        butonel2 = findViewById(R.id.button2);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        password2= (EditText) findViewById(R.id.password2);
        butonel2.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {
                                           try {
                                               if (password.getText().toString().equals(password2.getText().toString())) {
                                                   value = new Utils().search_user(email.getText().toString(), password.getText().toString(), MainActivity5.this);
                                                   if (value == 2) {
                                                       //valueo=add_user(email,password,this);
                                                       Toast toast = Toast.makeText(getApplicationContext(), "sign in succesful", Toast.LENGTH_SHORT);
                                                       toast.show();
                                                       i = new Intent(MainActivity5.this, MainActivity2.class);
                                                       startActivity(i);
                                                   } else {
                                                       Toast toast = Toast.makeText(getApplicationContext(), "there already is an account with this email", Toast.LENGTH_SHORT);
                                                       toast.show();
                                                   }
                                               }
                                               else {
                                                   Toast toast = Toast.makeText(getApplicationContext(), "passwords are not the same", Toast.LENGTH_SHORT);
                                                   toast.show();
                                               }
                                               } catch(FileNotFoundException e){
                                                   throw new RuntimeException(e);
                                               }
                                       }
                                   }
        );
        butonel.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            i = new Intent(MainActivity5.this, MainActivity4.class);
                                            startActivityForResult(i, 1);
                                        }
                                    }
        );
    }
}