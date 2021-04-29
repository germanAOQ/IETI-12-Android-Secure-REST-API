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

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.escuelaing.ieti.myapplication.controller.AuthService;
import edu.escuelaing.ieti.myapplication.model.LoginWrapper;
import edu.escuelaing.ieti.myapplication.model.Token;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") //localhost for emulator
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    AuthService authService = retrofit.create(AuthService.class);

    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        EditText emailInput = (EditText) findViewById(R.id.emailInput);
        EditText passwordInput = (EditText) findViewById(R.id.passwordInput);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                System.out.println(email);
                if (email.isEmpty()) {
                    emailInput.setError("Debe ingresar su correo");
                } else if (password.isEmpty()) {
                    passwordInput.setError("Debe ingresar una contraseña");
                } else {
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Response<Token> response = authService.login(new LoginWrapper(email, password)).execute();
                                Token token = response.body();
                                SharedPreferences sharedPref = getSharedPreferences( getString(  R.string.preference_file_key ), Context.MODE_PRIVATE );
                                sharedPref.edit().putInt("preference_file_key_token",token.hashCode());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
