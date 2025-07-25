package io.github.jonathan5c.clientes.servico;

import io.github.jonathan5c.clientes.dominio.Cliente;
import io.github.jonathan5c.clientes.dominio.exception.CpfInvalidoException;
import io.github.jonathan5c.clientes.dominio.exception.DadoObrigatorioException;

public class ValidadorCliente {

    // Checked exceptions => No tempo de compilação
    public static void validar(Cliente cliente) throws CpfInvalidoException {
        if (cliente.getCpf().length() != 11) {
            throw new CpfInvalidoException("CPF inválido!");
        }

        validarDadosObrigatorios(cliente);
    }

    // Unchecked exceptions => No tempo de execução
    private static void validarDadosObrigatorios(Cliente cliente) {
        if (cliente.getNome() == null && cliente.getCpf() == null && cliente.getSexo() == null) {
            throw new DadoObrigatorioException("Campo obrigatório!!");
        }
    }
}
