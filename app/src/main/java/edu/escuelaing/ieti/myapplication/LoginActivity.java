package edu.escuelaing.ieti.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
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
                if(email.isEmpty()){
                    emailInput.setError("Debe ingresar su correo");
                }else if(password.isEmpty()){
                    passwordInput.setError("Debe ingresar una contraseña");
                } else {
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
