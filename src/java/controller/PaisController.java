package controller;

import br.edu.ifsul.modelo.Pais;
import dao.PaisDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.Util;

@ManagedBean(name = "PaisController")
@SessionScoped
public class PaisController implements Serializable{
    private PaisDao dao;
    private Pais objeto;
    
    public PaisController(){
        dao = new PaisDao();
    }
    
    public PaisDao getDao() {
        return dao;
    }

    public void setDao(PaisDao dao) {
        this.dao = dao;
    }

    public Pais getObjeto() {
        return objeto;
    }

    public void setObjeto(Pais objeto) {
        this.objeto = objeto;
    }
    
    public String listar(){
        return "/privado/pais/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Pais();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if(dao.salvar(objeto)){
            Util.infoMessage(dao.getMensagem());
            return "listar?faces-redirect=true";      
        }
        
        else{
            Util.errorMessage(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            Util.infoMessage(dao.getMensagem());
        }
            else{
            Util.errorMessage(dao.getMensagem());
        }
    }

    
    
}
