/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Paulinho
 */

@Entity
@Table(name="tbl_livros")
@NamedQueries({@NamedQuery(name="Livro.listar", query = "SELECT livro FROM Livro livro"),
@NamedQuery(name = "Livro.buscarPorCodigo", query = "SELECT livro FROM Livro livro WHERE livro.codigo = :codigo")})
public class Livro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cd_livro")
    private Long codigo;
    
    @Column(name = "nm_nome", length = 50 , nullable = false)
    private String nome;
    
    @Column(name = "nm_editora", length = 50, nullable = false)
    private String nomeEditora;
    
    @Column(name = "dt_edicao", nullable = false)
    private Date data;
    
    @Column(name = "ds_livro", length = 50, nullable = false)
    private String descricao;
    
    @Column(name = "qt_livro", nullable = false)
    private Integer quantidade;
    
    @Column(name = "vl_preco", precision = 7, scale = 2, nullable = false)
    private BigDecimal preco;
    
    @JoinColumn(name = "tbl_autores_cd_autor", referencedColumnName = "cd_autor", nullable = false)
    private Autor autor;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeEditora() {
        return nomeEditora;
    }

    public void setNomeEditora(String nomeEditora) {
        this.nomeEditora = nomeEditora;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    
    
    
}
