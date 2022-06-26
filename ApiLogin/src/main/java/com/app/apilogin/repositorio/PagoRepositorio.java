package com.app.apilogin.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.apilogin.modelo.Pago;

@Repository
public interface PagoRepositorio extends JpaRepository<Pago, Long>{

//    @Query(value = "SELECT * FROM Usuarios p WHERE p.email LIKE %:email% AND p.password LIKE %:pass%",
//    nativeQuery = true)
//    Usuario validarDatos(@Param(value = "email") String email, @Param(value = "pass") String pass);
    
}
