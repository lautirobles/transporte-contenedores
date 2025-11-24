package com.backend.gestion.services.interfaces;
import com.backend.gestion.entities.Cliente;
import java.util.List;

public interface ClienteService {
    
    List<Cliente> getAllClientes();

    Cliente createCliente(Cliente cliente);

    Cliente updateCliente(Long id, Cliente cliente);

    void deleteCliente(Long id);
    
    Cliente getClienteById(Long id);
}
