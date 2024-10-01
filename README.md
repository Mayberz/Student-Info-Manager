# *Student-info-Manager*
  *Version: 3.3.3*
 ## *Project Overview*
 It is a Spring Boot-based RESTful API application designed to perform crud operation on Student Object.The application also supports JWT-based authentication and authurization 
 # *Technologies Used*
* Java 17
* Spring Boot
* Spring Security with JWT
* Spring Data JPA
* MongoDB
* Docker
* Kubernates
* Junit
* Mockito
* Lombhok
* Swagger for API documentation

**Note:** 

* you can also test the application wihout using kubernates,All you need to do is change the profile  name to 'test'.
* Customize the database properties (spring.datasource.*) according to their specific requirements before deploying the application
* in sim-mongo-configMap.yaml configure your own encoded secrets(username,password) based on your needs.

# *Running Application Using Swagger*

Once the application is running, you can access the Swagger UI at
```
http://localhost:8080/swagger-ui/index.html
```
**1.** Select **/student/api/v1/signup** for adding user in the database
**1.** Select **/student/api/v1/authenticate** & pass the username & password, it will generate the JWT Token as Response.

**2.** Copy the generated JWT Token & Paste it to the Authorize Section

**3.** Test rest of the Endpoints 


# *Running Application in Kubernetes*
after deploying your application on DockerHub run the following commands therminal
```
cd <project path> 
minikube start --driver=docker
minikube docker-env
@FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i
kubectl apply -f sim-mongo-configMap.yaml
kubectl apply -f sim-mongo-secrets.yaml
kubectl apply -f db-deployment.yaml
kubectl apply -f sim-deployment.yaml
kubectl port-forward svc/student-management-app-service 8080:8080    
```

# *Unit Testing*

Unit tests have been written using JUnit and Mockito to ensure the correctness of the application's functionality,if you are using a IDE then Right-click on the test class or method and select "Run" to execute the tests.
