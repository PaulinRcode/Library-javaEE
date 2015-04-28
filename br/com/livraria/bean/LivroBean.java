/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.bean;

import br.com.livraria.dao.AutorDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.domain.Livro;
import br.com.livraria.domain.Autor;
import br.com.livraria.dao.LivroDAO;
import br.com.livraria.util.FacesUtil;
import java.util.List;

/**
 *
 * @author Paulinho
 */
@ManagedBean
@ViewScoped
public class LivroBean {

    private Livro livroCadastro;

    private List<Livro> listarLivros;
    private List<Livro> listarLivrosFiltrados;

    private String acao;
    private Long codigo;

    private List<Autor> listaAutores;

    public Livro getLivroCadastro() {
        return livroCadastro;
    }

    public void setLivroCadastro(Livro livroCadastro) {
        this.livroCadastro = livroCadastro;
    }

    public List<Livro> getListarLivros() {
        return listarLivros;
    }

    public void setListarLivros(List<Livro> listarLivros) {
        this.listarLivros = listarLivros;
    }

    public List<Livro> getListarLivrosFiltrados() {
        return listarLivrosFiltrados;
    }

    public void setListarLivrosFiltrados(List<Livro> listarLivrosFiltrados) {
        this.listarLivrosFiltrados = listarLivrosFiltrados;
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

    public List<Autor> getListaAutores() {
        return listaAutores;
    }

    public void setListaAutores(List<Autor> listaAutores) {
        this.listaAutores = listaAutores;
    }

    public void novo() {
        livroCadastro = new Livro();
    }

    public void salvar() {
        try {
            LivroDAO livroDAO = new LivroDAO();
            livroDAO.salvar(livroCadastro);

            livroCadastro = new Livro();

            FacesUtil.adicionarMsgInfo("Livro salvo com sucesso");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar salvar um livro:" + ex.getMessage());
        }
    }

    public void carregarPesquisa() {
        try {
            LivroDAO livroDAO = new LivroDAO();
            listarLivros = livroDAO.listar();

        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar listar os livros:" + ex.getMessage());
        }
    }

    public void carregarCadastro() {
        try {
            if (codigo != null) {
                LivroDAO livroDAO = new LivroDAO();
                livroCadastro = livroDAO.buscarPorCodigo(codigo);
            } else {
                livroCadastro = new Livro();
            }

            AutorDAO autorDAO = new AutorDAO();
            listaAutores = autorDAO.listar();
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro tentar obter os dados do livro:" + ex.getMessage());
        }
    }

    public void excluir() {
        try {
            LivroDAO livroDAO = new LivroDAO();
            livroDAO.excluir(livroCadastro);

            FacesUtil.adicionarMsgInfo("Livro removido com sucesso");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar remover o livro:" + ex.getMessage());
        }
    }

    public void editar() {
        try {
            LivroDAO livroDAO = new LivroDAO();
            livroDAO.editar(livroCadastro);

            FacesUtil.adicionarMsgInfo("Livro editado com sucesso");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar editar os dados do livro:" + ex.getMessage());
        }
    }
}
