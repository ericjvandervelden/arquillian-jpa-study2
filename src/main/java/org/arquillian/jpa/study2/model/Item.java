package org.arquillian.jpa.study2.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;


@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version
	@Column(name = "version")
	private int version;

//	@NaturalId
	private String name;

	@OneToMany(mappedBy = "item",cascade=CascadeType.REMOVE)
	@Column(updatable=false)
	private Set<Bid> bids = new HashSet<>();
	
//	@ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE})
//	@ManyToMany(cascade=CascadeType.ALL)
//	@ManyToMany(cascade=CascadeType.PERSIST)
//	@ManyToMany(cascade=CascadeType.MERGE)
//	@JoinTable(joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "category_id") })
	@ManyToMany
//	@ManyToMany(mappedBy="items")
	private Set<Category>categories=new HashSet<>();
	
	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(String name) {
		this.name = name;
	}
	
	public void addCategory(Category category) {
		categories.add(category);
		category.getItems().add(this);
	}
	public void removeCategory(Category category) {
		categories.remove(category);
		category.getItems().remove(this);
	}
	
	public Item(String name,Set<Category>categories) {
		this.name=name;
		this.categories=categories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public void addBid(Bid bid) {

	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		return result;
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (!(obj instanceof Item)) {
//			return false;
//		}
//		Item other = (Item) obj;
//		if (id != null) {
//			if (id.equals(other.id)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return prime;
//	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return  true;
		if(obj==null)
			return false;
		if(this.getClass()!=obj.getClass())
			return false;
		return Objects.equals(name, ((Item)obj).getName());
		
	}
	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
}