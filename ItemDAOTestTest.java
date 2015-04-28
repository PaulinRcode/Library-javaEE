/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.test;

import java.util.List;
import br.com.livraria.dao.ItemDAO;
import br.com.livraria.domain.Livro;
import br.com.livraria.domain.Venda;
import br.com.livraria.dao.LivroDAO;
import br.com.livraria.dao.VendaDAO;
import br.com.livraria.domain.Item;
import java.math.BigDecimal;
import org.junit.Ignore;
import org.junit.Test;



/**
 *
 * @author Paulinho
 */
public class ItemDAOTestTest {
    
    @Test
    @Ignore
    public void salvar(){
        
        LivroDAO livroDAO = new LivroDAO();
        Livro livro = livroDAO.buscarPorCodigo(3L);
        
        VendaDAO vendaDAO = new VendaDAO();
        Venda venda = vendaDAO.buscarPorCodigo(2L);
                
        Item item = new Item();
        item.setLivro(livro);
        item.setQuantidade(4);
        item.setValor(new BigDecimal(32.54D));
        item.setVenda(venda);
        
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.salvar(item);
   
    }
    
    @Test
    @Ignore
    public void buscarPorCodigo(){
    
        ItemDAO dao = new ItemDAO();
        Item item = dao.buscarPorCodigo(2L);
        
        System.out.println(item);
    }
    
    @Test
    @Ignore
    public void listar(){
   
        ItemDAO dao = new ItemDAO();
        List<Item> item = dao.listar();
        
        System.out.println(item);
    }
    
    @Test
    @Ignore
    public void excluir(){
    
        ItemDAO dao = new ItemDAO();
        Item item = dao.buscarPorCodigo(3L);
        
        dao.excluir(item);
    }
    
    @Test
    @Ignore
    public void editar(){
    
        LivroDAO livroDAO = new LivroDAO();
        Livro livro = livroDAO.buscarPorCodigo(2L);
        
        VendaDAO vendaDAO = new VendaDAO();
        Venda venda = vendaDAO.buscarPorCodigo(2L);
        
        ItemDAO itemDAO = new ItemDAO();
        Item item = itemDAO.buscarPorCodigo(2L);
        
        item.setLivro(livro);
        item.setQuantidade(5);
        item.setValor(new BigDecimal(32.50D));
        item.setVenda(venda);
        
        itemDAO.editar(item);
                
    }
    
    
}
