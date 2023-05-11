package com.example.myapplication.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.Model.UserDomain;
import com.example.myapplication.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, description, numberOrderTxt, totalPriceTxt,starTxt,weightTxt,timeTxt;
    private ImageView plusBtn, minusBtn,picFood;
    private UserDomain object;
    private int numberOrder;
    private ManagementCart managementCart;



    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        managementCart = new ManagementCart(this);
        iniView();
        getBundle();

    }

    private void getBundle() {
        object = (UserDomain)getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(picFood);
        titleTxt.setText(object.getTitle());
        feeTxt.setText(object+ " руб");
        description.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        weightTxt.setText(object.getExp() + " лет");
        starTxt.setText(object.getStar()+"");
        timeTxt.setText(object.getTime()+" дней");
        totalPriceTxt.setText(numberOrder*object.getPrice()+" руб");
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText(numberOrder*object.getPrice()+"руб");

            }
        });


        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder>1){
                    numberOrder=numberOrder-1;


                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText(numberOrder*object.getPrice()+"руб");
            }
        });
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInHour(numberOrder);
                managementCart.insertUser(object);
            }
        });
    }

    private void iniView() {
        addToCartBtn =findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        description = findViewById(R.id.descriptionTxt);
        numberOrderTxt =findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCardBtn);
        minusBtn = findViewById(R.id.minusCardBtn);
        picFood = findViewById(R.id.foodPic);
        totalPriceTxt = findViewById(R.id.TotalPriceTxt);
        starTxt =findViewById(R.id.startTxt);
        weightTxt = findViewById(R.id.textVicaloriesTxt);
        timeTxt = findViewById(R.id.timeTxt);

    }
}
