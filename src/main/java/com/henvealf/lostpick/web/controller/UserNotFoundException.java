package com.henvealf.lostpick.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="该用户不存在。")
public class UserNotFoundException extends RuntimeException{

}
