package testes;

import br.edu.ifsul.modelo.Pais;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestePersistenciaPais {

   
    public static void main(String[] args) {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("JSFModelPU");
       EntityManager em = emf.createEntityManager();
       Pais p = new Pais() ;
       p.setNome("Brasil");
       p.setIso("BR");
       em.getTransaction().begin();
       em.persist(p);
       em.getTransaction().commit();
       em.close();
       emf.close();
       
    }
    
}
