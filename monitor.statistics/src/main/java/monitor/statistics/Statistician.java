package monitor.statistics;

import monitor.observer.DiagnosticDataPoint;
import stats.fancy.FancyStats;

import java.lang.reflect.Layer;
import java.util.stream.Stream;

public class Statistician {

	private final boolean isFancyAvailable;

	public Statistician() {
		isFancyAvailable = checkFancyStats();
	}

	private boolean checkFancyStats() {
		boolean isFancyAvailable = Layer.boot()
				.configuration()
				.findModule("stats.fancy")
				.isPresent();
		String message = "Module 'stats.fancy' is"
				+ (isFancyAvailable ? " " : " not ")
				+ "available.";
		System.out.println(message);
		return isFancyAvailable;
	}

	public Statistics emptyStatistics() {
		return Statistics.create();
	}

	public Statistics compute(Statistics currentStats, Iterable<DiagnosticDataPoint> dataPoints) {
		if (isFancyAvailable) {
			// here, fancystats could actually be used
			System.out.println(FancyStats.COPYRIGHT);
			return emptyStatistics();
		} else {
			Statistics finalStats = currentStats;
			for (DiagnosticDataPoint dataPoint : dataPoints)
				finalStats = finalStats.merge(dataPoint);
			return finalStats;
		}
	}

}
