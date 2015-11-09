package com.example.haslerchoo.saa.controller;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haslerchoo.saa.R;
import com.example.haslerchoo.saa.Tab_lista;

import java.util.ArrayList;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class FragmentControler extends FragmentStatePagerAdapter {

    public FragmentControler(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Listar_voo();
            case 1:
                return new Listar_reserva();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position==0 ? "Lista de Voos": "Reservas";
    }



}