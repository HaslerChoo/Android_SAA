package com.example.haslerchoo.saa.controller;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haslerchoo.saa.R;
import com.example.haslerchoo.saa.bean.Voo;
import com.example.haslerchoo.saa.util.Listas;

import java.util.List;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class DesignRecyclerAdapter  extends RecyclerView.Adapter<DesignRecyclerAdapter.ViewHolder> {

    private List<Voo> mItems;


    public DesignRecyclerAdapter(List<Voo> voo) {
           mItems = voo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        try {
            String inicio = Listas.localizacoes.get(mItems.get(i).getLocal_inicio()).getNome();
            String fim = Listas.localizacoes.get(mItems.get(i).getLocal_fim()).getNome();
            String item = inicio + " " + fim;
            viewHolder.origem.setText("Partida: " + Listas.localizacoes.get(mItems.get(i).getLocal_inicio()).getNome());
            viewHolder.destino.setText("Destino: " + Listas.localizacoes.get(mItems.get(i).getLocal_fim()).getNome());

        }
        catch (NullPointerException e)
        {

        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView origem;
        private final TextView destino;

        ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            origem = (TextView)v.findViewById(R.id.origem);
            destino = (TextView)v.findViewById(R.id.destino);
        }

        public void setItem(String item) {
            origem.setText(item);
        }
        @Override
        public void onClick(View v) {
            Listas.voo_escolhido= getPosition();
            Intent intent=new Intent(v.getContext(),Reserva.class);

            v.getContext().startActivity(intent);

        }
    }

}