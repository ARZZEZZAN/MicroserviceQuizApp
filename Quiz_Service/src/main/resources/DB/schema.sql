drop schema if exists Quiz cascade;
create schema if not exists Quiz;


CREATE TABLE Quiz.quiz (
                               Id SERIAL PRIMARY KEY,
                               title VARCHAR(10) NOT NULL
);
