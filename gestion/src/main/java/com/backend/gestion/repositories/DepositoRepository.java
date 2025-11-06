package com.backend.gestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.gestion.entities.Deposito;


@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {

}


