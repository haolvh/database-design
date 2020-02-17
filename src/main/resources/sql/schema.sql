/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/11/27 21:04:00                          */
/*==============================================================*/


/*drop table if exists BALLTEAM;

drop table if exists DEPARTMENT;

drop table if exists PEOPLE;

drop table if exists PICTURE;

drop table if exists ROUTINE;*/

/*==============================================================*/
/* Table: BALLTEAM                                              */
/*==============================================================*/
create table BALLTEAM
(
    team_id              int not null,
    people_id            int,
    team_name            varchar(50),
    team_introdce        text,
    primary key (team_id)
);

/*==============================================================*/
/* Table: DEPARTMENT                                            */
/*==============================================================*/
/*create table DEPARTMENT
(
    D_id                 int not null,
    D_name               varchar(50),
    primary key (D_id)
);*/

/*==============================================================*/
/* Table: PEOPLE                                                */
/*==============================================================*/
/*create table PEOPLE
(
    people_id            int not null,
    D_id                 int,
    picture_id           int,
    people_name          varchar(50),
    people_birthday      varchar(50),
    peopel_birthplace    varchar(50),
    people_introduce     text,
    people_qq            varchar(20),
    primary key (people_id)
);*/

/*==============================================================*/
/* Table: PICTURE                                               */
/*==============================================================*/
/*create table PICTURE
(
    picture_id           int not null,
    D_id                 int,
    people_id            int,
    picture_url          varchar(50),
    picture_position     varchar(50),
    primary key (picture_id)
);*/

/*==============================================================*/
/* Table: ROUTINE                                               */
/*==============================================================*/
create table ROUTINE
(
    routine_id           int not null,
    D_id                 int,
    routine_title        varchar(50),
    routine_content      text,
    routine_time         varchar(20),
    primary key (routine_id)
);

alter table BALLTEAM add constraint FK_Manage foreign key (people_id)
    references PEOPLE (people_id) on delete restrict on update restrict;

alter table PEOPLE add constraint FK_Member foreign key (D_id)
    references DEPARTMENT (D_id) on delete restrict on update restrict;

alter table PEOPLE add constraint FK_People_Image2 foreign key (picture_id)
    references PICTURE (picture_id) on delete restrict on update restrict;

alter table PICTURE add constraint FK_Group_Buding foreign key (D_id)
    references DEPARTMENT (D_id) on delete restrict on update restrict;

alter table PICTURE add constraint FK_People_Image foreign key (people_id)
    references PEOPLE (people_id) on delete restrict on update restrict;

alter table ROUTINE add constraint FK_Take foreign key (D_id)
    references DEPARTMENT (D_id) on delete restrict on update restrict;

