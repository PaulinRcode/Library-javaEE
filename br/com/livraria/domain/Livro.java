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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Paulinho
 */
@Entity
@Table(name = "tbl_livros")
@NamedQueries({
    @NamedQuery(name = "Livro.listar", query = "SELECT livro FROM Livro livro"),
    @NamedQuery(name = "Livro.buscarPorCodigo", query = "SELECT livro FROM Livro livro WHERE livro.codigo = :codigo")})
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cd_livro")
    private Long codigo;
 
    @NotEmpty(message = "O campo nome é obrigatório")
    @Column(name = "nm_livro", length = 50, nullable = false)
    private String nome;

    @NotEmpty(message = "O campo editora é obrigatório")
    @Column(name = "nm_editora", length = 50, nullable = false)
    private String nomeEditora;
    
    //@NotEmpty(message = "O campo data da edição e obrigatório")
    @NotNull(message = "O campo data de edição é obrigatório")
    @Temporal(value = TemporalType.DATE)
    @Column(name = "dt_edicao", nullable = false)
    private Date data;

    @Size(min = 5, max = 50, message = "Tamanho inválido para o campo descrição (5 - 50)")
    @Column(name = "ds_livro", length = 50, nullable = false)
    private String descricao;

    @NotNull(message = "O campo quantidade é obrigatório")
    @Min(value = 0, message = "Informe um valor maior ou igual a zero para o campo quantidade")
    @Max(value = 9999, message = "Informe um valor menor que dez mil para o campo quantidade")
    @Column(name = "qt_livro", nullable = false)
    private Integer quantidade;

    @NotNull(message = "O campo preço é obrigatório")
    @DecimalMin(value = "0.00", message = "Informe um valor maior ou igual a zero para o campo preço")
    @DecimalMax(value = "99999.99", message = "Infome um valor menor que 100 mil para o campo preço")
    @Digits(integer = 5, fraction = 2, message = "Informe um valor válido para o campo preço")
    @Column(name = "vl_preco", precision = 7, scale = 2, nullable = false)
    private BigDecimal preco;

    @NotNull(message = "O campo autor é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
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
    
    @Override
    public String toString() {
        return "Livro [codigo=" + codigo + ", nome=" + nome + ", nomeEditora=" + nomeEditora + ", data=" + data + ", descricao=" + descricao + ", quantidade=" + quantidade + ", preco=" + preco + ", autor=" + autor + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (obj == null) 
            return false;       
        if (getClass() != obj.getClass()) 
            return false;        
        Livro other = (Livro) obj;
        if (codigo == null) {
            if (other.codigo != null) 
                return false;            
        } else if (!codigo.equals(other.codigo)) 
            return false;        
        return true;

    }

}
