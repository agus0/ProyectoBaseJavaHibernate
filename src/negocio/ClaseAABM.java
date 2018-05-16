package negocio;

import java.util.List;

import dao.ClaseADao;
import datos.ClaseA;

public class ClaseAABM {
	ClaseADao dao = new ClaseADao();
	
	
/* 1.ABM */
	public int agregar(int atributo1, int atributo2) {
		claseA = new ClaseA(atributo1, atributo2);
		return dao.agregar(claseA);
	}
	
	public void modificar(ClaseA claseA) {
		dao.actualizar(claseA);	
	}
	
	public void eliminar(int idClaseA) throws Exception {
		ClaseA claseA = dao.traerClaseA(idClaseA);
		if (claseA==null)
			throw new Exception("El idClaseA ingresado no se corresponde a ningun claseA");
		dao.eliminar(claseA);
	}
/* --- */
	
	
/* 2.TRAYENDO LA INFORMACION */
	public ClaseA traerClaseA(int idClaseA) throws Exception{
		ClaseA claseA = dao.traerClaseA(idClaseA);
		if (claseA==null)
			throw new Exception("ClaseA nulo");
		return claseA;
	}
	
	public ClaseA traerClaseAPorAtributo(int atributo) throws Exception{
		ClaseA claseA = dao.traerClaseAPorAtributo(atributo);
		if (claseA==null)
			throw new Exception("ClaseA nulo");
		return claseA;
	}
	
	public List<ClaseA> traerClaseA() {
		return dao.traerClaseA();
	}
/* --- */
	
	
}
