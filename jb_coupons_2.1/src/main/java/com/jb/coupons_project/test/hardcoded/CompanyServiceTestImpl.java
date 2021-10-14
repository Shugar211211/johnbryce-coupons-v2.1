package com.jb.coupons_project.test.hardcoded;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jb.coupons_project.Category;
import com.jb.coupons_project.ClientType;
import com.jb.coupons_project.entity.Coupon;
import com.jb.coupons_project.login_manager.LoginManager;
import com.jb.coupons_project.service.ClientFacade;
import com.jb.coupons_project.service.CompanyService;
import com.jb.coupons_project.utils.custom_exceptions.DBOperationException;

@Component
public class CompanyServiceTestImpl implements CompanyServiceTest {

	private LoginManager loginManager;
	
	/**
	 * Constructor
	 * @param loginManager
	 */
	@Autowired
	public CompanyServiceTestImpl(LoginManager loginManager) {
		this.loginManager = loginManager;
	}
	
	/**
	 * Run tests of all operations of companyFacade.
	 * @throws DBOperationException in case of database related error
	 */
	@Override
	public void testCompanyService() throws DBOperationException 
	{
		System.out.println("-----------------------------------");
		System.out.println("\n*** Testing company facade:");
		String email = "sales@ksp.co.il";
		String password = "ksp";
		ClientType clientType = ClientType.COMPANY;
		
		// company login test
//		LoginManagerImpl loginManager = LoginManagerImpl.getInstance();
		ClientFacade clientFacade = loginManager.login(email, password, clientType);
		CompanyService companyService = (CompanyService) clientFacade;
		if(companyService == null)
		{
			System.out.println(loginManager.getClientMsg());
			return;
		}
		System.out.println(companyService.getClientMsg());
		
		Date startDate = Date.valueOf("2020-09-01");
		Date endDate = Date.valueOf("2020-12-01");
		
		// add coupon 1 test: should pass
		System.out.println("*** \nAdd coupon test: should pass.");
		Coupon coupon1 = new Coupon(companyService.getCompanyDetails(), 
									Category.FOOD, 
									"HP all-in-one", "HP ENVY 32-A0000NJ / 9CT26EA - All-in-One computer",
									startDate, 
									endDate,
									0, 
									2999.0,
									"https://img.ksp.co.il/item/99245/b_1.jpg?noCash");
		companyService.addCoupon(coupon1);
		System.out.println(companyService.getClientMsg());
		
		// add coupon 2 test: should pass
		System.out.println("***\n Add coupon test: should pass.");
		Coupon coupon2 = new Coupon(companyService.getCompanyDetails(),
									Category.COMPUTERS, 
									"KSP Special offer Skullcandy 40% discount.", 
									"40% discount on all Skullcandy products",
									Date.valueOf("2020-04-01"),
									Date.valueOf("2021-04-01"),
									80,
									19.99,
									"https://external-content.duckduckgo.com/iu/?u="
											+ "https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP."
											+ "OzDTZghgVopBI-r5ln4UwQHaEr%26pid%3DApi&f=1");
		companyService.addCoupon(coupon2);
		System.out.println(companyService.getClientMsg());
		
		// add coupon 3 test: should pass
		System.out.println("*** \nAdd coupon test: should pass.");
		Coupon coupon3 = new Coupon(companyService.getCompanyDetails(),
									Category.COMPUTERS, 
									"SAMSUNG galaxy S20 Ultra.", 
									"SAMSUNG galaxy S20 Ultra sale.",
									Date.valueOf("2020-08-01"),
									Date.valueOf("2020-11-01"),
									100,
									799.0,
									"https://external-content.duckduckgo.com/iu/?u=https"
											+ "%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.izseQry"
											+ "IJPOWeIVe5Ibp4AHaEK%26pid%3DApi&f=1");
		companyService.addCoupon(coupon3);
		System.out.println(companyService.getClientMsg());
		
		// add coupon 4 test: should pass
		// this coupon should be unavailable
		System.out.println("*** \nAdd coupon test: should pass.");
		startDate = Date.valueOf("2020-03-01");
		endDate = Date.valueOf("2020-12-01");
		Coupon coupon4 = new Coupon(companyService.getCompanyDetails(), 
									Category.COMPUTERS, 
									"Lenovo discount 20%", 
									"20% discount on all Lenovo products",
									Date.valueOf("2020-08-01"),
									Date.valueOf("2020-10-01"),
									0,			
									29.99,
									"");
		companyService.addCoupon(coupon4);
		System.out.println(companyService.getClientMsg());
		
		// add coupon 5 test: should pass
		System.out.println("*** \nAdd coupon test: should pass");
		startDate = Date.valueOf("2020-06-01");
		endDate = Date.valueOf("2020-12-31"); 
		Coupon coupon5 = new Coupon(companyService.getCompanyDetails(),  
									Category.BEAUTY, 
									"DG Light Blue", 
									"Dolce and Gabana Light Blue Intense",
									startDate, 
									endDate,
									40, 
									199,
									"");
		companyService.addCoupon(coupon5);
		System.out.println(companyService.getClientMsg());
		
		// update coupon 1 test: should pass
		System.out.println("*** \nUpdate coupon test: should pass.");
		startDate = Date.valueOf("2020-08-01");
		endDate = Date.valueOf("2020-10-01");
		coupon1 = new Coupon(1, 	// coupon id
							 companyService.getCompanyDetails(), 
							 Category.COMPUTERS, 
							 "HP all-in-one", "HP ENVY 32-A0000NJ / 9CT26EA - All-in-One computer",
							 startDate, 
							 endDate,
							 10, 
							 2999.0,
							 "");
		companyService.updateCoupon(coupon1);
		System.out.println(companyService.getClientMsg());
		
		// add coupon 6 test: should fail due to expired endDate
		System.out.println("*** \nAdd coupon test: should fail due to expired endDate.");
		startDate = Date.valueOf("2020-03-01");
		endDate = Date.valueOf("2020-09-01");
		Coupon coupon6 = new Coupon(companyService.getCompanyDetails(), 
									Category.COMPUTERS, 
									"Asus monitor", "Asus MG278Q 27 LED gaming monitor",
									startDate, 
									endDate,
									50, 
									1999.0,
									"https://img.ksp.co.il/item/31845/b_1.jpg?noCash");
		companyService.addCoupon(coupon6);
		System.out.println(companyService.getClientMsg());
		
//		// deleteCoupon test: should fail because of wrong coupon id
//		System.out.println("*** Delete coupon test: should fail due "
//				+ "to attempt to delete non-existent coupon");
//		companyService.deleteCoupon(515);
//		System.out.println(companyService.getClientMsg());
		
		// test getCompanyCoupons: should retrieve 5 coupons
		System.out.println("*** \nTest of retieving all company coupons: should return 5 coupons");
		List<Coupon> companyCoupons = companyService.getCompanyCoupons();
		System.out.println(companyService.getClientMsg());
		if(companyCoupons != null)
			for(Coupon currCoupon : companyCoupons)
				System.out.println(currCoupon);
		else
			System.out.println(companyService.getClientMsg());
		
		// test getCompanyCouponsByCategory: should return 4 coupons
		System.out.println("*** \nTest of retieving all company coupons in certain category: "
				+ "should return 4 coupons");
		List<Coupon> companyCoupons2 = companyService.getCompanyCoupons(Category.COMPUTERS);
		System.out.println(companyService.getClientMsg());
		if(companyCoupons2 != null)
			for(Coupon currCoupon : companyCoupons2)
				System.out.println(currCoupon);
		else
			System.out.println(companyService.getClientMsg());
		
		// test getCompanyCoupons by max price: should return 2 coupons
		System.out.println("*** \nTest of retieving all company coupons under certain price: "
				+ "should return 2 coupons");
		List<Coupon> companyCoupons3 = companyService.getCompanyCoupons(50.0);
		System.out.println(companyService.getClientMsg());
		if(companyCoupons3 != null)
			for(Coupon currCoupon : companyCoupons3)
				System.out.println(currCoupon);
		else
			System.out.println(companyService.getClientMsg());
		
		// deleteCoupon test: should pass
		System.out.println("*** \nDelete coupon test: should pass");
		companyService.deleteCoupon(3);
		System.out.println(companyService.getClientMsg());
		
		// test getCompanyDetails
		System.out.println("*** \nTest of retrieving company details: should pass");
		System.out.println(companyService.getCompanyDetails() != null ? companyService.getCompanyDetails() : companyService.getClientMsg());
		System.out.println("*** \nCompany facade test completed");
		System.out.println("-----------------------------------");
	}

}
