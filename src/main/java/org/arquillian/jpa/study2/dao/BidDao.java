package org.arquillian.jpa.study2.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.arquillian.jpa.study2.model.Bid;
import org.arquillian.jpa.study2.model.Item;

/**
 * DAO for Bid
 */
@Stateless
public class BidDao {
	
	@PersistenceContext(unitName = "arquillian-jpa-study2-persistence-unit")
	private EntityManager em;

	public void create(Bid bid,Item item) {
		if(!em.contains(item)) {
			item=em.merge(item);
		}
		item.getBids().add(bid);
		bid.setItem(item);
		em.persist(bid);
	}
	
	public void delete(Bid bid) {
		if (!em.contains(bid)) {
			bid=em.merge(bid);
		}
		em.remove(bid);
	}

	public void deleteById(Long id) {
		Bid entity = em.find(Bid.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Bid findById(Long id) {
		return em.find(Bid.class, id);
	}

	public Bid update(Bid entity) {
		return em.merge(entity);
	}

	public List<Bid> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Bid> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT b FROM Bid b LEFT JOIN FETCH b.item ORDER BY b.id",
						Bid.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
