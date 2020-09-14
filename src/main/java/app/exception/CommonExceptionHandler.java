package app.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import io.swagger.api.ApiResponseMessage;


@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	LocalValidatorFactoryBean validator;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
			, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}

		ApiResponseMessage error = new ApiResponseMessage();
		error.setCode(400);
		error.setMessage(details.toString());
		error.setType("Bad Request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	//BusinessException
		@ExceptionHandler(value = { BusinessException.class })
		protected ResponseEntity<Object> handleConflictBusinessException(
				BusinessException ex, WebRequest request) {
			List<String> details = new ArrayList<>();
			details.add(ex.getMessage());
			ApiResponseMessage error = new ApiResponseMessage();
			error.setCode(400);
			error.setMessage(details.toString());
			error.setType("Bad Request");
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

	@ExceptionHandler(value = { javax.validation.ConstraintViolationException.class })
	protected ResponseEntity<Object> handleConflictConstraintViolationException(
			ConstraintViolationException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		ApiResponseMessage error = new ApiResponseMessage();
		error.setCode(400);
		error.setMessage(details.toString());
		error.setType("Bad Request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { javax.validation.UnexpectedTypeException.class })
	protected ResponseEntity<Object> handleConflictConstraintViolationException(
			javax.validation.UnexpectedTypeException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		ApiResponseMessage error = new ApiResponseMessage();
		error.setCode(400);
		error.setMessage(details.toString());
		error.setType("Bad Request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
}
