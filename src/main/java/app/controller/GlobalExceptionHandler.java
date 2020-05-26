package app.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import app.dto.ErrorMsgDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private Gson gson;
	
	
	
	@Autowired
	public GlobalExceptionHandler(Gson gson) {
		super();
		this.gson = gson;
	}




	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<?> getException(RuntimeException exception) {
		
		
		
		String reason = null;
		Class<? extends RuntimeException> clazz = exception.getClass();
		if (clazz.isAnnotationPresent(ResponseStatus.class)) {
			ResponseStatus annotation = clazz.getAnnotation(ResponseStatus.class);
			 reason = annotation.reason();
		}
		
		
		ErrorMsgDTO errorMsgDTO = new ErrorMsgDTO(exception.getMessage());
		//return new ResponseEntity<>(gson.toJson(exception.getMessage()),HttpStatus.BAD_REQUEST);	
		return new ResponseEntity<>(gson.toJson(errorMsgDTO),HttpStatus.BAD_REQUEST);
		
		
	}
	

	
	
	

}
