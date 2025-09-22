package model;

import java.io.Serializable;

public enum RideStatus implements Serializable {
    REQUESTED,
    ONGOING,
    COMPLETED,
    CANCELLED
}
