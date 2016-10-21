package ua.service.implementation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Delivery;
import ua.service.DeliveryService;

public class DeliveryValidator implements Validator{
	
	@Autowired
	private DeliveryService deliveryService;
	
	public DeliveryValidator(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Delivery.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Delivery delivery = (Delivery) target;
		if(delivery.getId()==0)if(deliveryService.findByDeliveryCompany(delivery.getDeliveryCompany())!=null){
			errors.rejectValue("deliveryCompany", "", "Delivery Company with such name is already exist");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryCompany", "", "This field can`t be empty");
		if(delivery.getId()==0)if(deliveryService.findByDeliveryMethod(delivery.getDeliveryMethod())!=null){
			errors.rejectValue("deliveryMethod", "", "Delivery Method with such name is already exist");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryMethod", "", "This field can`t be empty");
	}

}
