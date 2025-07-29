package io.github.jonathan5c.clientes.servico;

import io.github.jonathan5c.clientes.dominio.Cliente;
import io.github.jonathan5c.clientes.dominio.exception.CpfInvalidoException;
import io.github.jonathan5c.clientes.utilitario.GerenciadorArquivos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LogicaCadastroMemoria implements Cadastro<Cliente> {

    private List<Cliente> lista;

    public LogicaCadastroMemoria() {
        this.lista = new ArrayList<>();
    }

    @Override
    public void salvar(Cliente cliente) throws CpfInvalidoException {
        ValidadorCliente.validar(cliente);
        this.lista.add(cliente);
        GerenciadorArquivos.persistirArquivo(cliente.getNome() + ".jpg", cliente.getFoto());
    }

    @Override
    public Optional<Cliente> buscar(UUID codigo) {
        Cliente clienteEncontrado = null;

        for (Cliente c : this.lista) {
            if (c.getCodigoCliente().equals(codigo)) {
                clienteEncontrado = c;
                break;
            }
        }

        return Optional.ofNullable(clienteEncontrado);
    }

    @Override
    public void deletar(UUID codigo) {
        this.buscar(codigo)
                .ifPresentOrElse(cliente -> lista.remove(cliente),
                () -> System.out.println("Cliente não removido, pois não foi encontrado"));
    }

    @Override
    public void atualizar(Cliente cliente) {

    }

    @Override
    public void imprimir() {
        for (Cliente cliente : this.lista) {
            System.out.println(cliente);
        }
    }
}
