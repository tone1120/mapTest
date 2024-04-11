package net.kdigital.map.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.map.dto.ChartDTO;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name="working_vessel_cnt")
public class ChartEntity {
	
	@Id
	@Column(name = "seq")
	private Long seq;
	
	@Column(name = "port_cd")
	private String portCd;
	
	@Column(name = "vessel_cnt")
	private double vesselCnt;
	
	public static ChartEntity toEntity(ChartDTO chartDTO) {
		return ChartEntity.builder()
				.seq(chartDTO.getSeq())
				.portCd(chartDTO.getPortCd())
				.vesselCnt(chartDTO.getVesselCnt())
				.build();
	}
}
