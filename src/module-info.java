/**
 * 
 */
/**
 * 
 */
module ContactManagementSystem {
	requires org.slf4j;
	requires java.sql;
	requires jdk.httpserver;
	requires com.google.gson;	
	opens com.dto to com.google.gson;
}