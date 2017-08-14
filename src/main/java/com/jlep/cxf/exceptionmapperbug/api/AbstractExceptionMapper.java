package com.jlep.cxf.exceptionmapperbug.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Provider
@Component
@Slf4j
public abstract class AbstractExceptionMapper<E extends Throwable> implements ExceptionMapper<E> {
	@Override
	public Response toResponse(E exception) {
		log.info("Do some common error handling for exception: {}", exception);
		return toResponse0(exception);
	}

	protected abstract Response toResponse0(E exception);
}
