Create table if not exists Report (
  id int auto_increment not null,
  project_name varchar (25) not null,
  project_type varchar (6) not null,
  priority varchar (10) not null,
  related_version varchar (50) ,
  corrected_version varchar (50) not null,
  final_date date,
  performer varchar (50) not null,
  strictness varchar (10) not null,
  test_environment varchar (3) not null,
  project_status varchar (25) not null,
  description varchar (500) not null,
  primary key (id)  );