package citamedica.model.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import citamedica.model.dao.UsuarioDao;
import citamedica.model.entities.Usuario;
import citamedica.model.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioDao usuarioDao;

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}
	
	@Override
	public Usuario login(String user, String pass) {
		Usuario usuario = usuarioDao.getUsuario(user);
		if(usuario == null || !usuario.getPassword().equals(pass)) {
			usuario = null;
		}
		return usuario;
	}

}
