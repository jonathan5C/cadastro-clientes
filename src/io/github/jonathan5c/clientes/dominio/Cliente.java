package io.github.jonathan5c.clientes.dominio;

import io.github.jonathan5c.clientes.dominio.enums.TipoSexo;

import java.util.Arrays;
import java.util.UUID;

public class Cliente implements Comparable<Cliente>{

    private UUID codigoCliente;
    private String nome;
    private String cpf;
    private TipoSexo sexo;
    private byte[] foto;

    public Cliente() {
        this.codigoCliente = UUID.randomUUID();
    }

    public UUID getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(UUID codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public TipoSexo getSexo() {
        return sexo;
    }

    public void setSexo(TipoSexo sexo) {
        this.sexo = sexo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;
        return codigoCliente.equals(cliente.codigoCliente) && nome.equals(cliente.nome) && cpf.equals(cliente.cpf) && sexo == cliente.sexo;
    }

    @Override
    public int hashCode() {
        int result = codigoCliente.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + cpf.hashCode();
        result = 31 * result + sexo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cliente [" +
                "codigoCliente=" + codigoCliente +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", sexo=" + sexo +
                ", foto=" + Arrays.toString(foto) +
                ']';
    }

    /**
     *
     * @param o the object to be compared.
     * @return
     *
     * 0 -> são iguais => 1==1
     * 1 -> é maior => 2 > 1
     * -1 -> é menor => 1 < 2
     */

    @Override
    public int compareTo(Cliente o) {
        int fator = this.nome.compareTo(o.getNome());

        if (fator == 0) {
            fator = this.sexo.equals(TipoSexo.F) ? -1 : 1;
        }

        return fator;

        // Depois é usado da seguinte forma: [variavel].sort([variavel de instância]::compareTo); <=> isso vai fazer a ordenação
    }
}
