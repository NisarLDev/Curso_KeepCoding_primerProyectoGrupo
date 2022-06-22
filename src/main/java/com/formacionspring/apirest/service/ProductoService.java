package com.formacionspring.apirest.service;
import java.util.List;
import com.formacionspring.apirest.entity.Producto;

public interface ProductoService {
	//Método para mostrar todos los Productos
	public List<Producto> mostrarTodos();
	//Método para mostrar un Producto por id
	public Producto mostrarPorId(Long id);
	//Método para guardar un Producto
	public Producto guardar(Producto producto);
	//Método para borrar un Producto
	public void borrar(Long id);
}
