package com.idempotencia.dao;

import com.idempotencia.dto.ProdutoDto;
import com.idempotencia.model.ProdutoModel;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProdutoDao {

    @Inject
    private EntityManager em;


    @CacheResult(cacheName = "produtoCache")
    @Transactional
    public ProdutoDto salvar(ProdutoDto produtoDto, @CacheKey String idempotencyKey) {

        ProdutoModel produto = new ProdutoModel();
        produto.setId(produtoDto.getId());
        produto.setNome(produtoDto.getNome());


        Log.info("Salvando produto: " + produto.getNome() + " com idempotencyKey: " + idempotencyKey);
        em.persist(produto);

        produtoDto.setId(produto.getId());

        return produtoDto;
    }


    public List<ProdutoModel> listarProdutos() {
        List<ProdutoModel> models = em.createQuery("SELECT p FROM ProdutoModel p", ProdutoModel.class).getResultList();
        return models;
    }


}
