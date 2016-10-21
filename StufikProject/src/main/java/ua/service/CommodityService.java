package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Commodity;
import ua.form.CommodityForm;
import ua.form.filter.CommodityFilterForm;

public interface CommodityService {
	
	void save(CommodityForm form);
	
	CommodityForm findForForm(int id);
	
	void delete(int id);
	
	Commodity findOne(int id);
	
	List<Commodity> findAll();
	
	List<Commodity> findAllCommoditiesByClient(int id);
	
	Page<Commodity> findAll(Pageable pageable, CommodityFilterForm filter);

	Page<Commodity> findAll(Pageable pageable);
	
	Page<Commodity> findSub(int id, Pageable pageable);
	
	Commodity findCommodityInited(int id);

}
