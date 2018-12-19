package controller;
import classes.Conv;
import classes.ConvUser;
import classes.User;
import dao.ConvDAO;
import dao.ConvUserDAO;
import dao.UserDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import util.ConexaoDAO;
import util.DataUtil;

public class PrincipalController {
    private Connection conn;
    private UserDAO userDao;
    private ConvDAO convDao;
    private ConvUserDAO convUserDao;
    
    public PrincipalController() {
        try {
            this.conn = ConexaoDAO.getConnection();
            this.userDao = new UserDAO();
            this.convDao = new ConvDAO();
            this.convUserDao = new ConvUserDAO();
        } catch (Exception e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }
    
    public ArrayList<User> buscarLogados(User userAtual) {
        UserDAO uDao = new UserDAO();
        ArrayList<User> users = uDao.buscarLogados(userAtual);
        return users;
    }
    
    public String buscarMensagensPorUser(User user) {
        String msg = "";
        ConvUserDAO cUserDAO = new ConvUserDAO();
        ArrayList<ConvUser> convUsers = cUserDAO.listar(user.getId_user());
        for(ConvUser convUser : convUsers) {
            UserDAO uDao = new UserDAO();
            Conv conv = convDao.procurarPorId(convUser.getId_conv());
            User remetente = uDao.procurarPorId(conv.getId_remetente());
            msg = msg + (DataUtil.formatarData(conv.getData()) + " " + remetente.getNome()
                    + " ("+remetente.getLogin()+")" +": " + conv.getTexto() + "\n");
        }
       
        return msg;
    }
    
    public void enviarMensagem(User user, String mensagem) {
        ConvDAO cDAO = new ConvDAO();
        ConvUserDAO cUserDAO = new ConvUserDAO();
        Conv conv = new Conv();
        conv.setId_remetente(user.getId_user());
        conv.setTexto(mensagem);
        int id_conv = cDAO.inserir(conv, user);
        cUserDAO.inserirParaLogados(id_conv);
    }
    
    public void enviarMensagem(User remetente, String mensagem, List<User> users) {
        ConvDAO cDAO = new ConvDAO();
        Conv conv = new Conv();
        conv.setId_remetente(remetente.getId_user());
        conv.setTexto(mensagem);
        int id_conv = cDAO.inserir(conv, remetente);
        
        //inserir para si mesmo também para poder visualizar
        ConvUserDAO remetenteUserDAO = new ConvUserDAO();
        remetenteUserDAO.inserirParaUser(id_conv, remetente);
        
        for(User u : users) {
            ConvUserDAO cUserDAO = new ConvUserDAO();
            cUserDAO.inserirParaUser(id_conv, u);
        }
    }
    
    public void limparMensagens(User user) {
        ConvUserDAO cUserDAO = new ConvUserDAO();
        cUserDAO.excluir(user.getId_user());
    }
}