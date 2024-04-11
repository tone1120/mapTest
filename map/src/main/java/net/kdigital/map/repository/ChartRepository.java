package net.kdigital.map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.map.entity.ChartEntity;

public interface ChartRepository extends JpaRepository<ChartEntity, Long> {
	List<ChartEntity> findByPortCd(String portCd);
}
