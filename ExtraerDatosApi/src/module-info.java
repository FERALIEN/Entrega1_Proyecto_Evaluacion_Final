/**
 * 
 */
/**
 * @author fptor
 *
 */
module ExtraerDatosApi {
	requires java.net.http;
	requires java.sql;
	requires com.google.gson;
	
	opens ExtraerDatosApi to com.google.gson;
}