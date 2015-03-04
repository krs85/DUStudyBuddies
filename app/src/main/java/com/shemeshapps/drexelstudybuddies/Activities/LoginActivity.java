package com.shemeshapps.drexelstudybuddies.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.shemeshapps.drexelstudybuddies.NetworkingServices.RequestUtil;
import com.shemeshapps.drexelstudybuddies.R;

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText password = (EditText)findViewById(R.id.password_login);
        final EditText username = (EditText)findViewById(R.id.username_login);
        Button login = (Button)findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestUtil.getAuthCode(username.getText().toString(),password.getText().toString(),new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Toast.makeText(getApplicationContext(),"WORKED",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"INCORRECT PASSWORD",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
