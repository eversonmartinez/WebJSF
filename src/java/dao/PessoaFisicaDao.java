//Relacionamento muitos para um
//ManyToOne
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import br.edu.ifsul.modelo.PessoaFisica;


public class PessoaFisicaDao<T> extends DaoGenerico<PessoaFisica> implements Serializable {

    public PessoaFisicaDao() {
        super();
        ordem="nome";
        classePersistente=PessoaFisica.class;
    }
    
}
