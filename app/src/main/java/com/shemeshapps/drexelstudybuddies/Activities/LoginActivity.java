package com.shemeshapps.drexelstudybuddies.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.shemeshapps.drexelstudybuddies.Models.LoginResponse;
import com.shemeshapps.drexelstudybuddies.NetworkingServices.RequestUtil;
import com.shemeshapps.drexelstudybuddies.R;

public class LoginActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkIfLoggedIn();
        setContentView(R.layout.activity_login);

        final ProgressBar loadingBar = (ProgressBar)findViewById(R.id.login_loading);
        final EditText password = (EditText)findViewById(R.id.password_login);
        final EditText username = (EditText)findViewById(R.id.username_login);
        Button login = (Button)findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBar.setVisibility(View.VISIBLE);
                RequestUtil.getAuthCode(username.getText().toString(),password.getText().toString(),new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        saveAuthKey((LoginResponse)response);
                        login();
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"INCORRECT PASSWORD",Toast.LENGTH_SHORT).show();
                        loadingBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    private void saveAuthKey(LoginResponse auth)
    {
        SharedPreferences pref = getSharedPreferences("login_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        e.putString("auth_key",auth.AuthKey);
        e.putString("user_id",auth.UserId);
        e.apply();
    }

    private void login()
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    private void checkIfLoggedIn()
    {
        SharedPreferences pref = getSharedPreferences("login_data", Context.MODE_PRIVATE);
        if(pref.contains("auth_key"))
        {
           login();
        }
    }
}
