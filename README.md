# Trading Application
This is a sample trading application using Java / Maven / Spring Boot

## How to Run 

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 11 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service using ```mvn spring-boot:run```

Once the application runs you should see something like this

```
2022-10-07 13:31:34.662  INFO 99596 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-10-07 13:31:34.671  INFO 99596 --- [           main] com.algo.trading.TradingApplication      : Started TradingApplication in 2.158 seconds (JVM running for 2.632)
```

## About the Service

The service is just a simple trading application which will receive signal(a simple integer) as only parameter for the REST service. This service has 3 actions

* Setup
* Reverse
* Perform

each action will call the respective methods of Algo library. At present in the project it has been configured to handle 10 signals, each signal specific action and parameter has been configured in ```application.properties``` file. So in future the team needs to add new signal, they no need to do any modification in the code, instead just add the new signal action and parameter in properties file.

Sample properties file:
```
#Received signal parameters
signal.param.value.1=60
signal.param.value.2=80
signal.param.value.3=90,15
signal.param.value.4=10,15,25
signal.param.value.5=50,22
signal.param.value.6=40,44
signal.param.value.7=50,55
signal.param.value.8=60,66
signal.param.value.9=60,66
signal.param.value.10=100,111

#Received signal action
signal.action.value.1=SETUP
signal.action.value.2=REVERSE
signal.action.value.3=PERFORM
signal.action.value.4=PERFORM
signal.action.value.5=PERFORM
signal.action.value.6=PERFORM
signal.action.value.7=PERFORM
signal.action.value.8=PERFORM
signal.action.value.9=REVERSE
signal.action.value.10=SETUP
```

The call to Algo methods based on the signal will be printed in application log (System.out)
```
2022-10-07 13:44:41.546  INFO 99986 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
2022-10-07 13:44:41.600  INFO 99986 --- [nio-8080-exec-1] c.a.t.service.impl.SignalHandlerImpl     : Received Signal config parameters 100,111
2022-10-07 13:44:41.602  INFO 99986 --- [nio-8080-exec-1] c.a.t.service.impl.SignalHandlerImpl     : Received Signal config action SETUP
setUp
setAlgoParam 10,100
setAlgoParam 10,111
performCalc
submitToMarket
doAlgo
```

### Calling handle signal service

Use the below handle signal service URL to call the REST service. Replace the ```<SIGNAL-ID>``` with the integer value
```
http://localhost:8080/tradingSignal/<SIGNAL-ID>
```

Example URL & Sample output in the log
```
http://localhost:8080/tradingSignal/6
```

```
2022-10-07 13:49:05.443  INFO 99986 --- [nio-8080-exec-5] c.a.t.service.impl.SignalHandlerImpl     : Received Signal config parameters 40,44
2022-10-07 13:49:05.443  INFO 99986 --- [nio-8080-exec-5] c.a.t.service.impl.SignalHandlerImpl     : Received Signal config action PERFORM
setAlgoParam 6,40
setAlgoParam 6,44
performCalc
submitToMarket
doAlgo
```
