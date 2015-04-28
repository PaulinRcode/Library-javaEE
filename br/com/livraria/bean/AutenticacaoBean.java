/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.bean;

import br.com.livraria.dao.FuncionarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.livraria.domain.Funcionario;
import br.com.livraria.util.FacesUtil;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Paulinho
 */
@ManagedBean
@SessionScoped
public class AutenticacaoBean {

    private Funcionario funcionarioLogado;

    public Funcionario getFuncionarioLogado() {
        if (funcionarioLogado == null) {
            funcionarioLogado = new Funcionario();
        }
        return funcionarioLogado;
    }

    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
    }

    public String autenticar() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioLogado = funcionarioDAO.autenticar(funcionarioLogado.getCpf(), DigestUtils.md5Hex(funcionarioLogado.getSenha()));

            if (funcionarioLogado == null) {
                FacesUtil.adicionarMsgError("CPF e/ou senha inválidos");
                return null;
            } else {
                FacesUtil.adicionarMsgInfo("Funcionário autenticado com sucesso");
                return "/pages/principal.xhtml?faces-redirect=true";

            }
        } catch (RuntimeException ex) {
            FacesUtil.adicionarMsgError("Erro ao tentar autenticar no sistema:" + ex.getMessage());
            return null;
        }
    }

    public String sair() {
        funcionarioLogado = null;
        return "/pages/autenticacao.xhtml?faces-redirect=true";
    }
}
