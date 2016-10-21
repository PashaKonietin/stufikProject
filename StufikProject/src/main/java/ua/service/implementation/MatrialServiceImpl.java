package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Material;
import ua.form.filter.MaterialFilterForm;
import ua.repository.MaterialRepository;
import ua.service.MaterialService;
import ua.service.implementation.specification.MaterialFilterAdapter;

@Service
public class MatrialServiceImpl implements MaterialService {

	@Autowired
	private MaterialRepository materialRepository;
	
	@Override
	public void save(Material material) {
		materialRepository.save(material);
	}

	@Override
	public void delete(int id) {
		materialRepository.delete(id);
	}

	@Override
	public Material findOne(int id) {
		return materialRepository.findOne(id);
	}

	@Override
	public List<Material> findAll() {
		return materialRepository.findAll();
	}

	@Override
	public Page<Material> findAll(Pageable pageable) {
		return materialRepository.findAll(pageable);
	}

	@Override
	public Page<Material> findAll(Pageable pageable, MaterialFilterForm filter) {
		return materialRepository.findAll(new MaterialFilterAdapter(filter), pageable);
	}

	@Override
	public Material findByName(String name) {
		return materialRepository.findByName(name);
	}

}
