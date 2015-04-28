/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.test;

import java.util.List;

import br.com.livraria.dao.FuncionarioDAO;
import br.com.livraria.domain.Funcionario;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author Paulinho
 */
public class FuncionarioDAOTestTest {
    
    @Test
    @Ignore
    public void salvar(){
    
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("812.678.584-51");
        funcionario.setFuncao("ADMINISTRADOR");
        funcionario.setNome("JOÃO DA SILVA");
        funcionario.setSenha("123456");
        
        FuncionarioDAO dao = new FuncionarioDAO();
        dao.salvar(funcionario);
            
    }
    
    @Test
    @Ignore
    public void listar(){
      FuncionarioDAO dao = new FuncionarioDAO();
      List<Funcionario> funcionarios = dao.listar();
      
      System.out.println(funcionarios);
    }
    
    @Test
    @Ignore
    public void buscarPorCodigo(){
     FuncionarioDAO dao = new FuncionarioDAO();
     
     Funcionario funcionario = dao.buscarPorCodigo(3L);
     
     System.out.println(funcionario);     
    }
    
    @Test
    @Ignore
    public void excluir(){
    FuncionarioDAO dao = new FuncionarioDAO();
    
    Funcionario funcionario = dao.buscarPorCodigo(3L);
    dao.excluir(funcionario);
    }
    
    @Test
    @Ignore
    public void editar(){
     FuncionarioDAO dao = new FuncionarioDAO();
     
     Funcionario funcionario = dao.buscarPorCodigo(1L);
     funcionario.setCpf("999.999.999-99");
     funcionario.setFuncao("USUARIO");
     funcionario.setNome("MARIA DA SILVA");
     funcionario.setSenha("123456789");
     
     dao.editar(funcionario);
             
    }
    
    @Test
    @Ignore
    public void autenticar(){
        FuncionarioDAO dao = new FuncionarioDAO();
        
        Funcionario funcionario = dao.autenticar("999.999.999-99","123456789");
        
        Assert.assertNotNull(funcionario);
        
    }
    
}
