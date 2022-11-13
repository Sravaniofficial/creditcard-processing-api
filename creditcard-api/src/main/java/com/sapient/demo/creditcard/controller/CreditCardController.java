package com.sapient.demo.creditcard.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sapient.demo.creditcard.model.CreditCard;
import com.sapient.demo.creditcard.model.CreditCardDAO;
import com.sapient.demo.creditcard.service.CreditCardService;

@RestController
public class CreditCardController {

	@Autowired
	CreditCardService creditCardService;

	CreditCardDAO creditCardDAO;

	@GetMapping("/getall")
	ResponseEntity<List> getAllCreditCard() {
		List<CreditCardDAO> allCards = creditCardService.getAll();
		return ResponseEntity.ok().body(allCards);
	}

	@PostMapping(value = "/add", produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<CreditCardDAO> addCreditCard(@Valid @RequestBody CreditCard creditCard) {

		CreditCardDAO creditCardDAO = creditCardService.addCreditCard(creditCard);

		if (creditCardDAO == null)
			return ResponseEntity.notFound().build();
		else {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{add}")
					.buildAndExpand(creditCardDAO.getId()).toUri();
			return ResponseEntity.created(uri).body(creditCardDAO);
		}
	}

}
