package br.com.fiap.aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import br.com.fiap.entity.Agenda;
import br.com.fiap.entity.MatMed;
import br.com.fiap.entity.Paciente;
import br.com.fiap.entity.Procedimento;
import br.com.fiap.helper.Helper;

public class SistemaConsultorio {

	public static void main(String[] args) {
		incluirPaciente();
		buscarPaciente("12345678909");
		System.out.println();
		buscarProcedimentoPorPaciente("12345678909");
		System.out.println();
		buscarMatMedPorPaciente("12345678909");
		System.exit(1);
	}

	private static void incluirPaciente() {
		Helper dao = new Helper();

		Paciente paciente = new Paciente();
		paciente.setCpf("12345678909");
		paciente.setNome("Tatiane dos Santos");
		paciente.setTelefone("123456789");
		paciente.setDatanasc(formataDataHora("16/07/1987", true));

		Agenda agenda = new Agenda();
		agenda.setData(formataDataHora("15/12/2017", true));
		agenda.setDescricao("Clínico");
		agenda.setHora(formataDataHora("14:00", false));

		MatMed matmed1 = new MatMed();
		matmed1.setDescricao("Dipirona");
		matmed1.setFabricante("Europharma");
		matmed1.setPreco(5.50);
		matmed1.setPaciente(paciente);

		MatMed matmed2 = new MatMed();
		matmed2.setDescricao("Seringa");
		matmed2.setFabricante("Europharma");
		matmed2.setPreco(1.50);
		matmed2.setPaciente(paciente);

		Procedimento procedimento1 = new Procedimento();
		procedimento1.setDescricao("Consulta simples");
		procedimento1.setPreco(50.60);
		procedimento1.setPaciente(paciente);

		Procedimento procedimento2 = new Procedimento();
		procedimento2.setDescricao("Exame");
		procedimento2.setPreco(45.00);
		procedimento2.setPaciente(paciente);

		paciente.getAgendas().add(agenda);
		paciente.getProcedimentos().add(procedimento1);
		paciente.getProcedimentos().add(procedimento2);
		paciente.getMatmed().add(matmed1);
		paciente.getMatmed().add(matmed2);

		try {
			dao.salvar(agenda);
			dao.salvar(paciente);
			System.out.println("Cliente incluído com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void buscarPaciente(String cpf) {
		Helper dao = new Helper();
		Paciente paciente;
		try {
			paciente = dao.buscarPaciente(cpf);
			System.out.println("Paciente encontrado com sucesso.");

			Set<Procedimento> procedimentos = paciente.getProcedimentos();
			Set<MatMed> matmeds = paciente.getMatmed();

			System.out.println("\nPaciente: " + paciente.getNome() + " CPF: " + paciente.getCpf());
			System.out.println("Data de nascimento: " + getDataNascFormatada(paciente.getDatanasc()) + " Telefone: "
					+ paciente.getTelefone());

			if (procedimentos != null && procedimentos.size() > 0) {
				System.out.println("\nProcedimentos:");
				for (Procedimento proc : procedimentos) {
					System.out.println("Descrição: " + proc.getDescricao());
					System.out.println("Preço: " + proc.getPreco());
					System.out.println("---------------------------------");
				}
			}

			if (matmeds != null && matmeds.size() > 0) {
				System.out.println("\nMateriais / Medicamentos:");
				for (MatMed matmed : matmeds) {
					System.out.println("Descrição: " + matmed.getDescricao());
					System.out.println("Preço: " + matmed.getPreco());
					System.out.println("Fabricante: " + matmed.getFabricante());
					System.out.println("---------------------------------");
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void buscarMatMedPorPaciente(String cpf) {
		Helper dao = new Helper();
		List<MatMed> matmeds;
		try {
			matmeds = dao.buscarMatMeds(cpf);
			System.out.println("Material(is) / Medicamento(s) encontrado(s) com sucesso.");

			if (matmeds != null && matmeds.size() > 0) {
				System.out.println("\nMateriais / Medicamentos:");
				for (MatMed matmed : matmeds) {
					System.out.println("Descrição: " + matmed.getDescricao());
					System.out.println("Preço: " + matmed.getPreco());
					System.out.println("Fabricante: " + matmed.getFabricante());
					System.out.println("---------------------------------");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void buscarProcedimentoPorPaciente(String cpf) {
		Helper dao = new Helper();
		List<Procedimento> procedimentos;
		try {
			procedimentos = dao.buscarProcedimentos(cpf);
			System.out.println("Procedimento(s) encontrado(s) com sucesso.");

			if (procedimentos != null && procedimentos.size() > 0) {
				System.out.println("\nProcedimentos:");
				for (Procedimento proc : procedimentos) {
					System.out.println("Descrição: " + proc.getDescricao());
					System.out.println("Preço: " + proc.getPreco());
					System.out.println("---------------------------------");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static Date formataDataHora(String valor, boolean isDate) {
		SimpleDateFormat dt;
		Date date = null;
		if (isDate) {
			dt = new SimpleDateFormat("dd/MM/yyyy");
		} else {
			dt = new SimpleDateFormat("hh:mm");
		}
		try {
			date = dt.parse(valor);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	private static String getDataNascFormatada(Date data) {
		SimpleDateFormat datanasc = new SimpleDateFormat("yyyyy-mm-dd");
		return datanasc.format(data);
	}
}