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
import com.sangamone.jaldishopping.domain.MyListDetails;
import com.sangamone.jaldishopping.domain.ProductDetails;
import com.sangamone.jaldishopping.domain.UserDetails;
import com.sangamone.jaldishopping.domain.VendorDetails;
import com.sangamone.jaldishopping.exception.JaldiShoppingBaseException;
import com.sangamone.jaldishopping.repositories.ProductDetailsRepository;
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
	
	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	
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
			List<ProductDetails> productDetails=getProductList();
			jaldiShoppingResponse = prepareResponse(null, userDetails,vendorDetails, productDetails);
		} catch (Exception e) {

			e.printStackTrace();

			jaldiShoppingResponse = prepareResponse(e, null, null, null);

		}

		return jaldiShoppingResponse;

	}
	




	private List<VendorDetails> getVendorList() {
		// TODO Auto-generated method stub
		return (List<VendorDetails>) vendorDetailsRepository.findAll();
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
			UserDetails userDetails= new UserDetails();
			/*UserDetails userDetails=adminService.validateUser(userEmail);*/
				
				adminService.addUsers(firstName,lastName,userEmail,userMobile,zipCode);
			
				List<VendorDetails> vendorDetails=getVendorList();
				List<ProductDetails> productDetails=getProductList();
			jaldiShoppingResponse = prepareResponse(null, userDetails, vendorDetails, productDetails);
			
		} catch (Exception e) {

			e.printStackTrace();

			jaldiShoppingResponse = prepareResponse(e, null, null, null);

		}

		return jaldiShoppingResponse;

	}

	
	
	

	private List<ProductDetails> getProductList() {
		// TODO Auto-generated method stub
		return (List<ProductDetails>) productDetailsRepository.findAll();
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



	@RequestMapping(value = "/getShoppingDetails/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public JaldiShoppingResponse getShoppingDetails(){
		JaldiShoppingResponse jaldiShoppingResponse;
		try {
			List<VendorDetails> vendorDetails=getVendorList();
			List<ProductDetails> productDetails=getProductList();
				
						jaldiShoppingResponse = responseShoppingDetails(null,vendorDetails, productDetails);
				
				
		} catch (Exception e) {
			e.printStackTrace();
			jaldiShoppingResponse = responseShoppingDetails(e, null, null);
		}
		return jaldiShoppingResponse;

	}
	

	@RequestMapping(value = "/getMyListDetails/{userId}/{productId}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public JaldiShoppingResponse getMyListDetails(@RequestBody @PathVariable String userId, @PathVariable String productId){
		JaldiShoppingResponse jaldiShoppingResponse;

		try {
			MyListDetails myListDetails = adminService.addMyListDetails(userId,productId);
			
		
			jaldiShoppingResponse = prepareMyListResponse(null);
		} catch (Exception e) {
			e.printStackTrace();
			jaldiShoppingResponse = prepareMyListResponse(e);

		}
		return jaldiShoppingResponse;

	}
	
	@RequestMapping(value = "/getMyListService/{userId}/{productId}", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public JaldiShoppingResponse getMyListService(@RequestBody @PathVariable String userId, @PathVariable String productId){
		JaldiShoppingResponse jaldiShoppingResponse;

		try {
			MyListDetails myListDetails = adminService.addMyListDetails(userId,productId);
			
		
			jaldiShoppingResponse = prepareMyListResponse(null);
		} catch (Exception e) {
			e.printStackTrace();
			jaldiShoppingResponse = prepareMyListResponse(e);

		}
		return jaldiShoppingResponse;

	}
	

	private JaldiShoppingResponse prepareResponse(Exception e, UserDetails userDetails, List<VendorDetails> vendorDetails, List<ProductDetails> productDetails) {

		JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
		
		

		if (e == null) {
			
			
			jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

			jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);
			
			jaldiShoppingResponse.setVendorDetails(vendorDetails);
			
			jaldiShoppingResponse.setProductDetails(productDetails);
			
			
		} else {

			jaldiShoppingResponse.setResponseCode(exceptionMessageConvertor.getCode(e));
			jaldiShoppingResponse.setDescription(exceptionMessageConvertor.getMessage(e));

		}

		return jaldiShoppingResponse;
	}
	
/*
	private JaldiShoppingResponse prepareLoginResponse(Exception e, Object object, Object object2, Object object3) {

		JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
		
		

		if (e == null) {
			
			
			jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

			jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);
			
			jaldiShoppingResponse.setVendorDetails(vendorDetails);
			
			jaldiShoppingResponse.setProductDetails(productDetails);
			
			
		} else {

			jaldiShoppingResponse.setResponseCode(exceptionMessageConvertor.getCode(e));
			jaldiShoppingResponse.setDescription(exceptionMessageConvertor.getMessage(e));

		}

		return jaldiShoppingResponse;
	}*/


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
	
	

	private JaldiShoppingResponse responseShoppingDetails(Exception e, List<VendorDetails> vendorDetails, List<ProductDetails> productDetails) {

		JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
		
		

		if (e == null) {
			
			
			jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

			jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);
			
			jaldiShoppingResponse.setVendorDetails(vendorDetails);
			
			jaldiShoppingResponse.setProductDetails(productDetails);
			
			
		
		} else {

			jaldiShoppingResponse.setResponseCode(exceptionMessageConvertor.getCode(e));
			jaldiShoppingResponse.setDescription(exceptionMessageConvertor.getMessage(e));

		}

		return jaldiShoppingResponse;
	}


	

	private JaldiShoppingResponse prepareMyListResponse(Exception e) {

		JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
		
		

		if (e == null) {
			
			
			jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
			jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);
		
				
		} else {

			jaldiShoppingResponse.setResponseCode(exceptionMessageConvertor.getCode(e));
			jaldiShoppingResponse.setDescription(exceptionMessageConvertor.getMessage(e));

		}

		return jaldiShoppingResponse;
	}
	
}