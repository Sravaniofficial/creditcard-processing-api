package com.sapient.demo.creditcard.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Service;

@Service
public class LuhnValidator implements ConstraintValidator<LuhnValidation, String> {
	public boolean isValid(String cardNo, ConstraintValidatorContext cxt) {

		int length = cardNo.length();
		int nSum = 0;
		boolean isSecond = false;

		if (length > 19) {
			return false;
		}

		for (int i = length - 1; i >= 0; i--) {
			int d = cardNo.charAt(i) - '0';
			if (isSecond == true)
				d = d * 2;

			nSum += d / 10;
			nSum += d % 10;

			isSecond = !isSecond;
		}
		return (nSum % 10 == 0);

	}

	@Override
	public void initialize(LuhnValidation constraintAnnotation) {

	}
}
