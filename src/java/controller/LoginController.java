/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import br.edu.ifsul.modelo.PessoaFisica;
import dao.PessoaFisicaDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.Util;

/**
 *
 * @author Administrador
 */

@ManagedBean (name= "loginController")
@SessionScoped
public class LoginController implements Serializable {
    
    private PessoaFisicaDao<PessoaFisica> dao;
    private PessoaFisica usuarioLogado;
    private String usuario;
    private String senha;
    
    public LoginController(){
        dao = new PessoaFisicaDao<>();
    }
 
    public PessoaFisicaDao<PessoaFisica> getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDao<PessoaFisica> dao) {
        this.dao = dao;
    }

    public PessoaFisica getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(PessoaFisica usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
        
    public String paginaLogin(){
        return "/loginfaces-redirect=true";
    }
   
    public String efetuarLogin(){
        if(dao.login(usuario, senha)){
            usuarioLogado= dao.localizaPorNomeUsuario(usuario);
            Util.infoMessage("Login realizado com sucesso!");
            return("/index?faces-redirect=true");
        } else {
            Util.errorMessage("Usuário ou senha inválidos");
            return "/login?faces-redirect=true";
        }
    }
    
    public String efetuarLogout(){
        usuarioLogado=null;
        Util.infoMessage("Logout realizado com sucesso!");
        return "/index?faces-redirect=true"; 
    }
}
