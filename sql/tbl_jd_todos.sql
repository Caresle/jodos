create table tbl_jd_todos (
	id serial primary key,
	name varchar(255) not NULL,
	completed smallint default 0 not null,
	created_at timestamp default now()
)
