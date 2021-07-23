-- DDL Cicerone

CREATE DATABASE cicerone
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE Category(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(250),
    studyGuide VARCHAR(250),
    disabled BOOLEAN,
    orderPosition INTEGER,
    iconPath VARCHAR(100),
    colorHexCode VARCHAR(7),
    PRIMARY KEY (id)
);

CREATE TABLE Subcategory(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(250),
    studyGuide VARCHAR(250),
    disabled BOOLEAN,
    orderPosition INTEGER,
    iconPath VARCHAR(100),
    colorHexCode VARCHAR(7),
    categoryId BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_subcategory_category FOREIGN KEY (categoryId)
        REFERENCES Category(id)
);

CREATE TABLE Course(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL UNIQUE,
    timeToFinishInHours INTEGER NOT NULL,
    disabled BOOLEAN,
    targetAudience VARCHAR(250),
    instructor VARCHAR(100) NOT NULL,
    program TEXT,
    skills TEXT,
    subcategoryId BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_course_subcategory FOREIGN KEY (subcategoryId)
        REFERENCES Subcategory(id)
);

CREATE TABLE Section(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL UNIQUE,
    orderPosition INTEGER,
    disabled BOOLEAN,
    exam BOOLEAN,
    courseId BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_section_course FOREIGN KEY (courseId)
        REFERENCES Course(id)
);

CREATE TABLE Explanation(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL UNIQUE,
    orderPosition INTEGER,
    disabled BOOLEAN,
    explanationText TEXT NOT NULL,
    sectionId BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_explanation_section FOREIGN KEY (sectionId)
        REFERENCES Section(id)
);

CREATE TABLE Video(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL UNIQUE,
    orderPosition INTEGER,
    disabled BOOLEAN,
    durationInMinutes INTEGER,
    transcription TEXT,
    sectionId BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_video_section FOREIGN KEY (sectionId)
        REFERENCES Section(id)
);

CREATE TABLE Question(
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL UNIQUE,
    orderPosition INTEGER,
    disabled BOOLEAN,
    questionText TEXT NOT NULL,
    type INTEGER NOT NULL,
    sectionId BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_question_section FOREIGN KEY (sectionId)
        REFERENCES Section(id)
);

CREATE TABLE AnswerAlternative(
    id BIGINT AUTO_INCREMENT,
    description TEXT NOT NULL,
    explanation TEXT,
    orderPosition INTEGER,
    correct BOOLEAN NOT NULL,
    questionId BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_answer_alternative_question FOREIGN KEY (questionId)
        REFERENCES Section(id)
);