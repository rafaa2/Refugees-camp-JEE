package tn.esprit.services;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Need;
import tn.esprit.entities.Stock;
import tn.esprit.entities.StockNotification;
import tn.esprit.interfaces.StockLocalInterface;
import tn.esprit.interfaces.StockRemoteInterface;

/*
* author: Salim Ben Hassine
*/

@Stateless
@LocalBean
public class StockService implements StockLocalInterface,StockRemoteInterface {
	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;
	
	StockNotificationService sn= new StockNotificationService();
	@Override
	public boolean add(Stock stock) {
		String req="Select s from Stock s where s.stockType=:p";
		List<Stock> stockTest=em.createQuery(req,Stock.class).setParameter("p",stock.getStockType()).getResultList();
		if(stockTest.size()==0) {
			if (stock != null) {
				em.persist(stock);
				return true;
			}
		}else if(stockTest.size()==1) {
			if (stock != null) {
			Stock stockAux=new Stock();
			stockAux=stockTest.get(0);
			stock.setId(stockAux.getId());
			stock.setStockValue(stockAux.getStockValue()+stock.getStockValue());
			stock.setQteTotal(stockAux.getQteInStock()+stock.getQteTotal());
			stock.setQteInStock(stock.getQteTotal());
			em.merge(stock);
			return true;
		}
		}
		
		return false;

	}

	@Override
	public List<Stock> findAll() {
		String requete = "SELECT n FROM Stock n";
		return em.createQuery(requete, Stock.class).getResultList();
	}

	@Override
	public boolean update(Stock stock) {
		// merge old and new instances
		if (stock != null) {
			em.merge(stock);
			return true;
		}
		return false;

	}

	@Override
	public Stock findById(int id) {
		return em.find(Stock.class, id);

	}

	@Override
	public boolean delete(Stock stock) {
		if (stock != null) {
			em.remove(em.merge(stock));
			return true;
		}
		return false;
	}
	/* ********** a remplacer ***/
	public Need findNeedById(int id) {
		return em.find(Need.class, id);

	}
	/* *****************************/
	
	public boolean AcceptNeedDemand(Need need) {
		Stock stock=new Stock();
		int status=0;
		if(need.getStatus()==1) {
			
			String req="Select s from Stock s where s.stockType=:p";
			stock=em.createQuery(req,Stock.class).setParameter("p",need.getType()).getSingleResult();
		
			/* * check Status **/ 
			if(stock.getQteInStock()>=need.getQuantity()) {
				/* status modification */
				status=1;
				double initValue=stock.getStockValue();
				stock.setStockValue(stock.getStockValue()-((initValue/stock.getQteInStock())*need.getQuantity()));
				stock.setQteInStock(stock.getQteInStock()-need.getQuantity());
				if(stock.getQteInStock()<=(stock.getQteTotal()*0.2)) {
					createStockNotif(stock);
				}
				need.setStatus(status);
				/* persist */
				update(stock);
				if (need != null) {
					em.merge(need);}
				return true;
			}
		
		
		}
		
		return false;
	}
	
	public boolean RefuseNeedDemand(Need need) {
		int status=0;
		if(need.getStatus()==-1) {
			
				status=-1;
				need.setStatus(status);
				/* persist */
				if (need != null) {
					em.merge(need);}
				return true;	
		}
		
		return false;
	}
	
	public boolean BackToPendingNeedDemand(Need need) {
		int status=0;
		if(need.getStatus()==0) {
			status=0;
			need.setStatus(status);
			/* persist */
			if (need != null) {
					em.merge(need);}
			return true;
		}
		
		return false;
	}
	
	public List<Need> ListNeedsByStatus(int status){
		String requete = "SELECT n FROM Need n where n.status=:p";
		return em.createQuery(requete,Need.class).setParameter("p",status).getResultList();
	}
	
	public List<Stock> ListStockBreak(){
		String requete = "SELECT n FROM Stock n where n.qteInStock<(n.qteTotal*0.2)";
		return em.createQuery(requete,Stock.class).getResultList();
	}
	
	private void createStockNotif(Stock stock)
	{
		
		StockNotification st=new StockNotification();
		st.setMessage("Stock will be ended soon under 20%");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		st.setDateOfNotification(timestamp);
		st.setStatus(0); /* 1 viewed notification | 0 unviewed*/
		st.setStock(stock);
		em.persist(st);
		
	}
}

