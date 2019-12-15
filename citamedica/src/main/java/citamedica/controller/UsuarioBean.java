package citamedica.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import citamedica.model.entities.Usuario;
import citamedica.model.service.UsuarioService;

@ManagedBean
@SessionScoped 
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{usuarioService}")
	private UsuarioService usuarioService;
	
	private Usuario usuario;
	
	private String user;
	private String pass;
	
	private boolean error;

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public void login() {
		usuario = usuarioService.login(user, pass);
		error = usuario == null;
		user = null;
		pass = null;
	}
	
	public void logout() {
		usuario = null;
	}
	
	public boolean isAdmin() {
		boolean isAdmin = false;
		if(usuario != null && usuario.getUser().equals("admin")) {
			isAdmin = true;
		}
		return isAdmin;
	}
	
	public boolean isMedico() {
		boolean isMedico = false;
		if(usuario != null && usuario.getUser().equals("medico") || isAdmin()) {
			isMedico = true;
		}
		return isMedico;
	}
	
	public boolean isReserva() {
		boolean isReserva = false;
		if(usuario != null && usuario.getUser().equals("reserva") || isAdmin()) {
			isReserva = true;
		}
		return isReserva;
	}
}
