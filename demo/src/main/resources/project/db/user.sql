CREATE TABLE `t_user` (
`id`  char(36),
`name`  varchar(10),
`gender`  tinyint(4),
`birthday`  timestamp,
`address`  varchar(255),
`create_time`  datetime,
`update_time`  datetime,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
ROW_FORMAT=DYNAMIC
;

CREATE TABLE `t_student` (
`id`  char(36),
`school`  varchar(30),
`academy`  varchar(30),
`major`  varchar(30),
`classes`  varchar(30),
`create_time`  datetime,
`update_time`  datetime,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
ROW_FORMAT=DYNAMIC
;