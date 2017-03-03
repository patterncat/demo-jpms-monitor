package monitor.persistence.dtos;

import monitor.statistics.Statistics.LivenessQuota;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotas")
public final class LivenessQuotaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private long dataPointCount;
	private long aliveCount;

	private LivenessQuotaEntity() {
	}

	public static LivenessQuotaEntity from(LivenessQuota quota) {
		LivenessQuotaEntity entity = new LivenessQuotaEntity();
		entity.dataPointCount = quota.dataPointCount();
		entity.aliveCount = quota.aliveCount();
		return entity;
	}

	public LivenessQuota toLivenessQuota() {
		return LivenessQuota.with(dataPointCount, aliveCount);
	}

}
