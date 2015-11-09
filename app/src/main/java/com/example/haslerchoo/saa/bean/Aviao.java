package com.example.haslerchoo.saa.bean;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class Aviao {
    private int codigo;
    private String modelo;

    public Aviao() {
    }

    public Aviao(int codigo, String modelo) {
        this.codigo = codigo;
        this.modelo = modelo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


}


