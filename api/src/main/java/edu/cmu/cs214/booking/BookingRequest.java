package edu.cmu.cs214.booking;

/**
 * An immutable request to book a room, passed to
 * {@link BookingApi#createBooking(BookingRequest)}.
 *
 * <p>
 * Build one with {@link #builder(String, long, long)}. The room and the
 * half-open minute range {@code [startMinute, endMinute)} are required; the
 * waitlist key and notes are optional and default to null. This class does not
 * validate its fields: {@code createBooking} rejects a null room or an empty
 * range, as documented there.
 */
public final class BookingRequest {

    private final String roomId;
    private final long startMinute;
    private final long endMinute;
    private final String waitlistKey;
    private final String notes;

    private BookingRequest(Builder builder) {
        this.roomId = builder.roomId;
        this.startMinute = builder.startMinute;
        this.endMinute = builder.endMinute;
        this.waitlistKey = builder.waitlistKey;
        this.notes = builder.notes;
    }

    /** Starts a request for the given room and half-open range. */
    public static Builder builder(String roomId, long startMinute, long endMinute) {
        return new Builder(roomId, startMinute, endMinute);
    }

    public String getRoomId() {
        return roomId;
    }

    public long getStartMinute() {
        return startMinute;
    }

    public long getEndMinute() {
        return endMinute;
    }

    /** The caller's waitlist key, or null to decline waitlisting. */
    public String getWaitlistKey() {
        return waitlistKey;
    }

    /** The caller's free-text notes, or null for none. */
    public String getNotes() {
        return notes;
    }

    /** Builder for {@link BookingRequest}. */
    public static final class Builder {

        private final String roomId;
        private final long startMinute;
        private final long endMinute;
        private String waitlistKey;
        private String notes;

        private Builder(String roomId, long startMinute, long endMinute) {
            this.roomId = roomId;
            this.startMinute = startMinute;
            this.endMinute = endMinute;
        }

        /** Sets the waitlist key; null (the default) declines waitlisting. */
        public Builder waitlistKey(String waitlistKey) {
            this.waitlistKey = waitlistKey;
            return this;
        }

        /** Sets the free-text notes; null (the default) means none. */
        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public BookingRequest build() {
            return new BookingRequest(this);
        }
    }
}
