/*==============================================================*/
/* DBMS name:      MySQL 8.0                                    */
/* Created on:     2024/6/27 19:52:32                           */
/*==============================================================*/
create database if not exists accommodation_management_system;

use accommodation_management_system;

drop table if exists accommodation_application;

drop table if exists accommodation_log;

drop table if exists accommodation_notification;

drop table if exists bed;

drop table if exists building;

drop table if exists dorm_sanitary_inspection_log;

drop table if exists dormitory_administrator;

drop table if exists dormitory_rating_dictionary;

drop table if exists dormitory_rating_log;

drop table if exists maintenance_administrator;

drop table if exists maintenance_record;

drop table if exists maintenance_request;

drop table if exists park;

drop table if exists responsible_leader;

drop table if exists role;

drop table if exists room;

drop table if exists sanitary_inspection;

drop table if exists sanitary_inspection_record;

drop table if exists sanitation_objection;

drop table if exists sanitation_objection_review_result_notification;

drop table if exists student;

drop table if exists system_administrator;

drop table if exists teacher;

drop table if exists teacher_student;

drop table if exists violation_record;

drop table if exists violation_warning;

/*==============================================================*/
/* Table: accommodation_application                             */
/*==============================================================*/
create table accommodation_application
(
   id                   bigint auto_increment not null comment '主键',
   student_id           bigint comment '外键 关联到表student 这条记录是谁申请的',
   request_time         datetime comment '申请时间',
   request_type         smallint comment '申请类型 0表示普通入住申请 1表示普通调宿申请 2表示互换申请 3表示个人退宿申请 4表示校外住宿申请',
   request_reason       varchar(80) comment '申请理由',
   state                smallint default 0 comment '申请状态 0表示等待审核 1表示审核通过 2表示被驳回',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
) comment '学生住退宿申请表';

/*==============================================================*/
/* Table: accommodation_log                                     */
/*==============================================================*/
create table accommodation_log
(
   id                   bigint auto_increment not null comment '主键',
   responsible_leader_id bigint comment '外键 关联到表responsible_leader 这条记录是谁审核的',
   student_id           bigint comment '外键 关联到表student 这条记录是谁申请的',
   request_time         datetime comment '此次申请的时间',
   request_type         smallint comment '此次申请的类型 0表示普通入住申请 1表示普通调宿申请 2表示互换申请 3表示个人退宿申请 4表示校外住宿申请',
   request_reason       varchar(80) comment '此次申请的理由',
   review_time          datetime comment '审核通过或者驳回的时间',
   review_state         bool comment '此次申请是通过还是驳回 0通过 1驳回',
   review_reason        varchar(80) comment '当review_state==1时才有意义 表示驳回的原因',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
) comment '住退宿日志 记录了所有学生的所有历史申请';

/*==============================================================*/
/* Table: accommodation_notification                            */
/*==============================================================*/
create table accommodation_notification
(
   id                   bigint auto_increment not null comment '主键',
   dormitory_administrator_id bigint comment '外键 关联到表dormitory_administrator 表示这条通知是发给哪个宿管',
   type                 smallint comment '通知类型 0批量排宿通知 1批量排宿通知 2普通退宿通知 3批量退宿通知 4同楼栋换宿通知',
   message              varchar(80) comment '通知附加信息（如果没有就为空）',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
) comment '住退宿通知表 这是分管领导发给宿舍管理员的通知';

/*==============================================================*/
/* Table: bed                                                   */
/*==============================================================*/
create table bed
(
   id                   bigint auto_increment not null comment '主键',
   student_id           bigint comment '外键 关联到表student 这张床是谁的',
   teacher_id           bigint comment '外键 关联到表teacher 这张床是谁的',
   room_id              bigint comment '外键 关联到表room 这张床在哪个房间',
   bed_number           smallint comment '床位号',
   is_empty             bool comment '此床是否有人 0没有 1有 这个字段为1时student_id，teacher_id才有意义',
   type                 bool comment '0表示是老师的床 1表示是学生的床 这个字段决定了student_id，teacher_id谁有意义',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
) comment '床位表';

/*==============================================================*/
/* Table: building                                              */
/*==============================================================*/
create table building
(
   id                   bigint auto_increment not null comment '主键',
   dormitory_administrator_id bigint comment '外键 关联到表dormitory_administrator 谁是这栋楼的宿管',
   park_id              bigint comment '外键 关联到表park 这栋楼在那个园区',
   building_name        varchar(20) comment '楼栋名',
   building_number      char(2) comment '楼栋号 楼栋号用且必须用两位数字表示 如01 02',
   nb_floor             int comment '楼层数',
   complicated_time     date comment '竣工时间',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '楼栋表';

/*==============================================================*/
/* Table: dorm_sanitary_inspection_log                          */
/*==============================================================*/
create table dorm_sanitary_inspection_log
(
   id                   bigint auto_increment not null comment '主键',
   room_id              bigint comment '外键 关联到表room 这条日志是哪个寝室的',
   sanitary_inspection_id bigint comment '外键 关联到表inspection_id 哪次卫生检查',
   score                float comment '成绩',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
) comment '宿舍卫生检查日志表';

/*==============================================================*/
/* Table: dormitory_administrator                               */
/*==============================================================*/
create table dormitory_administrator
(
   id                   bigint auto_increment not null comment '主键',
   building_id          bigint comment '外键 关联到表building 这个宿管管理哪栋',
   role_id              smallint default 2 comment '外键 关联到表role 这个宿管的角色 默认都是2 一般不会改变',
   fullname             varchar(15) comment '全名',
   job_id               char(13) comment '工号 用于登录 固定为13位 虽然也是以id结尾 但它不是外键',
   password             varchar(20) comment '密码',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
) comment '宿舍管理员表';

/*==============================================================*/
/* Table: dormitory_rating_dictionary                           */
/*==============================================================*/
create table dormitory_rating_dictionary
(
   id                   bigint auto_increment not null comment '主键',
   item                 varchar(20) comment '项 与评分相关的项 如成绩 卫生',
   proportion           float comment '该项的占比',
   description          varchar(80) comment '描述 比如怎么做可以得多少分',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
) comment '寝室评分字典 怎么去计算寝室的得分';

/*==============================================================*/
/* Table: dormitory_rating_log                                  */
/*==============================================================*/
create table dormitory_rating_log
(
   id                   bigint auto_increment not null comment '主键',
   room_id              bigint comment '外键 关联到表room 哪个寝室',
   score                float comment '分数',
   star_rated           smallint comment '星级',
   room_rank            int comment '排名',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '寝室评分日志 记录了寝室历史的评分信息';

/*==============================================================*/
/* Table: maintenance_administrator                             */
/*==============================================================*/
create table maintenance_administrator
(
   id                   bigint auto_increment not null comment '主键',
   role_id              smallint default 1 comment '外键 关联到表role 维修管理员的角色 默认都是1 一般不会改变',
   fullname             varchar(15) comment '全名',
   job_id               char(13) comment '工号 用于登录 固定为13位 虽然也是以id结尾 但它不是外键',
   password             varchar(20) comment '密码',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '维修管理员信息表';

/*==============================================================*/
/* Table: maintenance_record                                    */
/*==============================================================*/
create table maintenance_record
(
   id                   bigint auto_increment not null comment '主键',
   student_id           bigint comment '外键 关联到表student 这次维修是哪个学生发起的',
   maintenance_item     varchar(20) comment '被维修的东西',
   maintenance_time     date comment '维修时间',
   damage_reason        varchar(50) comment '损坏的原因',
   maintainer_name      varchar(20) comment '修理人员的名字 为了简化 我们把修理人员当作是系统外部的角色',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '维修记录表 记录了所有维修的历史';

/*==============================================================*/
/* Table: maintenance_request                                   */
/*==============================================================*/
create table maintenance_request
(
   id                   bigint auto_increment not null comment '主键',
   student_id           bigint comment '外键 关联到表student 这次维修是哪个学生发起的',
   request_time         datetime comment '发起维修请求的时间',
   request_item         varchar(20) comment '请求维修的东西',
   reason               varchar(80) comment '怎么坏了',
   room_number          char(4) comment '学生需要填写所在寝室号 寝室号固定为4为数字 为2位楼栋编号+2位数字',
   state                smallint comment '状态 0表示申请中 1表示已维修',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '维修申请表 由学生发起的';

/*==============================================================*/
/* Table: park                                                  */
/*==============================================================*/
create table park
(
   id                   bigint auto_increment not null comment '主键',
   park_name            varchar(20) comment '园区名',
   park_address         varchar(50) comment '地址',
   park_type            smallint comment '类型 0表示教师园区 1表示男生 2表示女生',
   complicated_time     date comment '竣工时间',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '园区表';

/*==============================================================*/
/* Table: responsible_leader                                    */
/*==============================================================*/
create table responsible_leader
(
   id                   bigint auto_increment not null comment '主键',
   role_id              smallint default 3 comment '外键 关联到表role 维修管理员的角色 默认都是3 一般不会改变',
   fullname             varchar(15) comment '全名',
   job_id               char(13) comment '工号 用于登录 固定为13位 虽然也是以id结尾 但它不是外键',
   password             varchar(20) comment '密码',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '分管领导表';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   -- 角色表是一张几乎不会改变的表 有6条数据 见下面的插入语句
   role_id              smallint not null,
   role_name            varchar(15),
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   primary key (role_id)
)comment '角色表';

insert into role(role_id, role_name) VALUES
    (0, '系统管理员'), (1, '维修管理员'), (2, '宿舍管理员'), (3, '分管领导'), (4, '教师'), (5, '学生');

/*==============================================================*/
/* Table: room                                                  */
/*==============================================================*/
create table room
(
   id                   bigint auto_increment not null comment '主键',
   building_id          bigint comment '外键 关联到表building 房间属于哪栋楼',
   room_number          char(4) comment '房间号固定为4为数字 为2位楼栋编号+2位数字',
   room_type            smallint comment '房间类型 0寝室 1辅导员室 2门卫室 3办公室 4储藏室 5洗手间 6洗室 7服务台 8服务间',
   room_floor           smallint comment '房间所在的楼层 在几楼',
   room_location        bool comment '房间在左手边还是右手边',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '房间表';

/*==============================================================*/
/* Table: sanitary_inspection                                   */
/*==============================================================*/
create table sanitary_inspection
(
   id                   bigint auto_increment not null comment '主键',
   dormitory_administrator_id bigint comment '外键 关联到表dormitory_administrator 谁创建的卫生检查',
   inspection_time      date comment '检查时间',
   overall_situation    smallint comment '整体情况 0优 1良 2中 3差',
   average_score        float comment '所有寝室的平均分',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '卫生检查表 此表只是记录了一次卫生检查的整体情况 明细情况在表sanitary_inspection_record';

/*==============================================================*/
/* Table: sanitary_inspection_record                            */
/*==============================================================*/
create table sanitary_inspection_record
(
   id                   bigint auto_increment not null comment '主键',
   sanitary_inspection_id bigint comment '外键 关联到表sanitary_inspection 这条明细属于哪次卫生检查',
   item                 varchar(10) comment '项 如床铺 桌面',
   score                float comment '具体项的得分，由item、score、以及寝室评分字典应该可以算出总分',
   description          varchar(80) comment '对该项的描述 比如哪里不合格导致扣的分',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '卫生检查明细表';

/*==============================================================*/
/* Table: sanitation_objection                                  */
/*==============================================================*/
create table sanitation_objection
(
   id                   bigint auto_increment not null comment '主键',
   student_id           bigint comment '外键 关联到表student 哪个学生发起的',
   objection_reason     varchar(80) comment '发起的理由',
   room_number          char(4) comment '发起人的房间号 房间号固定为4为数字 为2位楼栋编号+2位数字',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '卫生异议申请表';

/*==============================================================*/
/* Table: sanitation_objection_review_result_notification       */
/*==============================================================*/
create table sanitation_objection_review_result_notification
(
   id                   bigint auto_increment not null comment '主键',
   dormitory_administrator_id bigint comment '外键 关联到表dormitory_administrator 发给哪个宿管的',
   description          varchar(40) comment '描述 比如把xxx寝室的xxx项改为xxx分',
   state                bool comment '0表示这次异议被通过了 1表示这次异议不予通过',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '卫生异议审核结果通知表 这是分管领导发给宿管的通知 表示这次异议给不给过 若过了宿管要修改相应的得分';

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   id                   bigint auto_increment not null comment '主键',
   bed_id               bigint comment '外键 关联到表bed 这个学生的床位',
   role_id              smallint default 5 comment '外键 关联到表role 维修管理员的角色 默认都是5 一般不会改变',
   fullname             varchar(15) comment '全名',
   password             varchar(20) comment '密码',
   student_id           char(13) comment '学号 固定为13为 虽然以id结尾 但它不是外键',
   collage_name         varchar(50) comment '所在学院 为了简化 我们没有学院表 学院被认为是系统外的东西',
   major                varchar(20) comment '专业 为了简化 我们没有专业表 专业被认为是系统外的东西',
   classname            varchar(30) comment '班级 为了简化 我们没有班级表 班级被认为是系统外的东西',
   grade                int comment '年级',
   is_dormitory_resident bool comment '是否住校 0表示住校 1表示不住校',
   is_present_on_campus bool comment '是否在校 0表示在校 1表示不在校。即使是一个住校生 也未必在校',
   -- 例1：新生入学一般默认住校 但直到报道的那一天才在校 在这一天宿管更改他的在校字段  例2：请假的住校生不在校
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
);

/*==============================================================*/
/* Table: system_administrator                                  */
/*==============================================================*/
create table system_administrator
(
   id                   bigint auto_increment not null comment '主键',
   role_id              smallint default 0 comment '外键 关联到表role 维修管理员的角色 默认都是0 一般不会改变',
   fullname             varchar(15) comment '全名',
   job_id               char(13) comment '工号 用于登录 固定为13位 虽然也是以id结尾 但它不是外键',
   password             varchar(20) comment '密码',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '系统管理员';

/*==============================================================*/
/* Table: teacher                                               */
/*==============================================================*/
create table teacher
(
   id                   bigint auto_increment not null comment '主键',
   bed_id               bigint comment '外键 关联到表bed 这个教师的床位',
   role_id              smallint default 4 comment '外键 关联到表role 维修管理员的角色 默认都是4 一般不会改变',
   fullname             varchar(15) comment '全名',
   password             varchar(20) comment '密码',
   teacher_id           char(13) comment '教师号 固定为13为 虽然以id结尾 但它不是外键',
   professional_title   smallint comment '职称',
   collage_name         varchar(50) comment '学院',
   major                varchar(20) comment '专业',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
);

/*==============================================================*/
/* Table: teacher_student                                       */
/*==============================================================*/
create table teacher_student
(
   teacher_id           bigint not null,
   stu_id               bigint not null,
   primary key (teacher_id, stu_id)
)comment '学生和教师是多对多关系 因此有这张关系表 它不是实体';

/*==============================================================*/
/* Table: violation_record                                      */
/*==============================================================*/
create table violation_record
(
   id                   bigint auto_increment not null comment '主键',
   student_id           bigint comment '外键 关联到表student 谁违规了',
   dormitory_administrator_id bigint comment '外键 关联到表dormitory_administrator 谁抓到的',
   violation_time       datetime comment '被抓的时间',
   violation_item       varchar(50) comment '违规的项',
   reason               varchar(80) comment '定性为违规的理由',
   violation_degree     smallint comment '违规的程度 0记过 1严重警告 2警告',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '学生违规记录表';

/*==============================================================*/
/* Table: violation_warning                                     */
/*==============================================================*/
create table violation_warning
(
   id                   bigint auto_increment not null comment '主键',
   student_id           bigint comment '外键 关联到表student 谁违规了',
   violation_time       datetime comment '被抓的时间',
   description          varchar(80) comment '描述',
   degree               smallint comment '违规的程度 0记过 1严重警告 2警告',
   create_time          datetime default current_timestamp,
   update_time          datetime default current_timestamp on update current_timestamp,
   is_deleted           bool default 0,
   primary key (id)
)comment '学生违规警告表 这是分管领导发给学生的';

alter table accommodation_application add constraint FK_student_accommodation_application foreign key (student_id)
      references student (id) on delete restrict on update restrict;

alter table accommodation_log add constraint FK_responsible_leader_accommodation_log foreign key (responsible_leader_id)
      references responsible_leader (id) on delete restrict on update restrict;

alter table accommodation_log add constraint FK_student_accommodation_log foreign key (student_id)
      references student (id) on delete restrict on update restrict;

alter table accommodation_notification add constraint FK_dormitory_administrator_accommodation_notification foreign key (dormitory_administrator_id)
      references dormitory_administrator (id) on delete restrict on update restrict;

alter table bed add constraint FK_room_bed foreign key (room_id)
      references room (id) on delete restrict on update restrict;

alter table bed add constraint FK_student_bed2 foreign key (student_id)
      references student (id) on delete restrict on update restrict;

alter table bed add constraint FK_teacher_bed2 foreign key (teacher_id)
      references teacher (id) on delete restrict on update restrict;

alter table building add constraint FK_dormitory_administrator_building2 foreign key (dormitory_administrator_id)
      references dormitory_administrator (id) on delete restrict on update restrict;

alter table building add constraint FK_park_building foreign key (park_id)
      references park (id) on delete restrict on update restrict;

alter table dorm_sanitary_inspection_log add constraint FK_room_dorm_sanitary_inspection_log foreign key (room_id)
      references room (id) on delete restrict on update restrict;

alter table dorm_sanitary_inspection_log add constraint FK_sanitary_inspection_dorm_sanitary_inspection_log foreign key (sanitary_inspection_id)
      references sanitary_inspection (id) on delete restrict on update restrict;

alter table dormitory_administrator add constraint FK_dormitory_administrator_building foreign key (building_id)
      references building (id) on delete restrict on update restrict;

alter table dormitory_administrator add constraint FK_role_dormitory_administrator foreign key (role_id)
      references role (role_id) on delete restrict on update restrict;

alter table dormitory_rating_log add constraint FK_room_dormitory_rating_log foreign key (room_id)
      references room (id) on delete restrict on update restrict;

alter table maintenance_administrator add constraint FK_role_maintenance_administrator foreign key (role_id)
      references role (role_id) on delete restrict on update restrict;

alter table maintenance_record add constraint FK_student_maintenance_record foreign key (student_id)
      references student (id) on delete restrict on update restrict;

alter table maintenance_request add constraint FK_student_maintenance_request foreign key (student_id)
      references student (id) on delete restrict on update restrict;

alter table responsible_leader add constraint FK_role_responsible_leader foreign key (role_id)
      references role (role_id) on delete restrict on update restrict;

alter table room add constraint FK_building_room foreign key (building_id)
      references building (id) on delete restrict on update restrict;

alter table sanitary_inspection add constraint FK_dormitory_administrator_sanitary_inspection foreign key (dormitory_administrator_id)
      references dormitory_administrator (id) on delete restrict on update restrict;

alter table sanitary_inspection_record add constraint FK_sanitary_inspection_sanitary_inspection_record foreign key (sanitary_inspection_id)
      references sanitary_inspection (id) on delete restrict on update restrict;

alter table sanitation_objection add constraint FK_student_sanitation_objection foreign key (student_id)
      references student (id) on delete restrict on update restrict;

alter table sanitation_objection_review_result_notification add constraint FK_sanitation_objection_review_result_notification foreign key (dormitory_administrator_id)
      references dormitory_administrator (id) on delete restrict on update restrict;

alter table student add constraint FK_role_student foreign key (role_id)
      references role (role_id) on delete restrict on update restrict;

alter table student add constraint FK_student_bed foreign key (bed_id)
      references bed (id) on delete restrict on update restrict;

alter table system_administrator add constraint FK_role_system_administrator foreign key (role_id)
      references role (role_id) on delete restrict on update restrict;

alter table teacher add constraint FK_role_teacher foreign key (role_id)
      references role (role_id) on delete restrict on update restrict;

alter table teacher add constraint FK_teacher_bed foreign key (bed_id)
      references bed (id) on delete restrict on update restrict;

alter table teacher_student add constraint FK_teacher_student foreign key (teacher_id)
      references teacher (id) on delete restrict on update restrict;

alter table teacher_student add constraint FK_teacher_student2 foreign key (stu_id)
      references student (id) on delete restrict on update restrict;

alter table violation_record add constraint FK_dormitory_administrator_violation_record foreign key (dormitory_administrator_id)
      references dormitory_administrator (id) on delete restrict on update restrict;

alter table violation_record add constraint FK_student_violation_record foreign key (student_id)
      references student (id) on delete restrict on update restrict;

alter table violation_warning add constraint FK_student_violation_warning foreign key (student_id)
      references student (id) on delete restrict on update restrict;

