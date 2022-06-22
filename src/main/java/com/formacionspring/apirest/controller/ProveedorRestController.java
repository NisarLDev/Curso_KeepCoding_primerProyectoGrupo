package com.formacionspring.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.apirest.entity.Proveedor;
import com.formacionspring.apirest.service.ProveedorService;

@RestController
@RequestMapping("/api")
public class ProveedorRestController {
	@Autowired
	private ProveedorService servicio;
	
	@GetMapping({"/proveedor","/"})
	public List<Proveedor> index(){
		return servicio.mostrarTodos();
	}
	/*@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable long id) {
		return servicio.mostrarPorId(id);
	}*/
	@GetMapping("/proveedor/{id}")
	public ResponseEntity<?> show(@PathVariable long id) {
		Proveedor proveedor = null;
		Map<String,Object> response = new HashMap<>();
		try {
			proveedor = servicio.mostrarPorId(id);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (proveedor == null) {
			response.put("mensaje", "El proveedor con ID: "+id+" no existe en la base de datos");
			
		}
		return new ResponseEntity<Proveedor>(proveedor,HttpStatus.OK);
	}
	@PostMapping("/proveedor")
	public Proveedor create(@RequestBody Proveedor proveedor) {
		return servicio.guardar(proveedor);
	}
	//Buscar los datos de clienteUpdate por el modelo recibido
	@PutMapping("/proveedor/{id}")
	public Proveedor update(@RequestBody Proveedor proveedor, @PathVariable Long id) {
		//Buscar en el registro por id y guardar el objeto en clienteUpdate
		Proveedor proveedorUpdate = servicio.mostrarPorId(id);
		//Reemplazo los datos de clienteUpdate por el modelo recibido
		//en @RequestBody
		proveedorUpdate.setNombre(proveedor.getNombre());
		proveedorUpdate.setApellido(proveedor.getApellido());
		proveedorUpdate.setEmail(proveedor.getEmail());
		proveedorUpdate.setTelefono(proveedor.getTelefono());
		proveedorUpdate.setCreateAt(proveedor.getCreateAt());
		//Guardo y retorno los datos actualizados
		return servicio.guardar(proveedorUpdate);
	}
	
	@DeleteMapping("/proveedor/{id}")
	public Proveedor delete(@RequestBody @PathVariable Long id) {
		Proveedor mostrarIdBorrada = servicio.mostrarPorId(id);
		servicio.borrar(id);
		return mostrarIdBorrada;

	}
}