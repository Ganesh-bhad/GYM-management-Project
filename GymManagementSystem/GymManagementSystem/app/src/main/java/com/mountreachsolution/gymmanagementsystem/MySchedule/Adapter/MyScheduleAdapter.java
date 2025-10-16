package com.mountreachsolution.gymmanagementsystem.MySchedule.Adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mountreachsolution.gymmanagementsystem.MySchedule.Pojo.MyScheduleModel;
import com.mountreachsolution.gymmanagementsystem.R;

import java.util.List;

public class MyScheduleAdapter extends BaseAdapter {

    List<MyScheduleModel> myScheduleModels;
    Activity activity;
    TextView tv_no_records;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public MyScheduleAdapter(List<MyScheduleModel> list, Activity activity, TextView tv_no_records) {
        myScheduleModels = list;
        this.activity = activity;
        this.tv_no_records = tv_no_records;

        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = preferences.edit();
    }


    @Override
    public int getCount() {
        return myScheduleModels.size();
    }

    @Override
    public Object getItem(int position) {
        return myScheduleModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final MyScheduleAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null) {
            holder = new MyScheduleAdapter.ViewHolder();
            v = inflater.inflate(R.layout.lv_my_schedule, null);

            holder.txt_my_schedule_day = (TextView) v.findViewById(R.id.txt_my_schedule_day);
            holder.txt_my_schedule_workout_title = (TextView) v.findViewById(R.id.txt_my_schedule_workout_title);
            holder.txt_my_schedule_workout_description = (TextView) v.findViewById(R.id.txt_my_schedule_workout_description);

            v.setTag(holder);
        } else {
            holder = (MyScheduleAdapter.ViewHolder) v.getTag();
        }

        final MyScheduleModel obj = myScheduleModels.get(position);
        holder.txt_my_schedule_day.setText(obj.getDay());
        holder.txt_my_schedule_workout_title.setText(obj.getWorkout_title());
        holder.txt_my_schedule_workout_description.setText(obj.getWorkout_details());


        return v;
    }

    class ViewHolder {
        TextView id,txt_my_schedule_day,txt_my_schedule_workout_title,txt_my_schedule_workout_description;
    }
}
