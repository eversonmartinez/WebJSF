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
    private PaisDao<Pais> dao;
    private Pais objeto;
    
    public PaisController(){
        dao = new PaisDao<>();
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
        boolean persistiu = false;
        if(objeto.getId() == null){
            persistiu=dao.persist(objeto);
        }else{
            persistiu=dao.merge(objeto);
        }
        if(persistiu){
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
        if (dao.remove(objeto)){
            Util.infoMessage(dao.getMensagem());
        }
            else{
            Util.errorMessage(dao.getMensagem());
        }
    }       
}
