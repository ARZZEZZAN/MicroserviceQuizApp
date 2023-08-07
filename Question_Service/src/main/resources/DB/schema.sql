drop schema if exists Quiz cascade;
create schema if not exists Quiz;

CREATE TABLE Quiz.question (
                           Id SERIAL PRIMARY KEY,
                           question_title varchar(100) NOT NULL,
                           option1 VARCHAR(50) NOT NULL,
                           option2 VARCHAR(50) NOT NULL,
                           option3 VARCHAR(50) NOT NULL,
                           option4 VARCHAR(50) NOT NULL,
                           answer VARCHAR(50) NOT NULL,
                           category VARCHAR(10) NOT NULL,
                           difficulty VARCHAR(10) NOT NULL
);
