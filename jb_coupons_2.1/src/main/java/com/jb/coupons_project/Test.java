package com.jb.coupons_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jb.coupons_project.test.console_menu.MainMenu;
import com.jb.coupons_project.test.hardcoded.AdminServiceTest;
import com.jb.coupons_project.test.hardcoded.CompanyServiceTest;
import com.jb.coupons_project.test.hardcoded.CustomerServiceTest;

@Component
public class Test 
{	
	AdminServiceTest adminServiceTest;
	CompanyServiceTest companyServiceTest;
	CustomerServiceTest customerServiceTest;
	MainMenu mainMenu;
	
	@Autowired
	public Test(AdminServiceTest adminServiceTest,
				CompanyServiceTest companyServiceTest,
				CustomerServiceTest customerServiceTest,
				MainMenu mainMenu) 
	{
		this.adminServiceTest = adminServiceTest;
		this.companyServiceTest = companyServiceTest;
		this.customerServiceTest = customerServiceTest;
		this.mainMenu = mainMenu;
	}

	/**
	 * Start series of hard-coded tests to check all logical operations of all client facades.
	 * After hard-coded tests start interactive console menu for manual testing. 
	 */
	public void testAll()
	{
		try
		{	
			// hard-coded AdminFacade tests
			adminServiceTest.testAdminService();
			
			// hard-coded CompanyFacade tests
			companyServiceTest.testCompanyService();
			
			// hard-coded CustomerFacade tests
			customerServiceTest.testCustomerService();
			
			// all console menus
			System.out.println("\n\n*** Launching interactive menus:");
			mainMenu.loginMenu();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
//			e.printStackTrace(); // for debugging purposes
		}
	}
}
