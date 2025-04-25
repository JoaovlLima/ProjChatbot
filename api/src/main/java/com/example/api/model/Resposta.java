package com.example.api.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Resposta {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String texto;

    @ManyToOne
    private Intencao intencao;

    public Resposta() {
    }

    public Resposta(String texto, Intencao intencao) {
        this.texto = texto;
        this.intencao = intencao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Intencao getIntencao() {
        return intencao;
    }

    public void setIntencao(Intencao intencao) {
        this.intencao = intencao;
    }

    
}
