package com.earthzone.aa.security;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earthzone.aa.entity.UserEntity;
import com.earthzone.aa.model.User;



@Transactional(readOnly = true)
@Service("securityUserDetailService")
public class SecurityUserDetailService implements UserDetailsService{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//User user = entityManager.find(User.class,username);
		Query q =entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email=?1");
		q.setParameter(1, username);
		UserEntity userEntity = (UserEntity) q.getSingleResult();
		if(userEntity==null) {
			throw new UsernameNotFoundException("User Not Found!! Try Again");
		}
		User user=new User();
		user.setEmail(userEntity.getEmail());
		user.setPassword(userEntity.getPassword());
		user.setRole(userEntity.getRole());
		user.setEnabled(userEntity.getEnabled());
		return new SecurityUserDetails(user);
	}

}
