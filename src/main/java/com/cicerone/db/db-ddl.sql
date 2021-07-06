-- DDL Cicerone

CREATE TABLE categories(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL,
    description VARCHAR(150),
    study_guide VARCHAR(250),
    disabled TINYINT,
    order_position INTEGER,
    icon_path VARCHAR(100),
    color_hex_code VARCHAR(7),
    PRIMARY KEY (id)
);

CREATE TABLE subcategories(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL,
    description VARCHAR(150),
    study_guide VARCHAR(250),
    disabled TINYINT,
    order_position INTEGER,
    icon_path VARCHAR(100),
    color_hex_code VARCHAR(7),
    category_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_subcategories_categories FOREIGN KEY (category_id)
        REFERENCES categories(id)
);

CREATE TABLE courses(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL,
    time_to_finish_in_hours INTEGER,
    disabled TINYINT,
    target_audience VARCHAR(150),
    instructor VARCHAR(100),
    program VARCHAR(150),
    skills VARCHAR(150),
    subcategory_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_courses_subcategories FOREIGN KEY (subcategory_id)
        REFERENCES subcategories(id)
);

CREATE TABLE sections(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL,
    order_position INTEGER,
    disabled TINYINT,
    exam TINYINT,
    course_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_sections_courses FOREIGN KEY (course_id)
        REFERENCES courses(id)
);

CREATE TABLE explanations(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL,
    order_position INTEGER,
    disabled TINYINT,
    explanation_text TEXT,
    section_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_explanations_sections FOREIGN KEY (section_id)
        REFERENCES sections(id)
);

CREATE TABLE videos(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL,
    order_position INTEGER,
    disabled TINYINT,
    duration_in_minutes INTEGER,
    transcription TEXT,
    section_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_videos_sections FOREIGN KEY (section_id)
        REFERENCES sections(id)
);

CREATE TABLE questions(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL,
    order_position INTEGER,
    disabled TINYINT,
    question_text TEXT,
    type INTEGER,
    section_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_questions_sections FOREIGN KEY (section_id)
        REFERENCES sections(id)
);

CREATE TABLE answer_alternatives(
    id BIGINT AUTO_INCREMENT,
    description TEXT,
    explanation TEXT,
    order_position INTEGER,
    correct TINYINT,
    question_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_answer_alternatives_questions FOREIGN KEY (question_id)
        REFERENCES sections(id)
);