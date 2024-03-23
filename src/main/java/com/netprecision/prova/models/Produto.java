package com.netprecision.prova.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
   @Id
   private Integer codigo;

   @Column(nullable = false, unique = true)
   private String nome;

   @Column(nullable = false)
   private float preco;
}
