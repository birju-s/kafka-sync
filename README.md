# kafka-sync
spring kafka template with synchronous reply 


# To request a driver for customerId : 1 - copied from postman

POST /journey HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 29545317-a5bc-465a-9d9d-584c2bfa4469

{
	"customerId" : "1"
}


# To relase a driver just to add it back to queue ( "id" refered to as driver id ) - copied from postman

POST /release HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 4f2df104-63c9-27d1-5dd2-7028c98fb339

{
	"id" : "11"
}