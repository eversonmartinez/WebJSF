//Relacionamento Muitos para um
//ManyToOne
package controller;

import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Pais;
import br.edu.ifsul.modelo.Produto;
import dao.CidadeDao;
import dao.PessoaFisicaDao;
import dao.PaisDao;
import dao.ProdutoDao;
import dao.TipoEnderecoDao;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.Util;
import util.UtilRelatorios;

@ManagedBean(name = "PessoaFisicaController")
@ViewScoped
public class PessoaFisicaController implements Serializable {

    private PessoaFisicaDao<PessoaFisica> dao;
    private PessoaFisica objeto;
    private CidadeDao cidadeDao;
    private TipoEnderecoDao tipoEnderecoDao;
    private Endereco endereco;
    private Boolean novoEndereco;
    private ProdutoDao<Produto> produtoDao;
    private Produto produto;

    public PessoaFisicaController() {
        dao = new PessoaFisicaDao<>();
        cidadeDao = new CidadeDao();
        tipoEnderecoDao = new TipoEnderecoDao();
        produtoDao = new ProdutoDao<>();
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

    public ProdutoDao<Produto> getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao<Produto> produtoDao) {
        this.produtoDao = produtoDao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public void adicionarDesejo(){
        if(produto!=null){
            if(!objeto.getDesejos().contains(produto)){
                objeto.getDesejos().add(produto);
                Util.infoMessage("Desejo adicionado com sucesso!");
            } else{
                Util.errorMessage("Este desejo já existe na sua lista!");
            }
        }
    }
    
    public void removerDesejo(int index){
        produto = objeto.getDesejos().get(index);
        objeto.getDesejos().remove(produto);
        Util.infoMessage("Desejo removido com sucesso");
    }
    
    public void imprimeProdutos(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioProdutos", parametros, produtoDao.getListaTodos());
    }
}
