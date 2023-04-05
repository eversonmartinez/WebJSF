package dao;

import br.edu.ifsul.modelo.Pais;
import java.io.Serializable;

public class PaisDao<T> extends DaoGenerico<Pais> implements Serializable{

    public PaisDao(){
        super();
        ordem="nome";
        classePersistente = Pais.class ;
    }
}
