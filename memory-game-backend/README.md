
# Memory Game Challenge for Lepaya 

Microservice API to expose services for Memory Card

## Architecture Definition

We are developing using the [Classic Way](https://www.petrikainulainen.net/software-development/design/understanding-spring-web-application-architecture-the-classic-way/) with 3 layers to define the application architecture as described below:

**The web layer** is the uppermost layer of a web application. It is responsible of processing user’s input and returning the correct response back to the user. The web layer must also handle the exceptions thrown by the other layers. Because the web layer is the entry point of our application, it must take care of authentication and act as the first line of defense against unauthorized users.

**The service layer** resides below the web layer. It acts as a transaction boundary and contains both application and infrastructure services. The application services provide the public API of the service layer. They also act as a transaction boundary and are responsible for authorization. The infrastructure services contain the “plumbing code” that communicates with external resources such as file systems, databases, or email servers. Often these methods are used by more than a one application service.

**The repository layer** is the lowest layer of a web application. It is responsible for communicating with the data storage.

![alt text](http://www.petrikainulainen.net/wp-content/uploads/spring-web-app-architecture.png)
