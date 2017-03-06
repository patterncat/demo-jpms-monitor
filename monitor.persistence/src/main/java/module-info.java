module monitor.persistence {
	requires hibernate.jpa;
	requires monitor.statistics;

	exports monitor.persistence;
	exports monitor.persistence.dtos;
}
