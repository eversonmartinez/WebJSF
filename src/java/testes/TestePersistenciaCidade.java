/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testes;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import dao.EstadoDao;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrador
 */
public class TestePersistenciaCidade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager em = new EntityManagerUtil().getEntityManager();
        Cidade c = new Cidade();
        
        c.setNome("Macaé");
        EstadoDao estadoDao= new EstadoDao();
        c.setEstado((Estado) estadoDao.localizar(6));
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }
    
}
