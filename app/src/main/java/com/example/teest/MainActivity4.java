package com.example.teest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.io.FileNotFoundException;

import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    Button butonel;
    Button butonel2;
    TextView email;
    TextView parola;
    int value;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        butonel = findViewById(R.id.button);
        butonel2 = findViewById(R.id.button2);
        email = (EditText) findViewById(R.id.email);
        parola = (EditText) findViewById(R.id.password);
        butonel.setOnClickListener(new OnClickListener() {
                                       public void onClick(View v) {
                                           try {
                                               value = new Utils().search_user(email.getText().toString(), parola.getText().toString(), MainActivity4.this);
                                           switch(value) {
                                               case 0:
                                               {
                                                   Toast toast = Toast.makeText(getApplicationContext(), "log in succesful", Toast.LENGTH_SHORT);
                                                   toast.show();
                                                   i = new Intent(MainActivity4.this, MainActivity2.class);
                                                   startActivity(i);
                                                   break;
                                               }
                                               case 1:
                                               {
                                                   Toast toast = Toast.makeText(getApplicationContext(), "password incorrecte", Toast.LENGTH_SHORT);
                                                   toast.show();
                                                   break;
                                               }
                                               case 2:
                                               {
                                                   Toast toast = Toast.makeText(getApplicationContext(), "email not registered", Toast.LENGTH_SHORT);
                                                   toast.show();
                                                   break;
                                               }
                                           }
                                           } catch (FileNotFoundException e) {
                                               throw new RuntimeException(e);
                                           }
                                       }
                                   }
        );
        butonel2.setOnClickListener(new OnClickListener() {
                                        public void onClick(View v) {
                                            i = new Intent(MainActivity4.this, MainActivity5.class);
                                            startActivityForResult(i, 1);
                                        }
                                    }
            );
    }
    }