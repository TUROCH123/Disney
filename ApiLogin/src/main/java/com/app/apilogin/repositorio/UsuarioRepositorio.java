package com.app.apilogin.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.apilogin.modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	@Query(value = "SELECT * FROM Usuarios p WHERE p.email LIKE %:email% AND p.password LIKE %:pass%", nativeQuery = true)
	Usuario validarDatos(@Param(value = "email") String email, @Param(value = "pass") String pass);

	public Usuario findByEmail(String email);

}