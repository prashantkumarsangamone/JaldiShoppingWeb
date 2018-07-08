package com.sangamone.jaldishopping.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sangamone.jaldishopping.domain.OrderDetails;


public interface OrderDetailsRepository extends CrudRepository< OrderDetails, String> {

	List<OrderDetails> getByUserId(Long userId);


}
