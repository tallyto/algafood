package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
    List<Pedido> findAll();

    @Query("SELECT p FROM Pedido p " +
        "JOIN FETCH p.cliente " +
        "JOIN FETCH p.restaurante r JOIN FETCH r.cozinha " +
        "JOIN FETCH p.formaPagamento " +
        "JOIN FETCH p.itens i JOIN FETCH i.produto " +
        "WHERE p.id = :pedidoId")
    Optional<Pedido> findByIdWithAssociations(@Param("pedidoId") Long pedidoId);
}
