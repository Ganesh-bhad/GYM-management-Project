package com.mountreachsolution.gymmanagementsystem.MyPackage.Adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mountreachsolution.gymmanagementsystem.MyDiet.Adapter.MyDietAdapter;
import com.mountreachsolution.gymmanagementsystem.MyDiet.Pojo.MyDietModel;
import com.mountreachsolution.gymmanagementsystem.MyPackage.Pojo.MyPackageModel;
import com.mountreachsolution.gymmanagementsystem.R;

import java.util.List;

public class MyPackageAdapter extends BaseAdapter {

    List<MyPackageModel> myPackageModels;
    Activity activity;
    TextView tv_no_records;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public MyPackageAdapter(List<MyPackageModel> list, Activity activity, TextView tv_no_records) {
        myPackageModels = list;
        this.activity = activity;
        this.tv_no_records = tv_no_records;

        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = preferences.edit();
    }


    @Override
    public int getCount() {
        return myPackageModels.size();
    }

    @Override
    public Object getItem(int position) {
        return myPackageModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final MyPackageAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null) {
            holder = new MyPackageAdapter.ViewHolder();
            v = inflater.inflate(R.layout.lv_my_package, null);

            holder.txt_my_diet_package_name = (TextView) v.findViewById(R.id.txt_my_diet_package_name);
            holder.txt_my_diet_fees = (TextView) v.findViewById(R.id.txt_my_diet_fees);
            holder.txt_my_diet_benefit = (TextView) v.findViewById(R.id.txt_my_diet_benefit);

            v.setTag(holder);
        } else {
            holder = (MyPackageAdapter.ViewHolder) v.getTag();
        }

        final MyPackageModel obj = myPackageModels.get(position);
        holder.txt_my_diet_package_name.setText(obj.getPackage_name());
        holder.txt_my_diet_fees.setText(obj.getFees());
        holder.txt_my_diet_benefit.setText(obj.getBenefit());
        return v;
    }

    class ViewHolder {
        TextView id, txt_my_diet_package_name, txt_my_diet_fees, txt_my_diet_benefit;
    }
}