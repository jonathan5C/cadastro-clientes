package io.github.jonathan5c.clientes.servico;

import io.github.jonathan5c.clientes.dominio.Cliente;
import io.github.jonathan5c.clientes.dominio.exception.CpfInvalidoException;

public class ValidadorCliente {

    public static void validar(Cliente cliente) throws CpfInvalidoException {
        if (cliente.getCpf().length() != 11) {
            throw new CpfInvalidoException("CPF inválido!");
        }
    }
}
