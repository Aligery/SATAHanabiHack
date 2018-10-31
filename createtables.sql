
CREATE table users_table (user_id serial not null
constraint pk_users_table
primary key,
                          f_name VARCHAR(255),
                          s_name VARCHAR(255),
                          email VARCHAR(255));

CREATE table questions_table (question_id serial not null
constraint pk_questions_table
primary key,
                              subject VARCHAR(255),
                              question VARCHAR(255),
                              user_id INT references users_table(user_id));

-- добавить foreign key
SELECT * FROM question ORDER BY question_id LIMIT ?;
