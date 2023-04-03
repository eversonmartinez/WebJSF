package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class Util {
        
    public static String getErrorMessage(Exception e){
        while(e.getCause()!= null){
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        if (retorno.contains("foreign key")){
            retorno = "Registro não pode ser excluido por possuir referência no sistema...";
        }
        return retorno;
    }
    
    public static void infoMessage(String title){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, "");
        facesContext.addMessage(null, message);
    }
    
    public static void errorMessage(String title){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, "");
        facesContext.addMessage(null, message);
    }
}
