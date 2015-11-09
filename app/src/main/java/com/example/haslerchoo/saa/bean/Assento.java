package com.example.haslerchoo.saa.bean;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class Assento {
    private int codigo;
    private int linha;
    private int numero;
    private int codigo_classe;
    private boolean estado;
    private int codigo_voo;

    public Assento() {
    }

    public Assento(int codigo, int linha, int numero, int codigo_classe, boolean estado, int codigo_voo) {
        this.codigo = codigo;
        this.linha = linha;
        this.numero = numero;
        this.codigo_classe = codigo_classe;
        this.estado = estado;
        this.codigo_voo = codigo_voo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodigo_classe() {
        return codigo_classe;
    }

    public void setCodigo_classe(int codigo_classe) {
        this.codigo_classe = codigo_classe;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCodigo_voo() {
        return codigo_voo;
    }

    public void setCodigo_voo(int codigo_voo) {
        this.codigo_voo = codigo_voo;
    }
}
