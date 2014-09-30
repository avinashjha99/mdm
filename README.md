mdm
===

MDM server code

MDM server is meant to manage and analyse the Registered Mobile devices using a management console.

* Configuring and running the project.

This is a netbeans project, and right now we don't support pure maven based build system. So download the project and import in Netbeans. There are two dependencies for this project 1. ORMLite (Hibernates lightweight alternative), 2. GSON. So manually place there jars in the build path for Netbeans project. Once you have resolved dependencies, then project can be built (but can not be deployed yet). For deployement, we are using Glassfish4 server, but since we are using plain vanilla J2EE, it could be depolyed to any J2EE compliant server. Before deploying to server, you have to setup 1> MySql on that server , and then put corresponding user name, password and url to that Mysql server in Config.java file., 2> On Server set up a Task Handler Queue, we use this queue to perform operations on the mobile(both batch and third party operations). Setup queue and again update config variables. Once you are done with these things, project is ready to be deployed 3. Since WebHandling layer and Business layer has been developed independently of Javascript based client(mdm spa website), you need to drop that project from mdm-console in the same repository into web folder. Phew that's it!! Now we are ready to run this project.

* Undertanding the project.

This project aims at controlling the Enterprise owned mobiles from a central console. It allows to view the crucial data like installed apps and call records on managed devices. It also provides to control to those managed devices by unintalling app, locking out and changing password of devices in extreme cases. These features would be expanded by having a plugin based framework. We have used plain vanilla J2EE features, no framework. Using framework has obvious advantages but in this case wanted to stay close to the platform. Our project MDMServer consists of two modules, web-module MDMServer-War for web handling, and business tier module MDMServer-Ejb for handling system resources like queue, databases, task threads etc. This was done primarily to seperate the concerns. Details of each project would be available on a site wiki soon. To browse MDMServer-War project start with Controllers (Servlets), filters on those Servlets(for enabling cross domain, handling authentication etc.), Models. For main business logic start exploring MDMServer-Ejb particularly classes like DatabaseManager and ControllerFacade, Dao and DaoImpl.
