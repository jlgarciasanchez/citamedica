package citamedica.model.dao.implementation;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import citamedica.model.dao.ClienteDao;
import citamedica.model.entities.Cliente;

public class ClienteDaoImpl implements ClienteDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    @Transactional
	@Override
	public Cliente getCliente(int id) {
    	Cliente cliente = (Cliente) sessionFactory.getCurrentSession().get(Cliente.class, id);
		return cliente;
	}
    
    @Transactional
	@Override
	@SuppressWarnings("unchecked")
	public List<Cliente> getAllClientes() { 
		List<Cliente> clientes = (List<Cliente>) sessionFactory.getCurrentSession().createCriteria(Cliente.class).list();
		return clientes;
	}
    
    @Transactional
	@Override
	public void removeCliente(int id) {
		Cliente cliente = (Cliente) sessionFactory.getCurrentSession().get(Cliente.class, id);
		sessionFactory.getCurrentSession().delete(cliente);
	}

    @Transactional
	@Override
	public void saveCliente(Cliente cliente) {
    	sessionFactory.getCurrentSession().saveOrUpdate(cliente);
		
	}

	@Transactional
	@Override
	@SuppressWarnings("unchecked")
	public List<Cliente> getClientesFiltered(String razonSoc, String municipio) {
    	List<Cliente> clientes = (List<Cliente>) sessionFactory.getCurrentSession().createCriteria(Cliente.class)
    			.add( Restrictions.like("razonSocial", "%"+razonSoc+"%") ).add( Restrictions.like("municipio", "%"+municipio+"%") ).list();
    	return clientes;
	}
}