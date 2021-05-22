

CREATE TABLE CONNECTIONS (
  connection_id int NOT NULL,
  user_id_1 int DEFAULT NULL,
  user_id_2 int DEFAULT NULL,
  is_connected int DEFAULT NULL
  PRIMARY KEY(connection_id)
  FOREIGN KEY (user_id_1) REFERENCES USERS (user_id),
  FOREIGN KEY (user_id_2) REFERENCES USERS (user_id)

)


INSERT INTO CONNECTIONS (connection_id, user_id_1, user_id_2, is_connected) VALUES
(1, 1, 2, 0),
(2, 2, 1, 0),
(3, 3, 5, 2),
(4, 5, 3, 1),
(5, 6, 7, 0),
(6, 7, 6, 0),
(7, 8, 9, 2),
(8, 9, 8, 1),
(9, 10, 11, 0),
(10, 11, 10, 0),
(11, 9, 11, 0),
(12, 11, 9, 0),
(13, 12, 13, 2),
(14, 13, 12, 1),
(15, 14, 15, 0),
(16, 15, 14, 0),
(17, 16, 17, 0),
(18, 17, 16, 0),
(19, 18, 19, 0),
(20, 19, 18, 0),
(21, 20, 21, 0),
(22, 21, 20, 0),
(23, 22, 23, 0),
(24, 23, 22, 0),
(25, 24, 25, 0),
(26, 25, 24, 0),
(27, 26, 27, 2),
(28, 27, 26, 1),
(29, 28, 29, 2),
(30, 29, 28, 1),
(31, 30, 31, 0),
(32, 31, 30, 0),
(33, 32, 33, 2),
(34, 33, 32, 1),
(35, 34, 35, 0),
(36, 35, 34, 0),
(37, 36, 37, 2),
(38, 37, 36, 1),
(39, 38, 39, 0),
(40, 39, 38, 0),
(41, 41, 42, 0),
(42, 42, 41, 0),
(43, 43, 44, 2),
(44, 44, 43, 1),
(45, 45, 46, 0),
(46, 46, 45, 0),
(47, 47, 48, 0),
(48, 48, 47, 0),
(49, 49, 50, 0),
(50, 50, 49, 0);


ALTER TABLE CONNECTIONS
  ADD PRIMARY KEY (connection_id),
  ADD KEY user_id_1 (user_id_1),
  ADD KEY user_id_2 (user_id_2);


ALTER TABLE CONNECTIONS
  ADD CONSTRAINT CONNECTIONS_ibfk_1 FOREIGN KEY (user_id_1) REFERENCES USERS (user_id),
  ADD CONSTRAINT CONNECTIONS_ibfk_2 FOREIGN KEY (user_id_2) REFERENCES USERS (user_id);

