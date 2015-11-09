package com.example.haslerchoo.saa.util;

import com.example.haslerchoo.saa.bean.Cliente;
import com.example.haslerchoo.saa.bean.Localizacao;
import com.example.haslerchoo.saa.bean.Voo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class Listas {
    public static Cliente logInCliente=null;
    public static  ArrayList<Cliente> clientes=new ArrayList<Cliente>();
    public static ArrayList<Voo> voos=new ArrayList<Voo>();
    public static Map<Integer,Localizacao> localizacoes=new HashMap<Integer,Localizacao>();
}
