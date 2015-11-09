package com.example.haslerchoo.saa.bean;

import java.util.Date;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class Voo {
    private int codigo;
    private int local_inicio;
    private int local_fim;
    private Date data;
    private int codigo_aviao;

    public Voo() {
    }

    public Voo(int codigo, int local_inicio, int local_fim, Date data, int codigo_aviao) {
        this.codigo = codigo;
        this.local_inicio = local_inicio;
        this.local_fim = local_fim;
        this.data = data;
        this.codigo_aviao = codigo_aviao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getLocal_inicio() {
        return local_inicio;
    }

    public void setLocal_inicio(int local_inicio) {
        this.local_inicio = local_inicio;
    }

    public int getLocal_fim() {
        return local_fim;
    }

    public void setLocal_fim(int local_fim) {
        this.local_fim = local_fim;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCodigo_aviao() {
        return codigo_aviao;
    }

    public void setCodigo_aviao(int codigo_aviao) {
        this.codigo_aviao = codigo_aviao;
    }
}
