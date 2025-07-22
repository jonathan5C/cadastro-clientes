package io.github.jonathan5c.clientes.apresentacao;

import io.github.jonathan5c.clientes.dominio.Cliente;
import io.github.jonathan5c.clientes.dominio.enums.TipoSexo;
import io.github.jonathan5c.clientes.servico.Cadastro;
import io.github.jonathan5c.clientes.servico.LogicaCadastroClienteFake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame {

    private JLabel labelNome;
    private JLabel labelCpf;
    private JLabel labelSexo;
    private JTextField campoNome;
    private JTextField campoCpf;
    private JComboBox<TipoSexo> campoSexo;
    private JButton botaoSalvar;

    public TelaCadastro() {
        construirTela();
    }

    private void construirTela() {
        setSize(600,500);
        setTitle("Cadastro de Cliente");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        this.adicionarCampos();
        this.adicionarBotao();
        this.adicionarComponenteFoto();
    }

    private void adicionarCampos() {
        labelNome = new JLabel("Nome: ");
        labelNome.setBounds(20,20,200,20);
        getContentPane().add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(20,40,200,20);
        getContentPane().add(campoNome);

        labelCpf = new JLabel("CPF: ");
        labelCpf.setBounds(20,60,200,20);
        getContentPane().add(labelCpf);

        campoCpf = new JTextField();
        campoCpf.setBounds(20,80,200,20);
        getContentPane().add(campoCpf);

        labelSexo = new JLabel("Sexo: ");
        labelSexo.setBounds(20,100,200,20);
        getContentPane().add(labelSexo);

        TipoSexo[] tipoSexo = {
                null,
                TipoSexo.M,
                TipoSexo.F,
                TipoSexo.O
        };
        campoSexo = new JComboBox<>(tipoSexo);
        campoSexo.setBounds(20,120,200,20);
        getContentPane().add(campoSexo);
    }

    private void adicionarBotao() {
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBounds(20,160,100,20);

        ActionListener acaoBotaoSalvar = this.botaoSalvarActionListener();
        botaoSalvar.addActionListener(acaoBotaoSalvar);

        getContentPane().add(botaoSalvar);
    }

    private void adicionarComponenteFoto() {

    }

    private ActionListener botaoSalvarActionListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente();

                cliente.setNome(campoNome.getText());
                cliente.setCpf(campoCpf.getText());
                cliente.setSexo( (TipoSexo) campoSexo.getSelectedItem());

                Cadastro<Cliente> cadastroCliente = new LogicaCadastroClienteFake();
                cadastroCliente.salvar(cliente);
            }
        };
    }
}
