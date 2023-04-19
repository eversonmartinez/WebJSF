/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testes;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.TipoEndereco;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrador
 */
public class TestePersistenciaTipoEndereco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager em = new EntityManagerUtil().getEntityManager();
        TipoEndereco te1 = new TipoEndereco();
        te1.setDescricao("Trabalho");
        em.getTransaction().begin();
        em.persist(te1);
        em.getTransaction().commit();
        
        TipoEndereco te2 = new TipoEndereco();
        te2.setDescricao("Residencial");
        em.getTransaction().begin();
        em.persist(te2);
        em.getTransaction().commit();
        em.close();
    }
    
}
