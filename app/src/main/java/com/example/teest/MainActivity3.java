package com.example.teest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teest.ui.AddressAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    TextView name;
    ImageView img;
    TextView address;
    TextView comm;
    RatingBar rate;
    Spinner cat;
    Intent i;
    MyDatabaseHelper dbHandler;
    Button butonSav;
    Button butonUp;
    ArrayList<String> data;
    private static final int REQUEST_IMAGE_PICKER = 1;

    public Drawable bitmap_to_drawable(int res, int width, int height) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), res);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        Drawable drawable = new BitmapDrawable(getResources(), resizedBitmap);
        return drawable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data=new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            data.add(""); // Add empty string or any default value you want
        }
        LayoutInflater inflater= LayoutInflater.from(MainActivity3.this);
        View theInflatedView = inflater.inflate(R.layout.activity_main3, null);
        setContentView(theInflatedView);
        AutoCompleteTextView searchEditText = findViewById(R.id.searchEditText);
        AddressAdapter adapter = new AddressAdapter(this);
        searchEditText.setAdapter(adapter);
        searchEditText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Address selectedAddress = (Address) parent.getItemAtPosition(position);
                String simplifiedAddress = selectedAddress.getAddressLine(0);

                // Use the simplifiedAddress string for further processing or display
                // For example, set it to a TextView
                searchEditText.setText(simplifiedAddress);
                // Handle the selected address, e.g., display it on the map or store it in a variable.
            }
        });
        String[] items = {"Restaurant", "Cinema", "Barber","Dentist"};
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapterdrop = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapterdrop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterdrop);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        i=new Intent(MainActivity3.this,MainActivity2.class);
                        startActivity(i);
                        return true;
                    case R.id.navigation_account:
                        i=new Intent(MainActivity3.this,MainActivity6.class);
                        startActivity(i);
                        return true;
                    case R.id.navigation_newReview:
                        i=new Intent(MainActivity3.this,MainActivity3.class);
                        startActivity(i);
                        return true;
                }
                return false;
            }
        });
        /*binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as to  p level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main3);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);*/
        Toolbar toolbar = findViewById(R.id.toolbar_item2);
        setSupportActionBar(toolbar);
        setTitle("AllReview");
        toolbar.setLogo(bitmap_to_drawable(R.drawable.idk3, 90, 80));

        /*FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000)
                .setFastestInterval(2000);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                // Handle received location updates
                Location location = locationResult.getLastLocation();
                // Do something with the location
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);*/
        name = findViewById(R.id.editTextTextPersonName);
        img = findViewById(R.id.imageView);
        address = findViewById(R.id.searchEditText);
        comm = findViewById(R.id.commentInput);
        cat = findViewById(R.id.spinner);
        rate = findViewById(R.id.ratingBar);
        butonSav =findViewById(R.id.button4);
        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new MyDatabaseHelper(MainActivity3.this);
        butonUp=findViewById(R.id.selectButton);
        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data.set( 4,parent.getItemAtPosition(position).toString());
                // Do something with the selected date
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case when nothing is selected
                data.set( 4,"restaurant");
            }
        });
        butonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch image picker intent
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICKER);
            }
        });
        // below line is to add on click listener for our add course button.
        butonSav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                data.set(0, name.getText().toString());
                data.set(2, address.getText().toString());
                data.set(3, comm.getText().toString());
                data.set(5, String.valueOf(rate.getRating()));
                data.set(6,"Vlad_Petrea420");

                // validating if the text fields are empty or not.
                if (data.get(0).isEmpty() && data.get(2).isEmpty() && data.get(3).isEmpty() && data.get(5).isEmpty()) {
                    Toast.makeText(MainActivity3.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.insertData(data);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity3.this, "Review has been succesfully saved.", Toast.LENGTH_SHORT).show();
                i=new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICKER && resultCode == RESULT_OK && data != null) {
            // Handle the selected image
            Uri imageUri = data.getData();
            // Display the image in the ImageView
            img.setImageURI(imageUri);
            // Upload the image to the server
            uploadImage(imageUri);
        }
    }

    private void uploadImage(Uri imageUri) {
        // Implement the logic to upload the image to the server
        // You can use a network library like Retrofit or OkHttp for this purpose
        // Make a network request and send the image data
        // Handle the server's response in the appropriate callback or listener
        data.set(1,imageUri.toString());
    }
/*public class UploadActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICKER = 1;

    private ImageView imageView;
    private Button selectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        imageView = findViewById(R.id.imageView);
        selectButton = findViewById(R.id.selectButton);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch image picker intent
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICKER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICKER && resultCode == RESULT_OK && data != null) {
            // Handle the selected image
            Uri imageUri = data.getData();
            // Display the image in the ImageView
            imageView.setImageURI(imageUri);
            // Upload the image to the server
            uploadImage(imageUri);
        }
    }

    private void uploadImage(Uri imageUri) {
        // Implement the logic to upload the image to the server
        // You can use a network library like Retrofit or OkHttp for this purpose
        // Make a network request and send the image data
        // Handle the server's response in the appropriate callback or listener
    }
}*/

}