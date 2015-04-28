/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.converter;

import br.com.livraria.dao.AutorDAO;
import br.com.livraria.domain.Autor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Paulinho
 */
public class AutorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {

        try {
            Long codigo = Long.parseLong(valor);
            AutorDAO autorDAO = new AutorDAO();
            Autor autor = autorDAO.buscarPorCodigo(codigo);

            return autor;
        } catch (RuntimeException ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext FacesContext, UIComponent component, Object objeto) {
        try {

            Autor autor = (Autor) objeto;
            Long codigo = autor.getCodigo();
            return codigo.toString();
        } catch (RuntimeException ex) {
            return null;
        }
    }

}
