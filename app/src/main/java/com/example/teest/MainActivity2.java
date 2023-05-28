package com.example.teest;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Intent i;
    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    private NavController navController;
    private NavigationView navigationView;
    ImageView imgHom;
    ImageView imgAcc;
    ImageView imgRev;
    List<Review> data=new ArrayList<>();
    ArrayList<Scrollable_Object> myDataset = new ArrayList<Scrollable_Object>();
    RecyclerView recyclerView;

    public Drawable bitmap_to_drawable(int res, int width, int height) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), res);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        Drawable drawable = new BitmapDrawable(getResources(), resizedBitmap);
        return drawable;
    }
    public List<Review> filter(List<Review> data, String s, int parameter) //0 cat, 1 name, 2 author
    {
        s=s.toLowerCase();
        List<Review> filtered_data=new ArrayList<>();
        switch(parameter)
        {
            case 0: {
                for (Review item : data) {
                    if ((item.getCategory()).toLowerCase().equals(s))
                        filtered_data.add(item);
                }
                break;
            }
            case 1:{
                for(Review item:data)
                {
                    if((item.getName()).toLowerCase().contains(s))
                        filtered_data.add(item);
                }
                break;
            }
            case 2:
            {
                for (Review item : data) {
                    if ((item.getAuthor()).toLowerCase().equals(s))
                        filtered_data.add(item);
                }
                break;
            }
        }
        return filtered_data;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Permission already granted, proceed with reading from internal storage
            // ...
        } else {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
        if (ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Permission already granted, proceed with reading from internal storage
            // ...
        } else {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(MainActivity2.this);
        //SQLiteDatabase database = dbHelper.getReadableDatabase();

        i=getIntent();
        List<Review> data2=new ArrayList<>();
        data= ((MyDatabaseHelper) dbHelper).getAllData();
        if (i.hasExtra("PARAMETER")) {
            String parameter = i.getStringExtra("PARAMETER");
            data2= filter(data,parameter,0);
        }
        else {
            data2=data;
        }
        for (Review item : data2) {
            myDataset.add(new Scrollable_Object(item.getName(), item.getImage(), item.getId()));
        }
        LayoutInflater inflater= LayoutInflater.from(MainActivity2.this);
        View theInflatedView = inflater.inflate(R.layout.activity_main2, null);
        setContentView(theInflatedView);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new MyAdapter(myDataset,MainActivity2.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        EditText searchEditText = findViewById(R.id.editTextTextPersonName2);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // Perform search/filtering logic here

                    String query = v.getText().toString().trim();
                    List<Review> data2=new ArrayList<>();
                    data2 = filter(data, searchEditText.getText().toString(),1);
                    myDataset.clear();
                    for (Review item : data2) {
                        myDataset.add(new Scrollable_Object(item.getName(), item.getImage(), item.getId()));
                    }
                    recyclerView.setAdapter(new MyAdapter(myDataset,MainActivity2.this));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                    return true;
                }
                return false;
            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        //        R.id.menu_item1).build();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main3);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(theInflatedView.navView, navController);

        Toolbar toolbar = findViewById(R.id.toolbar_item);
        setSupportActionBar(toolbar);
        setTitle("AllReview");

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(MainActivity2.this);
        navigationView.bringToFront();

        imgHom=findViewById(R.id.imageView4);
        imgHom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                        i=new Intent(MainActivity2.this,MainActivity2.class);
                        startActivity(i);
            }
        });
        imgAcc=findViewById(R.id.imageView7);
        imgAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                       i=new Intent(MainActivity2.this,MainActivity6.class);
                        startActivity(i);
            }
        });
        imgRev=findViewById(R.id.imageView8);
        imgRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                        i=new Intent(MainActivity2.this,MainActivity3.class);
                        startActivity(i);
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item1:
                i=new Intent(MainActivity2.this,MainActivity2.class);
                i.putExtra("PARAMETER","restaurant");
                startActivity(i);
                return true;
            case R.id.menu_item2:
                i=new Intent(MainActivity2.this,MainActivity2.class);
                i.putExtra("PARAMETER","cinema");
                startActivity(i);
                return true;
            case R.id.menu_item3:
                i=new Intent(MainActivity2.this,MainActivity2.class);
                i.putExtra("PARAMETER","barber");
                startActivity(i);
                return true;
            case R.id.menu_item4:
                i=new Intent(MainActivity2.this,MainActivity2.class);
                i.putExtra("PARAMETER","dentist");
                startActivity(i);
                return true;
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}