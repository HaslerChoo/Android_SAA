package com.example.haslerchoo.saa;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haslerchoo.saa.controller.DesignRecyclerAdapter;
import com.example.haslerchoo.saa.controller.FragmentControler;
import com.example.haslerchoo.saa.controller.Listar_reserva;
import com.example.haslerchoo.saa.controller.Listar_voo;
import com.example.haslerchoo.saa.controller.Login;
import com.example.haslerchoo.saa.util.Backrun;
import com.example.haslerchoo.saa.util.Listas;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;


public class Tab_lista extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private static Context contexto;
    public TextView user_name;
    public TextView user_email;
    public CircleImageView profile_pic;
    final static String imageLocation="http://10.0.2.2:8080/ANDROID_REPOSITORIO/hasler_profile.jpg";
    //final static String imageLocation="http://pngimg.com/upload/sword_PNG5518.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_lista);

        //configurando action Bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //contexto
        contexto=getApplicationContext();

        //Navegador
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //addiconar Layout do Navegador
        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView = inflater.inflate(R.layout.setting_header,null, false);
        navigationView.addHeaderView(listHeaderView);


        user_name=(TextView)listHeaderView.findViewById((R.id.user));
        user_email=(TextView)listHeaderView.findViewById((R.id.user_email));
        profile_pic=(CircleImageView)listHeaderView.findViewById((R.id.profile_picture));

        //gerando menu do navegador
        Menu m=navigationView.getMenu();
        if (Listas.logInCliente!=null)
        {
            user_name.setText(Listas.logInCliente.getNome() + " " + Listas.logInCliente.getApelido());
            user_email.setText(Listas.logInCliente.getEmail());

          //  profile_pic.setImageResource(R.drawable.hasler_profile);
            new LoadProfilePic().execute();


            listHeaderView.setBackgroundResource(R.color.primary_dark);
            //menu para cliente
            SubMenu top=m.addSubMenu("Definicoes").setIcon(R.drawable.ic_done);
            top.add("Modificar Dados");
            SubMenu botton=m.addSubMenu("");
            botton.add("LogOut");

        }
        else
        {
            //menu para todos
            SubMenu top=m.addSubMenu("");
            top.add("LogIn").setIcon(R.drawable.ic_done);
        }
        //colocar titulo nos menus
        MenuItem mi= m.getItem(m.size()-1);
        mi.setTitle(mi.getTitle());

        //accao do navegador
        navigationView.setNavigationItemSelectedListener(this);



        //configurando tabs
        FragmentControler adapter = new FragmentControler(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        //carregamento da BD
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



    Bitmap bitmap;
    void loadImage(String image_location){

        URL imageURL = null;

        try {
            imageURL = new URL(image_location);
            Log.d("Loading","work1");
        }

        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection connection= (HttpURLConnection)imageURL.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            bitmap = BitmapFactory.decodeStream(inputStream);//Convert to bitmap
            Log.d("Loading","work2");
        }
        catch (IOException e) {

            e.printStackTrace();
        }
    }

    public class LoadProfilePic extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            Log.d("Loading","load");
            loadImage(imageLocation);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d("Loading","foto");
            profile_pic.setImageBitmap(bitmap);
            super.onPostExecute(aVoid);
        }
    }

}