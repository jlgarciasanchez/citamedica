package citamedica.model.service;

import citamedica.model.entities.Usuario;

public interface UsuarioService {
    public Usuario login(String user, String pass);
}
