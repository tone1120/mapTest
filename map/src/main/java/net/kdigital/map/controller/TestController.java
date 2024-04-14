package net.kdigital.map.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.map.dto.TestDTO;
import net.kdigital.map.entity.ChartEntity;
import net.kdigital.map.entity.TestEntity;
import net.kdigital.map.service.TestService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {
	private final TestService testService;
	
	/**
	 * 대기 선박 리스트 반환
	 * @param searchPort
	 * @param clickTime
	 * @return
	 */
	/*
	@GetMapping("/fun/waiting") // 컨트롤에서는 contextPath가 불필요
	@ResponseBody
	public List<TestEntity> waiting(
			@RequestParam(name="searchPort") String searchPort
			,@RequestParam(name="clickTime") String clickTime
			) {
		// 'yyyy-MM-dd HH:mm:ss' 형식의 clickTime을 파싱하기 위한 SimpleDateFormat 설정
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime clickTimeDate = LocalDateTime.parse(clickTime, dateTimeFormatter);
		List<TestEntity> targetPort = testService.selectWaiting(searchPort, clickTimeDate);
		
		return targetPort;
	}
	*/
	/**
	 * 작업중인 선박 리스트 반환
	 * @param searchPort
	 * @param clickTime
	 * @return
	 */
	@GetMapping("/fun/working")
	@ResponseBody
	public Map<String, List<?>> working(
			@RequestParam(name="searchPort") String searchPort
			,@RequestParam(name="clickTime") String clickTime
			){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime clickTimeDate = LocalDateTime.parse(clickTime, dateTimeFormatter);
		
		List<TestEntity> waitingVessels = testService.selectWaiting(searchPort, clickTimeDate);
		
		List<TestEntity> workingVessels = testService.selectWorking(searchPort, clickTimeDate);
		
		List<ChartEntity> portAvgCnt =  testService.findPort(searchPort);
        
		// 결과를 HashMap에 저장
        Map<String, List<?>> resultMap = new HashMap<>();
        resultMap.put("workingVessels", workingVessels);
        resultMap.put("waitingVessels", waitingVessels);
        resultMap.put("portAvgCnt", portAvgCnt);

        // 결과를 JSON 응답으로 직렬화하여 반환
        return resultMap;
	}
/*	
	@GetMapping("/fun/working")
	@ResponseBody
	public List<TestEntity> working(
			@RequestParam(name="searchPort") String searchPort
			,@RequestParam(name="clickTime") String clickTime
			){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime clickTimeDate = LocalDateTime.parse(clickTime, dateTimeFormatter);
		List<TestEntity> targetPort = testService.selectWorking(searchPort, clickTimeDate);
		
		List<ChartEntity> portAvgCnt =  testService.findPort(searchPort);
		
		return targetPort;
	}
*/
}
