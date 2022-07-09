package com.rest.service;

import java.util.List;

import com.rest.dto.RegDTO;
import com.rest.model.RegVO;

public interface RegService {
	
	public void insert(RegVO regVO);
	
	public List search();
	
	public void delete(RegVO regVO);
	
	public List searchById(RegVO regVO);
	
	public RegVO convert(RegDTO regdto);
	
	public void changeStatus(RegVO regVO);
}
