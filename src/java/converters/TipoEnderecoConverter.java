/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converters;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.TipoEndereco;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="TipoEnderecoConverter")
public class TipoEnderecoConverter implements Converter, Serializable {
    
    //converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;    
        }
        return EntityManagerUtil.getEntityManager().find(TipoEndereco.class, Integer.parseInt(string));
    }
    
    
    //converte de um objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o==null){
            return null;
        }
        TipoEndereco obj = (TipoEndereco) o;
        return obj.getId().toString();
    }
    
}
