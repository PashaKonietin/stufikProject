package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Brand;
import ua.form.filter.BrandFilterForm;
import ua.repository.BrandRepository;
import ua.service.BrandService;
import ua.service.implementation.specification.BrandFilterAdapter;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	public void save(Brand brand) {
		brandRepository.save(brand);
	}

	public void delete(int id) {
		brandRepository.delete(id);
	}

	public Brand findOne(int id) {
		return brandRepository.findOne(id);
	}

	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	public Page<Brand> findAll(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}

	public Page<Brand> findAll(Pageable pageable, BrandFilterForm filter) {
		return brandRepository.findAll(new BrandFilterAdapter(filter), pageable);
	}

	public Brand findByName(String name) {
		return brandRepository.findByName(name);
	}

}
