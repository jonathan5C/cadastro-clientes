package io.github.jonathan5c.clientes.servico;

import io.github.jonathan5c.clientes.dados.ClienteDAO;
import io.github.jonathan5c.clientes.dominio.Cliente;

import java.util.Optional;
import java.util.UUID;

public class LogicaCadastroBD implements Cadastro<Cliente> {
    private ClienteDAO clienteDAO;

    public LogicaCadastroBD(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public void salvar(Cliente cliente) throws Exception {
        ValidadorCliente.validar(cliente);
        clienteDAO.inserir(cliente);
    }

    @Override
    public Optional<Cliente> buscar(UUID codigo) {
        return Optional.empty();
    }

    @Override
    public void deletar(UUID codigo) {
        clienteDAO.deletar(codigo);
    }

    @Override
    public void atualizar(Cliente cliente) {
        clienteDAO.atualizar(cliente);
    }

    @Override
    public void imprimir() {
        clienteDAO.listar().forEach(System.out::println);
    }
}
