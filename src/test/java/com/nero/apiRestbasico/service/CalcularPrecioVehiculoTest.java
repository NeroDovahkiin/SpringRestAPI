package com.nero.apiRestbasico.service;

import com.nero.apiRestbasico.model.Vehiculo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcularPrecioVehiculoTest {

    @Test
    void calculatePrice() {
        // Config del test
        CalcularPrecioVehiculo cal = new CalcularPrecioVehiculo();
        Vehiculo v = new Vehiculo(null,"AAL1122","HYUNDAI","i10","NEGRO","2000");
        v.setPrecio(3000d);


        // Ejecuto comportamiento a testear
        double precio = cal.calculatePrice(v);
        System.out.println(precio);

        // comprobaciones aserciones
        assertTrue(precio > 0);
        assertEquals(4209.0,precio);

    }
}