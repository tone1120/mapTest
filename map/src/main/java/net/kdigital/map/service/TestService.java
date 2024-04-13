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
import net.kdigital.map.entity.TestEntity;
import net.kdigital.map.repository.TestRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {
	
	private final TestRepository testRepository;

	/**
	 * 입력된 PORT_CD와 같은 포트코드를 가진 것을 리스트로 가져옴
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
	
	public List<TestEntity> selectWorking(String searchPort, LocalDateTime clickTimeDate){
		List<TestEntity> targetPort = null;
		targetPort = testRepository.findByPortCdAndAtbDtLessThanAndAtdDtGreaterThan(searchPort, clickTimeDate, clickTimeDate);
		
		
		List<TestDTO> dtoList = new ArrayList<>();
		
		targetPort.forEach((entity) -> dtoList.add(TestDTO.toDTO(entity)));
		
		return targetPort;
	}

}
