/* ---------- 1. المستخدمون والأدوار ---------- */
CREATE TABLE app_user (
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(120) NOT NULL UNIQUE,
    password    TEXT         NOT NULL          -- كلمة المرور بعد التشفير
);

-- لأنك تستخدم @ElementCollection لأدوار الـ User Enum
CREATE TABLE app_user_roles (
    app_user_id BIGINT       NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    roles       VARCHAR(32)  NOT NULL,                 -- ADMIN | USER
    PRIMARY KEY (app_user_id, roles)
);

/* ---------- 2. المسرح والهيكل الداخلى ---------- */
CREATE TABLE theater (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(150) NOT NULL,
    city        VARCHAR(120),
    capacity    INT          NOT NULL
);

CREATE TABLE section (
    id          BIGSERIAL PRIMARY KEY,
    theater_id  BIGINT       NOT NULL REFERENCES theater(id) ON DELETE CASCADE,
    name        VARCHAR(100) NOT NULL,
    seat_count  INT          NOT NULL
);

CREATE TABLE seat (
    id          BIGSERIAL PRIMARY KEY,
    section_id  BIGINT       NOT NULL REFERENCES section(id) ON DELETE CASCADE,
    row_label   VARCHAR(10)  NOT NULL,
    seat_number INT          NOT NULL,
    UNIQUE (section_id, row_label, seat_number)        -- لمنع تكرار المقعد
);

/* ---------- 3. العروض (Event) ---------- */
CREATE TABLE event (
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(200) NOT NULL,
    description  TEXT,
    start_time   TIMESTAMP    NOT NULL,
    end_time     TIMESTAMP    NOT NULL,
    theater_id   BIGINT       NOT NULL REFERENCES theater(id) ON DELETE CASCADE
);

-- ربط كل مقعد بحالته داخل حدث معيّن
CREATE TABLE event_seat (
    event_id BIGINT NOT NULL REFERENCES event(id) ON DELETE CASCADE,
    seat_id  BIGINT NOT NULL REFERENCES seat(id)  ON DELETE CASCADE,
    is_free  BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (event_id, seat_id)
);

/* ---------- 4. الحجوزات ---------- */
CREATE TABLE booking (
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT    NOT NULL REFERENCES app_user(id),
    event_id     BIGINT    NOT NULL REFERENCES event(id) ON DELETE CASCADE,
    booked_at    TIMESTAMP NOT NULL DEFAULT NOW(),
    status       VARCHAR(20) NOT NULL              -- NEW | PAID | CANCELLED
);

CREATE TABLE booking_seat (
    booking_id BIGINT NOT NULL REFERENCES booking(id) ON DELETE CASCADE,
    seat_id    BIGINT NOT NULL REFERENCES seat(id),
    price      NUMERIC(8,2) NOT NULL,
    PRIMARY KEY (booking_id, seat_id)
);

/* ---------- 5. مؤشرات مفيدة ---------- */
CREATE INDEX idx_event_start_time  ON event(start_time);
CREATE INDEX idx_event_seat_free   ON event_seat(event_id, is_free);
CREATE INDEX idx_booking_user      ON booking(user_id);
