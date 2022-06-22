package com.formacionspring.apirest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspring.apirest.entity.Proveedor;
import com.formacionspring.apirest.repository.ProveedorDao;

@Service
public class ProveedorServiceImpl implements ProveedorService{
	@Autowired
	private ProveedorDao proveedorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> mostrarTodos() {
		// TODO Auto-generated method stub
		
		
		return (List<Proveedor>) proveedorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Proveedor mostrarPorId(Long id) {
		// TODO Auto-generated method stub
		return proveedorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Proveedor guardar(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return proveedorDao.save(proveedor);
	}

	@Override
	@Transactional
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		proveedorDao.deleteById(id);
	}

}
