package com.example.demo.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String produto;
	private String variedade;
	private String pais;
	private String categoria;
	private String safra;
	private Double preco;
}
