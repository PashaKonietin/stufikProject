package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.ModelName;
import ua.form.filter.ModelNameFilterForm;
import ua.repository.ModelNameRepository;
import ua.service.ModelNameService;
import ua.service.implementation.specification.ModelNameFilterAdapter;

@Service
public class ModelNameServiceImpl implements ModelNameService {

	@Autowired
	private ModelNameRepository modelNameRepository;
	
	@Override
	public void save(ModelName modelName) {
		modelNameRepository.save(modelName);
	}

	@Override
	public void delete(int id) {
		modelNameRepository.delete(id);
	}

	@Override
	public ModelName findByName(String name) {
		return modelNameRepository.findByName(name);
	}
	
	@Override
	public ModelName findOne(int id) {
		return modelNameRepository.findOne(id);
	}

	@Override
	public List<ModelName> findAll() {
		return modelNameRepository.findAll();
	}

	@Override
	public Page<ModelName> findAll(Pageable pageable) {
		return modelNameRepository.findAll(pageable);
	}

	@Override
	public Page<ModelName> findAll(Pageable pageable, ModelNameFilterForm filter) {
		return modelNameRepository.findAll(new ModelNameFilterAdapter(filter), pageable);
	}

}
