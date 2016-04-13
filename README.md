#Trainign Module 1#

###Pre-Requisites###

- **GIT**
	* Linux `sudo apt-get install git`
	* Windows https://git-scm.com/downloads
	
- **IDE**
	* IntelliJ(preferable) https://www.jetbrains.com/idea/download
	* Eclipse http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/mars2
	* NetBeans - **please don't**
	
- **JDK 7**
	* Linux `sudo apt-get install openjdk-7-jdk`
	* Windows 
		1. http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
		2. Computer(This PC) > Properties > Advanced System Settings > Environment Variables
			* New > name: JAVA_HOME value: path/to/java/jdk
			* Edit > PATH add `;%JAVA_HOME%;%JAVA_HOME/bin`
	
- **Maven**
	* Linux `sudo apt-get install maven`
	* Windows 
		1. https://maven.apache.org/download.cgi
		2. Computer(This PC) > Properties > Advanced System Settings > Environment Variables
			* New > name: MAVEN_HOME value: path/to/maven/bin
			* Edit > PATH add `;%MAVEN_HOME%`
			* to test type in cmd - `mvn -v`
			
- **Tomcat 7**
	* You will find it in the bellow package for both 32-bit and 64-bit architectures

###This is to be checked out locally:###

- `git clone https://github.com/atrifan/shield_training.git`
- `git checkout origin/training_part1 -b local_training_module1`

##TODO 1 - Inheritance##

In the following location please implement the SuperHero class so that it makes the Person class a mutable class.
You will find extra description inside the class.

Also because you have HTTP/TCP communication and some serializers need it and also for good practice 
try to declare also the default constructor.

`training-model/com.oneandone.training.communication.model.inheritance`

##TODO 2 - Perfect Socket calls##

Always remember to close a connection.

`training-module1/com.oneandone.training.service.impl`

##TODO 3 - Be yourself##

Make your own superhero

`training-module1/com.oneandone.training App.class`

##TODO 3 & 3/4 - Run things locally##

- `App.class` - training - with parameters run with ``persistent``
- `TCPServer.class` - server

##TODO 4 - Instantiate once use everywhere##

Give possible examples of classes that you might need to use everywhere with the same instantiated data.
Alternatives?

`training-util/com.oneandone.util`

##TODO 5 - Finding your way in doing something - first HTTP controller##

`training-server/com.oneandone.training.server.controller`


##TODO 6 - The Alternative for TODO 4##

- `training-server/resources/local/spring`
- `training-server/com.oneandone.training.server.controller`

##TODO 7 - Continue Working on that controller - make it fully usable##

`training-server/com.oneandone.training.server.controller`


##TODO 7 & 3/4##

- maven profiles
- build with maven
- deploy in tomcat
- manual test

###Congrats you are developer! :)###

##TODO 8 - With one hand you're a dev with one hand you are an automation testing engineer##

`training-module1/com.oneandone.training Connection.class`

##TODO 9 - Now testing testing testing##

`training-module1/com.oneandone.training App.class`

##TODO 10 - Putting it all together = peace of cake##

`training-module1/com.oneadone.training App.class`
`training-module1/com.oneandone.training.exceptions`
