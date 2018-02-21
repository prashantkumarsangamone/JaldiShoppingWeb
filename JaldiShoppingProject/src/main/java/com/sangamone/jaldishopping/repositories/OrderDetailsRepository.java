package com.sangamone.jaldishopping.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sangamone.jaldishopping.domain.OrderDetails;


public interface OrderDetailsRepository extends CrudRepository< OrderDetails, String> {


}
