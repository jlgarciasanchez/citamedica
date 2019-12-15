package citamedica.model.dao;

import java.util.Date;
import java.util.List;

import citamedica.model.entities.Cita;


public interface CitaDao {
    public Cita getCita(int id);
    public List<Cita> getAllCitas();
    public void removeCita(int id);
    public void saveCita(Cita cita);
    public List<Cita> getCitasFiltered(Date desde, Date hasta, Integer cliente);
}