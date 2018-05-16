package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.ClaseA;

public class ClaseADao {
	private static Session session;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	

/* 1.ABM */
	//Agregar
	public int agregar(ClaseA objeto) {
		int id=0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		}catch(HibernateException he) {
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		return id;
	}
	
	//Actualizar
	public void actualizar(ClaseA objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		}catch(HibernateException he) {
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
	}
	
	//Eliminar
	public void eliminar(ClaseA objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
/* --- */
	
	
/* 2.TRAYENDO LA INFORMACION */
	//Mediante su clave primaria
	public ClaseA traerClaseA(int idClaseA) throws HibernateException {
		ClaseA objeto = null ;
		try {
			iniciaOperacion();
			objeto = (ClaseA)session.get(ClaseA.class, idClaseA);
		} finally {
			session.close();
		}
		return objeto;
	}
	
	//Mediante algun atributo
	public ClaseA traerClaseAPorAtributo(int atributo) throws HibernateException {
		ClaseA objeto = null ;
		try {
			iniciaOperacion();
			objeto = (ClaseA)session.createQuery("from ClaseA c where c.atributo="+atributo).uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}
	
	//Traer en una lista todos los ClaseA's que hayan.
	@SuppressWarnings("unchecked")
	public List<ClaseA> traerClaseA() throws HibernateException {
		List<ClaseA> lista=null;

		try {
			iniciaOperacion();
			lista = session.createQuery("from ClaseA").list();
		}finally {
			session.close();
		}
		return lista;
	}
/* --- */
}
