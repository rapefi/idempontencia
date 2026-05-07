package com.idempotencia.resource;


import com.idempotencia.dao.ProdutoDao;
import com.idempotencia.dto.ProdutoDto;
import com.idempotencia.model.ProdutoModel;
import io.smallrye.common.constraint.NotNull;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/idempotencia")
public class IndempotenciaResource {

    @Inject
    ProdutoDao produtoDao;

    @POST
    public Response idempotencia(@NotNull @HeaderParam("Idempotency-Key") String idempotencyKey, ProdutoDto produtoDto) {
        ProdutoDto produtoDtoRetorno =  produtoDao.salvar(produtoDto, idempotencyKey);

        return Response.ok(" Produto Cadastrado com sucesso para chave:  " + idempotencyKey + " produto:  "+produtoDtoRetorno).build();
    }

    @GET
    public Response listarProdutos() {
        return Response.ok(produtoDao.listarProdutos()).build();
    }

}
