//Relacionamento muitos para um
//ManyToOne
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import br.edu.ifsul.modelo.Cidade;
import java.io.Serializable;


public class CidadeDao<T> extends DaoGenerico<Cidade> implements Serializable {

    public CidadeDao() {
        super();
        ordem="nome";
        classePersistente=Cidade.class;
    }
    
}
