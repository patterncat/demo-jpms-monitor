package monitor.persistence.dtos;

import monitor.statistics.Statistics;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class StatisticsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "quota_id", updatable = false)
	private LivenessQuotaEntity totalLivenessQuota;

	private StatisticsEntity() {
	}

	public static StatisticsEntity from(Statistics statistics) {
		StatisticsEntity entity = new StatisticsEntity();
		entity.totalLivenessQuota = LivenessQuotaEntity.from(statistics.totalLivenessQuota());
		return entity;
	}

	public Statistics toStatistics() {
		return Statistics.with(totalLivenessQuota.toLivenessQuota());
	}

}
