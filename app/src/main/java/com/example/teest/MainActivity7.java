package com.example.teest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity7 extends AppCompatActivity {

    Intent i;
    TextView name;
    ImageView img;
    TextView address;
    TextView comm;
    RatingBar rate;
    TextView cat;
    TextView author;
    Button buton;
    Review review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        i=getIntent();
        review=new Review();
        if (i.hasExtra("PARAMETER")) {
            review = (Review) i.getSerializableExtra("PARAMETER");
            // Use the review object in your activity as needed
        }
        name = findViewById(R.id.textView7);
        img = findViewById(R.id.imageView6);
        address = findViewById(R.id.textView15);
        comm = findViewById(R.id.textView13);
        cat = findViewById(R.id.textView17);
        author = findViewById(R.id.textView16);
        rate = findViewById(R.id.ratingBar2);
        name.setText(review.getName());
        img.setImageURI(Uri.parse(review.getImage()));
        address.setText(review.getAddress());
        comm.setText(review.getComment());
        cat.setText(review.getCategory());
        author.setText(review.getAuthor());
        rate.setRating(review.getRating());
        rate.setIsIndicator(true);
        buton=findViewById(R.id.button5);
        buton.setEnabled(false);
        buton.setVisibility(View.INVISIBLE);
        if(MainActivity.author.equals(review.getAuthor()))
        {
            buton.setEnabled(true);
            buton.setVisibility(View.VISIBLE);
        }
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.author.equals(review.getAuthor()))
                {
                    MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity7.this);
                    if(dbHelper.deleteData(review.getId())==1)
                    {
                        Toast.makeText(MainActivity7.this, "Review sters cu success", Toast.LENGTH_SHORT).show();
                        i=new Intent(MainActivity7.this,MainActivity2.class);
                        startActivity(i);
                    }
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        i=new Intent(MainActivity7.this,MainActivity2.class);
                        startActivity(i);
                        return true;
                    case R.id.navigation_account:
                        i=new Intent(MainActivity7.this,MainActivity6.class);
                        startActivity(i);
                        return true;
                    case R.id.navigation_newReview:
                        i=new Intent(MainActivity7.this,MainActivity3.class);
                        startActivity(i);
                        return true;
                }
                return false;
            }
        });
    }
}