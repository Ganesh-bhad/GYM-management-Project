package com.mountreachsolution.gymmanagementsystem.MyDiet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.gymmanagementsystem.Comman.Config;
import com.mountreachsolution.gymmanagementsystem.MyDiet.Adapter.MyDietAdapter;
import com.mountreachsolution.gymmanagementsystem.MyDiet.Pojo.MyDietModel;
import com.mountreachsolution.gymmanagementsystem.MySchedule.Adapter.MyScheduleAdapter;
import com.mountreachsolution.gymmanagementsystem.MySchedule.MyScheduleActivity;
import com.mountreachsolution.gymmanagementsystem.MySchedule.Pojo.MyScheduleModel;
import com.mountreachsolution.gymmanagementsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MyDietActivity extends AppCompatActivity {

    List<MyDietModel> myDietModels;
    ListView lv_my_diet;
    TextView tv_no_records;
    ProgressDialog progressDialog;
    MyDietAdapter adapter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diet);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();
        setTitle("My Schedule");

        Toast.makeText(this, "" + preferences.getString("member_name", ""), Toast.LENGTH_SHORT).show();
        myDietModels = new ArrayList<MyDietModel>();
        lv_my_diet = (ListView) findViewById(R.id.lv_my_diet);
        tv_no_records = (TextView) findViewById(R.id.tv_no_records);

        progressDialog = new ProgressDialog(MyDietActivity.this);
        progressDialog.setTitle("My Diet Loading");
        progressDialog.setMessage("Please Wait.......");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        getMyDiet();
    }

    private void getMyDiet() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("member_name", preferences.getString("member_name", ""));
        client.post(Config.urlGetMyDiet, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    progressDialog.dismiss();

                    JSONArray jarry = response.getJSONArray("getMyDiet");
                    for (int i = 0; i < jarry.length(); i++) {
                        JSONObject jsonObject = jarry.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String day = jsonObject.getString("day");
                        String meal_time = jsonObject.getString("meal_time");
                        String what_to_eat = jsonObject.getString("what_to_eat");

                        myDietModels.add(new MyDietModel(day, meal_time, what_to_eat));
                    }

                    adapter = new MyDietAdapter(myDietModels, MyDietActivity.this, tv_no_records);
                    lv_my_diet.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MyDietActivity.this, "could not connect", Toast.LENGTH_LONG).show();
            }
        });
    }
}