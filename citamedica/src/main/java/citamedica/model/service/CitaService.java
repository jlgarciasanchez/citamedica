package citamedica.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import citamedica.model.entities.Cita;

public interface CitaService {
    public Cita getCita(int id);
    public List<Cita> getAllCitas();
    public void removeCita(int id);
    public ArrayList<String> saveCita(Cita cita);
    public List<Cita> getCitasFiltered(Date desde, Date hasta, Integer cliente);
}
