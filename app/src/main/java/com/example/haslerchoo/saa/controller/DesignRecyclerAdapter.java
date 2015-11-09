package com.example.haslerchoo.saa.controller;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        try
        {
            String inicio= Listas.localizacoes.get(mItems.get(i).getLocal_inicio()).getNome();
            String fim= Listas.localizacoes.get(mItems.get(i).getLocal_fim()).getNome();
            String item = inicio+" "+fim;
            Log.d("Choo", "1 " +getItemCount());
            viewHolder.mTextView.setText(item);
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

        private final TextView mTextView;
        private String mItem;

        ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            mTextView = (TextView)v.findViewById(R.id.list_item);
        }

        public void setItem(String item) {
            mItem = item;
            mTextView.setText(item);
        }
        @Override
        public void onClick(View v) {
            Log.d("Choo", "onClick " + getPosition() + " " + mItem);
        }
    }

}