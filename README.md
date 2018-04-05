## Parser

Test done to WalletHub Interview. For this project I used [Jcommander](jcommander.org) (to all command line tools), Maven and Java (To Parse the log). 


**Deliverables Folder Structure:**
![enter image description hre](https://lh3.googleusercontent.com/qQIz_ebRurjs2C0OY50xvG9N3Z7JmdDWrz2dAYg6dVio-3sunGc6M8sHStry70LAgruVaRu1lKZNFQ)

 

----------


   

## Java
Into the folder contains:
 1. **log-analyzer-SOURCE_CODE.zip:**
	*source conde of project.*
 2. **parser.jar (executable jar):**
		*The executable file.*
 3. **dbConfiguration.properties**
	 *database connection file, that will be used to input the mysql connection paramns. It is mandatory for success of execution jar.*
![enter image description here](https://lh3.googleusercontent.com/BXMU1Qe5vo3hzokaYaMglvPfc1eOaD5CMHL8NT2Ya_VKDEeGA_FH7MsEFhgVe6Y3ZZqakfbsilH-xA)

## Sql
This folder contains:

 1. **db folder:**
	 *db folder where all the database scripts used in the project are located.*
	 
 2. The schema of the tables created and the queries requested in the test document.

![enter image description here](https://lh3.googleusercontent.com/mR-gFkx3xpBxCrybDHBRCHzHNRpLQfXMgaDnA2_7b4YImIIhmAzKqA8C8DYHtYo3gWAeExx9Q6S75w)

 3. In the file **sql_queries** there are the two queries requested in the **Java_MySQL_Test_Instructions.txt**.

## Instructions For Use

 1. Create a database.
 
 2. Execute the tables creation scripts (CREATE_TABLE_LOG.sql, CREATE_TABLE_IP_BLOCKED.sql) located in the folder:  **sql\db\** 

 3. In the Java folder, put the connection parameters in the **dbConfiguration.properties** file. This file should always be in the same directory as parse.jar.

 4. Execute the command passing the desired parameters, according to the examples below:
 

 
**TIP:**
The command below can be used to see help about parser.jar

    java -cp "parser.jar" com.ef.Parser --help

 

  Commands example:

       
> java -cp "parser.jar" com.ef.Parser --accesslog= /path/to/file
> --startDate= 2017-01-01.13:00:00 --duration= hourly --threshold= 100

Always that the command above was executed, the tables **log** and **ip_blocked** will be cleaned before the import accesslog file process.


----------


 
>  java -cp "parser.jar" com.ef.Parser --startDate= 2017-01-01.13:00:00
> --duration= hourly --threshold= 100

In the command above the tables will not be cleaned, the parser will be done using the existing data in the table **log.**


----------


**IMPORTANT**: There must be a space between the parameter and its value, so the jar can recognize it.

*Example*: 

    duration= daily (it's correct)
    duration=daily (it's wrong)


----------


> Autor: André Luiz Ximenes
> E-mail: andreluizximenes@gmail.com
