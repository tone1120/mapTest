package net.kdigital.map.service;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.map.dto.TestDTO;
import net.kdigital.map.entity.ChartEntity;
import net.kdigital.map.entity.TestEntity;
import net.kdigital.map.repository.ChartRepository;
import net.kdigital.map.repository.TestRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {
	
	private final TestRepository testRepository; 
	
	private final ChartRepository chartRepository;

	/**
	 * 확인 당시 항구의 대기 중인 선박 리스트를 가져옴
	 * @param searchPort
	 * @return
	 */
	public List<TestEntity> selectWaiting(String searchPort, LocalDateTime clickTimeDate) { // import java.sql.Date;
		List<TestEntity> targetPort = null;

		
		// targetPort =  testRepository.findByPortCd(searchPort);
		targetPort = testRepository.findByPortCdAndAtaDtLessThanAndAtbDtGreaterThan(searchPort, clickTimeDate, clickTimeDate);
		
		
		List<TestDTO> dtoList = new ArrayList<>();
		
		targetPort.forEach((entity) -> dtoList.add(TestDTO.toDTO(entity)));
		
		return targetPort;
	}
	
	/**
	 * 확인 당시 항구의 작업중인 선박의 리스트 가져옴
	 * @param searchPort
	 * @param clickTimeDate
	 * @return
	 */
	public List<TestEntity> selectWorking(String searchPort, LocalDateTime clickTimeDate){
		List<TestEntity> targetPort = null;
		targetPort = testRepository.findByPortCdAndAtbDtLessThanAndAtdDtGreaterThan(searchPort, clickTimeDate, clickTimeDate);
	
		
		List<TestDTO> dtoList = new ArrayList<>();
		
		targetPort.forEach((entity) -> dtoList.add(TestDTO.toDTO(entity)));
		
		return targetPort;
	}

	/**
	 * 항구의 평균 수용량을 가져옴
	 * @param searchPort
	 * @return 
	 */
	public List<ChartEntity> findPort(String searchPort) {
		// 항구의 평균 수용량을 가져옴
		List<ChartEntity> portAvgCnt = chartRepository.findByPortCd(searchPort);
		
		return portAvgCnt;
		
	}

}
