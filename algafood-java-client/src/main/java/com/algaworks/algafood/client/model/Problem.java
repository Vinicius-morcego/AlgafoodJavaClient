package com.algaworks.algafood.client.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class Problem {

	private Integer status;
	private OffsetDateTime timestamp;
	private String userMessage;
	private List<Objects> objects = new ArrayList<>();
	
	@Getter
	public static class Objects{
		private String name;
		private String userMessage;
	}
	
}
