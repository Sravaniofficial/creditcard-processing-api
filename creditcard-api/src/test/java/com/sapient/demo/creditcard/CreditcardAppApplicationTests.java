package com.sapient.demo.creditcard;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sapient.demo.creditcard.util.LuhnValidator;

@SpringBootTest
class CreditcardAppApplicationTests {

	@Autowired
	LuhnValidator luhnValidator;

	@Mock
	ConstraintValidatorContext ctx;

	@Test
	void testLuhnValidator_invalidCard() {
		boolean result = luhnValidator.isValid("1234", ctx);
		assertThat(result, is(false));

	}

	@Test
	void testLuhnValidator_validCard() {
		boolean result = luhnValidator.isValid("79927398713", ctx);
		assertThat(result, is(true));

	}

}
