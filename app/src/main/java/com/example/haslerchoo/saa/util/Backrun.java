package com.example.haslerchoo.saa.util;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.haslerchoo.saa.Tab_lista;
import com.example.haslerchoo.saa.controller.DesignRecyclerAdapter;
import com.example.haslerchoo.saa.controller.Listar_reserva;
import com.example.haslerchoo.saa.controller.Listar_voo;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class Backrun extends AsyncTask<Void,Void,Void> {

    @Override
    protected Void doInBackground(Void... params) {
        Listas.voos.clear();
        Listas.localizacoes.clear();

        Databaseconnector.load_cliente();
        Databaseconnector.load_voo();
        Databaseconnector.load_localizacao();
        if (Listas.logInCliente!=null)
            Databaseconnector.load_cliente_bilhete(Listas.logInCliente.getCodigo());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Listar_voo.recyclerView.setAdapter(new DesignRecyclerAdapter(Listas.voos));
        if (Listas.logInCliente!=null) {
            Listar_reserva.recyclerView.setAdapter(new DesignRecyclerAdapter(Listas.logInCliente.getVoos()));
        }

    }
}
