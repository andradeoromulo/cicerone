-- DDL Cicerone

CREATE DATABASE cicerone
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE Category(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(250),
    study_guide VARCHAR(250),
    disabled BOOLEAN,
    order_position INTEGER,
    icon_path VARCHAR(100),
    color_hex_code VARCHAR(7),
    PRIMARY KEY (id)
);

CREATE TABLE Subcategory(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(250),
    study_guide VARCHAR(250),
    disabled BOOLEAN,
    order_position INTEGER,
    icon_path VARCHAR(100),
    color_hex_code VARCHAR(7),
    category_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_subcategory_category FOREIGN KEY (category_id)
        REFERENCES Category(id)
);

CREATE TABLE Course(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    time_to_finish_in_hours INTEGER NOT NULL,
    disabled BOOLEAN,
    target_audience VARCHAR(250),
    instructor VARCHAR(100) NOT NULL,
    program TEXT,
    skills TEXT,
    subcategory_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_course_subcategory FOREIGN KEY (subcategory_id)
        REFERENCES Subcategory(id)
);

CREATE TABLE Section(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    order_position INTEGER,
    disabled BOOLEAN,
    exam BOOLEAN,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_section_course FOREIGN KEY (course_id)
        REFERENCES Course(id)
);

CREATE TABLE Explanation(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    order_position INTEGER,
    disabled BOOLEAN,
    explanation_text TEXT NOT NULL,
    section_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_explanation_section FOREIGN KEY (section_id)
        REFERENCES Section(id)
);

CREATE TABLE Video(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    order_position INTEGER,
    disabled BOOLEAN,
    duration_in_minutes INTEGER,
    transcription TEXT,
    section_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_video_section FOREIGN KEY (section_id)
        REFERENCES Section(id)
);

CREATE TABLE Question(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    order_position INTEGER,
    disabled BOOLEAN,
    question_text TEXT NOT NULL,
    type INTEGER NOT NULL,
    section_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_question_section FOREIGN KEY (section_id)
        REFERENCES Section(id)
);

CREATE TABLE AnswerAlternative(
    id BIGINT AUTO_INCREMENT,
    description TEXT NOT NULL,
    explanation TEXT,
    order_position INTEGER,
    correct TINYINT NOT NULL,
    question_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_answer_alternative_question FOREIGN KEY (question_id)
        REFERENCES Section(id)
);