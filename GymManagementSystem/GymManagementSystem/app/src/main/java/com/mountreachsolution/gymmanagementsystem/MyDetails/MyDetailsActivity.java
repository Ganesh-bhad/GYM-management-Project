package com.mountreachsolution.gymmanagementsystem.MyDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.gymmanagementsystem.Comman.Config;
import com.mountreachsolution.gymmanagementsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MyDetailsActivity extends AppCompatActivity {

    TextView id,member_name,member_id,member_package_type,member_mobile_no,member_email,member_gender,member_dob,member_address,member_password;
    ProgressDialog progressDialog;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setTitle("My Details");

        member_name = (TextView) findViewById(R.id.tv_profile_teacher_name);
        member_id = (TextView)findViewById(R.id.tv_member_id);
        member_package_type = (TextView) findViewById(R.id.tv_member_package_type);
        member_mobile_no = (TextView) findViewById(R.id.tv_member_mobile_number);
        member_email = (TextView)findViewById(R.id.tv_member_email);
        member_gender = (TextView)findViewById(R.id.tv_member_gender);
        member_dob = (TextView)findViewById(R.id.tv_member_dob);
        member_address = (TextView)findViewById(R.id.tv_member_address);

        progressDialog = new ProgressDialog(MyDetailsActivity.this);
        progressDialog.setTitle("Your Details is Loading");
        progressDialog.setMessage("Please Wait.......");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        getMyDetails();
    }

    private void getMyDetails() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("mobile_no",preferences.getString("mobile_no",""));
        client.post(Config.url_get_my_details, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    progressDialog.dismiss();
                    JSONArray jarry = response.getJSONArray("getMyDetails");

                    for (int i = 0; i < jarry.length(); i++) {
                        JSONObject jsonObject = jarry.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String memberloyee_id = jsonObject.getString("member_id");
                        String package_type = jsonObject.getString("package_type");
                        String mobile_no = jsonObject.getString("mobile_no");
                        String email = jsonObject.getString("email");
                        String gender = jsonObject.getString("gender");
                        String dob = jsonObject.getString("dob");
                        String address = jsonObject.getString("address");
                        String password = jsonObject.getString("password");

                        member_name.setText(name);
                        member_id.setText(memberloyee_id);
                        member_package_type.setText(package_type);
                        member_mobile_no.setText(mobile_no);
                        member_email.setText(email);
                        member_gender.setText(gender);
                        member_dob.setText(dob);
                        member_address.setText(address);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Toast.makeText(MyDetailsActivity.this, "could not connect", Toast.LENGTH_LONG).show();

            }
        });
    }
}