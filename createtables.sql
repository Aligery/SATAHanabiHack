CREATE table question (question_id INT PRIMARY KEY,
                      subject VARCHAR(255),
                      question VARCHAR(255),
                      user_id INT);
CREATE table users_table (userId INT PRIMARY KEY,
                         fName VARCHAR(255),
                          sName VARCHAR(255),
                          email VARCHAR(255));