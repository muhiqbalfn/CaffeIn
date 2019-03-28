package com.nuzul.caffein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuzul.caffein.dbSQLite.SessionManagement;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    CarouselView carouselView;
    CardView cvFood, cvDrink, cvCoffee, cvDessert, cvContact, cvSugestion;
    TextView tvUsername, tvLogout;
    //session
    SessionManagement sm;
    HashMap<String, String> loginUser;

    int[] imgCarousel = {R.drawable.carausel_1, R.drawable.carausel_2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        carouselView = findViewById(R.id.carouselView);
        cvFood = findViewById(R.id.cvFood);
        cvDrink = findViewById(R.id.cvDrink);
        cvCoffee = findViewById(R.id.cvCoffee);
        cvDessert = findViewById(R.id.cvDessert);
        cvContact = findViewById(R.id.cvContact);
        cvSugestion = findViewById(R.id.cvSugestion);
        tvUsername = findViewById(R.id.tvUsername);
        tvLogout = findViewById(R.id.tvLogout);

        //---------------------------------------------------------------------------------------
        sm = new SessionManagement(this);
        loginUser = sm.getUserInformation();

        String nama = loginUser.get(sm.KEY_USER);
        //Toast.makeText(this,"Selamat datang "+nama+" !", Toast.LENGTH_LONG).show();
        tvUsername.setText(nama);

        cvFood.setOnClickListener(this);
        cvDrink.setOnClickListener(this);
        cvCoffee.setOnClickListener(this);
        cvDessert.setOnClickListener(this);
        cvContact.setOnClickListener(this);
        cvSugestion.setOnClickListener(this);
        tvLogout.setOnClickListener(this);

        //set image carousel
        carouselView.setPageCount(imgCarousel.length);
        carouselView.setImageListener(imageListener);
    }

    //set slide carousel
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(imgCarousel[position]);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvFood:
                Intent i1 = new Intent(HomeActivity.this, FoodActivity.class);
                startActivity(i1);
                break;
            case R.id.cvDrink:
                Intent i2 = new Intent(HomeActivity.this, DrinkActivity.class);
                startActivity(i2);
                break;
            case R.id.cvCoffee:
                Intent i3 = new Intent(HomeActivity.this, CoffeeActivity.class);
                startActivity(i3);
                break;
            case R.id.cvDessert:
                Intent i4 = new Intent(HomeActivity.this, DessertActivity.class);
                startActivity(i4);
                break;
            case R.id.cvContact:
                Intent i5 = new Intent(HomeActivity.this, ContactActivity.class);
                startActivity(i5);
                break;
            case R.id.cvSugestion:
                Intent i6 = new Intent(HomeActivity.this, SugestionActivity.class);
                startActivity(i6);
                break;
            case R.id.tvLogout:
                sm.logoutUser();
                Toast.makeText(this, "Anda berhasil logout !", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_navbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                startActivity(new Intent(HomeActivity.this, CartActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}