package com.sangamone.jaldishopping.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.sangamone.jaldishopping.constants.Constants;
import com.sangamone.jaldishopping.domain.ProductDetails;
import com.sangamone.jaldishopping.domain.UserDetails;
import com.sangamone.jaldishopping.domain.VendorDetails;
import com.sangamone.jaldishopping.exception.JaldiShoppingBaseException;
import com.sangamone.jaldishopping.repositories.VendorDetailsRepository;
import com.sangamone.jaldishopping.services.AdminService;
import com.sangamone.jaldishopping.utils.ExceptionMessageConvertor;


@RestController()
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ExceptionMessageConvertor exceptionMessageConvertor;
	
	@Autowired
	private VendorDetailsRepository vendorDetailsRepository;
	

	
	
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
			List<VendorDetails> vendorDetails=getVendorList();
			jaldiShoppingResponse = prepareResponse(null, userDetails,vendorDetails);
		} catch (Exception e) {

			e.printStackTrace();

			jaldiShoppingResponse = prepareResponse(e, null, null);

		}

		return jaldiShoppingResponse;

	}
	


	private List<VendorDetails> getVendorList() {
		// TODO Auto-generated method stub
		return (List<VendorDetails>) vendorDetailsRepository.findAll();
	}



	private JaldiShoppingResponse prepareResponse(Exception e, UserDetails userDetails, List<VendorDetails> vendorDetails) {

		JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
		
		

		if (e == null) {
			
			
			jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

			jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);
			
			jaldiShoppingResponse.setVendorDetails(vendorDetails);
			
			
		
		} else {

			jaldiShoppingResponse.setResponseCode(exceptionMessageConvertor.getCode(e));
			jaldiShoppingResponse.setDescription(exceptionMessageConvertor.getMessage(e));

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
			
				List<VendorDetails> vendorDetails=getVendorList();
			jaldiShoppingResponse = prepareResponse(null, userDetails, vendorDetails);
			
		} catch (Exception e) {

			e.printStackTrace();

			jaldiShoppingResponse = prepareResponse(e, null, null);

		}

		return jaldiShoppingResponse;

	}

	
	
	

	@RequestMapping(value = "/getURLInput", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
	public ModelAndView getURLInput() {

		ModelAndView model = new ModelAndView();

		model.setViewName("reports/URLInput");

		return model;

	}

	@RequestMapping(value = "/getURLInputDetails", method = RequestMethod.POST ,produces = "application/json; charset=UTF-8")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
	public ModelAndView getURLInputDetails(Principal principal,
			@RequestParam(value = "productId", required = false) Long productId,
			@RequestParam(value = "categoryId", required = false) Long categoryId,
			@RequestParam(value = "vendorId", required = false) Long vendorId,
			@RequestParam(value = "locationId", required = false) Long locationId) {

		ModelAndView model = new ModelAndView();
		ProductDetails productDetails = 
				adminService.addProductDetails(productId,categoryId,vendorId,locationId);
		
	
		model.setViewName("reports/URLInput");

		return model;

	}
	
	
	@RequestMapping(value = "/getProductList/{productId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public JaldiShoppingResponse getProductList(@RequestBody @PathVariable Long productId) throws JaldiShoppingBaseException {
		
		JaldiShoppingResponse jaldiShoppingResponse;
		try {
			System.out.println(productId);
			List <ProductDetails> productDetails = adminService.findByProductId(productId);
			
			if(productDetails.isEmpty())
			{
			
			 ProductDetails productDetails2 = 
						adminService.addProductDetails(productId);
			
					
					List <ProductDetails> productDetails3 = adminService.findByProductId(productId);
				
						jaldiShoppingResponse = responseProductList(null, productDetails3);
				}
				
				
			else{
				
				jaldiShoppingResponse = responseProductList(null, productDetails);
		 
			}
		} catch (Exception e) {
			e.printStackTrace();
			jaldiShoppingResponse = responseProductList(e, null);
		}
		return jaldiShoppingResponse;

	}
	

	
	

	private JaldiShoppingResponse responseProductList(Exception e, List<ProductDetails> productDetails) {

		JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();

		if (e == null) {

			jaldiShoppingResponse.setProductDetails(productDetails);
			jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

			jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);

		} else {
			jaldiShoppingResponse.setResponseCode(exceptionMessageConvertor.getCode(e));
			jaldiShoppingResponse.setDescription(exceptionMessageConvertor.getMessage(e));

		}

		return jaldiShoppingResponse;
	}
	
	
	@RequestMapping(value = "/getProductListUPC/{barCode}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public JaldiShoppingResponse getProductListUPC(@RequestBody @PathVariable String barCode) throws JaldiShoppingBaseException {
		
		System.out.println("Inside Controller");
		
		JaldiShoppingResponse jaldiShoppingResponse;
		try {
			System.out.println(barCode);
			List <ProductDetails> productDetails = adminService.findByBarCode(barCode);
			
			
			if(productDetails.isEmpty())
			{
			
			 ProductDetails productDetails2 = 
						adminService.addProductDetails1(barCode);
			
					
					List <ProductDetails> productDetails3 = adminService.findByBarCode(barCode);
				
						jaldiShoppingResponse = responseProductList(null, productDetails3);
				}
				
				
			else{
				
				jaldiShoppingResponse = responseProductList(null, productDetails);
		 
			}
		} catch (Exception e) {
			e.printStackTrace();
			jaldiShoppingResponse = responseProductList(e, null);
		}
		return jaldiShoppingResponse;

	}

}