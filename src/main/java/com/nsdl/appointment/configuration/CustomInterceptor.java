package com.nsdl.appointment.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nsdl.appointment.dto.UserDTO;
import com.nsdl.appointment.utils.TokenValidator;

@Component
//@CrossOrigin
public class CustomInterceptor  extends HandlerInterceptorAdapter  {

	@Autowired
	UserDTO userdto;
	
	@Autowired
	private TokenValidator tokenValidator;
	
	@Value("${auth.jwt.base}")
	private String base;

	
	private void gettokenDetails(String token)
	{
		String userName=tokenValidator.getUsernameFromToken(token.replace(base, ""));
		String role=tokenValidator.getRoleFromToken(token.replace(base, ""));
		userdto.setRole(role);
		userdto.setUserName(userName);
	}
	 @Override
	   public boolean preHandle(
	      HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 String token=request.getHeader("Authorization");
		 if(token!=null)
		 {
			  gettokenDetails(token);
		 }
	      return true;
	   }
	   @Override
	   public void postHandle(
	      HttpServletRequest request, HttpServletResponse response, Object handler, 
	      ModelAndView modelAndView) throws Exception {}
	   
	   @Override
	   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
	      Object handler, Exception exception) throws Exception {}

   
	
}