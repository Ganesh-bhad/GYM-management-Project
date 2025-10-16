package com.mountreachsolution.gymmanagementsystem.MyPackage;

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
import com.mountreachsolution.gymmanagementsystem.MyDiet.MyDietActivity;
import com.mountreachsolution.gymmanagementsystem.MyDiet.Pojo.MyDietModel;
import com.mountreachsolution.gymmanagementsystem.MyPackage.Adapter.MyPackageAdapter;
import com.mountreachsolution.gymmanagementsystem.MyPackage.Pojo.MyPackageModel;
import com.mountreachsolution.gymmanagementsystem.MySchedule.Adapter.MyScheduleAdapter;
import com.mountreachsolution.gymmanagementsystem.MySchedule.Pojo.MyScheduleModel;
import com.mountreachsolution.gymmanagementsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MyPackageActivity extends AppCompatActivity {

    List<MyPackageModel> myPackageModels;
    ListView lv_my_package;
    TextView tv_no_records;
    ProgressDialog progressDialog;
    MyPackageAdapter adapter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_package);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();
        setTitle("My Schedule");

        Toast.makeText(this, "" + preferences.getString("member_name", ""), Toast.LENGTH_SHORT).show();
        myPackageModels = new ArrayList<MyPackageModel>();
        lv_my_package = (ListView) findViewById(R.id.lv_my_package);
        tv_no_records = (TextView) findViewById(R.id.tv_no_records);

        progressDialog = new ProgressDialog(MyPackageActivity.this);
        progressDialog.setTitle("My Package Loading");
        progressDialog.setMessage("Please Wait.......");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        getMyPackage();
    }

    private void getMyPackage() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.post(Config.urlGetMyPackage, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    progressDialog.dismiss();
                    JSONArray jarry = response.getJSONArray("getMyPackage");
                    for (int i = 0; i < jarry.length(); i++) {
                        JSONObject jsonObject = jarry.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String package_name = jsonObject.getString("package_name");
                        String fees = jsonObject.getString("fees");
                        String benefit = jsonObject.getString("benefit");

                        myPackageModels.add(new MyPackageModel(id,package_name,fees,benefit));
                    }
                    adapter = new MyPackageAdapter(myPackageModels, MyPackageActivity.this, tv_no_records);
                    lv_my_package.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MyPackageActivity.this, "could not connect", Toast.LENGTH_LONG).show();
            }
        });
    }

}