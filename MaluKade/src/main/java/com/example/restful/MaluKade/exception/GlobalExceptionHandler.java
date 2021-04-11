package com.example.restful.MaluKade.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.restful.MaluKade.response.Respones;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExceptionHandler(Exception ex,WebRequest request){
		Respones errorResponse = new Respones(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourseNotFoundException(Exception ex,WebRequest request){
    	   
    	Respones errorDetails =
    	       new Respones(new Date(), HttpStatus.NOT_FOUND.toString(), ex.getMessage(), request.getDescription(false));
    	return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    	Respones errorDetails =
     	       new Respones(new Date(), HttpStatus.BAD_REQUEST.toString(),ex.getBindingResult().toString(), request.getDescription(false));
     	return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
