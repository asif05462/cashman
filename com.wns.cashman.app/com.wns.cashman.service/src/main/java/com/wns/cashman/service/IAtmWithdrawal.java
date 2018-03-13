package com.wns.cashman.service;

import com.wns.cashman.util.RequestBean;
import com.wns.cashman.util.ResponseBean;

public interface IAtmWithdrawal {
	
	ResponseBean withdrawCash(int amount);
	
	//ResponseBean withdrawCash(RequestBean requestBean);

}
