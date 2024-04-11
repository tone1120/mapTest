package net.kdigital.map.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.map.entity.ChartEntity;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ChartDTO {
	private Long seq;
	private String portCd;
	private double vesselCnt;

	public static ChartDTO toDTO(ChartEntity chartEntity) {
		return ChartDTO.builder()
				.seq(chartEntity.getSeq())
				.portCd(chartEntity.getPortCd())
				.vesselCnt(chartEntity.getVesselCnt())
				.build();
	}










}
