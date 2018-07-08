package com.sangamone.jaldishopping.services;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

public interface AdminService {



	UserDetails validateUser(String userEmail) throws EmailIdAlreadyExistException;

	void addUsers(String firstName, String lastName, String userEmail, String userMobile,
			String zipCode);

	UserDetails validateLogin(String userEmail, String userPassword);

	ProductDetails addProductDetails(String productId, Long categoryId, Long vendorId, Long locationId);

	List<ProductDetails> findByProductId(String productId) throws JaldiShoppingBaseException;

	ProductDetails addProductDetails(String productId);

	List<ProductDetails> findByBarCode(String barCode) throws JaldiShoppingBaseException;

	ProductDetails addProductDetails1(String barCode);

	void addfbUsers(String firstName, String lastName, String userEmail, String userMobile, String zipCode);

	CartDetails addMyCartDetails(String userId, String productId,String numberOfItems, String amount);

	List<CartDetails> getCartDetails(String userId);

	OrderDetails addMyOrderDetails(String orderAmount, String orderDate, String quantity, String userId,
			String productId, String paymentDetailsId);

	List<OrderDetails> getOrderDetails(String userId);

	List<BeaconLocations> findByAppId(String appId);

	BeaconLocations addBeaconLocations(String appId, String appToken);

	List<BeaconLocations> findByBeaconLocations();

	UserDetails getUser(String userEmail);

	CartDetails validateCartDetails(String userId, String productId);
	
	void DeleteCartDetails(CartDetails cartDetails, String deleteFlag);

	void updateQuantity(CartDetails cartDetails1, String userId, String productId, String productQuantity);

	WishlistDetails addWishListDetails(String userId, String productId);

	List<WishlistDetails> getWishListDetails(String userId);

	WishlistDetails validateWishListDetails(String userId, String productId);

	void DeleteWishListDetails(WishlistDetails wishlistDetails, String deleteFlag);

	ProductDetails getProductAmount(String productId);

	List<CartDetails> getBillingDetails(String userId);

	TransactionDetails addMyTransactionDetails(String userId, List<CartDetails> cartDetails);





	

	







	
}
