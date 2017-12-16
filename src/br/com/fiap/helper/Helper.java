package br.com.fiap.helper;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.dao.JpaUtil;
import br.com.fiap.entity.Agenda;
import br.com.fiap.entity.MatMed;
import br.com.fiap.entity.Paciente;
import br.com.fiap.entity.Procedimento;

public class Helper {
	private EntityManager em;

	public Helper() {
		this.em = JpaUtil.getEntityManager();
	}

	public void salvar(Paciente paciente) throws Exception {
		try {
			GenericDao<Paciente> dao = new GenericDao<>(Paciente.class);
			dao.adicionar(paciente);
			System.out.println("Paciente incluído com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void salvar(Agenda agenda) throws Exception {
		try {
			GenericDao<Agenda> dao = new GenericDao<>(Agenda.class);
			dao.adicionar(agenda);
			System.out.println("Agenda incluída com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void salvar(Procedimento procedimento) throws Exception {
		try {
			GenericDao<Procedimento> dao = new GenericDao<>(Procedimento.class);
			dao.adicionar(procedimento);
			System.out.println("Procedimento incluído com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void salvar(MatMed matmed) throws Exception {
		try {
			GenericDao<MatMed> dao = new GenericDao<>(MatMed.class);
			dao.adicionar(matmed);
			System.out.println("Material/Medicamento incluído com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Paciente buscarPaciente(String cpf) {
		TypedQuery<Paciente> tQuery = em.createQuery("select p from Paciente p where cpf = :cpf", Paciente.class);
		tQuery.setParameter("cpf", cpf);
		return tQuery.getSingleResult();
	}
}