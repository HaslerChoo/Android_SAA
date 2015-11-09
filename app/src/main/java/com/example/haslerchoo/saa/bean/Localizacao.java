package com.example.haslerchoo.saa.bean;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class Localizacao {
    private int codigo;
    private String nome;
    private double longitude;
    private double latitude ;

    public Localizacao() {
    }

    public Localizacao(int codigo, String nome, double longitude, double latitude) {
        this.codigo = codigo;
        this.nome = nome;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
