package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Category;
import ua.form.CategoryForm;
import ua.form.filter.CategoryFilterForm;
import ua.repository.CategoryRepository;
import ua.service.CategoryService;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.implementation.specification.CategoryFilterAdapter;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private FileWriter fileWriter;
	
	@Override
	public void save(CategoryForm categoryForm) {
		Category category = new Category();
		category.setId(categoryForm.getId());
		category.setName(categoryForm.getName());
		category.setPath(categoryForm.getPath());
		category.setVersion(categoryForm.getVersion());
		categoryRepository.saveAndFlush(category);
		
		String extension = fileWriter.write(Folder.CATEGORY, categoryForm.getMultipartFile(), category.getId());
		if(extension != null){
			category.setVersion(categoryForm.getVersion()+1);
			category.setPath(extension);
			categoryRepository.save(category);
		}
	}

	@Override
	public void delete(int id) {
		categoryRepository.delete(id);
	}

	@Override
	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Page<Category> findAll(Pageable pageable, CategoryFilterForm filter) {
		return categoryRepository.findAll(new CategoryFilterAdapter(filter), pageable);
	}

	@Override
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	
	
}