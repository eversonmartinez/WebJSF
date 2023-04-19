//Relacionamento muitos para um
//ManyToOne
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import br.edu.ifsul.modelo.PessoaFisica;
import javax.persistence.Query;


public class PessoaFisicaDao<T> extends DaoGenerico<PessoaFisica> implements Serializable {

    public PessoaFisicaDao() {
        super();
        ordem="nome";
        classePersistente=PessoaFisica.class;
    }
    
    public boolean login(String usuario, String senha){
        Query query = em.createQuery("from PessoaFisica where upper(nomeUsuario) = :usuario and "
        + "uppser(senha) = :senha");
        
        query.setParameter("usuario", usuario.toUpperCase());
        query.setParameter("senha", senha.toUpperCase());
        
        if(!query.getResultList().isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public PessoaFisica localizaPorNomeUsuario(String usuario){
        Query query = em.createQuery("from PessoaFisica where upper(nomeUsuario) = :usuario") ;
        query.setParameter("usuario", usuario.toUpperCase());
        return(PessoaFisica) query.getSingleResult();
    }
}
