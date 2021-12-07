## Telenor Code Test:
In a basic Dockerized Springboot Maven application, build a single REST API endpoint that returns a filtered set of products from the provided data in the data.csv file.
## Tools and Prerequisites:
1.	Jdk 11
2.	IntelliJ for spring Boot development
3.	Maven
4.	Docker for desktop
## Implementation:
The assignment is implemented as a spring boot application with Jdk 11 to build a Single Rest API endpoint.

Implemented the listener that will be notified when application started, so the listener will read the CSV file and store into the H2 database. 

Then Developed Single Rest API to handle all the API parameters. 

We should be able to do below things with this project:
- build the application with Maven
- build the Docker image and run it
- make requests to verify the behavior

## How to run

I have created a Docker file which packs the application into docker image.

Following are the commands that are needed to run from project root directory
# Step 1:
    $ mvn clean package
    
<br /> After successful run, Target file will be generated.

# Step 2 :
    $ docker build -t springio/gs-spring-boot-docker .
    
<br /> After successful Run, Docker build success.
# Step3:
    $ docker run -p 8080:8080 -t springio/gs-spring-boot-docker
    
<br /> After successful run, Docker image and container will be generated
    
After that one can test with sample requests.

## Sample Request Tested:

Positive scenarios:

http://localhost:8080/product
 

http://localhost:8080/product?type=phone&max_price=1000&city=Stockholm

http://localhost:8080/product?type=phone&min_price=100&city=Stockholm


http://localhost:8080/product?type=phone&min_price=100&max_price=1000&city=Stockholm

http://localhost:8080/product?type=subscription&max_price=1000&city=Stockholm

http://localhost:8080/product?type=phone&min_price=100&max_price=1000&city=Stockholm&property:color=lila

You can use below curl commands if it is available for you.
    
    $ curl -i http://localhost:8080/product?type=subscription&max_price=1000&city=Stockholm


## Negative scenarios:

This solution won’t allow multiple values. Below request wont return any values.

http://localhost:8080/product?type=type=phone,subscription&min_price=100&max_price=1000&city=Stockholm&property:color=lila



After finish run following commands:
    
    $ docker ps
   
Now you have the container id. Run the following command with container id:
   
    $ docker stop container_id
    
Even, you can use `ctrl+c` which will stop the container. 
