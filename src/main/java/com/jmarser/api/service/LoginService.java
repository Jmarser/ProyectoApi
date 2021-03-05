package com.jmarser.api.service;

import com.jmarser.api.entity.Login;

public interface LoginService extends BaseService<Login>{

	public Login checkLogin(Login login);
	
}
