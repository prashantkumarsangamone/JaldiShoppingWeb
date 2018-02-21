package com.sangamone.jaldishopping.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sangamone.jaldishopping.constants.Constants;
import com.sangamone.jaldishopping.domain.UserDetails;
import com.sangamone.jaldishopping.domain.VendorDetails;
import com.sangamone.jaldishopping.services.AdminService;
import com.sangamone.jaldishopping.utils.ExceptionMessageConvertor;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ExceptionMessageConvertor exceptionMessageConvertor;
	
	
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
		}

		if (logout != null) {
			model.addObject("success", "You've been logged out successfully.");
		}
		model.setViewName("login/login");

		return model;

	}
	
	
			@RequestMapping(value =  "/home", method = RequestMethod.GET)
			@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
			public ModelAndView home() {

				ModelAndView model = new ModelAndView();

				model.setViewName("home/home");

				return model;

			}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
	public ModelAndView signup() {

		ModelAndView model = new ModelAndView();

		model.setViewName("login/signup");

		return model;

	}
	
	
	@RequestMapping(value = "/signupDetails", method = RequestMethod.POST)
	public ModelAndView signupDetails(Principal principal,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "userMobile", required = false) String userMobile,
			@RequestParam(value = "zipCode", required = false) String zipCode) {

		ModelAndView model = new ModelAndView();
	UserDetails userDetails=adminService.validateUser(userEmail);
		if(userDetails==null){
			
			adminService.addUsers(firstName,lastName,userEmail,userMobile,zipCode);
			
			model.addObject("success","You have successfully registered. please check your mail.");
			model.setViewName("login/login");
				
		}else{
			model.addObject("error","Email Id already Exist");
			model.setViewName("login/signup");
		}
		return model;

	}

	
	@RequestMapping(value = "/customerLogin", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody JaldiShoppingResponse customerLogin(
			@RequestBody @RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "userPassword", required = false) String userPassword) {

		JaldiShoppingResponse jaldiShoppingResponse;
		try {
			UserDetails userDetails = adminService.validateLogin(userEmail, userPassword);

			jaldiShoppingResponse = prepareResponse(null, userDetails);
		} catch (Exception e) {

			e.printStackTrace();

			jaldiShoppingResponse = prepareResponse(e, null);

		}

		return jaldiShoppingResponse;

	}
	
	private JaldiShoppingResponse prepareResponse(Exception e, UserDetails userDetails) {

		JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();

		if (e == null) {

			jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

			jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);
			
			/*jaldiShoppingResponse.setVendorId(vendorDetails.getVendorId());

			jaldiShoppingResponse.setVendorName(vendorDetails.getVendorName());*/
		
		} else {

			/*jaldiShoppingResponse.setResponseCode(exceptionMessageConvertor.getCode(e));
			jaldiShoppingResponse.setDescription(exceptionMessageConvertor.getMessage(e));
*/
		}

		return jaldiShoppingResponse;
	}

	
	@RequestMapping(value = "/customerSignup", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody JaldiShoppingResponse customerSignup(
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "userMobile", required = false) String userMobile,
			@RequestParam(value = "zipCode", required = false) String zipCode){

		JaldiShoppingResponse jaldiShoppingResponse;
		try {
			UserDetails userDetails=adminService.validateUser(userEmail);
				
				adminService.addUsers(firstName,lastName,userEmail,userMobile,zipCode);
			

			jaldiShoppingResponse = prepareResponse(null, userDetails);
			
		} catch (Exception e) {

			e.printStackTrace();

			jaldiShoppingResponse = prepareResponse(e, null);

		}

		return jaldiShoppingResponse;

	}
	
	
	/*@RequestMapping(value = "/getProductList/{merchantCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody

	public BillSangamResponse getProductList(@RequestBody @PathVariable String merchantCode) {
		List<ProductDetails> productdetailsList = null;
		BillSangamResponse billSangamResponse;

		try {
			System.out.println(merchantCode);
			merchantDetailsService.validateMerchantCode(merchantCode);
			try {
				productdetailsList = merchantDetailsService.getProductDetailsbyMerchantCode(merchantCode);
				billSangamResponse = responseProductList(null, productdetailsList);
			} catch (Exception e) {
				e.printStackTrace();
				billSangamResponse = responseProductList(e, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			billSangamResponse = perpareResponseMerchant(e, null);

		}
		return billSangamResponse;

	}

	private BillSangamResponse responseProductList(Exception e, List<ProductDetails> productdetailsList) {

		BillSangamResponse billSangamResponse = new BillSangamResponse();

		if (e == null) {

			billSangamResponse.setProductList(productdetailsList);

		} else {
			billSangamResponse.setResponseCode(exceptionMessageConvertor.getCode(e));
			billSangamResponse.setDescription(exceptionMessageConvertor.getMessage(e));

		}

		return billSangamResponse;
	}*/
	
}