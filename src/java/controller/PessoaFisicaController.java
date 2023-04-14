//Relacionamento Muitos para um
//ManyToOne
package controller;

import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Pais;
import dao.CidadeDao;
import dao.PessoaFisicaDao;
import dao.PaisDao;
import dao.TipoEnderecoDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.Util;

@ManagedBean(name = "PessoaFisicaController")
@ViewScoped
public class PessoaFisicaController implements Serializable {

    private PessoaFisicaDao<PessoaFisica> dao;
    private PessoaFisica objeto;
    private CidadeDao cidadeDao;
    private TipoEnderecoDao tipoEnderecoDao;
    private Endereco endereco;
    private Boolean novoEndereco;

    public PessoaFisicaController() {
        dao = new PessoaFisicaDao<>();
        cidadeDao = new CidadeDao();
        tipoEnderecoDao = new TipoEnderecoDao();
    }

    public PessoaFisicaDao getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDao dao) {
        this.dao = dao;
    }

    public PessoaFisica getObjeto() {
        return objeto;
    }

    public void setObjeto(PessoaFisica objeto) {
        this.objeto = objeto;
    }

    public String listar() {
        return "/privado/pessoafisica/listar?faces-redirect=true";
    }
    
    public void novoEndereco(){
        endereco = new Endereco();
        novoEndereco=true;
    }
    
    public void alterarEndereco(int index){
        endereco = objeto.getEnderecos().get(index);
        novoEndereco = false;
    }
    
    public void salvarEndereco (){
        if (novoEndereco){
            objeto.adicionarEndereco(endereco);
        }
        Util.infoMessage("Endereço persistido com sucesso!");
    }
    
    public void removerEndereco(int index){
        objeto.removerEndereco(index);
        Util.infoMessage("Endereço removido com sucesso!");
    }
    
    public void novo() {
        objeto = new PessoaFisica();
    }

    public void salvar() {
        boolean persistiu = false;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.infoMessage(dao.getMensagem());
        } else {
            Util.errorMessage(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remove(objeto)) {
            Util.infoMessage(dao.getMensagem());
        } else {
            Util.errorMessage(dao.getMensagem());
        }
    }

    public CidadeDao getCidadeDao() {
        return cidadeDao;
    }

    public void setCidadeDao(CidadeDao cidadeDao) {
        this.cidadeDao = cidadeDao;
    }

    public TipoEnderecoDao getTipoEnderecoDao() {
        return tipoEnderecoDao;
    }

    public void setTipoEnderecoDao(TipoEnderecoDao tipoEnderecoDao) {
        this.tipoEnderecoDao = tipoEnderecoDao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getNovoEndereco() {
        return novoEndereco;
    }

    public void setNovoEndereco(Boolean novoEndereco) {
        this.novoEndereco = novoEndereco;
    }

}
