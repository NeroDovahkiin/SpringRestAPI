package com.nero.apiRestbasico;

import com.nero.apiRestbasico.model.Vehiculo;
import com.nero.apiRestbasico.repository.VehiculoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApiRestBasicoApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(ApiRestBasicoApplication.class, args);
		VehiculoRepository repository = ctx.getBean(VehiculoRepository.class);

		Vehiculo v1 = new Vehiculo(null,"STX1234","FIAT","PALIO","BLANCO","2018");
		Vehiculo v2 = new Vehiculo(null,"LAC1204","KIA","RIO","AZUL","2011");
		Vehiculo v3 = new Vehiculo(null,"SCL5022","CHEVROLET","VECTRA","GRIS","2020");

		// almacenar
		repository.save(v1);
		repository.save(v1);
		repository.save(v2);
		repository.save(v3);
	}

}
