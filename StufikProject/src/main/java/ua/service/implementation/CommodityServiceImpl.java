package ua.service.implementation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Commodity;
import ua.form.CommodityForm;
import ua.form.filter.CommodityFilterForm;
import ua.repository.CommodityRepository;
import ua.service.CommodityService;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.implementation.specification.CommodityFilterAdapter;

@Service
public class CommodityServiceImpl implements CommodityService{

	@Resource
	private CommodityRepository commodityRepository;
	
	@Autowired
	private FileWriter fileWriter;
	
	@Override
	public void save(CommodityForm form) {
		Commodity commodity = new Commodity();
		commodity.setBrand(form.getBrand());
		commodity.setClients(form.getClients());
		commodity.setSubCategory(form.getSubCategory());
		commodity.setColor(form.getColor());
		commodity.setMaterial(form.getMaterial());
		commodity.setGuarantee(Integer.parseInt(form.getGuarantee()));
		commodity.setId(form.getId());
		commodity.setManager(form.getManager());
		commodity.setModelName(form.getModelName());
		commodity.setPrice(form.getPrice());
		commodity.setPath(form.getPath());
		commodity.setVersion(form.getVersion());
		commodity.setDescription(form.getDescription());
//		commodity.setComoditiesInStock(Integer.parseInt(form.getCommoditiesInStock()));
		commodityRepository.saveAndFlush(commodity);
		String extension = fileWriter.write(Folder.COMMODITY, form.getMultipartFile(), commodity.getId());
		if(extension!=null){
			commodity.setVersion(form.getVersion()+1);
			commodity.setPath(extension);
			commodityRepository.save(commodity);
		}
	}

	@Override
	public CommodityForm findForForm(int id) {
		Commodity commodity = commodityRepository.findOneCommodityInited(id);
		CommodityForm form = new CommodityForm();
		form.setBrand(commodity.getBrand());
		form.setClients(commodity.getClients());
		form.setSubCategory(commodity.getSubCategory());
		form.setColor(commodity.getColor());
		form.setMaterial(commodity.getMaterial());
		form.setGuarantee(String.valueOf(commodity.getGuarantee()));
		form.setId(commodity.getId());
		form.setManager(commodity.getManager());
		form.setModelName(commodity.getModelName());
		form.setPath(commodity.getPath());
		form.setVersion(commodity.getVersion());
		form.setDescription(commodity.getDescription());
		form.setPrice(commodity.getPrice());
//		form.setCommoditiesInStock(String.valueOf(commodity.getComoditiesInStock()));
		return form;
	}

	@Override
	public void delete(int id) {
		commodityRepository.delete(id);
	}

	@Override
	public Commodity findOne(int id) {
		return commodityRepository.findOne(id);
	}

	@Override
	public List<Commodity> findAll() {
		return commodityRepository.findAll();
	}

	@Override
	public Page<Commodity> findAll(Pageable pageable, CommodityFilterForm filter) {
		return commodityRepository.findAll(new CommodityFilterAdapter(filter), pageable);
	}

	@Override
	public Page<Commodity> findAll(Pageable pageable) {
		return commodityRepository.findAll(pageable);
	}

	public Page<Commodity> findSub(int id, Pageable pageable) {
		return commodityRepository.findBySub(id, pageable);
	}

	@Override
	public Commodity findCommodityInited(int id) {
		return commodityRepository.findOneCommodityInited(id);
	}

	@Override
	public List<Commodity> findAllCommoditiesByClient(int id) {
		return commodityRepository.findAllCommoditiesByClient(id);
	}



}
