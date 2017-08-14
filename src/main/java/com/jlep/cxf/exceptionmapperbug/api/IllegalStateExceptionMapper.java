package com.jlep.cxf.exceptionmapperbug.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

@Provider
@Component
public class IllegalStateExceptionMapper implements ExceptionMapper<IllegalStateException> {
	@Override
	public Response toResponse(IllegalStateException exception) {
		return Response
				.serverError()
				.entity("Processed by IllegalStateExceptionMapper: " + exception.getMessage())
				.build();
	}
}
