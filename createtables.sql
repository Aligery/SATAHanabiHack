CREATE table question (INT PRIMARY KEY question_id,
                      VARCHAR(255) question,
                      INT USER_ID)
CREATE table usertable (INT PRIMARY KEY user_id,
                        VARCHAR(255) fname,
                         VARCHAR(255) sname,
                         VARCHAR(255) email,
                         INT parrent_question_id)