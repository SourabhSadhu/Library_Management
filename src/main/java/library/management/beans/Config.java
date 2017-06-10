package library.management.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import library.management.service.*;
//import library.management.beans.*;

@Configuration
public class Config {

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Bean
	public BookService getBookService(){
		return new BookService();
	}
	
	@Bean
	public Utils getUtils(){
		return new Utils();
	}
}
