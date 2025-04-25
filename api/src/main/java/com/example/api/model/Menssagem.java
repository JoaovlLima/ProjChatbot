package com.example.api.model;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Menssagem {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String texto;
private LocalDateTime datahora;

@ManyToOne
private Usuario usuario;

public Menssagem(String texto, LocalDateTime datahora) {

    this.texto = texto;
    this.datahora = LocalDateTime.now();

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

public LocalDateTime getDatahora() {
    return datahora;
}

public void setDatahora(LocalDateTime datahora) {
    this.datahora = datahora;
}

public Usuario getUsuario() {
    return usuario;
}

public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
}


  


}
