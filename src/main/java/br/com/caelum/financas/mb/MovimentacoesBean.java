package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;
import java.time.LocalDateTime;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.CategoriaDao;
import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

@Named
@ViewScoped
public class MovimentacoesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Movimentacao> movimentacoes;
	private Movimentacao movimentacao = new Movimentacao();
	private List<Categoria> categorias;
	private Integer contaId;
	private Long categoriaId;

	@Inject
	private MovimentacaoDao movimentacaoDao;

	@Inject
	private ContaDao contaDao;

	@Inject
	private CategoriaDao categoriaDao;

	public void grava() {
		System.out.println("Fazendo a gravacao da movimentacao");

        Conta contaRelacionada = contaDao.busca(contaId);
        movimentacao.setConta(contaRelacionada);

        movimentacaoDao.adiciona(movimentacao);
        this.movimentacoes = movimentacaoDao.lista();
		limpaFormularioDoJSF();
	}

	public void remove() {
		System.out.println("Removendo a movimentacao");
        movimentacaoDao.remove(movimentacao);
		limpaFormularioDoJSF();
	}

	public void adicionaCategoria() {
		if(this.categoriaId != null && this.categoriaId > 0) {
			Categoria categoria = categoriaDao.busca(categoriaId);
			this.movimentacao.getCategorias().add(categoria);
		}
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}
	
	public Movimentacao getMovimentacao() {
		if(movimentacao.getData()==null) {
			movimentacao.setData(LocalDateTime.now());
		}
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

    public List<Categoria> getCategorias() {
	    if(categorias == null) {
            System.out.println("Listando categorias");
            categorias = categoriaDao.lista();
        }

        return categorias;
    }

    public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento manager que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.movimentacao = new Movimentacao();
	}

	public TipoMovimentacao[] getTiposDeMovimentacao() {
		return TipoMovimentacao.values();
	}
}
