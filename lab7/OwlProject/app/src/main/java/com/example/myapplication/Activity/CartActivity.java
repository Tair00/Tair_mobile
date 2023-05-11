package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.myapplication.Model.UserDomain;
import com.google.android.material.behavior.SwipeDismissBehavior;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.Interface.ChangeNumberItemsListener;
import com.example.myapplication.R;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt,taxTxt,deliveryTxt ;
    public TextView totalTxt;
private CartListAdapter cartListAdapter;
    private double tax;
    private ScrollView scrollView;
    private ConstraintLayout orderbtn;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        managementCart= new ManagementCart(this);

        initView();
        initList();
        bottomNavigation();

    }

    protected void bottomNavigation(){
    }

    protected void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        // Добавляем SwipeDismissBehavior для удаления элементов при свайпе
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(cartListAdapter) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                UserDomain cartItem = ((CartListAdapter) adapter).getItem(position);
                ((CartListAdapter) adapter).removeItem(position);
                calculateCard();

                Snackbar.make(recyclerViewList, "Удалили из избранных", Snackbar.LENGTH_LONG)
                        .setAction("Вернуть", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ((CartListAdapter) adapter).restoreItem(cartItem, position);
                                calculateCard();
                            }
                        })
                        .show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewList);

        recyclerViewList.setAdapter(adapter);

        if (managementCart.getListCart().isEmpty()) {
            scrollView.setVisibility(View.GONE);
        } else {
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    protected void calculateCard() {
        double percentTax = 0.03;
        double delivery = 500;
        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round((managementCart.getTotalFee() * 100.0) / 100.0);
//        totalFeeTxt.setText(itemTotal + " руб");
//        totalTxt.setText(total + " руб");
    }

    protected void initView() {
//        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        recyclerViewList = findViewById(R.id.view);
        scrollView = findViewById(R.id.scrollView);
    }
}