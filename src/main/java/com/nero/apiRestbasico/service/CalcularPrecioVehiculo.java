package com.nero.apiRestbasico.service;

import com.nero.apiRestbasico.model.Vehiculo;

// clase de prueba meramente para probar testing
public class CalcularPrecioVehiculo {

    public double calculatePrice(Vehiculo vehiculo){
        double price = vehiculo.getPrecio();

        double iva = 1.22;
        double comision = 1.15;
        return price * iva * comision;
    }
}
