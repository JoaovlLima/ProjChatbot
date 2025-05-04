package com.example.api.service;

import java.sql.JDBCType;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RespostaService {
   
   private final JdbcTemplate jdbcTemplate;

   public RespostaService(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
   }

   public String buscarResposta(String sqlGerado){
    
    System.out.println("Executando SQL: " + sqlGerado);

   try {
      List<Map<String,Object>> resultados = jdbcTemplate.queryForList(sqlGerado);
      ObjectMapper mapper = new ObjectMapper();
      String resultado = mapper.writeValueAsString(resultados);
      return (resultado != null) ? resultado.toString() : "Nenhum resultado encontrado.";

  } catch (Exception e) {
      e.printStackTrace();
      return "Erro ao consultar o SQL: " + e.getClass().getSimpleName() + " - " + e.getMessage();
  }
   }

   
}
