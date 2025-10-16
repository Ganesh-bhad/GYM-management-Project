package com.mountreachsolution.gymmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.gymmanagementsystem.Comman.Config;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    EditText edt_mobile_no,edt_password;
    Button btn_sign_in;
    CheckBox Show_Hide;
    TextView txt_forgot_password,txt_sign_up;
    boolean doubletap = false;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        edt_mobile_no = findViewById(R.id.edt_mobile_no_login);
        edt_password = findViewById(R.id.edt_password_login);
        btn_sign_in = findViewById(R.id.btn_signin_login);
        Show_Hide =  findViewById(R.id.chk_show_password);
        progress = findViewById(R.id.progress);

        if(preferences.getBoolean("islogin",false))
        {
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }


        Show_Hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    edt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_mobile_no.getText().toString().isEmpty())
                {
                    edt_mobile_no.setError("Please Enter valid Username");
                }
                else if (edt_password.getText().toString().isEmpty())
                {
                    edt_password.setError("Please Enter Valid Password");
                }
                else
                {
                    login();
                }
            }
        });
    }

    private void login()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("mobile_no",edt_mobile_no.getText().toString());
        params.put("password",edt_password.getText().toString());

        client.post(Config.url_login,params,new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                progress.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                progress.setVisibility(View.GONE);

                try {
                    editor.putString("mobile_no",edt_mobile_no.getText().toString()).commit();
                    String aa = response.getString("success");
                    String member_name = response.getString("name");
                    if (aa.equals("1"))
                    {
                        Toast.makeText(LoginActivity.this, "Login Successfully Done", Toast.LENGTH_SHORT).show();
                        editor.putBoolean("islogin",true).commit();
                        editor.putString("member_name",member_name).commit();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(LoginActivity.this, "Colud Not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (doubletap)
        {
            super.onBackPressed();
        }
        else
        {
            Toast.makeText(this, "Press again to exit the app", Toast.LENGTH_SHORT).show();
            doubletap = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubletap = false;
                }
            },2000);
        }
    }
}