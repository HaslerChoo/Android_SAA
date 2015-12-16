package com.example.haslerchoo.saa.util;

import android.util.Log;

import com.example.haslerchoo.saa.bean.Bilhete;
import com.example.haslerchoo.saa.bean.Cliente;
import com.example.haslerchoo.saa.bean.Localizacao;
import com.example.haslerchoo.saa.bean.Voo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hasler Choo on 08-Nov-15.
 */
public class Databaseconnector {
    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.d("BD", "sucesso2");
            return DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/saa", "root", "choo");
           // return DriverManager.getConnection("jdbc:mysql://10.71.34.1:3306/saa", "root", "choo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.d("BD", "fail");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("DB", "fail2");
        }

        return null;
    }


    public static void load_cliente() {
        Connection con = getConnection();
        // pega a conex ã o e o Statement
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("select * from cliente");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setApelido(rs.getString("apelido_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setCodigo(rs.getInt("cod_cliente"));
                cliente.setMorada(rs.getString("morada_cliente"));
                cliente.setData_nasc(new Date(rs.getDate("data_nasc_cliente").getTime()));
                cliente.setEmail(rs.getString("email_cliente"));
                cliente.setSenha(rs.getString("senha_cliente"));
                Listas.clientes.add(cliente);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void load_voo() {

        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("select * from voo");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Voo voo = new Voo();
                voo.setCodigo(rs.getInt("idvoo"));
                voo.setCodigo_aviao(rs.getInt("aviao_cod"));
                Date data = new Date(rs.getDate("data_voo").getTime());
                voo.setData(data);
                voo.setLocal_fim(rs.getInt("local_fim"));
                voo.setLocal_inicio(rs.getInt("local_inicio"));
                voo.setTempo_voo(rs.getString("hora_voo"));
                Listas.voos.add(voo);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void load_localizacao() {

        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("select * from localizacao");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Localizacao local = new Localizacao();
                int codigo = rs.getInt("cod_local");
                local.setCodigo(codigo);
                local.setLatitude(rs.getDouble("latitude"));
                local.setLongitude(rs.getDouble("longitude"));
                local.setNome(rs.getString("nome_local"));

                Listas.localizacoes.put(codigo, local);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void load_cliente_bilhete(int codigo) {

        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("select * from bilhete where cliente_cod=" + codigo);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Bilhete> ticket = new ArrayList<Bilhete>();
            while (rs.next()) {
                Bilhete bilhete = new Bilhete();

                bilhete.setCod_cliente(rs.getInt("cliente_cod"));
                bilhete.setAssento_codigo(rs.getInt("assento_cod"));
                bilhete.setData(new Date(rs.getDate("data").getTime()));
                bilhete.setVoo_codigo(rs.getInt("voo_cod"));
                Log.d("bilhete", bilhete.getCod_cliente() + "");
                ticket.add(bilhete);
            }
            Listas.logInCliente.setBilhete(ticket);
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveCliente(Cliente cliente) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into  cliente values (?,?,?,?,?,?,?)");
            stmt.setInt(1, cliente.getCodigo() + 1);
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getApelido());
            stmt.setString(4, cliente.getMorada());
            stmt.setDate(5, new java.sql.Date(cliente.getData_nasc().getTime()));
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getSenha());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void actualizarCliente(Cliente cliente) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update cliente set  apelido_cliente=?, nome_cliente=?, morada_cliente=?, data_nasc_cliente=?, senha_cliente=? where email_cliente=?");

            stmt.setString(1, cliente.getApelido());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getMorada());
            stmt.setDate(4, new java.sql.Date(cliente.getData_nasc().getTime()));
            stmt.setString(5, cliente.getSenha());
            stmt.setString(6, cliente.getEmail());

            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean tem_reserva(int cliente, int voo) {
        Connection con = getConnection();
        // pega a conex ã o e o Statement
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM bilhete  where cliente_cod=? and voo_cod=?");
            stmt.setInt(1, cliente);
            stmt.setInt(2, voo);
            stmt.execute();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Log.d("tem_reserva","true");
               return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.d("tem_reserva","false");
        return false;
    }


    public static void saveBilhete(int cliente,int voo) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO bilhete VALUES (?,?,?,?)");
            Log.d("saveBilhete", "ok");
            stmt.setInt(1, cliente);
            stmt.setInt(2, voo);
            stmt.setInt(3, 1);
            stmt.setDate(4, new java.sql.Date(new Date().getTime()));
            stmt.execute();
            stmt.close();
            con.close();
            Log.d("saveBilhete", "ok1");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void saveCancelar(int cliente, int voo) {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from bilhete where cliente_cod=? and voo_cod=?");
            stmt.setInt(1, cliente);
            stmt.setInt(2, voo);
             stmt.execute();
            stmt.close();
            con.close();
            Log.d("saveCancelar", "ok");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
