package com.example.haslerchoo.saa.bean;

import java.util.Date;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class Bilhete {
    private int cod_cliente;
    private int assento_codigo;
    private Date data;
    private int voo_codigo;

    public Bilhete(int cod_cliente, int assento_codigo, Date data, int voo_codigo) {
        this.cod_cliente = cod_cliente;
        this.assento_codigo = assento_codigo;
        this.data = data;
        this.voo_codigo = voo_codigo;
    }

    public Bilhete() {
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public int getAssento_codigo() {
        return assento_codigo;
    }

    public void setAssento_codigo(int assento_codigo) {
        this.assento_codigo = assento_codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getVoo_codigo() {
        return voo_codigo;
    }

    public void setVoo_codigo(int voo_codigo) {
        this.voo_codigo = voo_codigo;
    }
}
