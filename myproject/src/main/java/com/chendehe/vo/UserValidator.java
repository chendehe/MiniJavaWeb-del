package com.chendehe.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);

  @Override
  public boolean supports(Class<?> clazz) {
    return UserVo.class.equals(clazz);
  }

  @Override
  public void validate(Object obj, Errors e) {
    ValidationUtils.rejectIfEmpty(e, "address", "address.empty");
    LOGGER.info("===>{}", e.getFieldError().getField());
    LOGGER.info("===>{}", e.getFieldError().getCode());
  }
}
