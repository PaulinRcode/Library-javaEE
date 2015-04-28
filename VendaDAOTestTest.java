/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.test;

import java.util.List;
import br.com.livraria.domain.Funcionario;
import br.com.livraria.domain.Venda;
import br.com.livraria.dao.FuncionarioDAO;
import br.com.livraria.dao.VendaDAO;
import br.com.livraria.filter.VendaFilter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;



/**
 *
 * @author Paulinho
 */
public class VendaDAOTestTest {
    
    @Test
    @Ignore
    public void salvar(){
    
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.buscarPorCodigo(4L);
        
        Venda venda = new Venda();
        venda.setFuncionario(funcionario);
        venda.setHorario(new Date());
        venda.setValor(new BigDecimal(12));
        
        VendaDAO vendaoDAO = new VendaDAO();
        vendaoDAO.salvar(venda);
        
        
    }
    
    @Test
    @Ignore
    public void buscarPorCodigo(){
     
        VendaDAO vendaDAO = new VendaDAO();
        Venda venda = vendaDAO.buscarPorCodigo(2L);
        
        System.out.println(venda);
    }
    
    @Test
    @Ignore
    public void listar(){
    
        VendaDAO dao = new VendaDAO();
        List<Venda> vendas = dao.listar();
        
        System.out.println(vendas);
    
    }
    
    @Test
    @Ignore
    public void excluir(){
    
        VendaDAO vendaDAO = new VendaDAO();
        Venda ven1 = vendaDAO.buscarPorCodigo(3L);
        Venda ven2 = vendaDAO.buscarPorCodigo(4L);
        Venda ven3 = vendaDAO.buscarPorCodigo(5L);
        
        vendaDAO.excluir(ven1);
        vendaDAO.excluir(ven2);
        vendaDAO.excluir(ven3);
    }
    
    @Test
    @Ignore
    public void editar(){
    
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.buscarPorCodigo(1L);
        
        VendaDAO vendaDAO = new VendaDAO();
        Venda venda = vendaDAO.buscarPorCodigo(2L);
        
        venda.setHorario(new Date());
        venda.setValor(new BigDecimal(12.40D));
        venda.setFuncionario(funcionario);
        
        vendaDAO.editar(venda);
        
    }
    
    @Test
    @Ignore
    public void buscar() throws ParseException {
    
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        VendaFilter filtro = new VendaFilter();
        filtro.setDataInicial(formato.parse("02/04/2015"));
        filtro.setDataFinal(formato.parse("09/04/2015"));
        
        VendaDAO vendaDAO = new VendaDAO();
        List<Venda> vendas = vendaDAO.buscar(filtro);
        
        for(Venda venda : vendas) {
           System.out.println(venda);
        }
    
    }
    
}
