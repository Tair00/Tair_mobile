package com.example.myapplication.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.Interface.ChangeNumberItemsListener;
import com.example.myapplication.Model.UserDomain;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<UserDomain> listUserDomains;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    private List<UserDomain> mItems;

    public CartListAdapter(List<UserDomain> items) {
        mItems = items;
    }

    public void setData(List<UserDomain> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public List<UserDomain> getItems() {
        return mItems;
    }
    public CartListAdapter(ArrayList<UserDomain> listUserDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listUserDomains = listUserDomains;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listUserDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(listUserDomains.get(position).getPrice()));
//        holder.totalEachItem.setText(Math.round(listFoodSelected.get(position).getNumberInHour() * listFoodSelected.get(position).getPrice()) + "руб");
        holder.num.setText(String.valueOf(listUserDomains.get(position).getNumberInHour()));

        int drawableReourceId = holder.itemView.getContext().getResources().getIdentifier(listUserDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableReourceId).into(holder.pic);

        SwipeDismissBehavior<View> swipe = new SwipeDismissBehavior<>();
        swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
        swipe.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(View view) {
                int adapterPosition = holder.getAdapterPosition();
                managementCart.removeItem(listUserDomains, adapterPosition, () -> {
                    notifyDataSetChanged();
                    changeNumberItemsListener.changed();
                });
            }

            @Override
            public void onDragStateChanged(int state) {

            }
        });

        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        if (params instanceof CoordinatorLayout.LayoutParams) {
            ((CoordinatorLayout.LayoutParams) params).setBehavior(swipe);
        }

        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberFood(listUserDomains, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));
        holder.minusItem.setOnClickListener(v -> managementCart.minusNumberFood(listUserDomains, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));
    }
    public void deleteItem(int position) {
        listUserDomains.remove(position);
        notifyItemRemoved(position);
    }
    public UserDomain getItem(int position) {
        return listUserDomains.get(position);
    }
    public void restoreItem(UserDomain item, int position) {
        listUserDomains.add(position, item);
        notifyItemInserted(position);
    }
    public void removeItem(int position) {
        listUserDomains.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return listUserDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, totalEachItem, num;
        ImageView pic, plusItem, minusItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
//            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}