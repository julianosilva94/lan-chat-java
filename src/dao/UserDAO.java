/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.User;
import util.ConexaoDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 265551
 */
public class UserDAO {

    private Connection conn;

    public UserDAO() {
        try {
            this.conn = ConexaoDAO.getConnection();
        } catch (Exception e) {
            System.out.println("Erro de conexão: " + ":\n" + e.getMessage());
        }
    }

    public void inserir(User user) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (user == null) {
            System.out.println("O objeto user não pode ser nulo.");
        }
        try {
            String SQL = "INSERT INTO user (nome, login, senha, logado) "
                    + "values (?,?,?,?)";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getSenha());
            ps.setBoolean(4, user.isLogado());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Erro ao inserir novo usuario " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
    }

    public ArrayList<User> listar() {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList users = new ArrayList();
        try {
            String SQL = "SELECT * FROM user ORDER BY nome";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id_user = rs.getInt("id_user");
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                boolean logado = rs.getBoolean("logado");

                users.add(new User(id_user, nome, login, logado));

            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao listar agenda " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
        return users;
    }

    public User procurarPorId(int id_user) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        User user = new User();

        try {
            String SQL = "SELECT id_user, nome, login, logado FROM user WHERE id_user = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setInt(1, id_user);
            rs = ps.executeQuery();
            user = null;

            while (rs.next()) {
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                Boolean logado = rs.getBoolean("logado");
                user = new User(id_user, nome, login, logado);
            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao procurar user " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
        return user;
    }
    
    public User procurarPorLogin(String loginParametro) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        User user = new User();

        try {
            String SQL = "SELECT id_user, nome, login, senha, logado FROM user WHERE login = ?";
            connL = this.conn;
            ps = connL.prepareStatement(SQL);
            ps.setString(1, loginParametro);
            rs = ps.executeQuery();
            user = null;

            while (rs.next()) {
                int id_user = rs.getInt("id_user");
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                boolean logado = rs.getBoolean("logado");
                user = new User(id_user, nome, login, senha, logado);
            }
        } catch (SQLException sqle) {
            System.out.println("Erro ao procurar user " + sqle);
        } finally {
            // ConexaoAulaDAO.close(connL,ps);
        }
        return user;
    }

    public void excluir(int id_user) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (id_user == 0) {
            System.out.println("O objeto user não pode ser nulo.");
        }

        try {
            String SQL = "DELETE FROM user WHERE id_user = ?";
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
    
    public void login(User user) {
        PreparedStatement ps = null;
        Connection connL = null;
        if (user.getId_user() == 0) {
            System.out.println("O objeto user não pode ser nulo.");
        }

        try {
            String SQL = "UPDATE user SET logado = ? WHERE id_user = ?";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setBoolean(1, user.isLogado());
            ps.setInt(2, user.getId_user());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Erro ao logar user " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
    }
    
    public ArrayList<User> buscarLogados(User userAtual) {
        PreparedStatement ps = null;
        Connection connL = null;
        ResultSet rs = null;
        ArrayList users = new ArrayList();
        try {
            String SQL = "SELECT * FROM user WHERE logado = 1 AND id_user != ? ORDER BY nome";
            connL = this.conn;

            ps = connL.prepareStatement(SQL);
            ps.setInt(1, userAtual.getId_user());
            rs = ps.executeQuery();

            while (rs.next()) {
                int id_user = rs.getInt("id_user");
                String nome = rs.getString("nome");
                String login = rs.getString("login");
                boolean logado = rs.getBoolean("logado");

                users.add(new User(id_user, nome, login, logado));
            }

        } catch (SQLException sqle) {
            System.out.println("Erro ao listar agenda " + sqle);
        } finally {
            ConexaoDAO.close(connL, ps);
        }
        return users;
    }
}
