package org.arquillian.jpa.study2.dao;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.arquillian.jpa.study2.model.Category;
import org.arquillian.jpa.study2.model.Item;

/**
 * DAO for Item
 */
@Stateless
public class ItemDao {

	@PersistenceContext(unitName = "arquillian-jpa-study2-persistence-unit")
	private EntityManager em;

	public void create(Item item) {
		em.persist(item);
	}

	public void create(Item item, Set<Category> categories) {
		for (Category category : categories) {
			if (!em.contains(category)) {
				em.merge(category);
			}
		}
		item.setCategories(categories);
		for (Category category : categories) {
			category.getItems().add(item);
		}
		em.persist(item);
	}

	public void delete(Item item) {
		if (!em.contains(item)) {
			item = em.merge(item);
		}
		em.remove(item);
	}

	public Item getForName(String name) {
		TypedQuery<Item> query = em.createQuery("select i from Item i where i.name=?1", Item.class);
		query.setParameter(1, name);
		List<Item> items = query.getResultList();
		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);

	}

	public void deleteById(Long id) {
		Item entity = em.find(Item.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Item findById(Long id) {
		return em.find(Item.class, id);
	}

	public Item update(Item entity) {
		return em.merge(entity);
	}

	public List<Item> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Item> findAllQuery = em.createQuery("SELECT DISTINCT i FROM Item i ORDER BY i.id", Item.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
