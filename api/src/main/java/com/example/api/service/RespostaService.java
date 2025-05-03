package com.example.api.service;

import java.sql.JDBCType;
import java.util.List;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class RespostaService {
   
   private final JdbcTemplate jdbcTemplate;

   public RespostaService(JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
   }

   public String buscarResposta(String intencao){
    if ("Lucro Hoje".equals(intencao)){
        String sql = "Select valor from lucros where id_lucro = 1";
        return jdbcTemplate.queryForObject(sql,new Object[]{},String.class);
    }
    else{
        String sql = "Select count(*) from vendas";
        return jdbcTemplate.queryForObject(sql,new Object[]{},String.class);
    }
   }

   
}
