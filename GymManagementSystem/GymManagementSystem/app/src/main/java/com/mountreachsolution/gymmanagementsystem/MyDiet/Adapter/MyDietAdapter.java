package com.mountreachsolution.gymmanagementsystem.MyDiet.Adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mountreachsolution.gymmanagementsystem.MyDiet.Pojo.MyDietModel;

import com.mountreachsolution.gymmanagementsystem.R;

import java.util.List;

public class MyDietAdapter extends BaseAdapter {

    List<MyDietModel> myDietModels;
    Activity activity;
    TextView tv_no_records;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public MyDietAdapter(List<MyDietModel> list, Activity activity, TextView tv_no_records) {
        myDietModels = list;
        this.activity = activity;
        this.tv_no_records = tv_no_records;

        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = preferences.edit();
    }


    @Override
    public int getCount() {
        return myDietModels.size();
    }

    @Override
    public Object getItem(int position) {
        return myDietModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final MyDietAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null) {
            holder = new MyDietAdapter.ViewHolder();
            v = inflater.inflate(R.layout.lv_my_diet, null);

            holder.txt_my_diet_day = (TextView) v.findViewById(R.id.txt_my_diet_day);
            holder.txt_my_diet_meal_time = (TextView) v.findViewById(R.id.txt_my_diet_meal_time);
            holder.txt_my_diet_what_to_eat = (TextView) v.findViewById(R.id.txt_my_diet_what_to_eat);

            v.setTag(holder);
        } else {
            holder = (MyDietAdapter.ViewHolder) v.getTag();
        }

        final MyDietModel obj = myDietModels.get(position);
        holder.txt_my_diet_day.setText(obj.getDay());
        holder.txt_my_diet_meal_time.setText(obj.getMeal_time());
        holder.txt_my_diet_what_to_eat.setText(obj.getWhat_to_eat());


        return v;
    }

    class ViewHolder {
        TextView id, txt_my_diet_day, txt_my_diet_meal_time, txt_my_diet_what_to_eat;
    }
}