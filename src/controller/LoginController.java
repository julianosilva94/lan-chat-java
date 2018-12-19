package controller;

import classes.User;
import dao.UserDAO;
import java.sql.Connection;
import util.ConexaoDAO;

public class LoginController {

    private Connection conn;
    private UserDAO userDao;

    public LoginController() {
        try {
            this.conn = ConexaoDAO.getConnection();
            this.userDao = new UserDAO();
        } catch (Exception e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }

    public User autenticacao(String login, String senha) throws Exception {
        User user = userDao.procurarPorLogin(login);
        if (user.getSenha().equals(senha)) {
            user.setLogado(true);
            userDao.login(user);
        } else {
            throw new Exception();
        }
        return user;
    }

    public void deslogar(User user) {
        user.setLogado(false);
        userDao.login(user);
    }
}
