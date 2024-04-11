package net.kdigital.map.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.map.entity.ChartEntity;
import net.kdigital.map.repository.ChartRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChartService {
	private final ChartRepository chartRepository;
	
	public List<ChartEntity> chartSearch(String portCd) {
		List<ChartEntity> portList = chartRepository.findByPortCd(portCd);
		log.info("{}",portList);
		return portList;
	}

}
