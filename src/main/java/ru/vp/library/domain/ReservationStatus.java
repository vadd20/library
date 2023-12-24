package ru.vp.library.domain;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.24
 */
public enum ReservationStatus {
    ACTIVE, INACTIVE;

    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Активно";
            case INACTIVE:
                return "Недействительно";
            default:
                throw new IllegalArgumentException();
        }
    }
}
