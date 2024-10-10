DROP TABLE IF EXISTS `edu_apply`;

CREATE TABLE `edu_apply` (
	`apply_id`	INTEGER	NOT NULL,
	`member_id`	INTEGER	NULL,
	`lecture_id`	INTEGER	NULL,
	`created_date`	DATETIME	NULL
);

DROP TABLE IF EXISTS `edu_lecture`;

CREATE TABLE `edu_lecture` (
	`lecture_id`	INTEGER	NOT NULL,
	`title`	VARCHAR(255)	NULL,
	`content`	VARCHAR(255)	NULL,
	`quatity`	INTEGER	NULL,
	`start_date`	DATE	NULL,
	`end_date`	DATE	NULL,
	`online`	TINYINT	NULL,
	`room_id`	INTEGER	NULL,
	`member_id`	INTEGER	NULL
);

DROP TABLE IF EXISTS `edu_manager`;

CREATE TABLE `edu_manager` (
	`member_id`	INTEGER	NOT NULL,
	`position`	VARCHAR(50)	NULL,
	`department`	VARCHAR(50)	NULL,
	`fax`	VARCHAR(30)	NULL
);

DROP TABLE IF EXISTS `edu_student`;

CREATE TABLE `edu_student` (
	`member_id`	INTEGER	NOT NULL,
	`working`	TINYINT	NULL,
	`jumin`	VARCHAR(13)	NULL,
	`juso_id`	INTEGER	NULL,
	`detail_address`	VARCHAR(255)	NULL
);

DROP TABLE IF EXISTS `edu_room`;

CREATE TABLE `edu_room` (
	`room_id`	INTEGER	NOT NULL,
	`center_id`	INTEGER	NULL,
	`name`	VARCHAR(50)	NULL,
	`quantity`	INTEGER	NULL
);

DROP TABLE IF EXISTS `edu_teacher`;

CREATE TABLE `edu_teacher` (
	`member_id`	INTEGER	NOT NULL,
	`photo`	VARCHAR(255)	NULL,
	`major`	VARCHAR(50)	NULL,
	`wage`	INTEGER	NULL
);

DROP TABLE IF EXISTS `edu_room_photo`;

CREATE TABLE `edu_room_photo` (
	`romm_photo_id`	INTEGER	NOT NULL,
	`filepath`	VARCHAR(255)	NULL,
	`room_id`	INTEGER	NULL
);

DROP TABLE IF EXISTS `edu_center`;

CREATE TABLE `edu_center` (
	`center_id`	INTEGER	NOT NULL,
	`name`	VARCHAR(50)	NULL,
	`tel`	VARCHAR(30)	NULL,
	`fax`	VARCHAR(30)	NULL,
	`juso_id`	INTEGER	NULL,
	`detail_address`	VARCHAR(255)	NULL
);

DROP TABLE IF EXISTS `edu_juso`;

CREATE TABLE `edu_juso` (
	`juso_id`	INTEGER	NOT NULL,
	`post_no`	CHAR(5)	NULL,
	`basic_address`	VARCHAR(255)	NULL
);

DROP TABLE IF EXISTS `edu_lecture_teacher`;

CREATE TABLE `edu_lecture_teacher` (
	`lecture_id`	INTEGER	NOT NULL,
	`member_id`	INTEGER	NOT NULL
);

DROP TABLE IF EXISTS `edu_member`;

CREATE TABLE `edu_member` (
	`member_id`	INTEGER	NOT NULL,
	`name`	VARCHAR(50)	NULL,
	`email`	VARCHAR(40)	NULL,
	`tel`	VARCHAR(30)	NULL,
	`created_date`	DATETIME	NULL
);

ALTER TABLE `edu_apply` ADD CONSTRAINT `PK_EDU_APPLY` PRIMARY KEY (
	`apply_id`
);

ALTER TABLE `edu_lecture` ADD CONSTRAINT `PK_EDU_LECTURE` PRIMARY KEY (
	`lecture_id`
);

ALTER TABLE `edu_manager` ADD CONSTRAINT `PK_EDU_MANAGER` PRIMARY KEY (
	`member_id`
);

ALTER TABLE `edu_student` ADD CONSTRAINT `PK_EDU_STUDENT` PRIMARY KEY (
	`member_id`
);

ALTER TABLE `edu_room` ADD CONSTRAINT `PK_EDU_ROOM` PRIMARY KEY (
	`room_id`
);

ALTER TABLE `edu_teacher` ADD CONSTRAINT `PK_EDU_TEACHER` PRIMARY KEY (
	`member_id`
);

ALTER TABLE `edu_room_photo` ADD CONSTRAINT `PK_EDU_ROOM_PHOTO` PRIMARY KEY (
	`romm_photo_id`
);

ALTER TABLE `edu_center` ADD CONSTRAINT `PK_EDU_CENTER` PRIMARY KEY (
	`center_id`
);

ALTER TABLE `edu_juso` ADD CONSTRAINT `PK_EDU_JUSO` PRIMARY KEY (
	`juso_id`
);

ALTER TABLE `edu_lecture_teacher` ADD CONSTRAINT `PK_EDU_LECTURE_TEACHER` PRIMARY KEY (
	`lecture_id`,
	`member_id`
);

ALTER TABLE `edu_member` ADD CONSTRAINT `PK_EDU_MEMBER` PRIMARY KEY (
	`member_id`
);

ALTER TABLE `edu_apply` ADD CONSTRAINT `FK_edu_student_TO_edu_apply_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `edu_student` (
	`member_id`
);

ALTER TABLE `edu_apply` ADD CONSTRAINT `FK_edu_lecture_TO_edu_apply_1` FOREIGN KEY (
	`lecture_id`
)
REFERENCES `edu_lecture` (
	`lecture_id`
);

ALTER TABLE `edu_lecture` ADD CONSTRAINT `FK_edu_room_TO_edu_lecture_1` FOREIGN KEY (
	`room_id`
)
REFERENCES `edu_room` (
	`room_id`
);

ALTER TABLE `edu_lecture` ADD CONSTRAINT `FK_edu_manager_TO_edu_lecture_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `edu_manager` (
	`member_id`
);

ALTER TABLE `edu_manager` ADD CONSTRAINT `FK_edu_member_TO_edu_manager_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `edu_member` (
	`member_id`
);

ALTER TABLE `edu_student` ADD CONSTRAINT `FK_edu_member_TO_edu_student_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `edu_member` (
	`member_id`
);

ALTER TABLE `edu_student` ADD CONSTRAINT `FK_edu_juso_TO_edu_student_1` FOREIGN KEY (
	`juso_id`
)
REFERENCES `edu_juso` (
	`juso_id`
);

ALTER TABLE `edu_room` ADD CONSTRAINT `FK_edu_center_TO_edu_room_1` FOREIGN KEY (
	`center_id`
)
REFERENCES `edu_center` (
	`center_id`
);

ALTER TABLE `edu_teacher` ADD CONSTRAINT `FK_edu_member_TO_edu_teacher_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `edu_member` (
	`member_id`
);

ALTER TABLE `edu_room_photo` ADD CONSTRAINT `FK_edu_room_TO_edu_room_photo_1` FOREIGN KEY (
	`room_id`
)
REFERENCES `edu_room` (
	`room_id`
);

ALTER TABLE `edu_center` ADD CONSTRAINT `FK_edu_juso_TO_edu_center_1` FOREIGN KEY (
	`juso_id`
)
REFERENCES `edu_juso` (
	`juso_id`
);

ALTER TABLE `edu_lecture_teacher` ADD CONSTRAINT `FK_edu_lecture_TO_edu_lecture_teacher_1` FOREIGN KEY (
	`lecture_id`
)
REFERENCES `edu_lecture` (
	`lecture_id`
);

ALTER TABLE `edu_lecture_teacher` ADD CONSTRAINT `FK_edu_teacher_TO_edu_lecture_teacher_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `edu_teacher` (
	`member_id`
);

