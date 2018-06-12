

InfoHub is a simple Web application to demonstrate basic crud operations using Java/JSF/Primefaces/Spring/Hibernate/JPA/H2/Maven.

# Features:
	Login.
	Search Employee
	Add new Employee
	View Employee Detail
	Update Employee Detail
	Delete Employee

# User Types:
	2 type of users:
    Admin
	  User

  # Admin has the following rights:
		-Add new Employee
		-Search for Employee
		-View Employee Details	
		-Update Employee Details
		-Delete Employee

  # User has the following rights:	
		-Search for Employee
		-View Employee Details
	
  # Login:
    Login is just for the demo purpose.
    This can be improved by using Spring Security.
    Admin User: admin@infohub.com
    Admin Password: admin

  # Search Employee
	  User can search Employee by their name or nationality.
	  Both admin and normal user has the rights.

  # View Employee Detail
	  User can search Employee by their name or nationality and view the details.
	  Both admin and normal user has the rights.

  # Add new Employee
	  User can Add new Employee.
	  Only admin has the rights.
	  If password field is kept blank, default password 'pass123' will be set.

  # Update Employee Detail
	  User can edit the employee details.
	  Only admin has the rights.

  # Delete Employee
	  User can delete the employee details.
	  Only admin has the rights.
	
# Technical Details:

  # Tech Stack:
		  Java: 1.8
		  JSF: 2.2
		  Primefaces: 6.2
		  Spring Boot: 1.5.8
		  DB: H2/In memory.
	
  # Build & Run:
		  Open the terminal or cmd.
		  Checkout the project from :https://github.com/selectstarfrom/infohub.git
		  go to the project root directory.
		  run the maven command:
			  for WAR file: mvn clean install -DskipTests -Pwar
			  for JAR file: mvn clean install -DskipTests -Pjar
		  Once the build is success. go to the folder "infohub-webapp/target" inside the project.

		  run the command java -jar infohub.jar or java -jar infohub.war
		
		  Open the browser: http://localhost:8080/infohub/
		
		  You can also take the war and deploy in external tomcat server.
		
		  Note: on the startup, H2 in memory database will be populated with 15 users for demo purpose.


# Executable
  You can download the war file and run as below
      >> java -jar infohub.war
      
# Can be enhanced with:
	Spring security.
	Logging.
	Responsive UI.
	message properties and i18.
	JUnit.
  
  Thanks & Regards,
  Syed
