package monitor.persistence;

import monitor.persistence.dtos.StatisticsEntity;
import monitor.statistics.Statistics;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class StatisticsRepository {

	private final EntityManager entityManager;

	public StatisticsRepository(EntityManager entityManager) {
		this.entityManager = requireNonNull(entityManager);
	}

	public static StatisticsRepository initialize() {
		EntityManager entityManager = Persistence
				.createEntityManagerFactory("statistics-unit")
				.createEntityManager();
		return new StatisticsRepository(entityManager);
	}

	public Optional<Statistics> load() {
		return loadStatisticsEntity()
				.map(StatisticsEntity::toStatistics);
	}

	private Optional<StatisticsEntity> loadStatisticsEntity() {
		return Optional.ofNullable(entityManager.find(StatisticsEntity.class, 1));
	}

	public void store(Statistics statistics) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		storeStatistics(entityTransaction, StatisticsEntity.from(statistics));
		printStatistics(statistics);
	}

	private void storeStatistics(EntityTransaction entityTransaction, StatisticsEntity statistics) {
		entityTransaction.begin();
		entityManager.persist(statistics);
		entityTransaction.commit();
	}

	private void storeStatisticsSql(EntityTransaction entityTransaction, StatisticsEntity statistics) {
		entityTransaction.begin();
		entityManager.persist(statistics);
		entityTransaction.commit();
	}

	private void printStatistics(Statistics statistics) {
		System.out.printf("Total liveness: %s (from %d data points)%n",
				statistics.totalLivenessQuota().livenessQuota(),
				statistics.totalLivenessQuota().dataPointCount());
		statistics.livenessQuotaByService()
				.sorted(Comparator.comparing(Entry::getKey))
				.forEach(serviceLiveness ->
						System.out.printf(" * %s liveness: %s (from %d data points)%n",
								serviceLiveness.getKey(),
								serviceLiveness.getValue().livenessQuota(),
								serviceLiveness.getValue().dataPointCount()));
		System.out.println();
	}

}
