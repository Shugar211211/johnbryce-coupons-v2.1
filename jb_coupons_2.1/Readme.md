# John Bryce Coupon System Project - stage 2.

Stage 2 of John Bryce course 822-120 Coupon System project.
This stage represents first stage project rebuilt with Spring, Hibernate, JPA and Spring Boot.

### Setup data
To run this app you need: 
* Eclipse IDE 
* MySql server 

**MySql user account credentials:**

**username: jbcp**

**password: admin**

This data can be changed in `src/main/resources/application.properties` file

MySQL command you can use to create MySQL user account account:
`mysql> CREATE USER 'jbcp'@'localhost' IDENTIFIED BY 'admin';`

**Administrator login credentials:**

**login: admin@admin.com**

**password: admin**  

This data is hardcoded in AdminService login method.

### Using the app
On each run the application, the app should connect to MySQL database and check for schema **jb_coupons_2**.

This data can be changed in `src/main/resources/application.properties` file

If it doesn't find this schema, which is preferred flow, it should create new schema.
If it finds existing schema it should use the existing schema.

If for some reason application can not create database, you can create it manually using MySQL commands which will be posted below.

After this stage is complete, the application should launch daily task of cleaning expired coupons. This task is intended to run automatically every 24 hours and delete coupons whose end date has expired.

After this stage is complete the application should perform series of hard-coded tests to test all operations of client services.

After this stage is complete the application should launch interactive console menu which will allow more flexible selective testing. These menu allow testing of all functions of client services.
To use these tests you should follow onscreen instructions.
To stop application you should hit '4' and the application will quit.

In order to use menu tests you need to log in as administrator first (option '1'), then you can see/create/edit/delete other clients.

**To log in as administrator use these credentials:**

login: **admin@admin.com**

password: **admin**  


**Note**: Some hard-coded tests use constant IDs of client/coupon/company, which are fixed to match appropriate database entries during the first run only. On each next run without truncate schema some hard-coded tests fail or perform inconsistently. To avoid this you should delete or truncate schema 'jb_coupons_2' before each next run of application, or truncate tables *coupons, customers, companies, customers_vs_coupons*. You may use MySQL command: `DROP SCHEMA jb_coupons_2;`.


### MySQL statements for creating schema

```
CREATE SCHEMA IF NOT EXISTS JB_COUPONS_2;
```
```
USE JB_COUPONS;
```
```
CREATE TABLE COMPANIES 
	(ID INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	NAME VARCHAR(128) UNIQUE, 
	EMAIL VARCHAR(128) UNIQUE,
	PASSWORD VARCHAR(128),
	PRIMARY KEY(ID),
	INDEX (NAME)
);
```
```
CREATE TABLE CUSTOMERS (
	  ID INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	  FIRST_NAME VARCHAR(128),
	  LAST_NAME VARCHAR(128), 
	  EMAIL VARCHAR(128) UNIQUE,
	  PASSWORD VARCHAR(128),
	  PRIMARY KEY(ID),
	  INDEX (EMAIL)
	);
```
```
CREATE TABLE COUPONS (
	  ID INT UNSIGNED NOT NULL AUTO_INCREMENT, 
	  COMPANY_ID VARCHAR(128), 
	  CATEGORY_ID INT UNSIGNED,
	  TITLE VARCHAR(128),
	  DESCRIPTION VARCHAR(255),
	  START_DATE DATE,
	  END_DATE DATE,
	  AMOUNT INT,
	  PRICE DOUBLE,
	  IMAGE VARCHAR(255),
	  PRIMARY KEY(ID),
	  INDEX (TITLE),
	  CONSTRAINT FOREIGN KEY (COMPANY_ID) REFERENCES COMPANIES (ID)
	    ON DELETE NO ACTION ON UPDATE NO ACTION,
	  CONSTRAINT FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORIES (ID)
	    ON DELETE CASCADE ON UPDATE CASCADE
	);
```
```
CREATE TABLE CUSTOMERS_VS_COUPONS (
	  CUSTOMER_ID INT UNSIGNED, 
	  COUPON_ID INT UNSIGNED,
	  PRIMARY KEY(CUSTOMER_ID, COUPON_ID),
	  CONSTRAINT FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS (ID)
	    ON DELETE NO ACTION ON UPDATE NO ACTION,
	  CONSTRAINT FOREIGN KEY (COUPON_ID) REFERENCES COUPONS (ID)
	    ON DELETE NO ACTION ON UPDATE NO ACTION
	);
```

