package com.servicemodules.LoginModule;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.servicemodules.LoginModule.model.Role;
import com.servicemodules.LoginModule.model.User;
import com.servicemodules.LoginModule.model.UserRole;
import com.servicemodules.LoginModule.service.UserService;

@SpringBootApplication
public class LoginModuleApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(LoginModuleApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		System.out.println("Starting ...");
		
//		
//		User user = new User("pain",new BCryptPasswordEncoder().encode("abc"),"sai","kiran","abc@gmail.com","8989898989");
//		Set<UserRole> userRoles = new HashSet<>();
//		Role role = new Role(1L,"ROLE_ADMIN");
//		
//		userRoles.add(new UserRole(user,role));
//		
//		userService.createUser(user, userRoles);
		
	}
	

}
