package citamedica.model.dao.implementation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import citamedica.model.dao.CitaDao;
import citamedica.model.entities.Cita;
import citamedica.model.entities.Cliente;

public class CitaDaoImpl implements CitaDao{

	@Autowired
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Transactional
	@Override
	public Cita getCita(int id) {
    	Cita cita = (Cita) sessionFactory.getCurrentSession().get(Cita.class, id);
		return cita;
	}

    @SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Cita> getAllCitas() {
    	List<Cita> citas = (List<Cita>) sessionFactory.getCurrentSession().createCriteria(Cita.class).list();
		return citas;
	}

    @Transactional
	@Override
	public void removeCita(int id) {
    	Cita cita = (Cita) sessionFactory.getCurrentSession().get(Cita.class, id);
		sessionFactory.getCurrentSession().delete(cita);
	}

    @Transactional
	@Override
	public void saveCita(Cita cita) {
    	sessionFactory.getCurrentSession().saveOrUpdate(cita);	
	}

    @SuppressWarnings("unchecked")
    @Transactional
	@Override
	public List<Cita> getCitasFiltered(Date desde, Date hasta, Integer clienteId) {
    	List<Cita> citas = null;
    	//Si alguna fecha viene vacía, le asignamos un valor extremo para la búsqeuda.
    	if(desde == null) {
    		Calendar calendar = Calendar.getInstance();
    		calendar.clear();
    		calendar.set(Calendar.YEAR, 0);
    		desde = calendar.getTime();
    	}
    	if(hasta == null) {
    		Calendar calendar = Calendar.getInstance();
    		calendar.clear();
    		calendar.set(Calendar.YEAR, 9999);
    		hasta = calendar.getTime();
    	}
    	if(clienteId == null) {
    		citas = (List<Cita>) sessionFactory.getCurrentSession().createCriteria(Cita.class)
        			.add( Restrictions.between("fecha", desde, hasta)).list();
    	}
    	else {
    		Cliente cliente = (Cliente) sessionFactory.getCurrentSession().get(Cliente.class, clienteId);
        	citas = (List<Cita>) sessionFactory.getCurrentSession().createCriteria(Cita.class)
        			.add( Restrictions.between("fecha", desde, hasta)).add(Restrictions.eq("cliente", cliente)).list();
    	}
    	return citas;
	}
}
