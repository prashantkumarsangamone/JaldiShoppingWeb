package com.sangamone.jaldishopping.service.impl;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sangamone.jaldishopping.controller.Beacons;
import com.sangamone.jaldishopping.controller.Estimote;
import com.sangamone.jaldishopping.controller.Items;
import com.sangamone.jaldishopping.controller.Position;
import com.sangamone.jaldishopping.controller.Response;
import com.sangamone.jaldishopping.domain.AuthorityDetails;
import com.sangamone.jaldishopping.domain.BeaconLocations;
import com.sangamone.jaldishopping.domain.CartDetails;
import com.sangamone.jaldishopping.domain.MyListDetails;
import com.sangamone.jaldishopping.domain.OrderDetails;
import com.sangamone.jaldishopping.domain.ProductDetails;
import com.sangamone.jaldishopping.domain.TransactionDetails;
import com.sangamone.jaldishopping.domain.UserDetails;
import com.sangamone.jaldishopping.domain.WishlistDetails;
import com.sangamone.jaldishopping.exception.EmailIdAlreadyExistException;
import com.sangamone.jaldishopping.exception.JaldiShoppingBaseException;
import com.sangamone.jaldishopping.repositories.AuthorityDetailsRepository;
import com.sangamone.jaldishopping.repositories.BeaconLocationsRepository;
import com.sangamone.jaldishopping.repositories.CartDetailsRepository;
import com.sangamone.jaldishopping.repositories.CategoryDetailsRepository;
import com.sangamone.jaldishopping.repositories.LocationDetailsRepository;
import com.sangamone.jaldishopping.repositories.MyListDetailsRepository;
import com.sangamone.jaldishopping.repositories.OrderDetailsRepository;
import com.sangamone.jaldishopping.repositories.ProductDetailsRepository;
import com.sangamone.jaldishopping.repositories.TransactionDetailsRepository;
import com.sangamone.jaldishopping.repositories.UserDetailsRepository;
import com.sangamone.jaldishopping.repositories.VendorDetailsRepository;
import com.sangamone.jaldishopping.repositories.WishlistDetailsRepository;
import com.sangamone.jaldishopping.services.AdminService;
import com.sangamone.jaldishopping.services.EstimoteAPIRequestSender;
import com.sangamone.jaldishopping.services.MessageSender;
import com.sangamone.jaldishopping.services.WalmartAPIRequestSender;
import com.sangamone.jaldishopping.utils.RandomString;
import com.sangamone.jaldishopping.utils.ShaUtils;



@Component("adminService")

public class AdminServiceImpl implements AdminService{

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	
	@Autowired
	private CategoryDetailsRepository categoryDetailsRepository;
	
	@Autowired
	private WishlistDetailsRepository wishlistDetailsRepository;
	
	
	@Autowired
	private VendorDetailsRepository vendorDetailsRepository;
	
	@Autowired
	private LocationDetailsRepository locationDetailsRepository;
	
	@Autowired
	private MyListDetailsRepository myListDetailsRepository;
	
	@Autowired
	private TransactionDetailsRepository transactionDetailsRepository;
	

	@Autowired
	private MessageSender messageSender;
	
	@Autowired
	private WalmartAPIRequestSender walmartAPIRequestSender;
	
	@Autowired
	private EstimoteAPIRequestSender estimoteAPIRequestSender;
	
	@Autowired
	private AuthorityDetailsRepository authorityDetailsRepository;
	
	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	
	@Autowired
	private CartDetailsRepository cartDetailsRepository;
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	private BeaconLocationsRepository beaconLocationsRepository;
	
	
	@Override
	public UserDetails validateUser(String userEmail) throws EmailIdAlreadyExistException {
		// TODO Auto-generated method stub
				return userDetailsRepository.findByUserEmail(userEmail);
			
	}

	@Override
	public void addUsers(String firstName, String lastName, String userEmail, String userMobile, String zipCode) {
	
			// TODO Auto-generated method stub

			
			String enabled="1";
			UserDetails userDetails=new UserDetails();
			userDetails.setFirstName(firstName);
			userDetails.setLastName(lastName);
			userDetails.setUserEmail(userEmail);
			userDetails.setUserMobile(userMobile);
			String password = RandomString.generateRandomString();
			userDetails.setUserPassword(ShaUtils.getHash(password));
			userDetails.setZipCode(Long.parseLong(zipCode));
			userDetails.setEnabled(Long.parseLong(enabled));
			userDetailsRepository.save(userDetails);

			AuthorityDetails authorityDetails=new AuthorityDetails();
			authorityDetails.setUserEmail(userEmail);;
			authorityDetails.setAuthority("ROLE_ADMIN");
			authorityDetailsRepository.save(authorityDetails);
			String message = ":" + password + " \n - " ;

			try {
				messageSender.sendMessage(Long.parseLong(userMobile), userEmail,  message);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 
			}
	}
	
	@Override
	public UserDetails validateLogin(String userEmail, String userPassword) {
		// TODO Auto-generated method stub
				UserDetails userDetails = userDetailsRepository.findByUserEmailAndUserPassword(userEmail,
						ShaUtils.getHash(userPassword));
				return userDetails;
	}

	@Override
	public ProductDetails addProductDetails(String productId, Long categoryId, Long vendorId, Long locationId) {
		// TODO Auto-generated method stub
		int initialvalue= 0;
		
		
		String initialstring="unknown"; 
		
		ProductDetails productDetails = new ProductDetails();
		productDetails.setProductId(productId);
		productDetails.setProductName(initialstring);
		productDetails.setProductCode(initialstring);
		productDetails.setBarCode(initialstring);
		productDetails.setProductPrice(initialstring);
		productDetails.setProductQuantity(initialstring);
		productDetails.setProductType(initialstring);
		productDetails.setProductReview(initialstring);	
		productDetails.setProductRatings(initialstring);
		productDetails.setProductDiscount(initialstring);
		productDetails.setProductFinalPrice(initialstring);
		productDetails.setCurrency(initialstring);
		productDetails.setProductMoreInfo(initialstring);
		productDetails.setProductSize(initialstring);
		
		productDetails.setCategoryDetails(categoryDetailsRepository.findByCategoryId(Long.valueOf(categoryId)));
		productDetails.setVendorDetails(vendorDetailsRepository.findByVendorId(Long.valueOf(vendorId)));
		productDetails.setLocationDetails(locationDetailsRepository.findByLocationId(Long.valueOf(locationId)));
		productDetails.setProductImagePath1(initialstring);
	
		
		 Response response = walmartAPIRequestSender.sendRequest(productDetails); 
		
		productDetailsRepository.save(productDetails);

		productDetails.setProductName(response.item.get(0).getName());
		productDetails.setProductPrice(response.item.get(0).getSalePrice());
		
		
		
		productDetailsRepository.save(productDetails);
		
		
	 return productDetails;
	}

	@Override
	public List<ProductDetails> findByProductId(String productId) throws JaldiShoppingBaseException {
		// TODO Auto-generated method stub
		return (List<ProductDetails>) productDetailsRepository.findByProductId(productId);
	}

	@Override
	public ProductDetails addProductDetails(String productId) {
		 String initialstring="null"; 
		 
			long initialvalue = 1;
			
				
			 Response response = walmartAPIRequestSender.sendRequest1(productId);
			 System.out.println(response);
			ProductDetails productDetails1 = new ProductDetails();
			   
			    productDetails1.setProductId(response.item.get(0).getParentItemId());
			    productDetails1.setProductName(response.item.get(0).getName());
				productDetails1.setProductCode(initialstring);
				productDetails1.setBarCode(response.item.get(0).getItemId());
				productDetails1.setProductPrice(response.item.get(0).getSalePrice());
				productDetails1.setProductQuantity(initialstring);
				productDetails1.setProductType(response.item.get(0).getCategoryPath());
				productDetails1.setProductReview(response.item.get(0).getNumReviews());
				productDetails1.setProductRatings(initialstring);
				productDetails1.setProductDiscount(initialstring);
				productDetails1.setProductFinalPrice(initialstring);
				productDetails1.setCurrency(initialstring);
				productDetails1.setProductMoreInfo(initialstring);
				productDetails1.setProductSize(initialstring);
				productDetails1.setProductImagePath1(initialstring);
				productDetails1.setCategoryId(initialvalue);
				productDetails1.setLocationId(initialvalue);
				productDetails1.setVendorId(initialvalue);
				
				productDetailsRepository.save(productDetails1);
				return productDetails1;
	}

	@Override
	public List<ProductDetails> findByBarCode(String barCode) throws JaldiShoppingBaseException {
		// TODO Auto-generated method stub
		return (List<ProductDetails>) productDetailsRepository.findByBarCode(barCode);
	}

	@Override
	public ProductDetails addProductDetails1(String barCode) {
		String initialstring="null"; 
		 
		long initialvalue = 1; 
		 List<Items> items = walmartAPIRequestSender.sendRequest2(barCode);
		
		 System.out.println(items);
		 for(int i=0;i<=items.size();i++){
			 System.out.println("FreeShip"+items.get(0).getFreeShipToStore());
		 }
		 
		ProductDetails productDetails1 = new ProductDetails();
		    productDetails1.setProductId(items.get(0).getItems().get(0).getItemId());
		    System.out.println("Product Id"+items.get(0).getItems().get(0).getItemId());
		    productDetails1.setProductName(items.get(0).getItems().get(0).getName());
		    System.out.println("Product Id"+items.get(0).getItems().get(0).getName());
			productDetails1.setProductCode(initialstring);
			productDetails1.setBarCode(items.get(0).getItems().get(0).getUpc());
			productDetails1.setProductPrice(items.get(0).getItems().get(0).getSalePrice());
			productDetails1.setProductQuantity(initialstring);
			productDetails1.setProductType(items.get(0).getItems().get(0).getCategoryPath());
			productDetails1.setProductReview(items.get(0).getItems().get(0).getNumReviews());
			productDetails1.setProductImagePath1(initialstring);
			productDetails1.setCategoryId(initialvalue);
			productDetails1.setLocationId(initialvalue);
			productDetails1.setVendorId(initialvalue);
			
			productDetailsRepository.save(productDetails1);
			return productDetails1;
	}



	@Override
	public void addfbUsers(String firstName, String lastName, String userEmail, String userMobile, String zipCode) {

		String lastname1="NULL";
		String enabled="1";
		UserDetails userDetails=new UserDetails();
		userDetails.setFirstName(firstName);
		userDetails.setLastName(lastname1);
		userDetails.setUserEmail(userEmail);
		userDetails.setUserMobile(userMobile);
		String password = RandomString.generateRandomString();
		userDetails.setUserPassword(ShaUtils.getHash(password));
		userDetails.setZipCode(Long.parseLong(enabled));
		userDetails.setEnabled(Long.parseLong(enabled));
		userDetailsRepository.save(userDetails);

		AuthorityDetails authorityDetails=new AuthorityDetails();
		authorityDetails.setUserEmail(userEmail);;
		authorityDetails.setAuthority("ROLE_ADMIN");
		authorityDetailsRepository.save(authorityDetails);
		String message = ":" + password + " \n - " ;

		try {
			messageSender.sendMessage(Long.parseLong(userMobile), userEmail,  message);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
	
	
	
	
	@Override
	public CartDetails addMyCartDetails(String userId, String productId, String numberOfItems, String amount) {
		String flag="Y";  
		
		Double total= (Double.valueOf(numberOfItems))*(Double.valueOf(amount));
		
		CartDetails cartDetails = new CartDetails();
		//cartDetails.setCartDetailsId(Long.valueOf(initialvalue));
		cartDetails.setUserId(Long.valueOf(userId));
		cartDetails.setProductId(productId);
		cartDetails.setNumberOfItems(numberOfItems);
		cartDetails.setActualAmount(amount);
		cartDetails.setTotalAmount(String.valueOf(total));
		cartDetails.setDeleteFlag(flag);
		cartDetailsRepository.save(cartDetails);
		return cartDetails;
	}




	@Override
	public List<CartDetails> getCartDetails(String userId) 
	{
		System.out.println("UserId @ Impl"+userId);
		return cartDetailsRepository.getByUserId(Long.valueOf(userId));
	}
	

	@Override
	public OrderDetails addMyOrderDetails(String orderAmount, String orderDate, String quantity, String userId,
			String productId, String paymentDetailsId) {
		String initialvalue = "2"; 
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderAmount(orderAmount);
		orderDetails.setOrderDate(new Timestamp(System.currentTimeMillis()));
		orderDetails.setQuantity(quantity);
		orderDetails.setPaymentDetailsId(Long.valueOf(initialvalue));
		orderDetails.setUserId(Long.valueOf(initialvalue));
		orderDetails.setProductId(initialvalue);
		
		orderDetailsRepository.save(orderDetails);
		return orderDetails;
	}

	@Override
	public List<OrderDetails> getOrderDetails(String userId) {
		// TODO Auto-generated method stub
		return orderDetailsRepository.getByUserId(Long.valueOf(userId));
	}

	@Override
	public List<BeaconLocations> findByAppId(String appId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeaconLocations addBeaconLocations(String appId, String appToken) {
		// TODO Auto-generated method stub
		String initialstring="1"; 
		 
		long initialvalue = 1; 
		
		String initialvalue1 ="12417833";
		
		 List<Estimote> estimote = estimoteAPIRequestSender.sendRequest3(appId,appToken);
	
		 BeaconLocations beaconLocations = new BeaconLocations();
	        
	        for(int i=0;i<estimote.size();i++){
			    beaconLocations.setIdentifier(estimote.get(i).getIdentifier());
			    /*System.out.println("Identifier:"+estimote.get(i).getIdentifier());*/
			    for(Beacons beacons : estimote.get(i).getBeacons()) {
			    	
			    beaconLocations.setMac(beacons.getBeacon().getMac());
			    beaconLocations.setColor(beacons.getBeacon().getColor());
			    /*System.out.println("Color:"+beacons.getBeacon().getColor());*/
			    beaconLocations.setUuid(beacons.getBeacon().getUuid());
			    beaconLocations.setMajor(beacons.getBeacon().getMajor());
			    beaconLocations.setMinor(beacons.getBeacon().getMinor());
			    beaconLocationsRepository.save(beaconLocations);
			    }
			    for(Beacons position : estimote.get(i).getBeacons()) {
			    beaconLocations.setX(position.getPosition().getX());
			    /*System.out.println("X:"+position.getPosition().getX());*/
			    beaconLocations.setY(position.getPosition().getY());
			    beaconLocations.setOrientation(position.getPosition().getOrientation());
			    beaconLocationsRepository.save(beaconLocations);
			    }
			    beaconLocationsRepository.save(beaconLocations);
			    }
	       
	        return beaconLocations;
	        
	}

	@Override
	public List<BeaconLocations> findByBeaconLocations() {
		// TODO Auto-generated method stub
		return (List<BeaconLocations>) beaconLocationsRepository.findAll();
	}

	@Override
	public UserDetails getUser(String userEmail) {
		// TODO Auto-generated method stub
		return userDetailsRepository.findByUserEmail(userEmail);
	}

	@Override
	public CartDetails validateCartDetails(String userId, String productId) {
		// TODO Auto-generated method stub
		return cartDetailsRepository.validateDuplicateItem(Long.valueOf(userId),Long.valueOf(productId));
	}

	@Override
	public void DeleteCartDetails(CartDetails cartDetails, String deleteFlag) {
		cartDetails.setDeleteFlag(deleteFlag);
		cartDetailsRepository.save(cartDetails);
	}


	@Override
	public void updateQuantity(CartDetails cartDetails1, String userId, String productId, String numberOfItems) {
		cartDetails1.setNumberOfItems(numberOfItems);
		cartDetailsRepository.save(cartDetails1);
		
	}

	@Override
	public WishlistDetails addWishListDetails(String userId, String productId) {
		// TODO Auto-generated method stub
		//long initialvalue = 2; 
		String flag="Y";
		WishlistDetails wishlistDetails = new WishlistDetails();
		//wishlistDetails.setWishlistDetailsId(initialvalue);
		wishlistDetails.setUserId(Long.valueOf(userId));
		wishlistDetails.setProductId(productId);
		wishlistDetails.setDeleteFlag(flag);
		wishlistDetailsRepository.save(wishlistDetails);
		return wishlistDetails;	
	}

	@Override
	public List<WishlistDetails> getWishListDetails(String userId) {
		// TODO Auto-generated method stub
		return wishlistDetailsRepository.getByUserId(Long.valueOf(userId));
	}

	@Override
	public WishlistDetails validateWishListDetails(String userId, String productId) {
		// TODO Auto-generated method stub
		return wishlistDetailsRepository.validateDuplicateItem(Long.valueOf(userId),Long.valueOf(productId));
	}

	@Override
	public void DeleteWishListDetails(WishlistDetails wishlistDetails, String deleteFlag) {
		wishlistDetails.setDeleteFlag(deleteFlag);
		wishlistDetailsRepository.save(wishlistDetails);
		
	}

	@Override
	public ProductDetails getProductAmount(String productId) {
		// TODO Auto-generated method stub
		System.out.println("Product Id"+productId);
		return productDetailsRepository.getProductAmount(Long.valueOf(productId));
		
	}


	@Override
	public List<CartDetails> getBillingDetails(String userId) {
		
		System.out.println("UserId @ Impl"+userId);
		return cartDetailsRepository.getByUserId(Long.valueOf(userId));
	}

	@Override
	public TransactionDetails addMyTransactionDetails(String userId, List<CartDetails> cartDetails) {
		// TODO Auto-generated method stub
		
		TransactionDetails transactionDetails = new TransactionDetails();
		
		/*transactionDetails.setTransactionAmount(userId);
		transactionDetails.setTransactionDate();
		transactionDetails.setQuantity();
		transactionDetails.setqrCode();
		transactionDetails.setUserId(userId);
		transactionDetails.setProductId();
		transactionDetails.setPaymentDetailsId();*/
		
		transactionDetailsRepository.save(transactionDetails);
		return transactionDetails;	
	}

	
	

	


}
