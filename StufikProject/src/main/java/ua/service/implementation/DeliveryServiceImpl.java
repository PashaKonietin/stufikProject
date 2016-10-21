package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Delivery;
import ua.form.filter.DeliveryFilterForm;
import ua.repository.DeliveryRepository;
import ua.service.DeliveryService;
import ua.service.implementation.specification.DeliveryFilterAdapter;

@Service
public class DeliveryServiceImpl  implements DeliveryService{

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Override
	public void save(Delivery delivery) {
		deliveryRepository.save(delivery);
	}

	@Override
	public void delete(int id) {
		deliveryRepository.delete(id);
	}

	@Override
	public Delivery findById(int id) {
		return deliveryRepository.findOne(id);
	}

	@Override
	public Delivery findByDeliveryMethod(String deliveryMethod) {
		return deliveryRepository.findByDeliveryMethod(deliveryMethod);
	}

	@Override
	public Delivery findByDeliveryCompany(String deliveryCompany) {
		return deliveryRepository.findByDeliveryCompany(deliveryCompany);
	}

	@Override
	public List<Delivery> findAll() {
		return deliveryRepository.findAll();
	}

	@Override
	public Page<Delivery> findAll(Pageable pageable) {
		return deliveryRepository.findAll(pageable);
	}

	@Override
	public Page<Delivery> findAll(Pageable pageable, DeliveryFilterForm filter) {
		return deliveryRepository.findAll(new DeliveryFilterAdapter(filter), pageable);
	}

}
