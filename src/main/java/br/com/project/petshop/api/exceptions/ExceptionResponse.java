package br.com.project.petshop.api.exceptions;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExceptionResponse {
	private String message;
	private Date timestamp;
	private String detail;
	
	public ExceptionResponse() {
		super();
	}

	@Builder
	public ExceptionResponse(String message, Date timestamp, String detail) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.detail = detail;
	}
}
