package com.example.haslerchoo.saa.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haslerchoo.saa.R;
import com.example.haslerchoo.saa.Tab_lista;
import com.example.haslerchoo.saa.bean.Cliente;
import com.example.haslerchoo.saa.util.Backrun;
import com.example.haslerchoo.saa.util.Databaseconnector;
import com.example.haslerchoo.saa.util.Listas;

public class Login extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    Button login;
    Button signUp;
    EditText email;
    EditText pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigation_back);
        navigationView.setNavigationItemSelectedListener(this);

        email = (EditText) findViewById(R.id.email);
        pw = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.logIn);
        signUp = (Button) findViewById(R.id.signUp);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Cliente c : Listas.clientes) {
                    Log.d("user detalhes", c.getEmail() + " " + email.getText() + " " + c.getSenha() + " " + pw.getText());
                    Log.d("user detalhes", c.getEmail().equals(email.getText().toString()) + " " + c.getSenha().equals(pw.getText().toString()));
                    if (c.getEmail().equals(email.getText().toString()) && c.getSenha().equals(pw.getText().toString())) {
                        Log.d("login", "sucesso");
                        Listas.logInCliente = c;
                        new Backrun().execute();
                        Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), Tab_lista.class);
                        startActivity(intent);

                        return;
                    }
                }
                Toast.makeText(getApplicationContext(), "Dados Invaldos", Toast.LENGTH_LONG).show();
                Log.d("login", "fail");
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
               finish();
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
