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
import com.sangamone.jaldishopping.domain.BeaconLocations;
import com.sangamone.jaldishopping.domain.CartDetails;
import com.sangamone.jaldishopping.domain.MyListDetails;
import com.sangamone.jaldishopping.domain.OrderDetails;
import com.sangamone.jaldishopping.domain.ProductDetails;
import com.sangamone.jaldishopping.domain.TransactionDetails;
import com.sangamone.jaldishopping.domain.UserDetails;
import com.sangamone.jaldishopping.domain.VendorDetails;
import com.sangamone.jaldishopping.domain.WishlistDetails;
import com.sangamone.jaldishopping.exception.EmailIdAlreadyExistException;
import com.sangamone.jaldishopping.exception.JaldiShoppingBaseException;
import com.sangamone.jaldishopping.repositories.CartDetailsRepository;
import com.sangamone.jaldishopping.repositories.MyListDetailsRepository;
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

    @Autowired
    private MyListDetailsRepository myListDetailsRepository;

    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    /* Jaldi Shopping Controller */

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
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


    @RequestMapping(value = "/home", method = RequestMethod.GET)
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
                                      @RequestParam(value = "zipCode", required = false) String zipCode) throws EmailIdAlreadyExistException {

        ModelAndView model = new ModelAndView();
        UserDetails userDetails = adminService.validateUser(userEmail);
        if (userDetails == null) {

            adminService.addUsers(firstName, lastName, userEmail, userMobile, zipCode);

            model.addObject("success", "You have successfully registered. please check your mail.");
            model.setViewName("login/login");

        } else {
            model.addObject("error", "Email Id already Exist");
            model.setViewName("login/signup");
        }
        return model;

    }

    @RequestMapping(value = "/getURLInput", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ModelAndView getURLInput() {

        ModelAndView model = new ModelAndView();

        model.setViewName("reports/URLInput");

        return model;

    }

    @RequestMapping(value = "/getURLInputDetails", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    public ModelAndView getURLInputDetails(Principal principal,
                                           @RequestParam(value = "productId", required = false) String productId,
                                           @RequestParam(value = "categoryId", required = false) Long categoryId,
                                           @RequestParam(value = "vendorId", required = false) Long vendorId,
                                           @RequestParam(value = "locationId", required = false) Long locationId) {

        ModelAndView model = new ModelAndView();
        ProductDetails productDetails =
                adminService.addProductDetails(productId, categoryId, vendorId, locationId);


        model.setViewName("reports/URLInput");

        return model;

    }


    @RequestMapping(value = "/customerLogin", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody
    JaldiShoppingResponse customerLogin(
            @RequestBody @RequestParam(value = "userEmail", required = false) String userEmail,
            @RequestParam(value = "userPassword", required = false) String userPassword) {

        JaldiShoppingResponse jaldiShoppingResponse;
        try {
            System.out.println("" + userPassword);
            UserDetails userDetails = adminService.validateLogin(userEmail, userPassword);
            if (userDetails != null) {
                List<VendorDetails> vendorDetails = getVendorList();
                List<ProductDetails> productDetails = getProductList();

                adminService.getUser(userEmail);
                String userId = String.valueOf(userDetails.getUserId());
                System.out.println("UserId @ Controller-----------  " + userId);

                List<CartDetails> cartDetails = adminService.getCartDetails(userId);
                jaldiShoppingResponse = prepareLoginResponse(null, userDetails, vendorDetails, productDetails, cartDetails);
            } else {
                jaldiShoppingResponse = prepareLoginErrorResponse(null);
            }
        } catch (Exception e) {

            e.printStackTrace();

            jaldiShoppingResponse = prepareLoginResponse(e, null, null, null, null);

        }


        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/getShoppingDetail", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody
    JaldiShoppingResponse getShoppingDetail(
            @RequestBody @RequestParam(value = "userEmail", required = false) String userEmail) throws JaldiShoppingBaseException {

        JaldiShoppingResponse jaldiShoppingResponse;
        try {
            List<VendorDetails> vendorDetails = getVendorList();
            List<ProductDetails> productDetails = getProductList();

            UserDetails userDetails = adminService.getUser(userEmail);
            String userId = String.valueOf(userDetails.getUserId());
            System.out.println("UserId @ Controller-----------  " + userId);

            List<CartDetails> cartDetails = adminService.getCartDetails(userId);


            jaldiShoppingResponse = responseShoppingDetails(null, vendorDetails, productDetails, cartDetails);


        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = responseShoppingDetails(e, null, null, null);
        }
        return jaldiShoppingResponse;

    }


    private List<CartDetails> getCartList() {
        // TODO Auto-generated method stub
        return (List<CartDetails>) cartDetailsRepository.findAll();
    }


    private List<VendorDetails> getVendorList() {
        // TODO Auto-generated method stub
        return (List<VendorDetails>) vendorDetailsRepository.findAll();
    }


    @RequestMapping(value = "/customerSignup", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody
    JaldiShoppingResponse customerSignup(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "userEmail", required = false) String userEmail,
            @RequestParam(value = "userMobile", required = false) String userMobile,
            @RequestParam(value = "zipCode", required = false) String zipCode) {

        JaldiShoppingResponse jaldiShoppingResponse;
        try {
            //UserDetails userDetails= new UserDetails();
            UserDetails userDetails = adminService.validateUser(userEmail);

            adminService.addUsers(firstName, lastName, userEmail, userMobile, zipCode);

            List<VendorDetails> vendorDetails = getVendorList();
            List<ProductDetails> productDetails = getProductList();
            jaldiShoppingResponse = prepareSignUpResponse(null, userDetails, vendorDetails, productDetails);

        } catch (Exception e) {

            e.printStackTrace();

            jaldiShoppingResponse = prepareSignUpResponse(e, null, null, null);

        }

        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/customizedSignup", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody
    JaldiShoppingResponse customizedSignup(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "userEmail", required = false) String userEmail,
            @RequestParam(value = "userMobile", required = false) String userMobile,
            @RequestParam(value = "zipCode", required = false) String zipCode) {

        JaldiShoppingResponse jaldiShoppingResponse;
        try {
            //UserDetails userDetails= new UserDetails();
            UserDetails userDetails = adminService.validateUser(userEmail);

            adminService.addfbUsers(firstName, lastName, userEmail, userMobile, zipCode);

            List<VendorDetails> vendorDetails = getVendorList();
            List<ProductDetails> productDetails = getProductList();
            jaldiShoppingResponse = prepareSignUpResponse(null, userDetails, vendorDetails, productDetails);

        } catch (Exception e) {

            e.printStackTrace();

            jaldiShoppingResponse = prepareSignUpResponse(e, null, null, null);

        }

        return jaldiShoppingResponse;

    }


    private List<ProductDetails> getProductList() {
        // TODO Auto-generated method stub
        return (List<ProductDetails>) productDetailsRepository.findAll();
    }


    @RequestMapping(value = "/getProductList/{productId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse getProductList(@RequestBody @PathVariable String productId) throws JaldiShoppingBaseException {

        JaldiShoppingResponse jaldiShoppingResponse;
        try {
            System.out.println(productId);
            List<ProductDetails> productDetails = adminService.findByProductId(productId);

            if (productDetails.isEmpty()) {

                ProductDetails productDetails2 =
                        adminService.addProductDetails(productId);


                List<ProductDetails> productDetails3 = adminService.findByProductId(productId);

                jaldiShoppingResponse = responseProductList(null, productDetails3);
            } else {

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
            List<ProductDetails> productDetails = adminService.findByBarCode(barCode);


            if (productDetails.isEmpty()) {

                ProductDetails productDetails2 =
                        adminService.addProductDetails1(barCode);


                List<ProductDetails> productDetails3 = adminService.findByBarCode(barCode);

                jaldiShoppingResponse = responseProductList(null, productDetails3);
            } else {

                jaldiShoppingResponse = responseProductList(null, productDetails);

            }
        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = responseProductList(e, null);
        }
        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/getShoppingDetails/", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse getShoppingDetails() {
        JaldiShoppingResponse jaldiShoppingResponse;
        try {
            List<VendorDetails> vendorDetails = getVendorList();
            List<ProductDetails> productDetails = getProductList();

            jaldiShoppingResponse = responseShoppingDetails1(null, vendorDetails, productDetails);


        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = responseShoppingDetails1(e, null, null);
        }
        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/addWishListDetails", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse addWishListDetails(@RequestBody @RequestParam(value = "userEmail", required = false) String userEmail,
                                                    @RequestParam(value = "productId", required = false) String productId) {
        System.out.println(userEmail + "--------------------" + productId);
        JaldiShoppingResponse jaldiShoppingResponse;

        try {

            UserDetails userDetails = adminService.getUser(userEmail);
            String userId = String.valueOf(userDetails.getUserId());


            WishlistDetails wishlistDetails = adminService.validateWishListDetails(userId, productId);

            if (wishlistDetails != null) {

                jaldiShoppingResponse = productAlreadyExist();
            } else {


                WishlistDetails wishListDetails = adminService.addWishListDetails(userId, productId);

                jaldiShoppingResponse = prepareWishListResponse(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = prepareWishListResponse(e);

        }
        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/getWishListDetails", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse getWishListDetails(@RequestBody @RequestParam(value = "userEmail", required = false) String userEmail) {
        JaldiShoppingResponse jaldiShoppingResponse;

        try {

            UserDetails userDetails = adminService.getUser(userEmail);
            String userId = String.valueOf(userDetails.getUserId());

            List<WishlistDetails> wishlistDetails = adminService.getWishListDetails(userId);
            List<ProductDetails> productDetails = getProductList();

            jaldiShoppingResponse = prepareWishlistDetailsResponse(null, wishlistDetails, productDetails);
        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = prepareWishlistDetailsResponse(e, null, null);

        }
        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/deleteWishListProduct", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse deleteWishListProduct(@RequestBody @RequestParam(value = "userEmail", required = false) String userEmail,
                                                       @RequestParam(value = "productId", required = false) String productId) {
        System.out.println(userEmail + "--------------------" + productId);
        JaldiShoppingResponse jaldiShoppingResponse;

        try {

            UserDetails userDetails = adminService.getUser(userEmail);
            String userId = String.valueOf(userDetails.getUserId());
            String deleteFlag = "N";
            WishlistDetails wishlistDetails = adminService.validateWishListDetails(userId, productId);
            if (wishlistDetails == null) {
                jaldiShoppingResponse = productAlreadyDeleted();
            } else {
                adminService.DeleteWishListDetails(wishlistDetails, deleteFlag);

                jaldiShoppingResponse = prepareWishListItemDeleteResponse(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = prepareWishListItemDeleteResponse(e);

        }
        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/addMyCartDetails", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse addMyCartDetails(@RequestBody @RequestParam(value = "userEmail", required = false) String userEmail,
                                                  @RequestParam(value = "productId", required = false) String productId, @RequestParam(value = "numberOfItems", required = false) String numberOfItems) {
        System.out.println(userEmail + "--------------------" + productId + "-------------" + numberOfItems);
        JaldiShoppingResponse jaldiShoppingResponse;

        try {


            UserDetails userDetails = adminService.getUser(userEmail);
            String userId = String.valueOf(userDetails.getUserId());


            CartDetails cartDetails1 = adminService.validateCartDetails(userId, productId);

            if (cartDetails1 != null) {

                jaldiShoppingResponse = productAlreadyExist();
            } else {

                //String amount = null;
                ProductDetails productDetails = adminService.getProductAmount(productId);
                System.out.println("productDetails" + productDetails);
                String amount = productDetails.getProductFinalPrice();
                System.out.println("Amount" + amount);
                CartDetails cartDetails = adminService.addMyCartDetails(userId, productId, numberOfItems, amount);


                jaldiShoppingResponse = prepareCheckOutResponse(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = prepareCheckOutResponse(e);

        }
        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/getMyCartDetails", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse getMyCartDetails(@RequestBody @RequestParam(value = "userEmail", required = false) String userEmail) {
        JaldiShoppingResponse jaldiShoppingResponse;
        System.out.println("UserId " + userEmail);
        try {

            UserDetails userDetails = adminService.getUser(userEmail);
            String userId = String.valueOf(userDetails.getUserId());
            System.out.println("UserId @ Controller-----------  " + userId);
            List<CartDetails> cartDetails = adminService.getCartDetails(userId);
            if (cartDetails.isEmpty()) {
                jaldiShoppingResponse = cartIsEmpty();
            } else {
                jaldiShoppingResponse = prepareCheckOutServiceResponse(null, cartDetails);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = prepareCheckOutServiceResponse(e, null);

        }
        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/getMyBillingDetails", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse getMyBillingDetails(@RequestBody @RequestParam(value = "userEmail", required = false) String userEmail) {
        JaldiShoppingResponse jaldiShoppingResponse;
        System.out.println("UserId " + userEmail);
        try {

            UserDetails userDetails = adminService.getUser(userEmail);
            String userId = String.valueOf(userDetails.getUserId());
            System.out.println("UserId @ Controller-----------  " + userId);

            List<CartDetails> cartDetails = adminService.getBillingDetails(userId);


            if (cartDetails.isEmpty()) {
                jaldiShoppingResponse = cartIsEmpty();
            } else {
                TransactionDetails transactionDetails = adminService.addMyTransactionDetails(userId, cartDetails);

                jaldiShoppingResponse = prepareBillingDetailsResponse(null, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = prepareBillingDetailsResponse(e, null);

        }
        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/deleteCartProduct", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse deleteCartProduct(@RequestBody @RequestParam(value = "userEmail", required = false) String userEmail,
                                                   @RequestParam(value = "productId", required = false) String productId) {
        System.out.println(userEmail + "--------------------" + productId);
        JaldiShoppingResponse jaldiShoppingResponse;

        try {


            UserDetails userDetails = adminService.getUser(userEmail);
            String userId = String.valueOf(userDetails.getUserId());
            String deleteFlag = "N";
            CartDetails cartDetails = adminService.validateCartDetails(userId, productId);
            if (cartDetails == null) {
                jaldiShoppingResponse = productAlreadyDeleted();
            } else {
                adminService.DeleteCartDetails(cartDetails, deleteFlag);

                jaldiShoppingResponse = prepareCartItemDeleteResponse(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = prepareCartItemDeleteResponse(e);

        }
        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/createOrderDetails", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse createOrderDetails(@RequestBody @RequestParam(value = "orderAmount", required = false) String orderAmount,
                                                    @RequestParam(value = "orderDate", required = false) String orderDate,
                                                    @RequestParam(value = "quantity", required = false) String quantity,
                                                    @RequestParam(value = "userId", required = false) String userId,
                                                    @RequestParam(value = "productId", required = false) String productId,
                                                    @RequestParam(value = "paymentDetailsId", required = false) String paymentDetailsId) {

        JaldiShoppingResponse jaldiShoppingResponse;

        try {
            OrderDetails orderDetails = adminService.addMyOrderDetails(orderAmount, orderDate, quantity, userId, productId, paymentDetailsId);

            jaldiShoppingResponse = prepareCreateOrderDetailsResponse(null);
        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = prepareCreateOrderDetailsResponse(e);

        }
        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/getMyOrderDetails", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse getMyOrderDetails(@RequestBody @RequestParam(value = "userId", required = false) String userId) {
        JaldiShoppingResponse jaldiShoppingResponse;

        try {

            List<OrderDetails> orderDetails = adminService.getOrderDetails(userId);

            jaldiShoppingResponse = prepareOrderDetailsResponse(null, orderDetails);
        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = prepareOrderDetailsResponse(e, null);

        }
        return jaldiShoppingResponse;

    }


    @RequestMapping(value = "/cartQuantityUpdate", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse cartQuantityUpdate(@RequestBody @RequestParam(value = "userEmail", required = false) String userEmail,
                                                    @RequestParam(value = "productId", required = false) String productId, @RequestParam(value = "numberOfItems", required = false) String numberOfItems) {
        System.out.println(userEmail + "--------------------" + productId + "----------------" + numberOfItems);
        JaldiShoppingResponse jaldiShoppingResponse;

        try {


            UserDetails userDetails = adminService.getUser(userEmail);
            String userId = String.valueOf(userDetails.getUserId());


            CartDetails cartDetails1 = adminService.validateCartDetails(userId, productId);

            if (cartDetails1 == null) {

                jaldiShoppingResponse = productDoesNotExist();
            } else {

                adminService.updateQuantity(cartDetails1, userId, productId, numberOfItems);

                jaldiShoppingResponse = prepareCheckOutResponse(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = prepareCheckOutResponse(e);

        }
        return jaldiShoppingResponse;

    }


    /* Beacon Location */


    @RequestMapping(value = "/getBeaconLocation/{appId}/{appToken}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse getBeaconLocation(@RequestBody @PathVariable String appId, @PathVariable String appToken)
            throws JaldiShoppingBaseException {

        JaldiShoppingResponse jaldiShoppingResponse;
        try {
            List<BeaconLocations> beaconLocation = adminService.findByAppId(appId);
			
			/*
			if(beaconLocation.isEmpty())
			{*/

            BeaconLocations beaconLocation1 = adminService.addBeaconLocations(appId, appToken);


            List<BeaconLocations> beaconLocation3 = adminService.findByBeaconLocations();

            jaldiShoppingResponse = responseBeaconLocations(null, beaconLocation3);
				
				
				
			/*else{
				
				jaldiShoppingResponse = responseBeaconLocations(null, beaconLocation);
		 
			}*/
        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = responseBeaconLocations(e, null);
        }
        return jaldiShoppingResponse;

    }

    @RequestMapping(value = "/getBeaconLocations/{appId}/{appToken}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JaldiShoppingResponse getBeaconLocations(@RequestBody @PathVariable String appId, @PathVariable String appToken)
            throws JaldiShoppingBaseException {

        JaldiShoppingResponse jaldiShoppingResponse;
        try {


            List<BeaconLocations> beaconLocation3 = adminService.findByBeaconLocations();

            jaldiShoppingResponse = responseBeaconLocations(null, beaconLocation3);


        } catch (Exception e) {
            e.printStackTrace();
            jaldiShoppingResponse = responseBeaconLocations(e, null);
        }
        return jaldiShoppingResponse;

    }


    /*  Responses   */


    private JaldiShoppingResponse prepareWishListItemDeleteResponse(Exception e) {
        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
        if (e == null) {


            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);


        } else {

            jaldiShoppingResponse.setResponseCode(Constants.NOT_VALID_CREDENTIALS);
            jaldiShoppingResponse.setDescription(Constants.NOT_VALID_CREDENTIALS_MESSAGE);

        }

        return jaldiShoppingResponse;
    }


    private JaldiShoppingResponse prepareCartItemDeleteResponse(Exception e) {
        // TODO Auto-generated method stub
        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
        if (e == null) {


            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);


        } else {

            jaldiShoppingResponse.setResponseCode(Constants.NOT_VALID_CREDENTIALS);
            jaldiShoppingResponse.setDescription(Constants.NOT_VALID_CREDENTIALS_MESSAGE);

        }

        return jaldiShoppingResponse;
    }


    private JaldiShoppingResponse productDoesNotExist() {
        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
        jaldiShoppingResponse.setResponseCode(Constants.PRODUCT_DOES_NOT_EXIST);
        jaldiShoppingResponse.setDescription(Constants.PRODUCT_DOES_NOT_EXIST_MESSAGE);
        return jaldiShoppingResponse;
    }

    private JaldiShoppingResponse productAlreadyExist() {
        // TODO Auto-generated method stub
        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
        jaldiShoppingResponse.setResponseCode(Constants.PRODUCT_ALREADY_ADDED);
        jaldiShoppingResponse.setDescription(Constants.PRODUCT_ALREADY_ADDED_MESSAGE);
        return jaldiShoppingResponse;
    }


    private JaldiShoppingResponse cartIsEmpty() {
        // TODO Auto-generated method stub
        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
        jaldiShoppingResponse.setResponseCode(Constants.CART_IS_EMPTY);
        jaldiShoppingResponse.setDescription(Constants.CART_IS_EMPTY_MESSAGE);
        return jaldiShoppingResponse;
    }


    private JaldiShoppingResponse productAlreadyDeleted() {
        // TODO Auto-generated method stub
        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
        jaldiShoppingResponse.setResponseCode(Constants.PRODUCT_ALREADY_DELETED);
        jaldiShoppingResponse.setDescription(Constants.PRODUCT_ALREADY_DELETED_MESSAGE);
        return jaldiShoppingResponse;
    }


    private JaldiShoppingResponse responseBeaconLocations(Exception e, List<BeaconLocations> beaconLocation3) {

        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();


        if (e == null) {

            jaldiShoppingResponse.setBeaconLocations(beaconLocation3);
            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);


        } else {

            jaldiShoppingResponse.setResponseCode(Constants.INVALID_USERID);
            jaldiShoppingResponse.setDescription(Constants.INVALID_USERID_MESSAGE);

        }

        return jaldiShoppingResponse;
    }


    private JaldiShoppingResponse prepareCreateOrderDetailsResponse(Exception e) {
        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();


        if (e == null) {


            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);


        } else {

            jaldiShoppingResponse.setResponseCode(Constants.NOT_VALID_CREDENTIALS);
            jaldiShoppingResponse.setDescription(Constants.NOT_VALID_CREDENTIALS_MESSAGE);

        }

        return jaldiShoppingResponse;
    }

    private JaldiShoppingResponse prepareOrderDetailsResponse(Exception e, List<OrderDetails> orderDetails) {

        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();


        if (e == null) {

            jaldiShoppingResponse.setOrderDetails(orderDetails);
            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);


        } else {

            jaldiShoppingResponse.setResponseCode(Constants.INVALID_USERID);
            jaldiShoppingResponse.setDescription(Constants.INVALID_USERID_MESSAGE);

        }

        return jaldiShoppingResponse;
    }

    private JaldiShoppingResponse prepareLoginErrorResponse(Object object) {
        // TODO Auto-generated method stub
        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();
        jaldiShoppingResponse.setResponseCode(Constants.NOT_VALID_CREDENTIALS);
        jaldiShoppingResponse.setDescription(Constants.NOT_VALID_CREDENTIALS_MESSAGE);

        return jaldiShoppingResponse;
    }

    private JaldiShoppingResponse prepareCheckOutResponse(Exception e) {
        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();


        if (e == null) {


            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);


        } else {

            jaldiShoppingResponse.setResponseCode(Constants.NOT_VALID_CREDENTIALS);
            jaldiShoppingResponse.setDescription(Constants.NOT_VALID_CREDENTIALS_MESSAGE);

        }

        return jaldiShoppingResponse;
    }


    private JaldiShoppingResponse prepareCheckOutServiceResponse(Exception e, List<CartDetails> cartDetails) {
        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();


        if (e == null) {


            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);

            jaldiShoppingResponse.setCartDetails(cartDetails);


        } else {

            jaldiShoppingResponse.setResponseCode(Constants.NOT_VALID_CREDENTIALS);
            jaldiShoppingResponse.setDescription(Constants.NOT_VALID_CREDENTIALS_MESSAGE);

        }

        return jaldiShoppingResponse;
    }


    private JaldiShoppingResponse prepareBillingDetailsResponse(Exception e, List<TransactionDetails> transactionDetails) {
        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();


        if (e == null) {


            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);

            jaldiShoppingResponse.setTransactionDetails(transactionDetails);


        } else {

            jaldiShoppingResponse.setResponseCode(Constants.NOT_VALID_CREDENTIALS);
            jaldiShoppingResponse.setDescription(Constants.NOT_VALID_CREDENTIALS_MESSAGE);

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

            jaldiShoppingResponse.setResponseCode(Constants.NOT_VALID_CREDENTIALS);
            jaldiShoppingResponse.setDescription(Constants.NOT_VALID_CREDENTIALS_MESSAGE);

        }

        return jaldiShoppingResponse;
    }

    private JaldiShoppingResponse prepareSignUpResponse(Exception e, UserDetails userDetails, List<VendorDetails> vendorDetails, List<ProductDetails> productDetails) {

        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();


        if (e == null) {


            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);

            jaldiShoppingResponse.setVendorDetails(vendorDetails);

            jaldiShoppingResponse.setProductDetails(productDetails);


        } else {

            jaldiShoppingResponse.setResponseCode(Constants.EMAILID_ALREADY_REGISTERED);
            jaldiShoppingResponse.setDescription(Constants.EMAILID_ALREADY_REGISTERED_MESSAGE);

        }

        return jaldiShoppingResponse;
    }


    private JaldiShoppingResponse prepareLoginResponse(Exception e, UserDetails userDetails, List<VendorDetails> vendorDetails, List<ProductDetails> productDetails, List<CartDetails> cartDetails) {

        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();


        if (e == null) {


            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);

            jaldiShoppingResponse.setVendorDetails(vendorDetails);

            jaldiShoppingResponse.setProductDetails(productDetails);

            jaldiShoppingResponse.setCartDetails(cartDetails);


        } else {


            jaldiShoppingResponse.setResponseCode(Constants.NOT_VALID_CREDENTIALS);
            jaldiShoppingResponse.setDescription(Constants.NOT_VALID_CREDENTIALS_MESSAGE);

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
            jaldiShoppingResponse.setResponseCode(Constants.NOT_VALID_PRODUCT_CODE);
            jaldiShoppingResponse.setDescription(Constants.NOT_VALID_PRODUCT_CODE_MESSAGE);

        }

        return jaldiShoppingResponse;
    }


    private JaldiShoppingResponse responseShoppingDetails(Exception e, List<VendorDetails> vendorDetails, List<ProductDetails> productDetails, List<CartDetails> cartDetails) {

        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();


        if (e == null) {


            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);

            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);

            jaldiShoppingResponse.setVendorDetails(vendorDetails);

            jaldiShoppingResponse.setProductDetails(productDetails);

            jaldiShoppingResponse.setCartDetails(cartDetails);


        } else {

            jaldiShoppingResponse.setResponseCode(exceptionMessageConvertor.getCode(e));
            jaldiShoppingResponse.setDescription(exceptionMessageConvertor.getMessage(e));

        }

        return jaldiShoppingResponse;
    }


    private JaldiShoppingResponse responseShoppingDetails1(Exception e, List<VendorDetails> vendorDetails, List<ProductDetails> productDetails) {
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


    private JaldiShoppingResponse prepareWishListResponse(Exception e) {

        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();


        if (e == null) {

            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);


        } else {

            jaldiShoppingResponse.setResponseCode(Constants.INVALID_PRODUCTID_OR_USERID);
            jaldiShoppingResponse.setDescription(Constants.INVALID_PRODUCTID_OR_USERID_MESSAGE);

        }

        return jaldiShoppingResponse;
    }

    private JaldiShoppingResponse prepareWishlistDetailsResponse(Exception e, List<WishlistDetails> wishlistDetails, List<ProductDetails> productDetails) {

        JaldiShoppingResponse jaldiShoppingResponse = new JaldiShoppingResponse();


        if (e == null) {

            jaldiShoppingResponse.setWishlistDetails(wishlistDetails);
            jaldiShoppingResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
            jaldiShoppingResponse.setDescription(Constants.SUCCESS_RESPONSE_MESSAGE);


        } else {

            jaldiShoppingResponse.setResponseCode(Constants.INVALID_USERID);
            jaldiShoppingResponse.setDescription(Constants.INVALID_USERID_MESSAGE);

        }

        return jaldiShoppingResponse;
    }

}