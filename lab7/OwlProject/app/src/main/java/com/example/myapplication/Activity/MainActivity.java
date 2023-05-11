package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Adapter.CategoryAdapter;

import com.example.myapplication.Adapter.RecommendedAdapter;
import com.example.myapplication.Adapter.UserAdapter;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.Model.CategoryDomain;
import com.example.myapplication.Model.UserDomain;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList, productRecycler;
        static ArrayList<UserDomain> orderlist = new ArrayList<>();
    static ArrayList<UserDomain> orderlist1 = new ArrayList<>();
    static    ArrayList<CategoryDomain> categoryList = new ArrayList<>();
    static ArrayList<UserDomain> fullOrderlist = new ArrayList<>();
    public static RecommendedAdapter recommendedAdapter;
    static UserAdapter priceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
        setProductRecycler(orderlist);
        fullOrderlist.addAll(orderlist);
        EditText searchEditText = findViewById(R.id.editTextTextPersonName);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    private void filter(String text) {
        ArrayList<UserDomain> filteredList = new ArrayList<>();
        for (UserDomain item : fullOrderlist) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        orderlist.clear();
        orderlist.addAll(filteredList);
        priceAdapter.notifyDataSetChanged();
    }
    private void setProductRecycler(ArrayList<UserDomain> productList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        productRecycler = findViewById(R.id.ProductRecycler);
        productRecycler.setLayoutManager(layoutManager);
        priceAdapter = new UserAdapter(this, productList);
        productRecycler.setAdapter(priceAdapter);
        productRecycler.setHasFixedSize(true);
    }

    private void bottomNavigation(){
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn =findViewById(R.id.cartBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }
    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recyclerView);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        orderlist.add(new UserDomain("Игорь","teach","Учитель истории с многолетним опытом ",1800.0 , 5.0, 90,1,1,"teacher",12));
        orderlist.add(new UserDomain("Петр","teach","Учитель химии с многолетним опытом",800.0 , 5.0, 3,2,1,"teacher",2));
        orderlist.add(new UserDomain("Иван","teach","Учитель математики с многолетним опытом",300.0 , 5.0, 3,3,1,"teacher",14));
        orderlist.add(new UserDomain("Ян","teach","Учитель информатики с многолетним опытом",600.0 , 5.0, 3,4,1,"teacher",2));
        orderlist.add(new UserDomain("Сергей","teach","Учитель физики с многолетним опытом",1000.0 , 5.0, 3,5,1,"teacher",5));
        orderlist.add(new UserDomain("Павел","teach","Учитель химии с многолетним опытом",400.0 , 5.0, 3,2,1,"teacher",6));
        adapter2 = new RecommendedAdapter(orderlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.view1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        categoryList.add(new CategoryDomain("Физика", "pic1",5));
        categoryList.add(new CategoryDomain("Химия", "pic5",2));
        categoryList.add(new CategoryDomain("Математика", "pic2",3));
        categoryList.add(new CategoryDomain("Информатика", "pic3",4));
        categoryList.add(new CategoryDomain("История", "pic4",1));
        adapter =new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    public static void showOrderByCategory(int category){
        orderlist.clear();
        orderlist.addAll(fullOrderlist);
        List<UserDomain> filterOrder = new ArrayList<>();
        for(UserDomain c: fullOrderlist){
            if(c.getCat_id()==category)
                filterOrder.add(c);
        }
        orderlist.clear();
        orderlist.addAll(filterOrder);
        priceAdapter.notifyDataSetChanged();
    }
}