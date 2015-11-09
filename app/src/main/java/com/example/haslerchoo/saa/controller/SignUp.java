package com.example.haslerchoo.saa.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.haslerchoo.saa.R;
import com.example.haslerchoo.saa.bean.Cliente;
import com.example.haslerchoo.saa.util.Databaseconnector;
import com.example.haslerchoo.saa.util.Listas;

import java.util.Date;

public class SignUp extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener  {

    private NavigationView navigationView;
    EditText nome;
    EditText apelido;
    EditText morada;
    EditText data;
    EditText email;
    EditText senha;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigation_back);
        navigationView.setNavigationItemSelectedListener(this);

        nome=(EditText)findViewById(R.id.nome);
        apelido=(EditText)findViewById(R.id.apelido);
        morada=(EditText)findViewById(R.id.morada);
        data=(EditText)findViewById(R.id.dataNasc);
        email=(EditText)findViewById(R.id.email);
        senha=(EditText)findViewById(R.id.password);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Salvando", Snackbar.LENGTH_SHORT).setAction("Action", null).show();

                if (nome.getText().toString().equals(""))
                {
                    Snackbar.make(view, "Introduzir Nome", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    return;
                }
                if (apelido.getText().toString().equals(""))
                {
                    Snackbar.make(view, "Introduzir apelido", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    return;
                }
                if (email.getText().toString().equals(""))
                {
                    Snackbar.make(view, "Introduzir email", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    return;
                }
                if (morada.getText().toString().equals(""))
                {
                    Snackbar.make(view, "Introduzir morada", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    return;
                }

                if (data.getText().toString().equals(""))
                {
                    Snackbar.make(view, "Introduzir data de nascimento", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    return;
                }

                if (senha.getText().toString().equals(""))
                {
                    Snackbar.make(view, "Introduzir senha", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    return;
                }

                Cliente novo=new Cliente(Listas.clientes.size(),nome.getText().toString(), apelido.getText().toString(),morada.getText().toString(), new Date(),
                        email.getText().toString(), senha.getText().toString());

                for (Cliente c:Listas.clientes)
                {
                    if (c.equals(novo))
                    {
                        Snackbar.make(view, "Conta Ja existe", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        return;
                    }
                }

                new NovoCliente(novo).execute();
                Snackbar.make(view, "Criado", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }

    public class NovoCliente extends AsyncTask<Void,Void,Void> {

        Cliente cliente;
        public NovoCliente(Cliente c)
        {
            cliente=c;
        }
        @Override
        protected Void doInBackground(Void... params) {
            Databaseconnector.saveCliente(cliente);
            Log.d("signUp", "Criado");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
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
