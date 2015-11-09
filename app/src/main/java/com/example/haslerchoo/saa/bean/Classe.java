package com.example.haslerchoo.saa.bean;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class Classe {

    private int codigo;
    private String nome;
    private String classe;

    public Classe() {
    }

    public Classe(int codigo, String nome, String classe) {

        this.codigo = codigo;
        this.nome = nome;
        this.classe = classe;
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
