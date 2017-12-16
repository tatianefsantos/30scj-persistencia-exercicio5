package br.com.fiap.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "agenda", schema = "consultorio")
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data")
	private Date data;

	@Temporal(TemporalType.TIME)
	@Column(name = "hora")
	private Date hora;

	@Column(name = "descricao")
	private String descricao;

	@ManyToMany
	@JoinTable(name = "agenda_paciente", 
		joinColumns = { @JoinColumn(name = "agenda_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "paciente_cpf") })
	private Set<Paciente> pacientes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Set<Paciente> getPacientes() {
		return pacientes;
	}
	
	public void setPacientes(Set<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

}
