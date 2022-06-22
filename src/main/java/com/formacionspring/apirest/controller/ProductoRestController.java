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

import com.formacionspring.apirest.entity.Producto;
import com.formacionspring.apirest.service.ProductoService;

@RestController
@RequestMapping("/api")
public class ProductoRestController {
	@Autowired
	private ProductoService servicio;
	
	@GetMapping({"/producto","/"})
	public List<Producto> index(){
		return servicio.mostrarTodos();
	}
	/*@GetMapping("/clientes/{id}")
	public Producto show(@PathVariable long id) {
		return servicio.mostrarPorId(id);
	}*/
	@GetMapping("/producto/{id}")
	public ResponseEntity<?> show(@PathVariable long id) {
		Producto producto = null;
		Map<String,Object> response = new HashMap<>();
		try {
			producto = servicio.mostrarPorId(id);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (producto == null) {
			response.put("mensaje", "El cliente con ID: "+id+" no existe en la base de datos");
			
		}
		return new ResponseEntity<producto>(producto,HttpStatus.OK);
	}
	@PostMapping("/producto")
	public producto create(@RequestBody producto producto) {
		return servicio.guardar(producto);
	}
	//Buscar los datos de clienteUpdate por el modelo recibido
	@PutMapping("/producto/{id}")
	public producto update(@RequestBody producto producto, @PathVariable Long id) {
		//Buscar en el registro por id y guardar el objeto en clienteUpdate
		producto productoUpdate = servicio.mostrarPorId(id);
		//Reemplazo los datos de clienteUpdate por el modelo recibido
		//en @RequestBody
		productoUpdate.setNombre(producto.getNombre());
		productoUpdate.setApellido(producto.getApellido());
		productoUpdate.setEmail(producto.getEmail());
		productoUpdate.setTelefono(producto.getTelefono());
		productoUpdate.setCreateAt(producto.getCreateAt());
		//Guardo y retorno los datos actualizados
		return servicio.guardar(productoUpdate);
	}
	
	@DeleteMapping("/producto/{id}")
	public producto delete(@RequestBody @PathVariable Long id) {
		producto mostrarIdBorrada = servicio.mostrarPorId(id);
		servicio.borrar(id);
		return mostrarIdBorrada;

	}
}