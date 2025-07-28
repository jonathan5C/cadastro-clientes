package io.github.jonathan5c.clientes.servico;

import io.github.jonathan5c.clientes.dominio.Cliente;
import io.github.jonathan5c.clientes.dominio.exception.CpfInvalidoException;
import io.github.jonathan5c.clientes.utilitario.GerenciadorArquivos;

import java.util.ArrayList;
import java.util.List;
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
    public Cliente buscar(UUID codigo) {
        Cliente clienteEncontrado = null;

        for (Cliente c : this.lista) {
            if (c.getCodigoCliente().equals(codigo)) {
                clienteEncontrado = c;
                break;
            }
        }

        return  clienteEncontrado;
    }

    @Override
    public void deletar(UUID codigo) {
        Cliente clienteEncontrado = this.buscar(codigo);

        if (clienteEncontrado != null){
            this.lista.remove(clienteEncontrado);
        }
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
