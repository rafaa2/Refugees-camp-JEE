package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.Need;
import tn.esprit.interfaces.NeedService;

@Stateless
@LocalBean
public class NeedImpl implements NeedService{

	@PersistenceContext   // vih +entites persistance  ...injectih v entity manager
	EntityManager em;     //gestionnaire entites

	@Override
	public void addNeed(Need n) {
		// TODO Auto-generated method stub
		em.persist(n);
		em.flush();
		System.out.println("Need ajouté avec succes");

		
	}

	@Override
	public void deleteNeed(Need n) {
		// TODO Auto-generated method stub
		em.remove(em.merge(n));

	}

	@Override
	public void updateNeed(Need n) {
		// TODO Auto-generated method stub
		em.merge(n);	

	}

	@Override
	public Need findNeedById(int id) {
		// TODO Auto-generated method stub
		return em.find(Need.class,id);
	}

	@Override
	public List<Need> listAll() {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT n FROM Need n",Need.class).getResultList();
	}

	@Override
	public List<Need> getNeedByType(String n) {
		// TODO Auto-generated method stub
		Query req = em.createQuery
				("select n from Need n where n.name like :x ");
			req.setParameter("x", "%" +n+ "%");
			List resultList = req.getResultList();
			return resultList;
	}

	@Override
	public List<Need> getNeedByDis(DistrictChef d) {
		Query req = em.createQuery
				("select n from Need n where n.dischef = :x ");
			req.setParameter("x",d);
			List resultList = req.getResultList();
			return resultList;
	}
	
	

}
