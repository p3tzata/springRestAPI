package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import app.dto.ErrorMsgDTO;
import app.dto.SuccessfullMsgDTO;
import app.dto.UserRegisterDTO;
import app.service.UserService;

@RestController()


//@CrossOrigin(origins = "http://127.0.0.1:3000",methods = {RequestMethod.GET,RequestMethod.POST})
public class AccountController {

	private Gson gson;
	private UserService userService;

	
	
	
	
	@Autowired
	public AccountController(Gson gson,UserService userservice) {
		this.gson = gson;
		this.userService = userservice;
	}



	@GetMapping("/testRegister")
	public ResponseEntity<?> testRegisterGet() {
		
		SuccessfullMsgDTO successfullMsgDTO = new SuccessfullMsgDTO("Successfull Register User");
			return new ResponseEntity<>(gson.toJson(successfullMsgDTO),HttpStatus.CREATED);
		
	}

	@PostMapping("/testRegister")
	public ResponseEntity<?> testRegisterPost() {
		
		SuccessfullMsgDTO successfullMsgDTO = new SuccessfullMsgDTO("Successfull Register User");
		ErrorMsgDTO erroMsgDTO = new ErrorMsgDTO("UnSuccessfull Register User");
			return new ResponseEntity<>(gson.toJson(successfullMsgDTO),HttpStatus.CREATED);
		
	}
	
	


	@PostMapping(value="/register")
	public ResponseEntity<?> register(@RequestBody UserRegisterDTO userRegisterDTO  ) {
		
		try {
		if (userService.register(userRegisterDTO)) {
						
			SuccessfullMsgDTO successfullMsgDTO = new SuccessfullMsgDTO("Successfull Register User");
			return new ResponseEntity<>(gson.toJson(successfullMsgDTO),HttpStatus.CREATED);
			
		} 
		
		
		} catch (Exception e) {
			throw new IllegalStateException("Can not register user");
		}
		
		return null;
		
		
		
		
		
	}
	
	
}
