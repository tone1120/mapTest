package net.kdigital.map.repository;



import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.map.dto.TestDTO;
import net.kdigital.map.entity.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Long> {

	// 항구 선별

	List<TestEntity> findByPortCdAndAtaDtLessThanAndAtbDtGreaterThan(String portCd, LocalDateTime clickTimeDate, LocalDateTime clickTimeDate2);

	List<TestEntity> findByPortCdAndAtbDtLessThanAndAtdDtGreaterThan(String searchPort, LocalDateTime clickTimeDate, LocalDateTime clickTimeDate2);
	


	
}
