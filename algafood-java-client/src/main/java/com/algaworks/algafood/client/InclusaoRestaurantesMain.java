package com.algaworks.algafood.client;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.algaworks.algafood.client.api.ClientApiException;
import com.algaworks.algafood.client.api.RestauranteClient;
import com.algaworks.algafood.client.model.input.CidadeIdInput;
import com.algaworks.algafood.client.model.input.CozinhaIdInput;
import com.algaworks.algafood.client.model.input.EnderecoInput;
import com.algaworks.algafood.client.model.input.RestauranteInput;

public class InclusaoRestaurantesMain {

	public static void main(String... args) {
		try {			
			RestTemplate template = new RestTemplate();
			RestauranteClient restauranteClient = 
					new RestauranteClient("http://api.algafood.local:8080", template);
			var cozinha = new CozinhaIdInput();
			cozinha.setId(2L);		
			
			var cidade = new CidadeIdInput();
			cidade.setId(3L);
			
			var endereco = new EnderecoInput();
			
			endereco.setBairro("Tabajara");
			endereco.setLogradouro("Rua dos tabajaras");
			endereco.setNumero("300");
			endereco.setCep("00000000");
			endereco.setComplemento("Perto do MC Donalds");
			endereco.setCidade(cidade);			
			
			var restaurante = new RestauranteInput();			
			restaurante.setNome("Argentino");			
			restaurante.setTaxaFrete(new BigDecimal(12.11));
			restaurante.setCozinha(cozinha);
			restaurante.setEndereco(endereco);
			
			restauranteClient.cadastrar(restaurante);
			restauranteClient.listar().stream()
				.forEach(r -> System.out.println(r));
			
		} catch (ClientApiException e) {
			if(e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());
				e.getProblem().getObjects().stream()
					.forEach(object -> System.out.println(object.getUserMessage()));
			}else {
				System.out.println("Erro desconhecido");
				e.printStackTrace();
			}
		}
	}
	
	
}
