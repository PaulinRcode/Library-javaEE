/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.domain.Autor;
import br.com.livraria.dao.AutorDAO;
import br.com.livraria.util.FacesUtil;
import java.util.List;

/**
 *
 * @author Paulinho
 */
@ManagedBean
@ViewScoped
public class AutorBean {

    private Autor autorCadastro;

    private List<Autor> listaAutores;
    private List<Autor> listaAutoresFiltrados;

    private String acao;
    private Long codigo;

    public Autor getAutorCadastro() {
        return autorCadastro;
    }

    public void setAutorCadastro(Autor autorCadastro) {
        this.autorCadastro = autorCadastro;
    }

    public List<Autor> getListaAutores() {
        return listaAutores;
    }

    public void setListaAutores(List<Autor> listaAutores) {
        this.listaAutores = listaAutores;
    }

    public List<Autor> getListaAutoresFiltrados() {
        return listaAutoresFiltrados;
    }

    public void setListaAutoresFiltrados(List<Autor> listaAutoresFiltrados) {
        this.listaAutoresFiltrados = listaAutoresFiltrados;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public void novo() {
        autorCadastro = new Autor();
    }

    public void salvar() {
        try {
            AutorDAO autorDAO = new AutorDAO();
            autorDAO.salvar(autorCadastro);

            autorCadastro = new Autor();

            FacesUtil.adicionarMsgInfo("Autor salvo com sucesso");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar incluir um Autor:" + ex.getMessage());
        }
    }

    public void carregarPesquisa() {
        try {
            AutorDAO autorDAO = new AutorDAO();
            listaAutores = autorDAO.listar();
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar listar os autores:" + ex.getMessage());
        }
    }

    public void carregarCadastro() {
        try {
            if (codigo != null) {

                AutorDAO autorDAO = new AutorDAO();
                autorCadastro = autorDAO.buscarPorCodigo(codigo);
            } else {
                autorCadastro = new Autor();
            }
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar obter os autores:" + ex.getMessage());
        }
    }

    public void excluir() {
        try {
            AutorDAO autorDAO = new AutorDAO();
            autorDAO.excluir(autorCadastro);

            FacesUtil.adicionarMsgInfo("Autor removido com sucesso");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar remover um autor:" + ex.getMessage());
        }
    }

    public void editar() {
        try {
            AutorDAO autorDAO = new AutorDAO();
            autorDAO.editar(autorCadastro);

            FacesUtil.adicionarMsgInfo("Autor editado com sucesso");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar editar um autor" + ex.getMessage());
        }
    }
}
