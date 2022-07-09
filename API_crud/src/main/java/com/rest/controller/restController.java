package com.rest.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.rest.service.RegService;
import com.rest.dto.RegDTO;
import com.rest.dto.Response;
import com.rest.model.RegVO;



@RestController
@CrossOrigin(value="http://localhost:8080")
public class restController {

	@Autowired
	private RegService regservice;
	
	
	@GetMapping("/")
	public ResponseEntity index(){
		
//		List searchList = this.regservice.search();
		
		return new ResponseEntity("kavan",HttpStatus.OK);
	}
	
	
//	request body bind the json came from user into model object 
//	bad request :400 if request is not proper
	
	@PostMapping("/user")
	public ResponseEntity SaveData(@RequestBody RegDTO d1){
		
		String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9.-]+.[a-zA-Z]+$";  
        //Compile regular expression to get the pattern  
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(d1.getEmailID());  
		
		String password = d1.getPassword();
		
		if(d1.getFirstName()==null && d1.getFirstName().isEmpty()){
				
			return new ResponseEntity("firstname required",HttpStatus.BAD_REQUEST);
		}
		else if(d1.getLastName()==null && d1.getLastName().isEmpty()){
			
			return new ResponseEntity("lastname required",HttpStatus.BAD_REQUEST);
		}
		      
		else if(!matcher.matches()){
			
			return new ResponseEntity("please enter correct E-mail-ID",HttpStatus.BAD_REQUEST);
		}
		else if(password.length() < 8 && password.length() > 16){
			
			return new ResponseEntity("please enter password between 8 to 16 ",HttpStatus.BAD_REQUEST);
		}
		else{
			
			System.out.println("data is here !!");
			RegVO v1 = this.regservice.convert(d1);
			this.regservice.insert(v1);
			return new ResponseEntity(v1,HttpStatus.OK);
		}
		
		
	}
	
	@GetMapping("/user")
	public ResponseEntity getData(){
		
		List searchList = this.regservice.search();
		
		return new ResponseEntity(searchList,HttpStatus.OK);
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity getDataByID(@PathVariable int id){
		
		RegVO v1 = new RegVO();
		v1.setId(id);
		List ls = this.regservice.searchById(v1);
		
		if(!ls.isEmpty()){
			
			return new ResponseEntity(ls,HttpStatus.OK);
		}else{
			
			return new ResponseEntity("data not found !!!",HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("user/{id}")
	public ResponseEntity deleteData(@PathVariable int id){
		
		Response res = new Response();
		
		RegVO v1 = new RegVO();
		v1.setId(id);
		List ls = this.regservice.searchById(v1);
		
		if(!ls.isEmpty()){
			
			this.regservice.delete(v1);
			res.setMessage("data deleted sucessfully !!!");
			res.setStatus(true);
			
			return new ResponseEntity(res,HttpStatus.OK);
		}else{
			
			res.setMessage("data not found !!!");
			res.setStatus(false);
			return new ResponseEntity(res,HttpStatus.OK);
		}
		
	}
	
//	user/id -> /user?id=2
	@PatchMapping("user/{id}")
	public ResponseEntity PatchData(@PathVariable int id){
		
		Response res = new Response();
		
		RegVO v1 = new RegVO();
		v1.setId(id);
		List ls = this.regservice.searchById(v1);
		
		if(!ls.isEmpty()){
			
			RegVO temp = (RegVO) ls.get(0);
			Boolean sta = temp.isStatus();
			
			temp.setStatus(!sta);
//			this.regservice.insert(temp);
			
			res.setMessage("data updated sucessfully !!!");
			res.setStatus(true);
			
			return new ResponseEntity(res,HttpStatus.OK);
		}else{
			
			res.setMessage("data not found !!!");
			res.setStatus(false);
			return new ResponseEntity(res,HttpStatus.OK);
		}
		
	}
	
// for update : we use put mapping ->same as insert > check id -> yes, then update 
	@PutMapping("/user/{id}")
	public ResponseEntity updateData(@PathVariable int id ,@RequestBody RegDTO d1){
		
		
		Response response = new Response();
		
		RegVO data = this.regservice.convert(d1);
		System.out.println(data.getId());
		
		RegVO v2 = new RegVO();
		v2.setId(id);
		
		List ls = this.regservice.searchById(v2);
		
		if(ls.isEmpty()){
			
			response.setMessage("data not found !!");
			response.setStatus(false);
			return new ResponseEntity(response,HttpStatus.OK);
		}
		else{
			this.regservice.insert(data);
//			List vo_data = this.regservice.searchById(data)();
			response.setMessage("data update sucessfully !!");
			response.setStatus(true);
			return new ResponseEntity(response,HttpStatus.OK);
		}
		
	}

}
