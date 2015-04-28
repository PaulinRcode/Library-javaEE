/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.livraria.util;

import br.com.livraria.bean.AutenticacaoBean;
import br.com.livraria.domain.Funcionario;
import java.util.Map;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;



/**
 *
 * @author Paulinho
 */
public class AutenticacaoPhaseListener  implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        facesContext.getViewRoot();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        String paginaAtual = uiViewRoot.getViewId();
        
        boolean ehPaginaAutenticacao = paginaAtual.contains("autenticacao.xhtml");
        
        if(!ehPaginaAutenticacao){
            ExternalContext externalContext = facesContext.getExternalContext();
            Map<String, Object> mapa = externalContext.getSessionMap();
            AutenticacaoBean autenticacaoBean = (AutenticacaoBean) mapa.get("autenticacaoBean");
            
            Funcionario funcionario = autenticacaoBean.getFuncionarioLogado();
            
            if(funcionario.getFuncao() ==null) {
            FacesUtil.adicionarMsgError("Funcionário não autenticado");
            
                Application application = facesContext.getApplication();
                NavigationHandler navigationHandler = application.getNavigationHandler();
                navigationHandler.handleNavigation(facesContext, null, "/pages/autenticacao.xhtml?faces-redirect=true");
            
            }
            
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {        
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}