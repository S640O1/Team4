DROP DATABASE IF EXISTS `cafe`;
CREATE DATABASE IF NOT EXISTS `cafe`;

USE `cafe`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`u_id`	varchar(12)	primary key,
	`u_pw`	varchar(15)	NOT NULL,
	`u_nickname`	varchar(10)	NOT NULL,
	`u_phone`	char(13)	NOT NULL,
	`u_birth`	char(6)	NOT NULL,
	`u_gender`	char(1)	NOT NULL
);

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
	`c_num`	int	primary key auto_increment,
	`c_title`	varchar(15) not null
);

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
	`b_num`	int	primary key auto_increment,
	`b_title`	varchar(20) not null,
	`b_c_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
	`p_num`	int	primary key auto_increment,
	`p_title`	varchar(50) not null,
	`p_date`	date not null,
	`p_content`	longtext,
	`p_b_num`	int	NOT NULL,
	`p_u_id`	varchar(12)	NOT NULL
);

ALTER TABLE `board` ADD CONSTRAINT `FK_category_TO_board_1` FOREIGN KEY (
	`b_c_num`
)
REFERENCES `category` (
	`c_num`
);

ALTER TABLE `post` ADD CONSTRAINT `FK_board_TO_post_1` FOREIGN KEY (
	`p_b_num`
)
REFERENCES `board` (
	`b_num`
);

ALTER TABLE `post` ADD CONSTRAINT `FK_user_TO_post_1` FOREIGN KEY (
	`p_u_id`
)
REFERENCES `user` (
	`u_id`
);

