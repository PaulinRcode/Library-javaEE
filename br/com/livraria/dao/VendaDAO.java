/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.dao;

import br.com.livraria.domain.Venda;
import br.com.livraria.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Paulinho
 */
public class VendaDAO {

    public Long salvar(Venda venda) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        Long codigo = null;

        try {
            transacao = sessao.beginTransaction();
            codigo = (Long) sessao.save(venda);
            transacao.commit();
        } catch (RuntimeException ex) {
            if (transacao != null) {
                transacao.rollback();

            }
            throw ex;
        } finally {
            sessao.close();
        }
        return codigo;
    }

    public List<Venda> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Venda> vendas = null;

        try {
            Query consulta = sessao.getNamedQuery("Venda.listar");
            vendas = consulta.list();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }
        return vendas;
    }

    public Venda buscarPorCodigo(Long codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Venda venda = null;

        try {
            Query consulta = sessao.getNamedQuery("Venda.buscarPorCodigo");
            consulta.setLong("codigo", codigo);
            venda = (Venda) consulta.uniqueResult();
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            sessao.close();
        }
        return venda;
    }

    public void excluir(Venda venda) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.delete(venda);
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

    public void editar(Venda venda) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.update(venda);
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
