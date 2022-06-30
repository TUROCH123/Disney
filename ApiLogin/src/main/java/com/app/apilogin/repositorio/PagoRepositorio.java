package com.app.apilogin.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.apilogin.modelo.Pago;

@Repository
public interface PagoRepositorio extends JpaRepository<Pago, Integer>{
    
}
