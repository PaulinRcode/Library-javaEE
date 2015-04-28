/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.bean;

import br.com.livraria.dao.FuncionarioDAO;
import br.com.livraria.dao.ItemDAO;
import br.com.livraria.dao.LivroDAO;
import br.com.livraria.dao.VendaDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.domain.Livro;
import br.com.livraria.domain.Venda;
import br.com.livraria.domain.Item;
import br.com.livraria.domain.Funcionario;
import br.com.livraria.filter.VendaFilter;
import br.com.livraria.util.FacesUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Paulinho
 */
@ManagedBean
@ViewScoped
public class VendaBean {

    private List<Livro> listaLivros;
    private List<Livro> listaLivrosFiltrados;

    private Venda vendaCadastro;
    private List<Item> listaItens;

    @ManagedProperty(value = "#{autenticacaoBean}")
    private AutenticacaoBean autenticacaoBean;

    private VendaFilter filtro;
    private List<Venda> listaVendas;

    public List<Livro> getListaLivros() {
        return listaLivros;
    }

    public void setListaLivros(List<Livro> listaLivros) {
        this.listaLivros = listaLivros;
    }

    public List<Livro> getListaLivrosFiltrados() {
        return listaLivrosFiltrados;
    }

    public void setListaLivrosFiltrados(List<Livro> listaLivrosFiltrados) {
        this.listaLivrosFiltrados = listaLivrosFiltrados;
    }

    public Venda getVendaCadastro() {
        if (vendaCadastro == null) {
            vendaCadastro = new Venda();
            vendaCadastro.setHorario(new Date());
            vendaCadastro.setValor(new BigDecimal("0.00"));
            vendaCadastro.setQuantidade(0);
        }
        return vendaCadastro;
    }

    public void setVendaCadastro(Venda vendaCadastro) {
        this.vendaCadastro = vendaCadastro;
    }

    public List<Item> getListaItens() {
        if (listaItens == null) {
            listaItens = new ArrayList<>();
        }
        return listaItens;
    }

    public void setListaItens(List<Item> listaItens) {
        this.listaItens = listaItens;
    }

    public VendaFilter getFiltro() {
        if (filtro == null) {
            filtro = new VendaFilter();
        }
        return filtro;
    }

    public void setFiltro(VendaFilter filtro) {
        this.filtro = filtro;
    }

    public List<Venda> getListaVendas() {
        return listaVendas;
    }

    public void setListaVendas(List<Venda> listaVendas) {
        this.listaVendas = listaVendas;
    }

    public AutenticacaoBean getAutenticacaoBean() {
        return autenticacaoBean;
    }

    public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
        this.autenticacaoBean = autenticacaoBean;
    }
    

    public void carregarLivros() {
        try {
            LivroDAO livroDAO = new LivroDAO();
            listaLivros = livroDAO.listar();
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar listar os livros:" + ex.getMessage());
        }
    }

    public void adicionar(Livro livro) {
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < listaItens.size() & posicaoEncontrada < 0; pos++) {
            Item itemTemp = listaItens.get(pos);

            if (itemTemp.getLivro().equals(livro)) {
                posicaoEncontrada = pos;
            }
        }

        Item item = new Item();
        item.setLivro(livro);

        if (posicaoEncontrada < 0) {
            item.setQuantidade(1);
            item.setValor(livro.getPreco());
            listaItens.add(item);
        } else {
            Item itemTemp = listaItens.get(posicaoEncontrada);
            item.setQuantidade(itemTemp.getQuantidade() + 1);

            item.setValor(livro.getPreco().multiply(new BigDecimal(item.getQuantidade())));
            listaItens.set(posicaoEncontrada, item);

        }

        vendaCadastro.setValor(vendaCadastro.getValor().add(livro.getPreco()));
        vendaCadastro.setQuantidade((vendaCadastro.getQuantidade() + 1));
    }

    public void remover(Item item) {
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < listaItens.size() & posicaoEncontrada < 0; pos++) {
            Item itemTemp = listaItens.get(pos);

            if (itemTemp.getLivro().equals(item.getLivro())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            listaItens.remove(posicaoEncontrada);
            vendaCadastro.setValor(vendaCadastro.getValor().subtract(item.getValor()));
            vendaCadastro.setQuantidade(vendaCadastro.getQuantidade() - item.getQuantidade());
        }

    }

    public void carregarDadosVenda() {
        vendaCadastro.setHorario(new Date());

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getCodigo());

        vendaCadastro.setFuncionario(funcionario);

    }

    public void salvar() {
        try {
            VendaDAO vendaDAO = new VendaDAO();

            Long codigoVenda = vendaDAO.salvar(vendaCadastro);
            Venda vendaFK = vendaDAO.buscarPorCodigo(codigoVenda);

            for (Item item : listaItens) {
                item.setVenda(vendaFK);

                ItemDAO itemDAO = new ItemDAO();
                itemDAO.salvar(item);

            }

            vendaCadastro = new Venda();
            vendaCadastro.setValor(new BigDecimal("0.00"));

            listaItens = new ArrayList<Item>();

            FacesUtil.adicionarMsgInfo("Venda salva com sucesso");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar salvar a venda" + ex.getMessage());
        }
    }

    public void buscar() {
        try {
            VendaDAO vendaDAO = new VendaDAO();
            listaVendas = vendaDAO.buscar(filtro);

            for (Venda venda : listaVendas) {
                System.out.println(venda);
            }
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar buscar uma venda:" + ex.getMessage());
        }

    }
}
