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
import javax.persistence.Transient;

/**
 *
 * @author Paulinho
 */
@Entity
@Table(name = "tbl_vendas")
@NamedQueries({
    @NamedQuery(name = "Venda.listar", query = "SELECT venda FROM Venda venda"),
    @NamedQuery(name = "Venda.buscarPorCodigo", query = "SELECT venda FROM Venda venda WHERE venda.codigo = :codigo")})
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cd_venda")
    private Long codigo;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "hr_venda", nullable = false)
    private Date horario;

    @Column(name = "vl_venda", precision = 7, scale = 2, nullable = false)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_funcionarios_cd_funcionario", referencedColumnName = "cd_funcionario", nullable = false)
    private Funcionario funcionario;
    
    @Transient
    private Integer quantidade;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Autor [codigo=" + codigo + ", horario=" + horario + ", valor=" + valor + ", funcionario=" + funcionario + "]";
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
        Venda other = (Venda) obj;
        if (codigo == null) {
            if (other.codigo != null) 
                return false;            
        } else if (!codigo.equals(other.codigo)) 
            return false;        
        return true;

    }
}
