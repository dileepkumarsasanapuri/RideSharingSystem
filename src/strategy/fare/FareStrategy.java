package strategy.fare;

import java.io.Serializable;

public interface FareStrategy extends Serializable {
    double calculateFare(double distance);
}
