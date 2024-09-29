package com.example.demo.entity.pessoa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Pessoa {
    private int id;
    private String nome;
    private String cpf;
}
