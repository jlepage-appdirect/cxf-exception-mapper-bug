package com.jlep.cxf.exceptionmapperbug.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Path("/exception")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class ExceptionThrowingService {

	public ExceptionThrowingService() {
		System.out.printf("ExceptionThrowingService created");
	}

	@POST
	@Path("/illegalStateException")
	public void illegalStateException() {
		throw new IllegalStateException("Illegal State Kaboom");
	}

	@POST
	@Path("/illegalArgumentException")
	public void illegalArgumentException() {
		throw new IllegalArgumentException("Illegal Argument Kaboom");
	}

	@POST
	@Path("/webApplicationException")
	public void webApplicationException() {
		Response response = Response.serverError().entity("Error Payload").build();
		throw new WebApplicationException("WebApplication Kaboom", response);
	}

	@POST
	@Path("/unmappedException")
	public void unmappedException() {
		throw new UnmappedRuntimeException("Unmapped Kaboom");
	}

	@POST
	@Path("/throwable")
	public void throwable() throws Throwable {
		throw new Throwable("Throwable Kaboom");
	}
}
