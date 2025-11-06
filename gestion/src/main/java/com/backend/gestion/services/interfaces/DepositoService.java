package com.backend.gestion.services.interfaces;

import com.backend.gestion.entities.Deposito;
import java.util.List;
public interface DepositoService {
    
    List<Deposito> getAllDepositos();

    Deposito createDeposito(Deposito deposito);

    Deposito updateDeposito(Long id, Deposito deposito);

    void deleteDeposito(Long id);
}
