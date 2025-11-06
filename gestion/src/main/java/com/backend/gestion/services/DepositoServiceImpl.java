package com.backend.gestion.services;

import org.springframework.stereotype.Service;
import com.backend.gestion.services.interfaces.DepositoService;
import com.backend.gestion.entities.Deposito;
import com.backend.gestion.repositories.DepositoRepository;
import java.util.List;

@Service

public class DepositoServiceImpl implements DepositoService {

    private final DepositoRepository depositoRepository;

    public DepositoServiceImpl(DepositoRepository depositoRepository) {
        this.depositoRepository = depositoRepository;
    }

    @Override
    public List<Deposito> getAllDepositos() {
        return depositoRepository.findAll();
    }

    @Override
    public Deposito createDeposito(Deposito deposito) {
        return depositoRepository.save(deposito);
    }

    @Override
    public Deposito updateDeposito(Long id, Deposito deposito) {
        if (depositoRepository.existsById(id)) {
            deposito.setId(id);
            return depositoRepository.save(deposito);
        }
        return null;
    }

    @Override
    public void deleteDeposito(Long id) {
        depositoRepository.deleteById(id);
    }
}

    


