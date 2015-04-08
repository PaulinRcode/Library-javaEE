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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Paulinho
 */
@Entity
@Table(name = "tbl_vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cd_venda")
    private Long codigo;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "hr_venda", nullable = false)
    private Date horario;
    
    @Column(name = "vl_venda", precision = 7, scale = 2 , nullable = false)
    private BigDecimal valor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tbl_funcionarios_cd_funcionario", referencedColumnName = "cd_funcionario", nullable = false)
    private Funcionario funcionario;

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
    
    

}
