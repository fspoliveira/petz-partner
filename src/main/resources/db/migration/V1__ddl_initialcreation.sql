CREATE TABLE public.customer (
	id uuid                NOT NULL,
	name      varchar(2000) NOT NULL,
	cpforcnpj varchar(500) NOT NULL,
    dateofbirth varchar(500) NOT NULL,
    streetname varchar(500) NOT NULL,
    "number" varchar(500) NOT NULL,
    complement varchar(500) NULL,
    neighbourhood varchar(500) NULL,
    city varchar(500) NOT NULL,
    state varchar(500) NOT NULL,
    country varchar(500) NOT NULL
);

CREATE TABLE public.pet (
	id uuid                NOT NULL,
	name      varchar(2000) NOT NULL,
	"type"      varchar(500) NOT NULL,
    active varchar(1) NOT NULL    
);