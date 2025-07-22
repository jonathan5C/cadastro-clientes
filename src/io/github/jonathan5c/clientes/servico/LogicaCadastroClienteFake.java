package io.github.jonathan5c.clientes.servico;

import io.github.jonathan5c.clientes.dominio.Cliente;

import javax.swing.*;
import java.util.UUID;

public class LogicaCadastroClienteFake implements Cadastro<Cliente>{
    @Override
    public void salvar(Cliente objetoCadastrar) {
        JOptionPane.showMessageDialog(null, objetoCadastrar);
    }

    @Override
    public Cliente buscar(UUID codigo) {
        return null;
    }

    @Override
    public void deletar(UUID codigo) {

    }

    @Override
    public void atualizar(Cliente objetoAtualizar) {

    }
}
