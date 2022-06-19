INSERT INTO job (id, description, state, deleted, created_date, updated_date, operator_id) VALUES
    (21, 'TestProduct1', 2, false, CURRENT_TIME , CURRENT_TIME , 1),
    (22, 'TestProduct1', 1, false, CURRENT_TIME , CURRENT_TIME , 1);

INSERT INTO product (id, job_id, description, deleted, state, created_date, updated_date, operator_id) VALUES
                                                      (1, 21 , 'TestProduct1', false , 1, CURRENT_TIME , CURRENT_TIME , 1);

INSERT INTO users (id , username, name_surname, password) VALUES (1, 'furkanDev', 'Furkan Ceylan', 'Furkan34FB');