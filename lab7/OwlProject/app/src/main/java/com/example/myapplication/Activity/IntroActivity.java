package com.example.myapplication.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.Model.UserDomain;
import com.example.myapplication.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Button startButton= findViewById(R.id.reg_button);
        Button login_button = findViewById(R.id.login_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,SignUpActivity.class));


            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }
        });


    }


    public static class ShowDetailActivity extends AppCompatActivity {
        private TextView addToCart;
        private TextView titleTxt, feeTxt,descriptionTxt,numberOrderTxt,totalPriceTxt,starTxt,textWeightTxt,timeTxt;
        private ImageView plusBtn,minusBtn,picFood;
        private UserDomain object;
        private int numberOrder = 1;
        private ManagementCart managementCart;
        private Button login_button;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_detail);


            managementCart = new ManagementCart(this);
            initView();
            getBundle();
        }

        private void getBundle() {
            object = (UserDomain)getIntent().getSerializableExtra("object");
            int drawableResourceId =this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
            Glide.with(this).load(drawableResourceId).into(picFood);
            titleTxt.setText(object.getTitle());
            feeTxt.setText(object.getPrice()+"руб");
            descriptionTxt.setText(object.getDescription());
            numberOrderTxt.setText(String.valueOf(numberOrder));
            textWeightTxt.setText(object.getExp()+" лет");
            starTxt.setText(object.getStar()+"");
            timeTxt.setText(object.getTime()+ " мин");
            totalPriceTxt.setText(Math.round(numberOrder * object.getPrice())+"руб");

            plusBtn.setOnClickListener(v -> {
                numberOrder = numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText(Math.round(numberOrder * object.getPrice())+"руб");
            });

            minusBtn.setOnClickListener(v -> {
                if(numberOrder>1){
                    numberOrder = numberOrder-1;

                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText(Math.round(numberOrder * object.getPrice())+"руб");

            });
            addToCart.setOnClickListener(v -> {
                object.setNumberInHour(numberOrder);
                managementCart.insertUser(object);
            });
        }


        private void initView(){

            addToCart=findViewById(R.id.addToCartBtn);
            titleTxt = findViewById(R.id.titleTxt);
            feeTxt = findViewById(R.id.priceTxt);
            descriptionTxt = findViewById(R.id.descriptionTxt);
            numberOrderTxt = findViewById(R.id.numberItemTxt);
            plusBtn = findViewById(R.id.plusCardBtn);
            minusBtn= findViewById(R.id.minusCardBtn);
            picFood =findViewById(R.id.foodPic);
            totalPriceTxt =findViewById(R.id.TotalPriceTxt);
            starTxt= findViewById(R.id.startTxt);
            textWeightTxt = findViewById(R.id.textVicaloriesTxt);
            timeTxt = findViewById(R.id.timeTxt);
        }
    }
}