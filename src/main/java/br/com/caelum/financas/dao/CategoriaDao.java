package br.com.caelum.financas.dao;

import br.com.caelum.financas.modelo.Categoria;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoriaDao {

    @PersistenceContext
    private EntityManager manager;

    public Categoria busca(Long id) {
        return manager.find(Categoria.class, id);
    }

    public List<Categoria> lista() {
        return manager.createQuery("select c from Categoria c")
                .getResultList();
    }
}
