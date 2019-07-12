create sequence graph_seq start 1 increment 1;
create sequence route_seq start 1 increment 1;

create table graph (graph_id int8 not null, primary key (graph_id));
create table routes (id int8 not null, distance int4 not null, source varchar(255) not null, target varchar(255) not null, graph_id int8, primary key (id));

alter table routes add constraint FKdwya4k3o8bd2hum4qvdmhto0y foreign key (graph_id) references graph;