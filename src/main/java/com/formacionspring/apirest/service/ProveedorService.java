package com.formacionspring.apirest.service;
import java.util.List;
import com.formacionspring.apirest.entity.Proveedor;

public interface ProveedorService {
	//Método para mostrar todos los clientes
	public List<Proveedor> mostrarTodos();
	//Método para mostrar un cliente por id
	public Proveedor mostrarPorId(Long id);
	//Método para guardar un cliente
	public Proveedor guardar(Proveedor proveedor);
	//Método para borrar un cliente
	public void borrar(Long id);
}
