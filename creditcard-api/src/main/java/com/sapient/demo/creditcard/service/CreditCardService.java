package com.sapient.demo.creditcard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import jakarta.validation.Valid;

import com.sapient.demo.creditcard.model.CreditCard;
import com.sapient.demo.creditcard.model.CreditCardDAO;

@Service
public class CreditCardService {

	@Autowired
	CreditCardRepository creditCardRepository;

	public CreditCardDAO addCreditCard(CreditCard creditCard) {
		CreditCardDAO creditCardDAO = new CreditCardDAO();
		creditCardDAO.setName(creditCard.getName());
		creditCardDAO.setCardNumber(creditCard.getCardNumber());
		creditCardDAO.setLimit(creditCard.getLimit());
		creditCardDAO.setBalance(0D);

		CreditCardDAO result = creditCardRepository.save(creditCardDAO);

		return result;
	}

	public List<CreditCardDAO> getAll() {
		return (List<CreditCardDAO>) creditCardRepository.findAll();
	}

}
