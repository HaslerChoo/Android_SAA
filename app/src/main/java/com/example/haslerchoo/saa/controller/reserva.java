package com.example.haslerchoo.saa.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haslerchoo.saa.R;
import com.example.haslerchoo.saa.Tab_lista;
import com.example.haslerchoo.saa.bean.Assento;
import com.example.haslerchoo.saa.util.Databaseconnector;
import com.example.haslerchoo.saa.util.Listas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Reserva extends AppCompatActivity {

    private TextView origem;
    private TextView destino;
    private TextView data;
    private TextView horas;
    private ImageView image;

    Button cancelar;
    Button reservar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(Listas.localizacoes.get(Listas.voos.get(Listas.voo_escolhido).getLocal_inicio()).getNome() + " --> " + Listas.localizacoes.get(Listas.voos.get(Listas.voo_escolhido).getLocal_fim()).getNome());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(Listas.voos.get(Listas.voo_escolhido).getData());

        origem = (TextView) findViewById(R.id.reserva_origem);
        destino = (TextView) findViewById(R.id.reserva_destino);
        data = (TextView) findViewById(R.id.data_voo);
        horas = (TextView) findViewById(R.id.horas_voo);
        image = (ImageView) findViewById(R.id.mapa);
        reservar = (Button) findViewById(R.id.btnReservar);
        cancelar = (Button) findViewById(R.id.btnCancelar);
        if (Listas.logInCliente != null) {
            new Mostrar().execute();
        } else {
            cancelar.setVisibility(View.INVISIBLE);
        }

        reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Listas.logInCliente == null) {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                } else {
                    new Bilhete().execute();
                    Listas.logInCliente.addBilhete(Listas.voos.get(Listas.voo_escolhido).getCodigo());
                    Toast.makeText(getApplicationContext(), "Reservado", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Tab_lista.contexto(), Tab_lista.class);
                    startActivity(intent);
                }
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Canclear().execute();
                Listas.logInCliente.removeBilhete(Listas.voos.get(Listas.voo_escolhido).getCodigo());
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Tab_lista.class);
                startActivity(intent);


            }
        });

        switch (Listas.voo_escolhido) {
            case 1:
                image.setImageResource(R.drawable.capture);
                break;
            case 2:
                image.setImageResource(R.drawable.mapa);
                break;
            case 0:
                image.setImageResource(R.drawable.mapa);
                break;
        }
        origem.setText(Listas.localizacoes.get(Listas.voos.get(Listas.voo_escolhido).getLocal_inicio()).getNome());
        destino.setText(Listas.localizacoes.get(Listas.voos.get(Listas.voo_escolhido).getLocal_fim()).getNome());

        data.setText(date);
        horas.setText(Listas.voos.get(Listas.voo_escolhido).getTempo_voo());

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

    public class Bilhete extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Databaseconnector.saveBilhete(Listas.logInCliente.getCodigo(), Listas.voos.get(Listas.voo_escolhido).getCodigo());
            return null;
        }

    }

        public class Canclear extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... params) {

                Databaseconnector.saveCancelar(Listas.logInCliente.getCodigo(), Listas.voos.get(Listas.voo_escolhido).getCodigo());
                return null;
            }


            }

    public class Mostrar extends AsyncTask<Void, Void, Void> {
        boolean estado=false;
        @Override
        protected Void doInBackground(Void... params) {

            estado=Databaseconnector.tem_reserva(Listas.logInCliente.getCodigo(), Listas.voos.get(Listas.voo_escolhido).getCodigo());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.d("estado", Listas.logInCliente.getCodigo() + "");
            Log.d("estado",Listas.voos.get(Listas.voo_escolhido).getCodigo()+"");
            if (estado)
            {
                cancelar.setVisibility(View.VISIBLE);
                reservar.setVisibility(View.INVISIBLE);
            }
            else
            {
                reservar.setVisibility(View.VISIBLE);
                cancelar.setVisibility(View.INVISIBLE);
            }

        }



    }

}
