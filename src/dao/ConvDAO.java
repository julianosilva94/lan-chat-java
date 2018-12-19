/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Conv;
import classes.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.ConexaoDAO;
import util.DataUtil;

/**
 *
 * @author 265551
 */
public class ConvDAO {

    private Connection conn;

    public ConvDAO() {
        try {
            this.conn = ConexaoDAO.getConnection();
        } catch (Exception e) {
            System.out.println("Erro de conexão: " + ":" + e.getMessage());
        }
    }

    public int inserir(Conv conv, User user) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        
        int id_conv = 0;
        
        if (conv == null) {
            System.out.println("O objeto conv não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO conv (id_remetente, texto, data) "
                    + "values (?,?, NOW())";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, user.getId_user());
            ps.setString(2, conv.getTexto());
            ps.executeUpdate();
                        
        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir nova mensagem " + sqle);
        }
        
        try {            
            String SQLId = "SELECT MAX(id_conv) as id_conv FROM conv";
            connL = this.conn;
            ps = connL.prepareStatement(SQLId);
            rs = ps.executeQuery();
            while (rs.next()) {
                id_conv = rs.getInt("id_conv");
            }
        } catch (SQLException sqle) {
            System.out.println("Erro ao pegar id max " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
        
        return id_conv;
    }

    public ArrayList<Conv> listar() {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList conversas = new ArrayList();
        try {
            String SQL = "SELECT * FROM conv ORDER BY data";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id_conv = rs.getInt("id_conv");
                Date data = rs.getDate("data");
                String texto = rs.getString("texto");

                conversas.add(new Conv());
            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao listar mensagens " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
        return conversas;
    }

    public Conv procurarPorId(int id_conv) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        Conv conv = null;

        try {
            String SQL = "SELECT * FROM conv WHERE id_conv = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, id_conv);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id_remetente = rs.getInt("id_remetente");
                java.util.Date data;
                data = (rs.getTimestamp("data"));
                String texto = rs.getString("texto");
                conv = new Conv(id_conv, id_remetente, data, texto);
            }
        } catch (SQLException sqle) {
            System.out.println("Erro ao procurar mensagens " + sqle);
        } finally {
            // ConexaoAulaDAO.close(connL,ps);
        }
        return conv;
    }
}
