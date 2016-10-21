package ua.service.implementation;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Client;
import ua.entity.Commodity;
import ua.service.CartService;
import ua.service.ClientService;
import ua.service.CommodityService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private ClientService clientService;

	@Override
	@Transactional
	public void addCommodity(int id, int clientId) {
		Client client = clientService.findById(clientId);
		List<Commodity> commodities = client.getCommodityes();
		Commodity commodity = commodityService.findOne(id);
		commodities.add(commodity);
	}

	@Override
	public void deleteCommodity(int id, int clientId) {
		Client client = clientService.findById(clientId);
		List<Commodity> commodities = commodityService.findAllCommoditiesByClient(clientId);
//		Commodity commodity2 = commodityService.findOne(id);
		Iterator<Commodity> iterator = commodities.iterator();		
		while(iterator.hasNext()){
			Commodity commodity = iterator.next();
			if(commodity.getId()==id){
				System.out.println("Test");
				iterator.remove();
			}
		}
//		commodities.removeIf((commodity) -> commodity.equals(commodity2));
		
	}

	@Override
	public BigDecimal totalPrice(int clientId) {
		Client client = clientService.findById(clientId);
		List<Commodity> commodities = client.getCommodityes();
		BigDecimal total = new BigDecimal(0);
		if(!commodities.isEmpty()){
			for (Commodity commodity : commodities) {
				total = total.add(commodity.getPrice());
			}
			return total;
		}
		return total;
	}

}
