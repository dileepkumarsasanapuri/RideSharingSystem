package strategy.fare;

public class SurgeFare implements FareStrategy {
        private final FareStrategy inner;
        private final double surgeFactor;

        public SurgeFare(FareStrategy inner, double surgeFactor) {
            if (inner == null) throw new IllegalArgumentException("inner strategy required");
            if (surgeFactor < 1.0) throw new IllegalArgumentException("surgeFactor must be >= 1");
            this.inner = inner;
            this.surgeFactor = surgeFactor;
        }

        @Override
        public double calculateFare(double distanceKm) {
            return inner.calculateFare(distanceKm) * surgeFactor;
        }
    }