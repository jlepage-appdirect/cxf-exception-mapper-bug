package com.jlep.cxf.exceptionmapperbug.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

@Provider
@Component
public class IllegalArgumentExceptionMapper extends AbstractExceptionMapper<IllegalArgumentException> {
	@Override
	protected Response toResponse0(IllegalArgumentException exception) {
		return Response
				.serverError()
				.entity("Processed by IllegalArgumentExceptionMapper: " + exception.getMessage())
				.build();
	}
}
