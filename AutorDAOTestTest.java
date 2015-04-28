/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.test;

import java.util.List;
import br.com.livraria.dao.AutorDAO;
import br.com.livraria.domain.Autor;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Paulinho
 */
public class AutorDAOTestTest {

    @Test
    @Ignore
    public void salvar() {

        Autor aut1 = new Autor();
        aut1.setNome("NOMEA");

        Autor aut2 = new Autor();
        aut2.setNome("NOMEB");

        AutorDAO dao = new AutorDAO();

        dao.salvar(aut1);
        dao.salvar(aut2);
    }

    @Test
    @Ignore
    public void listar() {

        AutorDAO dao = new AutorDAO();
        List<Autor> autores = dao.listar();

        System.out.println(autores);

    }

    @Test
    @Ignore
    public void buscarPorCodigo() {

        AutorDAO dao = new AutorDAO();

        Autor aut1 = dao.buscarPorCodigo(3L);
        Autor aut2 = dao.buscarPorCodigo(7L);

        System.out.println(aut1);
        System.out.println(aut2);
    }

    @Test
    @Ignore
    public void excluir() {

        AutorDAO dao = new AutorDAO();

        Autor aut1 = dao.buscarPorCodigo(9L);
        Autor aut2 = dao.buscarPorCodigo(10L);
        Autor aut3 = dao.buscarPorCodigo(11L);
        Autor aut4 = dao.buscarPorCodigo(12L);
        Autor aut5 = dao.buscarPorCodigo(13L);
        
        dao.excluir(aut1);
        dao.excluir(aut2);
        dao.excluir(aut3);
        dao.excluir(aut4);
        dao.excluir(aut5);

    }

    @Test
    @Ignore
    public void editar() {

        AutorDAO dao = new AutorDAO();

        Autor aut1 = dao.buscarPorCodigo(8L);
        aut1.setNome("NOMEX");

        dao.editar(aut1);
    }

}
