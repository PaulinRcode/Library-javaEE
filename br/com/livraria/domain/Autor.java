/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Paulinho
 */
@Entity
@Table(name = "tbl_autores")
@NamedQueries({
    @NamedQuery(name = "Autor.listar", query = "SELECT autor FROM Autor autor"),
    @NamedQuery(name = "Autor.buscarPorCodigo", query = "SELECT autor FROM Autor autor WHERE autor.codigo = :codigo")})
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cd_autor")
    private Long codigo;

    @NotEmpty(message = "O campo nome é obrigatório")
    @Column(name = "nm_autor", length = 50, nullable = false)
    private String nome;

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

    @Override
    public String toString() {
        return "Autor [codigo=" + codigo + ", nome=" + nome + "]";
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
        Autor other = (Autor) obj;
        if (codigo == null) {
            if (other.codigo != null) 
                return false;            
        } else if (!codigo.equals(other.codigo)) 
            return false;        
        return true;

    }
}
