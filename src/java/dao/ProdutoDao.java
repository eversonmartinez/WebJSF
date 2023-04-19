//Relacionamento muitos para um
//ManyToOne
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import br.edu.ifsul.modelo.Produto;
import java.io.Serializable;


public class ProdutoDao<T> extends DaoGenerico<Produto> implements Serializable {

    public ProdutoDao() {
        super();
        ordem="nome";
        classePersistente=Produto.class;
    }
    
}
