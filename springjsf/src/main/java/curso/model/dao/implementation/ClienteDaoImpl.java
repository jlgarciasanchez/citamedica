package curso.model.dao.implementation;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import curso.model.dao.ClienteDao;
import curso.model.entities.Cliente;

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
}