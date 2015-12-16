package com.example.haslerchoo.saa.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.haslerchoo.saa.R;
import com.example.haslerchoo.saa.Tab_lista;
import com.example.haslerchoo.saa.bean.Voo;
import com.example.haslerchoo.saa.util.Listas;

import java.util.ArrayList;


public class Listar_reserva extends Fragment {

    public static RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v =  inflater.inflate(R.layout.fragment_listar_reserva, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerview_reserva);
        recyclerView.setLayoutManager(new LinearLayoutManager(Tab_lista.contexto()));
        if (Listas.logInCliente!=null) {
            recyclerView.setAdapter(new DesignRecyclerAdapter(Listas.logInCliente.getVoos()));
        }
        return v;
    }
}