package com.shopme.admin.brand;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shopme.common.entity.Brand;
import com.shopme.common.entity.User;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer> {

	@Query("select b from Brand b Where CONCAT(b.id,' ',b.name) LIKE %?1%")
	public Page<Brand> findAll(String keyword, Pageable pageable);

	public Brand findByNameAndId(String name, int id);

	public Brand findByName(String name);

	@Query("SELECT NEW Brand(b.id,b.name) FROM Brand b ORDER BY b.name ASC")
	public List<Brand> findAll();

}
