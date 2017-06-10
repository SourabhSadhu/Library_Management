package library.management.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/*import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;*/

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import library.management.beans.LibraryUserBean;
import library.management.service.BookService;
import library.management.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LibraryUserBean userBean;

	@Autowired
	LoginService loginService;

	@Autowired
	BookService getBookService;
	
//	@Autowired BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@RequestMapping( value = "endPoints", method = RequestMethod.GET )
	public String getEndPointsInView( Model model )
	{
	    model.addAttribute( "endPoints", requestMappingHandlerMapping.getHandlerMethods().keySet() );
	    return "endpoints";
	}


	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	private ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user",new LibraryUserBean());
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	private ModelAndView login(LibraryUserBean user) {
		ModelAndView mav = new ModelAndView();
		userBean = loginService.userValidate(user.getEmail(), user.getPassword());
		if (null != userBean) {

			if (userBean.getRole() == 1) {
//				mav.addObject("userList", loginService.listAllUser());
//				mav.setViewName("user_manage");
				mav.addObject("user", userBean);
				mav.setViewName("library_management");
			} else {
				mav.addObject("successMessage", "Welcome " + userBean.getName() + " || " + userBean.getEmail());
				mav.addObject("success_image", true);
				mav.addObject("user", new LibraryUserBean());
				mav.setViewName("login");
			}
		} else {
			mav.addObject("successMessage", "Invalid User Email or Password");
			mav.addObject("user", new LibraryUserBean());
			mav.setViewName("dashboard");
		}

		return mav;
	}
	
	/*@RequestMapping(value="/angular", method=RequestMethod.GET)
	private ModelAndView getAngular(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("angular");
		return mav;
	}*/
	
//	@GET
//	@Path("/getUserList")
//    @Produces(MediaType.APPLICATION_JSON)
	
	
	@RequestMapping(value = { "/auto" }, method = RequestMethod.GET)
	private ModelAndView auto() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("angular_autocomplete");
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
