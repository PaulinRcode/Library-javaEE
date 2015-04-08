/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Paulinho
 */
@Entity
@Table(name = "tbl_itens")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cd_item")
    private Long codigo;
    
    @Column(name = "qt_item", nullable = false)
    private Integer quantidade;
    
    @Column(name = "vl_parcial_item", precision = 7, scale = 2, nullable = false)
    private BigDecimal valor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_vendas_cd_venda", referencedColumnName = "cd_venda", nullable = false)
    private Venda venda;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_livros_cd_livro", referencedColumnName = "cd_livro", nullable = false)
    private Livro livro;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
    
    
    
    
}
