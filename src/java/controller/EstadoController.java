package controller;

import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Pais;
import dao.EstadoDao;
import dao.PaisDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.Util;

@ManagedBean(name = "PaisController")
@ViewScoped
public class EstadoController implements Serializable{
    private EstadoDao<Estado> dao;
    private Estado objeto;
    private PaisDao<Pais> paisDao;
    
    public EstadoController(){
        dao = new EstadoDao<>();
        paisDao = new PaisDao();
    }
    
    public EstadoDao getDao() {
        return dao;
    }

    public void setDao(EstadoDao dao) {
        this.dao = dao;
    }

    public Estado getObjeto() {
        return objeto;
    }

    public void setObjeto(Estado objeto) {
        this.objeto = objeto;
    }
    
    public String listar(){
        return "/privado/estado/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Estado();
    }
    
    public void salvar(){
        boolean persistiu = false;
        if(objeto.getId() == null){
            persistiu=dao.persist(objeto);
        }else{
            persistiu=dao.merge(objeto);
        }
        if(persistiu){
            Util.infoMessage(dao.getMensagem());   
        }
        
        else{
            Util.errorMessage(dao.getMensagem());
        }
    }
    
    
    public void editar(Integer id){
        objeto = dao.localizar(id);
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

    public PaisDao<Pais> getPaisDao() {
        return paisDao;
    }

    public void setPaisDao(PaisDao<Pais> paisDao) {
        this.paisDao = paisDao;
    }
}
