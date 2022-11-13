package com.sapient.demo.creditcard.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sapient.demo.creditcard.model.CreditCardDAO;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCardDAO, Integer> {
}
