SET GLOBAL max_allowed_packet = 1024*1024*1024;

drop table if exists rating;
drop table if exists comment;
drop table if exists characteer;
drop table if exists franchise;
drop table if exists photo;
drop table if exists observation;
drop table if exists user;
drop table if exists binary_photo;

create table binary_photo(
  binary_photo_id int primary key AUTO_INCREMENT,
  binary_data longblob
);



create table user(
  username nvarchar(16) primary key,
  passwd nvarchar(512),
  avatar_binary_photo_id int,
  foreign key (avatar_binary_photo_id) references binary_photo(binary_photo_id)
);



create table observation(
  observation_id int primary key AUTO_INCREMENT,
  observed nvarchar(16),
  observer nvarchar(16),
  foreign key (observed) references user(username),
  foreign key (observer) references user(username)
);



create table photo(
  photo_id int primary key AUTO_INCREMENT,
  photo_binary_photo_id int,
  username nvarchar(16),
  upload_date date,
  description nvarchar(512),
  foreign key (username) references user(username),
  foreign key (photo_binary_photo_id) references binary_photo(binary_photo_id)
);



create table franchise(
  franchise_id int primary key AUTO_INCREMENT,
  photo_id int,
  franchise_name nvarchar(128),
  foreign key (photo_id) references photo(photo_id)
);



create table characteer(
  characteer_id int primary key AUTO_INCREMENT,
  photo_id int,
  characteer_name nvarchar(128),
  foreign key (photo_id) references photo(photo_id)
);



create table comment(
  comment_id int primary key AUTO_INCREMENT,
  content nvarchar(256),
  username nvarchar(16),
  comment_date date,
  photo_id int,
  foreign key (photo_id) references photo(photo_id),
  foreign key (username) references user(username)
);



create table rating(
  rating_id int primary key AUTO_INCREMENT,
  username nvarchar(16),
  photo_id int,
  rating_similarity int,
  rating_quality int,
  rating_arrangemnt int,
  foreign key (photo_id) references photo(photo_id),
  foreign key (username) references user(username)
);

insert into user(username, passwd) VALUES ('Shafear', 'password');