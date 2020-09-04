package com.earthzone.aa.dao.Retailer;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.earthzone.aa.entity.RetailerEntity;
import com.earthzone.aa.entity.UserEntity;
import com.earthzone.aa.enums.Role;
import com.earthzone.aa.model.Retailer;
import com.earthzone.aa.model.User;

public class RetailerDAOImpl implements RetailerDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	


	@Override
	public String addDetailsRetailer(Retailer retailer,String email) {
		RetailerEntity retailerEntity = entityManager.find(RetailerEntity.class, email);
		retailerEntity.setAadhaar(retailer.getAadhaar());
		retailerEntity.setGst(retailer.getGst());
		retailerEntity.setPan(retailer.getPan());
		retailerEntity.setShopAddress(retailer.getShopAddress());
		retailerEntity.setShopCity(retailer.getShopCity());
		retailerEntity.setShopName(retailer.getShopName());
		retailerEntity.setShopPincode(retailer.getShopPincode());
		retailerEntity.setShopState(retailer.getShopState());
		entityManager.persist(retailerEntity);
		return "Retailer Details Added Successfully";
	}
	

}
