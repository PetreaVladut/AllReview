package com.example.teest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity6 extends AppCompatActivity {

    Intent i;
    Button buton;
    String author;
    public Drawable bitmap_to_drawable(int res, int width, int height) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), res);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        Drawable drawable = new BitmapDrawable(getResources(), resizedBitmap);
        return drawable;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Toolbar toolbar = findViewById(R.id.toolbar_item3);
        setSupportActionBar(toolbar);
        setTitle("AllReview");
        toolbar.setLogo(bitmap_to_drawable(R.drawable.idk3, 90, 80));
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        buton=findViewById(R.id.button3);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(MainActivity6.this,MainActivity2.class);
                startActivity(i);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        i=new Intent(MainActivity6.this,MainActivity2.class);
                        startActivity(i);
                        return true;
                    case R.id.navigation_account:
                        i=new Intent(MainActivity6.this,MainActivity6.class);
                        startActivity(i);
                        return true;
                    case R.id.navigation_newReview:
                        i=new Intent(MainActivity6.this,MainActivity3.class);
                        startActivity(i);
                        return true;
                }
                return false;
            }
        });
    }
}