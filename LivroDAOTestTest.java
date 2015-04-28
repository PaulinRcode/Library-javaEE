/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.test;

import java.util.List;

import org.junit.Test;
import br.com.livraria.dao.AutorDAO;
import br.com.livraria.dao.LivroDAO;
import br.com.livraria.domain.Livro;
import br.com.livraria.domain.Autor;
import java.math.BigDecimal;
import java.util.Date;
import org.junit.Ignore;

/**
 *
 * @author Paulinho
 */
public class LivroDAOTestTest {
    
    @Test
    @Ignore
    public void salvar(){
        
     AutorDAO autorDAO= new AutorDAO();
     Autor autor = autorDAO.buscarPorCodigo(1L);
     
     Livro livro = new Livro();
     livro.setNome("LIVROA");
     livro.setNomeEditora("EDITORAA");
     livro.setData(new Date());
     livro.setQuantidade(17);
     livro.setDescricao("DESCRICAOA");
     livro.setPreco(new BigDecimal(25.50D));
     livro.setAutor(autor);
     
     LivroDAO livroDAO = new LivroDAO();
     livroDAO.salvar(livro);
             
    }
    
    @Test
    @Ignore
    public void buscarPorCodigo(){
     LivroDAO livroDAO = new LivroDAO();
     Livro livro = livroDAO.buscarPorCodigo(2L);
     
     System.out.println(livro);
    }
    
    @Test
    //@Ignore
    public void listar(){
    
        LivroDAO dao = new LivroDAO();
        
        List<Livro> livros = dao.listar();
    
        System.out.println(livros);
    }
    
    @Test
    @Ignore
    public void excluir(){
    
        LivroDAO dao = new LivroDAO();
        Livro liv1 = dao.buscarPorCodigo(12L);
        Livro liv2 = dao.buscarPorCodigo(13L);
        Livro liv3 = dao.buscarPorCodigo(14L);
        Livro liv4 = dao.buscarPorCodigo(15L);
        
        dao.excluir(liv1);
        dao.excluir(liv2);
        dao.excluir(liv3);
        dao.excluir(liv4);
    }
    
    @Test
    @Ignore
    public void editar(){
    
        LivroDAO livroDAO = new LivroDAO();
        Livro livro = livroDAO.buscarPorCodigo(3L);
        livro.setNome("NOMEX");
        livro.setNomeEditora("EDITORAX");
        livro.setData(new Date());
        livro.setQuantidade(9);
        livro.setDescricao("DESCRICAOX");
        livro.setPreco(new BigDecimal(25.50D));
        
        AutorDAO autorDAO = new AutorDAO();
        Autor autor = autorDAO.buscarPorCodigo(1L);
        livro.setAutor(autor);
        
        livroDAO.editar(livro);
    }
    
}
