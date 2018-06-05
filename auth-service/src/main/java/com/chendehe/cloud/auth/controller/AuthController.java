package com.chendehe.cloud.auth.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {

  @GetMapping(value = "/current")
  public Principal getUser(Principal principal) {
    return principal;
  }
}