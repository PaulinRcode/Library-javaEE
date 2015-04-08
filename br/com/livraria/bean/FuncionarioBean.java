/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.livraria.domain.Funcionario;
import br.com.livraria.dao.FuncionarioDAO;
import br.com.livraria.util.FacesUtil;
/**
 *
 * @author Paulinho
 */
import java.util.List;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

    private Funcionario funcionarioCadastro;

    private List<Funcionario> listarFuncionarios;
    private List<Funcionario> listarFuncionariosFiltrados;

    private String acao;
    private Long codigo;

    public Funcionario getFuncionarioCadastro() {
        return funcionarioCadastro;
    }

    public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
        this.funcionarioCadastro = funcionarioCadastro;
    }

    public List<Funcionario> getListarFuncionarios() {
        return listarFuncionarios;
    }

    public void setListarFuncionarios(List<Funcionario> listarFuncionarios) {
        this.listarFuncionarios = listarFuncionarios;
    }

    public List<Funcionario> getListarFuncionariosFiltrados() {
        return listarFuncionariosFiltrados;
    }

    public void setListarFuncionariosFiltrados(List<Funcionario> listarFuncionariosFiltrados) {
        this.listarFuncionariosFiltrados = listarFuncionariosFiltrados;
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
        funcionarioCadastro = new Funcionario();
    }

    public void salvar() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.salvar(funcionarioCadastro);

            funcionarioCadastro = new Funcionario();
            FacesUtil.adicionarMsgInfo("Funcionário salvo com sucesso");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar salvar funcionário:" + ex.getMessage());
        }
    }

    public void carregarPesquisa() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            listarFuncionarios = funcionarioDAO.listar();
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar listar os funcionários:" + ex.getMessage());
        }
    }

    public void carregarCadastro() {
        try {
            if (codigo != null) {
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                funcionarioCadastro = funcionarioDAO.buscarPorCodigo(codigo);
            } else {
                funcionarioCadastro = new Funcionario();
            }
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar obter os dados do funcionário:" + ex.getMessage());
        }
    }

    public void excluir() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.excluir(funcionarioCadastro);

            FacesUtil.adicionarMsgInfo("Funcionário excluido com sucessco");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar remover um funcionário:" + ex.getMessage());
        }
    }

    public void editar() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.editar(funcionarioCadastro);

            FacesUtil.adicionarMsgInfo("Funcionário editado com sucesso");
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar editar od dados do funcionário:" + ex.getMessage());
        }

    }
}
