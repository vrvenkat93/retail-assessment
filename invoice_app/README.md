Assumptions: 
************
1. Total discount includes both product discounts and discounts offered to users.
2.Based on their type. 
 a. If the user type is EMPLOYEE, they receive a 30% discount. 
 a. If the user type is AFFILIATE, a 10% discount is applied.
 b. If the user type is OLD CUSTOMER, they will receive a 5% discount. 
3.Depending on the product purchased, the user receives a 5% discount if it is a grocery item no discount provided.  

Steps to execute 
****************

TO RUN TESTS FROM COMMAND LINE RUN THE BELOW COMMAND
mvn -Dtest=RetailStoreApplicationTests test

(OR) 

Goto RetailStoreApplicationTests class You right click the method you want to unit test individually in the Outline pane, select Run As -> Click on JUnit Test.

Testcases : 

ProductDetails : (Common for all testcases)

productType=GROCERY, productName=Berries, price=50
productType=CLOTHING, productName=Jeans, price=1000
productType=ELECTRONICS, productName=Pendrive, price=500
productType=COSMETICS, productName=Perfume, price=250
productType=GROCERY, productName=Apple, price=100

For EMPLOYEE
************
Total Amount of all the products = 1900
Grocery Product amount = 150
Non Grocery Product amount = 1750
User Type Discount = 0.30
Depending on the product purchased 5 % based on Total Product Bill Amount = 95
Calculating NonGroceryAmount with user discount (1750*0.30 = 175) 1750 - 525 = 1225
Final Bill = [(Grocery Bill + NonGroceryBillWithDiscount) - Based on Product discount] (150 + 1225) - 95 = 1280/-

For AFFILIATE
************
Total Amount of all the products = 1900
Grocery Product amount = 150
Non Grocery Product amount = 1750
User Type Discount = 0.10
Depending on the product purchased 5 % based on Total Product Bill Amount = 95
Calculating NonGroceryAmount with user discount (1750*0.10 = 175) 1750 - 175 = 1575
Final Bill = [(Grocery Bill + NonGroceryBillWithDiscount) - Based on Product discount] (150 + 1225) - 95 = 1630/-


For RECENT CUSTOMER (Since less than two years)
***********************************************
Total Amount of all the products = 1900
Grocery Product amount = 150
Non Grocery Product amount = 1750
User Type Discount = 0.0
Depending on the product purchased 5 % based on Total Product Bill Amount = 95
Calculating NonGroceryAmount without discount Amount for this user = 1750
Final Bill = [(Grocery Bill + NonGroceryBillWithDiscount) - Based on Product discount] (150 + 1750) - 95 = 1805/-

For OLD CUSTOMER (Since more than two years)
********************************************
Total Amount of all the products = 1900
Grocery Product amount = 150
Non Grocery Product amount = 1750
User Type Discount = 0.05
Depending on the product purchased 5 % based on Total Product Bill Amount = 95
Calculating NonGroceryAmount with user discount (1750*0.05 = 87.5) 1750 - 87.5 = 1662.5
Final Bill = [(Grocery Bill + NonGroceryBillWithDiscount) - Based on Product discount] (150 + 1662.5) - 95 = 1717.5/-