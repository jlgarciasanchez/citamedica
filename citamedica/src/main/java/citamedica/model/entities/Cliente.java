package citamedica.model.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	@Column(name = "razon_social", length = 200)
	String razonSocial;
	@Column(length = 9)
	String cif;
	@Column(length = 50)
	String direccion;
	@Column(length = 20)
	String municipio;
	@Column(length = 20)
	String provincia;
	@Column
	Date inicioContrato;
	@Column
	Date finContrato;
	@Column(name = "numero_reconocimientos", nullable=false)
	int numReconocimientos;
	
	public Cliente() {
		super();
	}
	
	public Cliente(int id, String razonSocial, String cif, String direccion, String municipio, String provincia,
			Date inicioContrato, Date finContrato, int numReconocimientos) {
		super();
		this.id = id;
		this.razonSocial = razonSocial;
		this.cif = cif;
		this.direccion = direccion;
		this.municipio = municipio;
		this.provincia = provincia;
		this.inicioContrato = inicioContrato;
		this.finContrato = finContrato;
		this.numReconocimientos = numReconocimientos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public Date getInicioContrato() {
		return inicioContrato;
	}
	public String getInicioContratoStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(inicioContrato);
		return strDate;
	}
	public void setInicioContrato(Date inicioContrato) {
		this.inicioContrato = inicioContrato;
	}
	public Date getFinContrato() {
		return finContrato;
	}
	public String getFinContratoStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(finContrato);
		return strDate;
	}

	public void setFinContrato(Date finContrato) {
		this.finContrato = finContrato;
	}
	public int getNumReconocimientos() {
		return numReconocimientos;
	}
	public void setNumReconocimientos(int numReconocimientos) {
		this.numReconocimientos = numReconocimientos;
	}
	
	
	
}
