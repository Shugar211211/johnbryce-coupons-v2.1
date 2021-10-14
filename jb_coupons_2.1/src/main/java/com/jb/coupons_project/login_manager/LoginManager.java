package com.jb.coupons_project.login_manager;

import com.jb.coupons_project.ClientType;
import com.jb.coupons_project.service.ClientFacade;
import com.jb.coupons_project.utils.custom_exceptions.DBOperationException;

public interface LoginManager {
	public String getClientMsg();
	public ClientFacade login(String email, String password, ClientType clientType) throws DBOperationException;
	public void logout(ClientFacade clientFacade);
}