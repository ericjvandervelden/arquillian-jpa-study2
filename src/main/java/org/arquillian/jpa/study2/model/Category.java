package org.arquillian.jpa.study2.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Version;

@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Version
	@Column(name = "version")
	private int version;

//	@ManyToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST})
//	@JoinTable(joinColumns = { @JoinColumn(name = "category_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "item_id") })
//	@ManyToMany
	@ManyToMany(mappedBy="categories")
	private Set<Item> items = new HashSet<>();
	
	public void addItem(Item item) {
		items.add(item);
		item.getCategories().add(this);
	}
	public void removeItem(Item item) {
		items.remove(item);
		item.getCategories().remove(this);
	}
	
	private String name;
	
	public Category() {
		
	}
	public Category(String name) {
		this.setName(name);
		
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
//		if (!(obj instanceof Category)) {
//			return false;
//		}
//		Category other = (Category) obj;
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

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}