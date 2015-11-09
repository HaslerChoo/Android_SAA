package com.example.haslerchoo.saa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haslerchoo.saa.controller.FragmentControler;
import com.example.haslerchoo.saa.controller.Login;
import com.example.haslerchoo.saa.util.Backrun;
import com.example.haslerchoo.saa.util.Databaseconnector;
import com.example.haslerchoo.saa.util.Listas;

import java.util.List;


public class Tab_lista extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    static Context contexto;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_lista);
        contexto=getApplicationContext();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        Menu m=navigationView.getMenu();
        if (Listas.logInCliente!=null)
        {

            SubMenu top=m.addSubMenu("Dados Do utilizador");
            top.add("").setIcon(R.drawable.setting_header);
            top.add(Listas.logInCliente.getNome()+" "+Listas.logInCliente.getApelido());
            top.add(Listas.logInCliente.getEmail());
            SubMenu botton=m.addSubMenu("");
            botton.add("LogOut");

        }
        else
        {
            SubMenu top=m.addSubMenu("");
            top.add("LogIn").setIcon(R.drawable.ic_done);
        }

        MenuItem mi=m.getItem(m.size()-1);
        mi.setTitle(mi.getTitle());

        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        FragmentControler adapter = new FragmentControler(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);


        new Backrun().execute();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getTitle().toString().equals("LogIn")) {
            item.setChecked(true);
            mDrawerLayout.closeDrawers();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
        else if(item.getTitle().toString().equals("LogOut"))
            {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                Listas.logInCliente=null;
                Toast.makeText(getApplicationContext(),"Sair",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),Tab_lista.class);
                startActivity(intent);
            }
        return true;

    }

    public static Context contexto()
    {
        return contexto;
    }

}