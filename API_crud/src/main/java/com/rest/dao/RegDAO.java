package com.rest.dao;

import java.util.*;
import com.rest.model.*;

public interface RegDAO {
	
	public void insert(RegVO regvo);
	
	public List search();
	
	public void delete(RegVO regvo);
	
	public List searchById(RegVO regvo);
}
