--用户表
CREATE TABLE  t_e_user(
  user_id VARCHAR(10) PRIMARY KEY ,
  user_name VARCHAR(16),
  password VARCHAR(128) NOT NULL ,
  login_name VARCHAR(50) NOT NULL ,
  idcard_no VARCHAR(22),
  mobile_no VARCHAR(14)
  );

--菜单表
CREATE TABLE t_e_menu(
  menu_id VARCHAR(10) PRIMARY KEY ,
  menu_name VARCHAR(50) NOT NULL ,
  parent_menu_id VARCHAR(10),
  menu_url VARCHAR(200),
  order_id INT,
  level INT,
  last BOOL
);

--角色表
CREATE TABLE t_e_role(
  role_id VARCHAR(10) PRIMARY KEY ,
  role_name VARCHAR(50) NOT NULL ,
  role_desc VARCHAR(200)
);

--用户菜单中间表
CREATE TABLE t_e_user_menu(
  user_id VARCHAR(10) NOT NULL ,
  menu_id VARCHAR(10) NOT NULL
);

--用户角色中间表
CREATE TABLE t_e_user_role(
  user_id VARCHAR(10) NOT NULL ,
  role_id VARCHAR(10) NOT NULL
);

INSERT INTO t_e_role(role_id, role_name, role_desc) VALUES ('r00000','开发','开发者');

INSERT INTO t_e_user_role(user_id, role_id) VALUES ('u00000','r00000');

INSERT INTO t_e_menu(menu_id, menu_name, parent_menu_id, menu_url, order_id, level, last)
VALUES ('m00000','后台管理','','',1,1,FALSE );

INSERT INTO t_e_menu(menu_id, menu_name, parent_menu_id, menu_url, order_id, level, last)
VALUES ('m00001','客服中心','','',2,1,FALSE );

INSERT INTO t_e_user_menu (user_id, menu_id) VALUES ('u00000', 'm00000');
INSERT INTO t_e_user_menu (user_id, menu_id) VALUES ('u00000', 'm00001');