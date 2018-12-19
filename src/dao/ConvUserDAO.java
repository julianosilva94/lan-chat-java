/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import classes.Conv;
import classes.ConvUser;
import classes.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.ConexaoDAO;

/**
 *
 * @author 265551
 */
public class ConvUserDAO {
    
    private Connection conn;

    public ConvUserDAO() {
        try {
            this.conn = ConexaoDAO.getConnection();
        } catch (Exception e) {
            System.out.println("Erro de conexão: " + ":" + e.getMessage());
        }
    }

    public void inserir(ConvUser convUser) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (convUser == null) {
            System.out.println("O objeto conv_user não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO conv_user (id_conv, id_user) "
                    + "values (?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
           
            ps.setInt(1, convUser.getId_conv());
            ps.setInt(2, convUser.getId_user());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir novo usuario " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
    }
    
    public void inserirParaLogados(int id_conv) {
        PreparedStatement ps = null;
        Connection connL = null;
        try {
            String SQL = "INSERT INTO conv_user (id_conv, id_user)"
                    + "SELECT ?, id_user FROM user WHERE logado = 1";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
           
            ps.setInt(1, id_conv);
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir nova conv user " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
    }
    
    public void inserirParaUser(int id_conv, User user) {
        PreparedStatement ps = null;
        Connection connL = null;
        try {
            String SQL = "INSERT INTO conv_user (id_conv, id_user)"
                    + "VALUES (?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
           
            ps.setInt(1, id_conv);
            ps.setInt(2, user.getId_user());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir nova conv user " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
    }

    public ArrayList<ConvUser> listar(int id_user_fk) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList convUsers = new ArrayList();
        try {
            String SQL = "SELECT * FROM conv_user WHERE id_user = ?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, id_user_fk);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id_conv = rs.getInt("id_conv");
                int id_user = rs.getInt("id_user");
                
                convUsers.add(new ConvUser(id_conv, id_user));
            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao listar conv user " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
        return convUsers;
    }

   
    public void excluir(int id_user ) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (id_user == 0) {
            System.out.println("O objeto user não pode ser nulo.");
        }

        try {
            String SQL = "DELETE FROM conv_user WHERE id_user = ?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, id_user);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao excluir user " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
    }
    
     public void excluir(int id_user, int id_conv ) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (id_user == 0) {
            System.out.println("O objeto user não pode ser nulo.");
        }

        try {
            String SQL = "DELETE FROM conv_user WHERE id_user = ? AND id_conv = ?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, id_user);
            ps.setInt(2, id_conv);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao excluir user " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
    }
    
    
    
}
