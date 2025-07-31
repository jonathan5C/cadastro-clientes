package io.github.jonathan5c.clientes.apresentacao;

import io.github.jonathan5c.clientes.dados.ClienteDAO;
import io.github.jonathan5c.clientes.dados.FabricaConexoes;
import io.github.jonathan5c.clientes.dominio.Cliente;
import io.github.jonathan5c.clientes.dominio.enums.TipoSexo;
import io.github.jonathan5c.clientes.dominio.exception.CpfInvalidoException;
import io.github.jonathan5c.clientes.servico.Cadastro;
import io.github.jonathan5c.clientes.servico.LogicaCadastroBD;
import io.github.jonathan5c.clientes.servico.LogicaCadastroMemoria;
import io.github.jonathan5c.clientes.utilitario.ConversorIconParaByteArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class TelaCadastro extends JFrame {

    private JLabel labelNome;
    private JLabel labelCpf;
    private JLabel labelSexo;
    private JLabel labelFoto;

    private JTextField campoNome;
    private JTextField campoCpf;

    private JComboBox<TipoSexo> campoSexo;

    private JButton botaoSalvar;
    private JButton botaoEscolherFoto;

    private Cadastro<Cliente> logicaCadastro;

    public TelaCadastro() {
        construirTela();
        var clienteDAO = new ClienteDAO(FabricaConexoes.criarConexao());
        this.logicaCadastro = new LogicaCadastroBD(clienteDAO);
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
        ImageIcon imagemIcon = obterImagemPadraoFoto();

        labelFoto = new JLabel();
        labelFoto.setIcon(imagemIcon);
        labelFoto.setBounds(240,0,200,200);

        getContentPane().add(labelFoto);

        botaoEscolherFoto = new JButton("Alterar foto");
        botaoEscolherFoto.setBounds(260,200,180,20);
        botaoEscolherFoto.addActionListener(botaoEscolherFotoActionListener());
        getContentPane().add(botaoEscolherFoto);
    }

    private ImageIcon obterImagemPadraoFoto() {
        String caminhoArquivo = "/io/github/jonathan5c/clientes/apresentacao/image.png";
        URL localizacao = getClass().getResource(caminhoArquivo);
        ImageIcon imagemIcon = new ImageIcon(localizacao);

        Image imageRedimencionada = imagemIcon.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH);

        imagemIcon = new ImageIcon(imageRedimencionada);
        return imagemIcon;
    }

    private ActionListener botaoEscolherFotoActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int opcaoUsuarioFoto = fileChooser.showOpenDialog(TelaCadastro.this);

                if (opcaoUsuarioFoto == JFileChooser.APPROVE_OPTION) {
                    File arquivoSelecionado = fileChooser.getSelectedFile();
                    String caminhoFoto = arquivoSelecionado.getAbsolutePath();

                    ImageIcon imageIcon = new ImageIcon(caminhoFoto);
                    labelFoto.setIcon(imageIcon);
                }
            }
        };
    }

    private ActionListener botaoSalvarActionListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente();

                cliente.setNome(campoNome.getText());
                cliente.setCpf(campoCpf.getText());
                cliente.setSexo( (TipoSexo) campoSexo.getSelectedItem());

                byte [] byteArray = ConversorIconParaByteArray.converter(labelFoto.getIcon());
                cliente.setFoto(byteArray);

                try {
                    logicaCadastro.salvar(cliente);
                    campoNome.setText("");
                    campoCpf.setText("");
                    campoSexo.setSelectedIndex(0);
                    labelFoto.setIcon(TelaCadastro.this.obterImagemPadraoFoto());

                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                    logicaCadastro.imprimir();
                } catch (CpfInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(null, exp.getMessage());
                }
            }
        };
    }
}
