package com.jb.coupons_project.test.hardcoded;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jb.coupons_project.Category;
import com.jb.coupons_project.ClientType;
import com.jb.coupons_project.entity.Coupon;
import com.jb.coupons_project.login_manager.LoginManager;
import com.jb.coupons_project.service.ClientFacade;
import com.jb.coupons_project.service.CompanyService;
import com.jb.coupons_project.service.CustomerService;
import com.jb.coupons_project.utils.custom_exceptions.DBOperationException;

@Component
public class CustomerServiceTestImpl implements CustomerServiceTest {
	
	private LoginManager loginManager;
	
	/**
	 * Constructor
	 * @param loginManager
	 */
	@Autowired
	public CustomerServiceTestImpl(LoginManager loginManager) {
		this.loginManager = loginManager;
	}
	
	
	/**
	 * Run tests of all operations of customerService.
	 * @throws DBOperationException in case of database related error
	 */
	@Override
	public void testCustomerService() throws DBOperationException 
	{
		System.out.println("-----------------------------------");
		System.out.println("\n*** \nTesting customer facade:");
		String email = "k@gmail.com";
		String password = "java123";
		ClientType clientType = ClientType.CUSTOMER;
		
		// customer login test
		ClientFacade clientFacade = loginManager.login(email, password, clientType);
		CustomerService customerService = (CustomerService) clientFacade;
		if(customerService == null)
		{
			System.out.println(loginManager.getClientMsg());
			return;
		}
		System.out.println(customerService.getClientMsg());
		
		// company login - used for retrieving coupons for testing
		String companyEmail = "sales@ksp.co.il";
		String companyPassword = "ksp";
		clientType = ClientType.COMPANY;
		clientFacade = loginManager.login(companyEmail, companyPassword, clientType);
		CompanyService companyService = (CompanyService) clientFacade;
//		List<Coupon> coupons = companyService.getCompanyCoupons();
//		if(coupons == null || coupons.size()<4)
//		{
//			System.out.println("Unable to test client facade because of lack of coupons for purchase.");
//			return;
//		}
		
		// purchaseCoupon test: should pass: should pass
		System.out.println("*** \nPurchase coupon test: should pass");
		customerService.purchaseCoupon(companyService.getCompanyCoupons().get(0));
		System.out.println(customerService.getClientMsg());
		
		// purchaseCoupon test: should pass
		System.out.println("*** \nPurchase coupon test: should pass");
		customerService.purchaseCoupon(companyService.getCompanyCoupons().get(1));
		System.out.println(customerService.getClientMsg());
		
		// purchaseCoupon test
		System.out.println("*** \nPurchase coupon test: should pass");
		customerService.purchaseCoupon(companyService.getCompanyCoupons().get(3));
		System.out.println(customerService.getClientMsg());
		
		// purchaseCoupon test: should fail due to attempt to purchase coupon that was already purchased
		System.out.println("*** \nPurchase coupon test: should fail "
				+ "due to attempt to purchase coupon that was already purchased");
		customerService.purchaseCoupon(companyService.getCompanyCoupons().get(0));
		System.out.println(customerService.getClientMsg());
		
		// purchaseCoupon test: should fail because coupon is not available
		System.out.println("*** \nPurchase coupon test: should fail "
				+ "because coupon is not available");
		customerService.purchaseCoupon(companyService.getCompanyCoupons().get(2));
		System.out.println(customerService.getClientMsg());
		
		// getCustomerCoupons test: should retrieve 3 coupons
		System.out.println("*** \nGet all coupons of this customer test: should retrieve 3 coupons");
		List<Coupon> customerCoupons = customerService.getCustomerCoupons();
		System.out.println(customerService.getClientMsg());
		if(customerCoupons != null)
			for(Coupon currCoupon : customerCoupons)
				System.out.println(currCoupon);
		else
			System.out.println(customerService.getClientMsg());
		
		// getCustomerCoupons by category test: should retrieve 1 coupon
		System.out.println("*** \nGet all customer coupons by category test: should retrieve 1 coupon");
		List<Coupon> customerCoupons2 = customerService.getCustomerCoupons(Category.BEAUTY);
		System.out.println(customerService.getClientMsg());
		if(customerCoupons2 != null)
			for(Coupon currCoupon : customerCoupons2)
				System.out.println(currCoupon);
		else
			System.out.println(customerService.getClientMsg());
		
		// getCustomerCoupons by max price test: should retrieve 1 coupon
		System.out.println("*** \nGet all customer coupons under certain price test: "
				+ "should retrieve 1 coupon");
		List<Coupon> customerCoupons3 = customerService.getCustomerCoupons(45.0);
		System.out.println(customerService.getClientMsg());
		if(customerCoupons3 != null)
			for(Coupon currCoupon : customerCoupons3)
				System.out.println(currCoupon);
		else
			System.out.println(customerService.getClientMsg());
		
		// getCustomerDetails test
		System.out.println("*** \nget customer details test: should pass");
		System.out.println(customerService.getCustomerDetails() != null ? customerService.getCustomerDetails() : customerService.getClientMsg());
		
		// delete coupon purchase test
		System.out.println("*** \ndelete coupon purchase test: should pass.");
		customerService.deletePurchase(companyService.getCompanyCoupons().get(0));
		System.out.println(customerService.getClientMsg());
		
		// delete coupon purchase test
		System.out.println("*** \ndelete coupon purchase test: should pass.");
		customerService.deletePurchase(companyService.getCompanyCoupons().get(1));
		System.out.println(customerService.getClientMsg());
		
		// getCustomerCoupons test
		System.out.println("*** \nGet all coupons of this customer test");
		customerCoupons = customerService.getCustomerCoupons();
		System.out.println(customerService.getClientMsg());
		if(customerCoupons != null)
			for(Coupon currCoupon : customerCoupons)
				System.out.println(currCoupon);
			else
				System.out.println(customerService.getClientMsg());
		
		System.out.println("*** \nCustomer facade test completed");
		System.out.println("-----------------------------------");
	}
}
