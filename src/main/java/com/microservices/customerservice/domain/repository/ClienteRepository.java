package com.microservices.customerservice.domain.repository;

import com.microservices.customerservice.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByIdentificacion(String identificacion);

    List<Cliente> findByEstadoTrue();

    @Query("SELECT c FROM Cliente c WHERE c.estado = true AND c.nombre LIKE %:nombre%")
    List<Cliente> findByNombreContainingAndEstadoTrue(@Param("nombre") String nombre);

    boolean existsByIdentificacion(String identificacion);

    @Query("SELECT c FROM Cliente c WHERE c.estado = true")
    List<Cliente> findAllActiveClients();
}

