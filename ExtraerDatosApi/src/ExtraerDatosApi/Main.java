package ExtraerDatosApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

public class Main {
	
	//Metodo Principal
	public static void main(String[] args) throws IOException, InterruptedException {
		//Se crean las listas para almacenar las naves y sus ID
		ArrayList<Nave> listaNaves = new ArrayList<>();
		ArrayList<Integer> idNaves = new ArrayList<>(Arrays.asList(2, 3, 5, 9, 10, 11, 12, 13, 15, 17, 21, 22, 23, 27, 28, 29, 31, 32, 39, 40, 41, 43, 47, 48, 49, 52, 58, 59, 61, 63, 64, 65, 66, 68, 74, 75));
		
		Gson gson = new Gson();
		
		//Este bucle recorrera los ID y llamara a la API
		for (Integer i : idNaves) {
			String datosNaves = llamadaApi(i);
			//Creara las naves siempre que existan
			if(!datosNaves.contains("Not found")) {
				Nave nave = gson.fromJson(datosNaves, Nave.class);
				listaNaves.add(nave);
			}
		}
		
		//Se ejecuta un metodo para iniciar la conexion con la base de datos
		Conector.ejecutarConexion(listaNaves);
	}
	
	//Metodo para conectar con la API
	public static String llamadaApi(int id) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://swapi.dev/api/starships/"+ id + "/"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		//Devuelve los datos de las naves
		String datosNaves = response.body();
		return datosNaves;
	}

}