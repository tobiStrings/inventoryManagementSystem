#Shopify Backend Developer Intern Challenge
This is my solution to the Backend Developer Intern Challenge 2022. The task is to build an inventory tracking system for a logistics company. Details of the project can be found [here]()
*
#GitHub Repository
https://github.com/tobiStrings/inventoryManagementSystem
*
# Project Description

This is a simple inventory tracking logistics system with CRUD functionality and an  additional feature of loading all
inventory data to a CSV file. The project was built with SpringBoot(Java).


#Prerequisites
* Java 16
* Maven
* PostMan
* MongoDb


##To build
shell
mvn clean install



##To run
shell
java -jar ./target/inventoryManagement.jar



#Rest API Usage
*

##Create Inventory Item
> #### P0ST http://localhost:8080/api/inventory/save

#####Parameter
json
{
"productName": "String",
"imageUrls": "list of strings",
"priceForEach": "double",
"quantity": "int",
"operation":"String",
"description":"String"
}


##Response
####200 OK on successful request

json
{
"id": "String",
"productName": "String",
"imageUrls": "String",
"savedTime": "ISO 8601 timestamp",
"priceForEach": "Double",
"quantity": "int",
trackInfo:"Object",
"description":"String"
}

*
##Find All Inventory
> #### GET http://localhost:8080/api/inventory/findAllTrackInfo

Returns list of items in the database

##Response
####200 OK on successful request

json
[
"id": "String",
"productName": "String",
"imageUrls": "String",
"savedTime": "ISO 8601 timestamp",
"priceForEach": "Double",
"quantity": "int",
trackInfo:"Object",
"description":"String"
}
]

*
##Delete Inventory
> #### DELETE http://localhost:8080/api/inventory/deleteProduct

#####Parameters
Product name


##Response
####200 Ok on successful request
*
##Update Inventory Item
> #### PATCH http://localhost:8080/api/inventory/removeProduct

#####Parameters
json
{
"productName": "String",
"quantity":"int"
}


##Response
####200 OK on successful request

*
#Write to CSV File
####Get http://localhost:8080/api/inventory/writeToCsv
