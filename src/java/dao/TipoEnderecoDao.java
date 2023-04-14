//Relacionamento muitos para um
//ManyToOne
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import br.edu.ifsul.modelo.TipoEndereco;


public class TipoEnderecoDao<T> extends DaoGenerico<TipoEndereco> implements Serializable {

    public TipoEnderecoDao() {
        super();
        ordem="descricao";
        classePersistente=TipoEndereco.class;
    }
    
}
