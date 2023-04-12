package com.nero.apiRestbasico.controller;

import com.nero.apiRestbasico.model.Vehiculo;
import com.nero.apiRestbasico.repository.VehiculoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VehiculoController {

    private final Logger log = LoggerFactory.getLogger(VehiculoController.class);

    private VehiculoRepository vehiculoRepository;

    // OBS: Spring va a detectar que esta clase necesita un BeanRepository para crearse, buscara en el contenedor de Beans, y lo inyecta
    public VehiculoController(VehiculoRepository vehiculoRepository){
        this.vehiculoRepository = vehiculoRepository;
    }

    // CRUD sobre la entidad Vehículo

    // Buscar todos los Vehículos (Lista)
    @GetMapping("/api/vehiculos")
    public List<Vehiculo> findAll(){
        // recuperar los libros de base de datos
        // findAll devuelve una List<T>
        return vehiculoRepository.findAll();
        // esto, por debajo utiliza la libreria jackson, que devuelve parseado a Json
    }
    // Buscar un solo vehículo en base a su id

    @GetMapping("/api/vehiculos/{id}")  // si no usamos el mismo nombre de la variable, debemos especificar el nombre con @PathVariable("id")
    public ResponseEntity<Vehiculo> findOneById(@PathVariable Long id){
        // la L es por Long
        // debemos pasar la variable string que viene en la url a Long en este caso
        Optional<Vehiculo> vehOpcional = vehiculoRepository.findById(id);

        // devolveriamos una response Entity, porque si el libro no existe, devolveremos un error 404
        /*
        if(vehOpcional.isPresent()){
            return vehOpcional.get();
        } else{
            return null;
        }
        // se puede remplazar por la expresion funcional: return vehOpcional.orElse(null);
        */
        if(vehOpcional.isPresent()){
            return ResponseEntity.ok(vehOpcional.get());
        } else{
            return ResponseEntity.notFound().build();
        }
        // se puede remplazar por la expFuncional: return vehOpcional.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build())
    }

    // GUARDAR VEHICULO
    @PostMapping("api/vehiculos")
    public Vehiculo create(@RequestBody Vehiculo vehiculo){
    // RequestBody Vehiculo vehiculo, esta linea significa,extrae los parametros del body y cargalos en un parametro de tipo libro
        //guardo el libro recibido por paramentro en la bc
        return vehiculoRepository.save(vehiculo);
    }

    //mejorando el metodo con condicionales

    /*
    @PostMapping("api/vehiculos")
    public ResponseEntity<Vehiculo> create(@RequestBody Vehiculo vehiculo){
        private final Logger log = LoggerFactory.getLogger(VehiculoController.class); // viene de org.slf4j.logger y loggerFactory
        if(Vehiculo.getId() != null){

        // registrando el error en un archivo log, spring tiene integrado un sistema de log
        log.warn("intento de crear un vehiculo con id"); // sale en consola con un WARN con hora y fecha, y la clase que lo imprime

            return Response.Entity.badRequest().build();
        }
    Vehiculo result = VehiculoRepository = save(Vehiculo);
        return ResponseEntity.ok(vehiculo);
    }
    */

    // Crear un nuevo Vehículo en la base de datos

    // Actualizar un Vehículo existente

    @PutMapping("api/vehiculos")
    public ResponseEntity<Vehiculo> update(@RequestBody Vehiculo vehiculo){

        // lo ideal seria buscar primero el vehiculo, obtenerlo, y aplicar los metodos seter para actualizarlo, para que no nos actualizen todas las prop
        // por ejemplo, la Date de creacion no nos interesa cambiar

        if(vehiculo.getId() == null){
            log.warn("Intentando actualizar un Vehiculo sin brindar su identificador");
            return ResponseEntity.badRequest().build();
        }
        if(!vehiculoRepository.existsById(vehiculo.getId())){
            log.warn("Intentando actualizar un Vehiculo que no existe");
            return ResponseEntity.notFound().build();
        }
        Vehiculo result = vehiculoRepository.save(vehiculo);
        return ResponseEntity.ok(vehiculo);
    }

    // Borrar un vehiculo
    @DeleteMapping("api/vehiculos/{id}")
    public ResponseEntity<Vehiculo> delete(@PathVariable Long id){
        // lo correcto seria tener un try catch ya que si este objeto depende de otros y no lo controlamos, nos va a surgir una cascada de errores


        if(!vehiculoRepository.existsById(id)){
            log.warn("Intentando borrar un vehiculo que no existe");
            return ResponseEntity.notFound().build();
        }
        vehiculoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // borrar Todos
    @DeleteMapping("api/vehiculos")
    public ResponseEntity<Vehiculo> deleteAll(){
        log.info("Ejecutando metodo borrar todos");
        vehiculoRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
