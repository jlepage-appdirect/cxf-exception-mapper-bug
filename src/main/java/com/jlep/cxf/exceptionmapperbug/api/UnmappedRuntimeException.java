package com.jlep.cxf.exceptionmapperbug.api;

public class UnmappedRuntimeException extends RuntimeException {
	public UnmappedRuntimeException(String message) {
		super(message);
	}
}
