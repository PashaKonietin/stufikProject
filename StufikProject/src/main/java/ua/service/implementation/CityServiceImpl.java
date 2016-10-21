package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.City;
import ua.form.filter.CityFilterForm;
import ua.repository.CityRepository;
import ua.service.CityService;
import ua.service.implementation.specification.CityFilterAdapter;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public void save(City city) {
		cityRepository.save(city);
	}

	@Override
	public void delete(int id) {
		cityRepository.delete(id);
	}

	@Override
	public City findOne(int id) {
		return cityRepository.findOne(id);
	}

	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}

	@Override
	public Page<City> findAll(Pageable pageable) {
		return cityRepository.findAll(pageable);
	}

	@Override
	public Page<City> findAll(Pageable pageable, CityFilterForm filter) {
		return cityRepository.findAll(new CityFilterAdapter(filter), pageable);
	}

	public City findByName(String name) {
		return cityRepository.findByName(name);
	}

}
