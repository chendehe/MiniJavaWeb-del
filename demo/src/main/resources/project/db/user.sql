CREATE TABLE `t_user` (
`id`  char(32),
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