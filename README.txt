How To Build 
Prerequisite: You have to install maven.
-Clone this repo
-Run "mvn clean package" and note where package installed e.g.: /Users/username/.m2/repository/com/devatate/zopaloan/target/ZopaLoanTest-1.0-SNAPSHOT.jar
-Run "java -jar target\\Zopaloan-1.0-SNAPSHOT.jar [csv_file] [requested_amount]" e.g.: java -jar target/Zopaloan-1.0-SNAPSHOT.jar src\\main\\resources\\market.csv 1000
