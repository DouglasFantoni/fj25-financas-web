package br.com.caelum.financas.mb;

import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ContasBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Inject
    private ContaDao contaDao;

	private Conta conta = new Conta();
	private List<Conta> contas;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void grava() {
		System.out.println("Gravando a conta");

		if(conta.getId() == null) {
			contaDao.adiciona(conta);
		} else {
			contaDao.altera(conta);
		}

        this.contas = contaDao.lista();
		limpaFormularioDoJSF();
	}

	public List<Conta> getContas() {
	    if(contas == null) {
            this.contas = contaDao.lista();
        }
        System.out.println("Listando as contas");
        return this.contas;
	}

	public void remove() {
		System.out.println("Removendo a conta");

		limpaFormularioDoJSF();
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.conta = new Conta();
	}
}
