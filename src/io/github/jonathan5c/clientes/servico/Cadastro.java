package io.github.jonathan5c.clientes.servico;

import java.util.UUID;

public interface Cadastro<TIPO> {
    void salvar(TIPO objetoCadastrar) throws Exception;
    TIPO buscar(UUID codigo);
    void deletar(UUID codigo);
    void atualizar(TIPO objetoAtualizar);
    void imprimir();
}
