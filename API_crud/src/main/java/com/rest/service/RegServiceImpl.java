package com.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.*;
import com.rest.dto.RegDTO;
import com.rest.model.RegVO;

@Service
public class RegServiceImpl implements RegService {

	@Autowired
	RegDAO regdao;
	
	@Transactional
	public void insert(RegVO regVO) {
		// TODO Auto-generated method stub
		
		this.regdao.insert(regVO);
	}
	
	@Transactional
	public List search() {
		// TODO Auto-generated method stub
		
		List data = this.regdao.search();
		return data;
	}
	
	@Transactional
	public void delete(RegVO regVO) {
		// TODO Auto-generated method stub
		this.regdao.delete(regVO);
	}

	@Transactional
	public List searchById(RegVO regvo) {
		// TODO Auto-generated method stub
		
		return this.regdao.searchById(regvo);
	}

	public RegVO convert(RegDTO regdto) {
		
		RegVO regvo = new RegVO();
		regvo.setId(regdto.getId());
		regvo.setFirstName(regdto.getFirstName());
		regvo.setLastName(regdto.getLastName());
		regvo.setEmailID(regdto.getEmailID());
		regvo.setPassword(regdto.getPassword());
		regvo.setStatus(regdto.getStatus());
		return regvo;
	}

	public void changeStatus(RegVO regVO) {
		// TODO Auto-generated method stub
		
	}
	
//	here we use path variable annotation for getting data from path variable
//	after that data we didn't get then we send 404 error about data not found
	
//	practically at this moment we actually send 200 response :
//	repsonse has 3 parts : status , data and message 
//	if we didnt get any record from DB so error is in user data not in API so we send appro. data
//	with status 200 coz api runs sucessfully but data is malicious 
}
