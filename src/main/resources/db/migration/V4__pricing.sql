-- V1__create_rate_plan_and_rate_history.sql
-- MySQL 8+ migration

SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------------
-- RATE_PLAN
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS rate_plan (
    rate_plan_id CHAR(36) PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    base_price DECIMAL(12,2) NOT NULL CHECK (base_price >= 0),
    cancellation_policy TEXT,
    is_refundable BOOLEAN DEFAULT TRUE,
    min_stay_nights INT DEFAULT 1 CHECK (min_stay_nights >= 0),
    max_stay_nights INT DEFAULT 0 CHECK (max_stay_nights >= 0),
    valid_from DATE,
    valid_to DATE,

    created_by CHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_by CHAR(36),
    modified_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX idx_rate_plan_validity 
ON rate_plan (valid_from, valid_to);

-- ------------------------------------------------------------
-- RATE_HISTORY
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS rate_history (
    rate_history_id CHAR(36) PRIMARY KEY,
    rate_plan_id CHAR(36) NOT NULL,
    season_name VARCHAR(150),
    description TEXT,
    price DECIMAL(12,2) NOT NULL CHECK (price >= 0),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,

    created_by CHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_by CHAR(36),
    modified_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_rate_history_rate_plan
        FOREIGN KEY (rate_plan_id)
        REFERENCES rate_plan(rate_plan_id)
        ON DELETE CASCADE,

    CONSTRAINT chk_rate_history_dates
        CHECK (start_date <= end_date)
);

CREATE INDEX idx_rate_history_rate_plan 
ON rate_history (rate_plan_id);

CREATE INDEX idx_rate_history_dates 
ON rate_history (start_date, end_date);

-- ------------------------------------------------------------
-- PREVENT DATE OVERLAP TRIGGER (IMPORTANT)
-- ------------------------------------------------------------

DELIMITER $$

CREATE TRIGGER trg_rate_history_no_overlap
BEFORE INSERT ON rate_history
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1 FROM rate_history
        WHERE rate_plan_id = NEW.rate_plan_id
          AND (
                NEW.start_date <= end_date
            AND NEW.end_date >= start_date
          )
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Overlapping date range for this Rate Plan';
    END IF;
END$$

CREATE TRIGGER trg_rate_history_no_overlap_update
BEFORE UPDATE ON rate_history
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1 FROM rate_history
        WHERE rate_plan_id = NEW.rate_plan_id
          AND rate_history_id <> NEW.rate_history_id
          AND (
                NEW.start_date <= end_date
            AND NEW.end_date >= start_date
          )
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Overlapping date range for this Rate Plan';
    END IF;
END$$

DELIMITER ;

SET FOREIGN_KEY_CHECKS = 1;
