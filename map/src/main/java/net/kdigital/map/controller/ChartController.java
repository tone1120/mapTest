package net.kdigital.map.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.map.entity.ChartEntity;
import net.kdigital.map.service.ChartService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChartController {
	private final ChartService chartService;
	
	@GetMapping("/chart/chartSearch") // 컨트롤에서는 contextPath가 불필요
	@ResponseBody
	public ChartEntity chartSearch(
			@RequestParam(name ="portCd") String portCd
			) {
		
		List<ChartEntity> searchPort = chartService.chartSearch(portCd);
		return searchPort.get(0);
	}
	
}
