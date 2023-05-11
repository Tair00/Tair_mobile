package com.example.myapplication.Helper;

import android.content.Context;

import com.example.myapplication.Interface.ChangeNumberItemsListener;
import com.example.myapplication.Model.UserDomain;

import java.util.ArrayList;

import android.widget.Toast;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertUser(UserDomain item){
        ArrayList<UserDomain> listOrder= getListCart();
        boolean existAlready=false;
        int n=0;
        for(int i = 0; i<listOrder.size();i++){
            if(listOrder.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=i;
                break;
            }
        }
        if(existAlready){
            listOrder.get(n).setNumberInHour(item.getNumberInHour());


        }else {
            listOrder.add(item);
        }
        tinyDB.putListObject("CardList",listOrder);
        Toast.makeText(context, "Добавлен в избранные", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<UserDomain> getListCart(){
        return tinyDB.getListObject("CardList");

    }
    public void minusNumberFood(ArrayList<UserDomain>listorder, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(listorder.get(position).getNumberInHour()==1){
            listorder.remove(position);
        }
        else {
            listorder.get(position).setNumberInHour(listorder.get(position).getNumberInHour()-1);
        }
        tinyDB.putListObject("CardList",listorder);
        changeNumberItemsListener.changed();
    }

    public void plusNumberFood(ArrayList<UserDomain> listorder, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listorder.get(position).setNumberInHour(listorder.get(position).getNumberInHour()+1);
        tinyDB.putListObject("CardList",listorder);
        changeNumberItemsListener.changed();
    }


    public Double getTotalFee(){
        ArrayList<UserDomain>listorder2=getListCart();
        double fee=0;
        for (int i = 0 ; i< listorder2.size();i++){
            fee=fee+(listorder2.get(i).getPrice()*listorder2.get(i).getNumberInHour());
        }
        return fee;
    }
    public void removeItem(ArrayList<UserDomain> listOrder, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listOrder.remove(position);
        tinyDB.putListObject("CardList", listOrder);
        changeNumberItemsListener.changed();
    }
    public void removeCart(ArrayList<UserDomain> listOrder, int position) {
        listOrder.remove(position);
        tinyDB.putListObject("CardList", listOrder);
    }
}