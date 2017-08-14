# cxf-exception-mapper-bug

## Problem
Minimal SpringBoot App showing bug related to ExceptionMapper hierarchies.

In a nutshell:
* com.jlep.cxf.exceptionmapperbug.api.AbstractExceptionMapper is Exception superclass
* com.jlep.cxf.exceptionmapperbug.api.IllegalArgumentExceptionMapper extends AbstractExceptionMapper
* com.jlep.cxf.exceptionmapperbug.api.IllegalStateExceptionMapper however doesn't extend the abstract exception mapper, and directly implements javax.ws.rs.ext.ExceptionMapper

Behavior:
* When JAX-RS endpoints throws either IllegalArgumentException or IllegalStateException, correct ExceptionMapper impl seems selected (GOOD) 
* However, when another RuntimeException subtype (here com.jlep.cxf.exceptionmapperbug.api.UnmappedRuntimeException) is thrown, then IllegalArgumentExceptionMapper is selected, causing a ClassCastException to be thrown (BAD!) 

## How to reproduce

### Build and run the server
```sh
mvn clean package
mvn spring-boot:run
```

### Make it happen
On separate shell:
```sh
curl -X POST -v http://localhost:8080/services/exception/unmappedException
```

You should get a 500.
On the server shell, you should have the following:
```
java.lang.ClassCastException: com.jlep.cxf.exceptionmapperbug.api.UnmappedRuntimeException cannot be cast to java.lang.IllegalArgumentException
	at com.jlep.cxf.exceptionmapperbug.api.IllegalArgumentExceptionMapper.toResponse0(IllegalArgumentExceptionMapper.java:8)
	at com.jlep.cxf.exceptionmapperbug.api.AbstractExceptionMapper.toResponse(AbstractExceptionMapper.java:18)
	at org.apache.cxf.jaxrs.utils.ExceptionUtils.convertFaultToResponse(ExceptionUtils.java:86)
	at org.apache.cxf.jaxrs.utils.JAXRSUtils.convertFaultToResponse(JAXRSUtils.java:1624)
	...
```

