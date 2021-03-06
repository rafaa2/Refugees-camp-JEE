package tn.esprit.services;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import tn.esprit.interfaces.IdentifiedInterface;

public abstract class AbstractFacade <T extends IdentifiedInterface>{

	private Class<T> entityClass;
	
	public AbstractFacade(Class<T> entityClass){
		this.entityClass=entityClass;
	}
	
	protected abstract EntityManager getEntityManager();
	
	public T create(T entity){
		getEntityManager().persist(entity);
		return entity;
	}
	
	public void update(T entity){
		getEntityManager().merge(entity);
	}
	
	public void remove(T entity){
		try
		{
		getEntityManager().remove(getEntityManager().merge(entity));}
		catch(Exception e){}
	}
	
	public T find(Object id){
		return getEntityManager().find(entityClass, id);
	}
	

	
	public List<T> findAll(){
		CriteriaQuery cq= getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}
	
	public List<T> findRange(int index,int maxResult){
		CriteriaQuery cq=getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query q= getEntityManager().createQuery(cq).setFirstResult(((index-1)*maxResult)).setMaxResults(maxResult);
		
		return q.getResultList();		
	}
	
	
	public int count(){
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt=cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query q= getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
	
}
