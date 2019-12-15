package citamedica.model.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Cita implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@ManyToOne
	@JoinColumn(name="cliente_fk")
	Cliente cliente;
	
	@Column
	Date fecha;
	
	@Column
	Date hora;
	
	@Column(name = "numero_visitas_previstas")
	Integer numeroVisitasPrevistas;
	
	@Column(name = "numero_visitas_realizadas")
	Integer numeroVisitasRealizadas;
	
	public Cita() {
		super();
	}	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		//Soluciona un error por el que el que se iba al día anterior.
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.set(Calendar.HOUR_OF_DAY,1);
		this.fecha = calendar.getTime();
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Integer getNumeroVisitasPrevistas() {
		return numeroVisitasPrevistas;
	}

	public void setNumeroVisitasPrevistas(Integer numeroVisitasPrevistas) {
		this.numeroVisitasPrevistas = numeroVisitasPrevistas;
	}
	
	public Integer getNumeroVisitasRealizadas() {
		return numeroVisitasRealizadas;
	}

	public void setNumeroVisitasRealizadas(Integer numeroVisitasRealizadas) {
		this.numeroVisitasRealizadas = numeroVisitasRealizadas;
	}
	
	public String getFechaStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(fecha);
		return strDate;
	}
	
	public String getHoraStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		String strDate = formatter.format(hora);
		return strDate;
	}

	
}
