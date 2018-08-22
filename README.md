# kafka-sync
spring kafka template with synchronous reply 

Definition:

We have 5 customers, like customer1, cusomer2… customer5
We have 10 drivers, like driver1,driver2…driver10.
Each customer sends a request to the topic of kafka  for a driver.
Topic listener consumes the request and assign a driver randomly to the requested customer. Each driver only can be assigned one time.
The assigned driver object is send back to customer through a reply queue
Request object should be a customer, replied object should be driver.
Five requests should be processed with multithreading. 

----------------

# One customer can ask for more than one journey :- allowed
# Drivers are added to queue and assigned in a queue as well ( If you need a different behaviour let me know )
# Timeout is handled by spring boot as of now.
# I have focused on two things mainly. spring kafka template with synchronous response. multithreading. 
  - and one driver will be assigned for a unique request.
 
-----------------

In order to setup  download the code and just run it in IDE ( Spring boot )
Or please let me know if you require jar directly

Run kafka and run the following commands ( Windows )
1) kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic requestfordriver-topic
2) kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic requestreplyfordriver-topic

-------------------
Type of Requests

# To request a driver for customerId : 1 - copied from postman

POST /journey HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
{
	"customerId" : "1"
}


# To relase a driver just to add it back to queue ( "id" refered to as driver id ) - copied from postman

POST /release HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
{
	"id" : "11"
}

