package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.ModelName;

public interface ModelNameRepository extends JpaRepository<ModelName, Integer>, JpaSpecificationExecutor<ModelName> {
	
	ModelName findByName(String name);

}
