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

import com.formacionspring.apirest.entity.Cliente;
import com.formacionspring.apirest.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {
	@Autowired
	private ClienteService servicio;
	
	@GetMapping({"/clientes","/"})
	public List<Cliente> index(){
		return servicio.mostrarTodos();
	}
	/*@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable long id) {
		return servicio.mostrarPorId(id);
	}*/
	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> show(@PathVariable long id) {
		Cliente clientes = null;
		Map<String,Object> response = new HashMap<>();
		try {
			clientes = servicio.mostrarPorId(id);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (clientes == null) {
			response.put("mensaje", "El cliente con ID: "+id+" no existe en la base de datos");
			
		}
		return new ResponseEntity<Cliente>(clientes,HttpStatus.OK);
	}
	@PostMapping("/clientes")
	public Cliente create(@RequestBody Cliente cliente) {
		return servicio.guardar(cliente);
	}
	//Buscar los datos de clienteUpdate por el modelo recibido
	@PutMapping("/cliente/{id}")
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {
		//Buscar en el registro por id y guardar el objeto en clienteUpdate
		Cliente clienteUpdate = servicio.mostrarPorId(id);
		//Reemplazo los datos de clienteUpdate por el modelo recibido
		//en @RequestBody
		clienteUpdate.setNombre(cliente.getNombre());
		clienteUpdate.setNif(cliente.getNif());
		clienteUpdate.setDireccion(cliente.getDireccion());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setTelefono(cliente.getTelefono());
		clienteUpdate.setCreateAt(cliente.getCreateAt());
		//Guardo y retorno los datos actualizados
		return servicio.guardar(clienteUpdate);
	}
	
	@DeleteMapping("/clientes/{id}")
	public Cliente delete(@RequestBody @PathVariable Long id) {
		Cliente mostrarIdBorrada = servicio.mostrarPorId(id);
		servicio.borrar(id);
		return mostrarIdBorrada;

	}
}