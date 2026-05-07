package com.idempotencia.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO")
public class ProdutoModel {

        @Column(name = "ID")
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "NOME")
        private String nome;
        @Column(name = "PRECO")
        private BigDecimal preco;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public BigDecimal getPreco() {
            return preco;
        }

        public void setPreco(BigDecimal preco) {
            this.preco = preco;
    }

    @Override
    public String toString() {
        return "ProdutoModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}
