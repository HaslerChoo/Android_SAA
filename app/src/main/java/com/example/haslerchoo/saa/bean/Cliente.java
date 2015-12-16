package com.example.haslerchoo.saa.bean;

import android.util.Log;

import com.example.haslerchoo.saa.controller.Listar_voo;
import com.example.haslerchoo.saa.util.Listas;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class Cliente {

    private int codigo;
    private String nome;
    private String apelido;
    private String morada;
    private Date data_nasc;
    private String email;
    private String senha;
    private ArrayList<Bilhete> bilhete;
    private ArrayList<Voo> voos;
    public Cliente() {
    }

    public Cliente(int codigo, String nome, String apelido, String morada, Date data_nasc, String email, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.apelido = apelido;
        this.morada = morada;
        this.data_nasc = data_nasc;
        this.email = email;
        this.senha = senha;
        bilhete=new ArrayList<>();
        voos=new ArrayList<>();
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList getBilhete() {
        return bilhete;
    }

    public void setBilhete(ArrayList bilhete) {
        this.bilhete = bilhete;
    }


    public ArrayList<Voo> getVoos() {
        if (voos == null || voos.size()==0) {
            voos = new ArrayList<>();
        }
            refreshVoos();
        return voos;
    }

    public void setVoos(ArrayList<Voo> voos) {
        this.voos = voos;
    }

    public void refreshVoos() {
        if (bilhete==null)
        {
            bilhete=new ArrayList<>();
        }
        voos.clear();
        for (Bilhete bi:bilhete)
        {
            for (Voo vo:Listas.voos)
            {
                if(vo.getCodigo()==bi.getVoo_codigo())
                {
                    voos.add(vo);
                }
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Cliente)
        {
            Cliente novo=(Cliente)o;
            return novo.getEmail().equals(getEmail());
        }
        return super.equals(o);

    }

    public void removeBilhete(int codigo) {
        for (Bilhete b:bilhete)
        {
            if (codigo==b.getVoo_codigo())
            {
                bilhete.remove(b);
                return;
            }
        }
    }

    public void addBilhete(int codigo) {
        Bilhete b=new  Bilhete(this.codigo, 1, new Date(), codigo);
    }
}
