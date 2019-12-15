package citamedica.model.dao.implementation;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import citamedica.model.dao.UsuarioDao;
import citamedica.model.entities.Usuario;

public class UsuarioDaoImpl implements UsuarioDao{

	@Autowired
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Transactional
	@Override
	public Usuario getUsuario(String user) {
		Usuario usuario =  (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class).add(Restrictions.eq("user", user)).uniqueResult();
		return usuario;
	}

}
