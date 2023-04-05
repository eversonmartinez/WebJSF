/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import util.Util;

public class DaoGenerico<T> implements Serializable {
    private List<T> listaObjetos;
    private List<T> listaTodos;
    protected Class classePersistente;
    protected String mensagem="";
    protected EntityManager em;   //utilizado para acessar os objetosdo banco
    protected String ordem ="id"; //guarda a oordenação atual da classe
    protected String filtro=""; //ordena o filtro informado pelo usuário
    protected Integer maximoObjetos=8; //quantidade de objetos que serão exibidos na tela
    protected Integer posicaoAtual=0; //gaurda a página em que estamos atualmente (4-10)
    protected Integer totalObjetos=0;

    public DaoGenerico(){
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<T> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        filtro = filtro.replaceAll("[';-]", "");
        if(filtro.length()>0){
            if (ordem.equals("id")){
                try{
                    Integer.parseInt(filtro);
                    where+= " where " + ordem + " = '" + filtro + "' ";
                }catch(Exception e){}
            } else{
                where += " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }
            jpql+=where;
            jpql += " order by " + ordem;
        }
        totalObjetos=em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
    }
    
    public List<T> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName() + " order by " + ordem;
        return em.createQuery(jpql).getResultList();
    }
    
    public void primeiraPagina(){
        posicaoAtual = 0;
    }
    
    public void anteriorPagina(){
        posicaoAtual -= maximoObjetos;
        if(posicaoAtual<0){
            posicaoAtual =0;
        }
    }
    
    public void proximaPagina(){
        if(posicaoAtual + maximoObjetos < totalObjetos){
            posicaoAtual += maximoObjetos;
        }
    }
    
    public void ultimaPagina(){
        int resto = totalObjetos%maximoObjetos;
        if(resto > 0){
            posicaoAtual = totalObjetos - resto;
        }
        else{
            posicaoAtual = totalObjetos - maximoObjetos;
        }
    }
    
    public String getMensagemNavegacao(){
        int ate = posicaoAtual + maximoObjetos;
        if (ate > totalObjetos){
            ate = totalObjetos;
        }
        
        return "Listando de " + (posicaoAtual+1) + " até " + ate + " de " + totalObjetos + " registros";
    }
    
    public void rollback(){
        if (em.getTransaction().isActive()==false){
            em.getTransaction().begin();
        }
        
        em.getTransaction().rollback();
    }
    
    public boolean persist(T objeto){
        try{
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        }catch (Exception ex){
            rollback();
            mensagem="Erro ao persistir: " + Util.getErrorMessage(ex);
            return false;
        }
    }
    
    public boolean merge(T objeto){
        try{
            em.getTransaction().begin();
            em.merge(objeto);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        }catch (Exception ex){
            rollback();
            mensagem="Erro ao persistir: " + Util.getErrorMessage(ex);
            return false;
        }
    }
    
    public boolean remove(T objeto){
        try{
            em.getTransaction().begin();
            em.remove(objeto);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        }catch (Exception ex){
            rollback();
            mensagem="Erro ao remover: " + Util.getErrorMessage(ex);
            return false;
        }
    }
    
    public T localizar(Integer id){
        rollback(); //em algumas situações ao tentar editar, algo pode ser mantido na seção de forma errônea
        T obj = (T) em.find(classePersistente, id);
        return obj;
    }

    public void setListaObjetos(List<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public void setListaTodos(List<T> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Integer getMaximoObjetos() {
        return maximoObjetos;
    }

    public void setMaximoObjetos(Integer maximoObjetos) {
        this.maximoObjetos = maximoObjetos;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }
}
