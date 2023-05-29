package pe.com.apiciberelectrik;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.com.apiciberelectrik.entity.gestion.RolEntity;
import pe.com.apiciberelectrik.entity.gestion.UsuarioEntity;
import pe.com.apiciberelectrik.seguridad.SeguridadConfig;
import pe.com.apiciberelectrik.service.gestion.RolService;
import pe.com.apiciberelectrik.service.gestion.UsuarioService;

@SpringBootTest
class ApiCiberElectrikApplicationTests {
	@Autowired
	private UsuarioService usuarioservicio;
	@Autowired
	private SeguridadConfig seguridadConfig;
	@Autowired
	private RolService rolservicio;
	@Test
	void crearUsuarioAdmin() {
		RolEntity roladmin = new RolEntity();
		roladmin.setNombre("Administrador");
		roladmin.setEstado(true);
		rolservicio.add(roladmin);

		RolEntity rol = new RolEntity();
		rol.setCodigo(1);

		UsuarioEntity us = new UsuarioEntity();
		us.setUsuario("Jorge7700");
		us.setNombre("Jorge Enriquez");
		us.setPassword(seguridadConfig.passwordEncoder().encode("jorgeadmin"));
		us.setEstado(true);
		us.setRol(rol);
		us.setDni("77418201");

		usuarioservicio.add(us);
	}

}
