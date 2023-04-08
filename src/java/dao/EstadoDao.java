/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import br.edu.ifsul.modelo.Estado;


public class EstadoDao<T> extends DaoGenerico<Estado> implements Serializable {

    public EstadoDao() {
        super();
        ordem="nome";
        classePersistente=Estado.class;
    }
    
}
