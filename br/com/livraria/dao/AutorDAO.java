/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.dao;

import br.com.livraria.domain.Autor;
import br.com.livraria.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Paulinho
 */
public class AutorDAO {

    public void salvar(Autor autor) {

        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.save(autor);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {

            sessao.close();
        }

    }

    public List<Autor> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Autor> autores = null;

        try {
            Query consulta = sessao.getNamedQuery("Autor.listar");
            autores = consulta.list();

        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }
        return autores;

    }

    public Autor buscarPorCodigo(Long codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Autor autor = null;

        try {
            Query consulta = sessao.getNamedQuery("Autor.buscarPorCodigo");
            consulta.setLong("codigo", codigo);

            autor = (Autor) consulta.uniqueResult();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }
        return autor;
    }

    public void excluir(Autor autor) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.delete(autor);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            sessao.close();
        }
    }

    public void editar(Autor autor) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.update(autor);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw ex;
        } finally {
            sessao.close();
        }
    }
}
