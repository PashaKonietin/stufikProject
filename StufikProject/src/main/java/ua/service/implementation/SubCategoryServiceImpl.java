package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.SubCategory;
import ua.form.SubCategoryForm;
import ua.form.filter.SubCategoryFilterForm;
import ua.repository.SubCategoryRepository;
import ua.service.SubCategoryService;
import ua.service.implementation.specification.SubCategoryFilterAdapter;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public void save(SubCategoryForm form) {
		SubCategory subCategory = new SubCategory();
		subCategory.setCategory(form.getCategory());
		subCategory.setName(form.getName());
		subCategory.setId(form.getId());
		subCategoryRepository.save(subCategory);
	}

	@Override
	public SubCategoryForm findFormForm(int id) {
		SubCategory subCategory = subCategoryRepository.findOneSubCategoryInited(id);
		SubCategoryForm form = new SubCategoryForm();
		form.setCategory(subCategory.getCategory());
		form.setId(subCategory.getId());
		form.setName(subCategory.getName());
		return form;
	}

	@Override
	public void deleteById(int id) {
		subCategoryRepository.delete(id);
	}

	@Override
	public SubCategory findOne(int id) {
		return subCategoryRepository.findOne(id);
	}

	@Override
	public List<SubCategory> findAll() {
		return subCategoryRepository.findAll();
	}

	@Override
	public Page<SubCategory> findAll(Pageable pageable) {
		return subCategoryRepository.findAll(pageable);
	}

	@Override
	public Page<SubCategory> findAll(Pageable pageable, SubCategoryFilterForm filter) {
		return subCategoryRepository.findAll(new SubCategoryFilterAdapter(filter), pageable);
	}

}
