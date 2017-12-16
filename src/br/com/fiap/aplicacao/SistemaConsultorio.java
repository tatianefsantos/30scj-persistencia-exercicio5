package br.com.fiap.aplicacao;

import br.com.fiap.entity.Paciente;
import br.com.fiap.helper.Helper;

public class SistemaConsultorio {

	public static void main(String[] args) {
		incluirPaciente();
		buscarPaciente(1);
		System.exit(1);
	}

	private static void incluirPaciente() {
		Helper dao = new Helper();
		Paciente paciente = new Paciente();
		paciente.setCpf("12345678909");

		try {
			dao.salvar(paciente);
			System.out.println("Cliente incluído com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void buscarPaciente(Integer idCliente) {
	}
}