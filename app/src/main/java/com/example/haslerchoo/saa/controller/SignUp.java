package com.example.haslerchoo.saa.controller;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.haslerchoo.saa.R;
import com.example.haslerchoo.saa.bean.Cliente;
import com.example.haslerchoo.saa.util.Databaseconnector;
import com.example.haslerchoo.saa.util.Listas;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUp extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,DatePickerDialog.OnDateSetListener{
    private final int RESULT_LOAD_IMAGE=1;

    private NavigationView navigationView;

    private EditText nome;
    private EditText apelido;
    private EditText morada;
    private EditText data;
    private EditText email;
    private EditText senha;
    private int year;
    private int day;
    private int month;

    private FloatingActionButton fab;

    public CircleImageView profile_pic;

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

        profile_pic=(CircleImageView)findViewById((R.id.upload_profile_picture));
        nome=(EditText)findViewById(R.id.nome);
        apelido=(EditText)findViewById(R.id.apelido);
        morada=(EditText)findViewById(R.id.morada);
        data=(EditText)findViewById(R.id.dataNasc);
        email=(EditText)findViewById(R.id.email);
        senha=(EditText)findViewById(R.id.password);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSelector(v);
            }
        });

        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,RESULT_LOAD_IMAGE);
            }
        });
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
                Calendar dat=Calendar.getInstance();
                dat.set(year, month,day);
                Cliente novo=new Cliente(Listas.clientes.size(),nome.getText().toString(), apelido.getText().toString(),morada.getText().toString(), dat.getTime(),
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
                Listas.clientes.add(novo);
                Snackbar.make(view, "Criado", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
               finish();
            }
        });
    }

    public void dataSelector(View v)
    {
        Calendar calendar=Calendar.getInstance();
        if (year==0)
        {
            year=calendar.get(Calendar.YEAR);
            day=calendar.get(Calendar.DAY_OF_MONTH);
            month=calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, day);
        Calendar min=Calendar.getInstance();
        Calendar max=Calendar.getInstance();
        min.set(min.get(Calendar.YEAR)-100, 0, 1);
        max.set(max.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        DatePickerDialog cal=DatePickerDialog.newInstance(this,year,month,day);
        cal.setMinDate(min);
        cal.setMaxDate(max);
        cal.show(getFragmentManager(), "Data de Nascimento");
        onDateSet(null,year,month,day);

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        this.year=year;
        month=monthOfYear;
        day=dayOfMonth;
        data.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
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
            Log.d("edit", "modificado");
            return null;
        }

        @Override
        protected
        void onPostExecute(Void aVoid) {
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


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try
        {
            if (requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data!=null)
            {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE))
                    {

                    }
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},RESULT_LOAD_IMAGE);
                }
                Uri selectedImage=data.getData();
                profile_pic.setImageURI(selectedImage);
            }
        }
        catch (SecurityException e)
        {
            Log.d("Permissions","Acesso negado pelo utilizador");
        }

    }



}
