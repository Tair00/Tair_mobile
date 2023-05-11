package com.example.myapplication.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.IntroActivity;
import com.example.myapplication.Model.UserDomain;
import com.example.myapplication.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ProductViewHolder> {
    Context context;
    ArrayList<UserDomain> user;

    public UserAdapter(Context context, ArrayList<UserDomain> products) {
        this.context = context;
        this.user = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productsItems = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new UserAdapter.ProductViewHolder(productsItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.productTitles.setText(user.get(position).getTitle());
        holder.productPrice.setText(String.valueOf(user.get(position).getPrice()));

        int imageId = holder.itemView.getContext().getResources()
                .getIdentifier( user.get(position).getPic(),"drawable",holder.itemView.getContext()
                        .getPackageName());
        Glide.with(holder.itemView.getContext()).load(imageId).into(holder.productImage);
        holder.productTitles.setText(user.get(position).getTitle());
        holder.productPrice.setText(String.valueOf(user.get(position).getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)context,
                        new Pair<View, String>(holder.productImage,"productImage"));
                Intent intent = new Intent(holder.itemView.getContext(), IntroActivity.ShowDetailActivity.class);
                intent.putExtra("object",user.get(position));
                holder.itemView.getContext().startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView productTitles, productPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage= itemView.findViewById(R.id.pic);
            productTitles= itemView.findViewById(R.id.title);
            productPrice= itemView.findViewById(R.id.fee);

        }
    }
}
