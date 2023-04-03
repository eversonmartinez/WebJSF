package dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Pais;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import util.Util;

public class PaisDao implements Serializable{
    
    private String mensagem = "";
    private EntityManager em;
    
    public PaisDao(){
        em = EntityManagerUtil.getEntityManager();
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public List<Pais> getList(){
        return em.createQuery("from Pais order by nome").getResultList();
    }
    
    public boolean salvar(Pais obj){
        try {
            em.getTransaction().begin();
            if(obj.getId()==null){
                em.persist(obj);
            } else{
                em.merge(obj);
            }
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        }catch (Exception e){
            if(em.getTransaction().isActive()==false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao persistir objeto! " + Util.getErrorMessage(e);
            return false;
        }
    }
    
    public boolean remover(Pais obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        }catch (Exception e){
            if(em.getTransaction().isActive()==false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao remover objeto! " + Util.getErrorMessage(e);
            return false;
        }
    }
    
    public Pais localizar(Integer id){
        return em.find(Pais.class, id);
    }
}
