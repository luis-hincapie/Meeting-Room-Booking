package com.javainterns.bookingroom.controller;

import java.security.Principal;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

  @GetMapping("/username")
  @ResponseBody
  public String currentUserName(@NotNull Principal principal) {
    return principal.getName();
  }
}