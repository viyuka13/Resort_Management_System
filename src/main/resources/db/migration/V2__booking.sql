SET FOREIGN_KEY_CHECKS = 0;

-- ============================================================
-- RESERVATION
-- ============================================================

CREATE TABLE reservation (
    reservation_id CHAR(36) PRIMARY KEY,
    reservation_number VARCHAR(50) NOT NULL UNIQUE,

    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,

    status ENUM(
        'CREATED',
        'CONFIRMED',
        'CHECKED_IN',
        'CHECKED_OUT',
        'CANCELLED'
    ) NOT NULL DEFAULT 'CREATED',

    total_amount DECIMAL(12,2) DEFAULT 0.00,
    tax_amount DECIMAL(12,2) DEFAULT 0.00,
    discount_amount DECIMAL(12,2) DEFAULT 0.00,
    grand_total DECIMAL(12,2) DEFAULT 0.00,

    currency VARCHAR(10) DEFAULT 'INR',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT chk_reservation_dates
        CHECK (check_in_date < check_out_date)
);

CREATE INDEX idx_reservation_dates
ON reservation(check_in_date, check_out_date);

CREATE INDEX idx_reservation_status
ON reservation(status);


-- ============================================================
-- BOOKING_GUEST
-- ============================================================

CREATE TABLE booking_guest (
    booking_guest_id CHAR(36) PRIMARY KEY,
    reservation_id CHAR(36) NOT NULL,

    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    email VARCHAR(150),
    phone VARCHAR(20),

    is_primary_guest BOOLEAN DEFAULT FALSE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_booking_guest_reservation
        FOREIGN KEY (reservation_id)
        REFERENCES reservation(reservation_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_booking_guest_reservation
ON booking_guest(reservation_id);


-- ============================================================
-- RESERVATION_ADD_ON
-- ============================================================

CREATE TABLE reservation_add_on (
    reservation_add_on_id CHAR(36) PRIMARY KEY,
    reservation_id CHAR(36) NOT NULL,

    add_on_name VARCHAR(150) NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    unit_price DECIMAL(12,2) NOT NULL,
    total_price DECIMAL(12,2) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_add_on_reservation
        FOREIGN KEY (reservation_id)
        REFERENCES reservation(reservation_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_add_on_reservation
ON reservation_add_on(reservation_id);


-- ============================================================
-- RESERVATION_DAILY_RATE
-- ============================================================

CREATE TABLE reservation_daily_rate (
    reservation_daily_rate_id CHAR(36) PRIMARY KEY,
    reservation_id CHAR(36) NOT NULL,

    stay_date DATE NOT NULL,
    base_price DECIMAL(12,2) NOT NULL,
    final_price DECIMAL(12,2) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_daily_rate_reservation
        FOREIGN KEY (reservation_id)
        REFERENCES reservation(reservation_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_daily_rate_reservation
ON reservation_daily_rate(reservation_id);

CREATE INDEX idx_daily_rate_date
ON reservation_daily_rate(stay_date);


-- ============================================================
-- RESERVATION_ROOM_ASSIGNMENT
-- ============================================================

CREATE TABLE reservation_room_assignment (
    reservation_room_assignment_id CHAR(36) PRIMARY KEY,
    reservation_id CHAR(36) NOT NULL,

    room_id CHAR(36) NOT NULL,
    assigned_from DATE NOT NULL,
    assigned_to DATE NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_room_assignment_reservation
        FOREIGN KEY (reservation_id)
        REFERENCES reservation(reservation_id)
        ON DELETE CASCADE,

    CONSTRAINT chk_room_assignment_dates
        CHECK (assigned_from <= assigned_to)
);

CREATE INDEX idx_room_assignment_reservation
ON reservation_room_assignment(reservation_id);

CREATE INDEX idx_room_assignment_room
ON reservation_room_assignment(room_id);


-- ============================================================
-- RESERVATION_SERVICE_BOOKING
-- ============================================================

CREATE TABLE reservation_service_booking (
    reservation_service_booking_id CHAR(36) PRIMARY KEY,
    reservation_id CHAR(36) NOT NULL,

    service_name VARCHAR(150) NOT NULL,
    service_date DATE NOT NULL,
    service_time TIME,
    quantity INT DEFAULT 1,
    price DECIMAL(12,2) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_service_booking_reservation
        FOREIGN KEY (reservation_id)
        REFERENCES reservation(reservation_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_service_booking_reservation
ON reservation_service_booking(reservation_id);

CREATE INDEX idx_service_booking_date
ON reservation_service_booking(service_date);


SET FOREIGN_KEY_CHECKS = 1;
