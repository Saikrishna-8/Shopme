package com.shopme.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 128, nullable = false, unique = true)
	private String name;
	@Column(length = 64, nullable = false, unique = true)
	private String alias;
	@Column(length = 128, nullable = false)
	private String image;

	private boolean enabled;

	@Column(name = "all_parent_ids", length = 256, nullable = true)
	private String allParentIDs;

	@OneToOne
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	@OrderBy("name asc")
	private Set<Category> children = new HashSet<>();

	public Category() {
	}

	public Category(String name, String alias, String image) {
		this.name = name;
		this.alias = alias;
		this.image = image;
	}

	public Category(String name, String alias, String image, Category category) {
		this.name = name;
		this.alias = alias;
		this.image = image;
		this.parent = category;
	}

	public Category(Integer id, String name, String alias, String image, boolean enabled, Category parent,
			Set<Category> children) {
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.image = image;
		this.enabled = enabled;
		this.parent = parent;
		this.children = children;
	}

	public Category(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAllParentIDs() {
		return allParentIDs;
	}

	public void setAllParentIDs(String allParentIDs) {
		this.allParentIDs = allParentIDs;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}

	@Transient
	public static Category CopyIdAndName(Category cat, String str) {
		Category cat1 = new Category();
		cat1.setId(cat.getId());
		cat1.setName(str + cat.getName());
		return cat1;
	}

	@Transient
	public String getPhotosImagePath() {
		if (id == null)
			return "/images/image-thumbnail.png";
		return "/category-images/" + this.id + "/" + this.image;
	}

	@Transient
	public static Category copyAll(Category cat, String str) {
		Category cat1 = new Category();
		cat1.setId(cat.getId());
		cat1.setName(str + cat.getName());
		cat1.setAlias(cat.getAlias());
		cat1.setChildren(cat.getChildren());
		cat1.setEnabled(cat.isEnabled());
		cat1.setImage(cat.getImage());
		cat1.setParent(cat.getParent());
		cat1.setHasChildren(cat.getChildren().size() > 0);
		return cat1;
	}

	@Transient
	public boolean hasChildren;

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
