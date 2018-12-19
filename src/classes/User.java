/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 *
 * @author 265551
 */
public class User {
    
    int id_user;
    String nome;
    String login;
    String senha;
    boolean logado;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public User() {
    }

    public User(int ID_User, String nome, String login, String senha, boolean logado) {
        this.id_user = ID_User;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.logado = logado;
    }

    public User(int ID_User, String nome, String login, boolean logado) {
        this.id_user = ID_User;
        this.nome = nome;
        this.login = login;
        this.logado = logado;
    }
    
    //É um metodo padrão de qualquer classe
    //que é executado quando um objeto é chamado em tela
    @Override
    public String toString() {
        return this.nome;
    }
    
    
}
