CREATE table question (INT PRIMARY KEY questionID,
                      VARCHAR(255) question,
                      INT USER_ID)
CREATE table usertable (INT PRIMARY KEY userID,
                        VARCHAR(255) fname,
                         VARCHAR(255) sname,
                         VARCHAR(255) email,
                         INT parrent_question_id)