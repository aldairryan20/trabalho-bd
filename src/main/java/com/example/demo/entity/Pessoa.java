package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import java.util.Objects;

public class Pessoa {
    @Id
    private int id;
    private String nome;
    private String cpf;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa id(int id) {
        setId(id);
        return this;
    }

    public Pessoa nome(String nome) {
        setNome(nome);
        return this;
    }

    public Pessoa cpf(String cpf) {
        setCpf(cpf);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pessoa)) {
            return false;
        }
        Pessoa pessoa = (Pessoa) o;
        return id == pessoa.id && Objects.equals(nome, pessoa.nome) && Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", cpf='" + getCpf() + "'" +
            "}";
    }
}
