create table usuarios(

        id bigint not null auto_increment,
        nombre varchar(100) not null,
        email varchar(100) not null unique,
        password varchar(300) not null,
        activo tinyint not null,

        primary key(id)
);

create table cursos(

        id bigint not null auto_increment,
        nombre varchar(100) not null unique,
        categoria varchar(100) not null,
        activo tinyint not null,

        primary key(id)
);

create table topicos(

        id bigint not null auto_increment,
        titulo varchar(100) not null,
        mensaje varchar(200) not null,
        fecha_creacion datetime not null,
        id_usuario bigint not null,
        id_curso bigint not null,
        activo tinyint not null,

        primary key(id),
        foreign key(id_curso) references cursos(id),
        foreign key(id_usuario) references usuarios(id)
);

create table respuestas(

        id bigint not null auto_increment,
        mensaje varchar(100) not null,
        id_topico bigint not null,
        fecha_creacion datetime not null,
        id_autor bigint not null,
        solucion tinyint not null,
        activo tinyint not null,


        primary key(id),
        foreign key(id_topico) references topicos(id),
        foreign key(id_autor) references usuarios(id)


);