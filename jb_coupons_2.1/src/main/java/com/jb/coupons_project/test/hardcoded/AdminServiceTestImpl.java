package com.jb.coupons_project.test.hardcoded;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jb.coupons_project.ClientType;
import com.jb.coupons_project.entity.Company;
import com.jb.coupons_project.entity.Customer;
import com.jb.coupons_project.login_manager.LoginManager;
import com.jb.coupons_project.service.AdminService;
import com.jb.coupons_project.service.ClientFacade;
import com.jb.coupons_project.utils.custom_exceptions.DBOperationException;

@Component
public class AdminServiceTestImpl implements AdminServiceTest {

	private LoginManager loginManager;
	
	@Autowired
	public AdminServiceTestImpl (LoginManager loginManager) {
		this.loginManager = loginManager;
	}
	
	/**
	 * Run tests of all operations of adminFacade.
	 * @throws DBOperationException in case of database related error
	 */
	@Override
	public void testAdminService() throws DBOperationException 
	{
		System.out.println("-----------------------------------");
		System.out.println("\n*** Testing admin service:");
		String email = "admin@admin.com";
		String password = "admin";
		ClientType clientType = ClientType.ADMINISTRATOR;
		
		// admin login test
//		LoginManagerImpl loginManager = LoginManagerImpl.getInstance();
		ClientFacade clientFacade = loginManager.login(email, password, clientType);
		AdminService adminService = (AdminService) clientFacade;
		if(adminService == null)
		{
			System.out.println(loginManager.getClientMsg());
			return;
		}
		System.out.println(adminService.getClientMsg());
		
		// add company test: should pass
		System.out.println("*** \nAdd new company test: should pass");
		Company company1 = new Company("KSP computers", "sales@ksp.co.il", "ksp");
		adminService.addCompany(company1);
		System.out.println(adminService.getClientMsg());
		System.out.println(adminService.getOneCompany(1));
		
		// addCompany test.
		// Should pass successfully.
		System.out.println("*** \nAdd new company test: should pass");
		Company company2 = new Company("ISSTA", "ic@issta.com", "issta321");
		adminService.addCompany(company2);
		System.out.println(adminService.getClientMsg());
		System.out.println(adminService.getOneCompany(2));
		
		// addCompany test.
		// Should pass successfully.
		System.out.println("*** \nAdd new company test: should pass");	
//		List<Coupon> coupons2 = new ArrayList<Coupon>();
//		Coupon pizza1 = new Coupon(Category.FOOD, 
//								   "Family pizza for 40 nis", 
//								   "1 Family pizza including delivery for 40 nis",
//								   Date.valueOf("2020-06-15"),
//								   Date.valueOf("2020-12-15"),
//								   0,
//								   40.00,
//								   "");
//		coupons2.add(pizza1);
//		
//		Coupon pizza2 = new Coupon(Category.FOOD, 
//									"XL Pizza for 50 nis", 
//									"XL Pizza including delivery for 50 nis",
//									Date.valueOf("2020-04-01"),
//									Date.valueOf("2020-08-01"),
//									80,
//									50.00,
//									"");
//		coupons2.add(pizza2);
		
		Company company3 = new Company("Dominos Pizza", "pizza@pizza.com", "pizza123");
		adminService.addCompany(company3);
		System.out.println(adminService.getClientMsg());
		System.out.println(adminService.getOneCompany(3));
		
		// addCompany test.
		// Should fail due to email duplicate
		System.out.println("*** \nAdd new company test: should fail due to email duplicate");
		Company tivtaam = new Company("Tiv Taam", "ic@issta.com", "tivtaam999");
		adminService.addCompany(tivtaam);
		System.out.println(adminService.getClientMsg());
		
		// updateCompany test
		// Should pass successfully
		System.out.println("*** \nUpdate company test: should pass successfully");
		Company pizzaUpdate = adminService.getOneCompany(3);
		if(pizzaUpdate != null)
		{
			pizzaUpdate.setEmail("p@pizza.com");
			pizzaUpdate.setPassword("pizza");
//			pizzaUpdate.setCoupons(new ArrayList<Coupon>());
			adminService.updateCompany(pizzaUpdate);
		}
		System.out.println(adminService.getClientMsg());
		
		// updateCompany test
		// Should fail due to name change
		System.out.println("*** \nUpdate company test: should fail due to attempt to change company name");
		Company tempCompany = adminService.getOneCompany(1);
		if(tempCompany != null)
		{
			tempCompany.setName("Pizza Hut");
			tempCompany.setPassword("pizza");
			adminService.updateCompany(tempCompany);
		}
		System.out.println(adminService.getClientMsg());
		
		// deleteCompany test
		// should pass successfully
		System.out.println("*** \nDelete company test: should pass");
		adminService.deleteCompany(2);
		System.out.println(adminService.getClientMsg());
		
		// deleteCompany test
		// should fail
		System.out.println("*** \nDelete company test: should fail due to wrong company id");
		adminService.deleteCompany(18);
		System.out.println(adminService.getClientMsg());
		
		// getAllCompanies test: should return 2 companies
		System.out.println("*** \ngetAllCompanies test: should return 2 companies");
		List<Company> companies = adminService.getAllCompanies();
		System.out.println(adminService.getClientMsg());
		for(Company currCompany : companies)
		{
			System.out.println(currCompany.toString());
		}
		
		// getOneCompany test: should pass successfully
		System.out.println("*** \ngetOneCompany test: should pass successfully");
		System.out.println(adminService.getOneCompany(1) != null ? adminService.getOneCompany(1) : adminService.getClientMsg());
		System.out.println(adminService.getClientMsg());
		
		// getOneCompany test: should fail due to wrong id
		System.out.println("*** \ngetOneCompany test: should fail due to wrong id");
		System.out.println(adminService.getOneCompany(-16) != null ? adminService.getOneCompany(-16) : adminService.getClientMsg());
//		System.out.println(adminService.getClientMsg());
		
		// addCustomer test: should pass successfully
		// add customer 1
		System.out.println("*** \naddCustomer test: should pass successfully");
		Customer customer1 = new Customer("FirstName", 
										  "LastName", 
										  "c@gmail.com", 
										  "java123");
		adminService.addCustomer(customer1);
		System.out.println(adminService.getClientMsg());
		
		// add customer 2 test: should pass successfully
		System.out.println("*** \naddCustomer test: should pass successfully");
		Customer customer3 = new Customer("Ana", 
										  "Lk", 
										  "aka@gmail.com", 
										  "java345");
		adminService.addCustomer(customer3);
		System.out.println(adminService.getClientMsg());
		
		// add customer 3 test: should fail dut to email duplicate
		System.out.println("*** \naddCustomer test: should fail due to email duplicate");
		Customer customer2 = new Customer("Vadim", 
										  "Sando", 
										  "aka@gmail.com", 
										  "java234");
		adminService.addCustomer(customer2);
		System.out.println(adminService.getClientMsg());
		
		// add customer 4 test: should pass successfully
		System.out.println("*** \naddCustomer test: should pass successfully");
		Customer customer4 = new Customer("Arye", 
										  "Kanas", 
										  "k@gmail.com", 
										  "java123");
		adminService.addCustomer(customer4);
		System.out.println(adminService.getClientMsg());
		
		// test updateCustomer test: should pass successfully
		System.out.println("*** \nupdateCustomer test: should pass successfully");
		Customer customerUpdated1 = adminService.getOneCustomer(1);
		if(customerUpdated1 != null)
		{
			customerUpdated1.setEmail("ks@gmail.com");
			customerUpdated1.setFirstName("Eugeny");
			customerUpdated1.setLastName("K");
			adminService.updateCustomer(customerUpdated1);
		}
		System.out.println(adminService.getClientMsg());
		
		// test updateCustomer
//		System.out.println("*** updateCustomer test: should fail due to attempt to change ID");
//		Customer customerUpdated2 = adminFacade.getOneCustomer(01);
//		if(customerUpdated2 != null)
//		{
//			customerUpdated2.setId(10);
//			adminFacade.updateCustomer(customerUpdated2);
//		}
//		System.out.println(adminFacade.getClientMsg());
//		
		// test getAllCustomers: should return 3 customers
		System.out.println("*** \ngetAllCustomersTest: should return 3 customers");
		List<Customer> customers = adminService.getAllCustomers();
		System.out.println(adminService.getClientMsg());
		for(Customer currCustomer : customers)
		{
			System.out.println(currCustomer.toString());
		}
		
		// test deleteCustomer
		System.out.println("*** \ndeleteCustomer test: should pass successfully");
		adminService.deleteCustomer(1);
		System.out.println(adminService.getClientMsg());
				
		// test deleteCustomer : should fail due to inexisting ID
		System.out.println("*** \nupdateCustomer test: should fail due to inexisting ID");
		adminService.deleteCustomer(52);
		System.out.println(adminService.getClientMsg());
		
		// test getOneCustomer: should pass successfully
		System.out.println("*** \ngetOneCustomer test: should pass successfully");
		System.out.println(adminService.getOneCustomer(3) != null ? adminService.getOneCustomer(3) : loginManager.getClientMsg());
		
		// log out test
		System.out.println("Admin log out: ");
		loginManager.logout(clientFacade);
		System.out.println(loginManager.getClientMsg());
		System.out.println("*** \nAdmin service test completed");
		System.out.println("-----------------------------------");
		System.out.println();
	}
}
