package utnfc.isi.back.spring.geoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeoapiApplication {

	public static void main(String[] args) {
		// http://localhost:8080/api/distancia?origen=-31.4827704690759,-64.1706996125787&destino=-31.442852668404747,-64.19416119192971
		SpringApplication.run(GeoapiApplication.class, args);
	}

}
