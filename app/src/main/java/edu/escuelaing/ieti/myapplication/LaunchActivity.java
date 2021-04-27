package edu.escuelaing.ieti.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    public static final String TOKEN_KEY = "TOKEN_KEY";

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        SharedPreferences sharedPref =
                getSharedPreferences( getString(  R.string.preference_file_key ), Context.MODE_PRIVATE );

        if(sharedPref.contains(TOKEN_KEY)){
            setContentView(R.layout.activity_main);
        }else{
            setContentView(R.layout.login_activity);
        }

        Button loginButton = (Button) findViewById(R.id.loginButton);
        EditText emailInput = (EditText) findViewById(R.id.emailInput);
        EditText passwordInput = (EditText) findViewById(R.id.passwordInput);

    }
}