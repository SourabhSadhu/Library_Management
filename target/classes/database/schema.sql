-- Schema for User role

CREATE TABLE user_role
(
  id integer NOT NULL,
  role character varying NOT NULL,
  CONSTRAINT "USER_ROLE_PK" PRIMARY KEY (role)
);

-- Schema for User

CREATE TABLE library_user
(
  role integer NOT NULL,
  name character varying NOT NULL,
  email character varying NOT NULL,
  password character varying NOT NULL,
  creation_date date DEFAULT now(),
  active_till date,
  id bigserial NOT NULL,
  CONSTRAINT lbry_user_pk PRIMARY KEY (email)
);

--Schema for Student(User)

CREATE TABLE student_repo
(
  id bigserial NOT NULL,
  name character varying NOT NULL,
  email character varying NOT NULL,
  year character varying NOT NULL,
  stream character varying NOT NULL,
  roll integer NOT NULL,
  max_books integer DEFAULT 3,
  book_count integer NOT NULL DEFAULT 0,
  CONSTRAINT "STUDENT_REPO_PK" PRIMARY KEY (email)
);

-- Schema for Books

CREATE TABLE book_repo
(
  id bigserial NOT NULL,
  name character varying NOT NULL,
  author character varying,
  category character varying NOT NULL,
  sub_category character varying,
  entry_date date DEFAULT now(),
  count integer,
  CONSTRAINT "BOOK_REPO_PK" PRIMARY KEY (id)
);
  
-- Schema for Student Book relation
  
CREATE TABLE student_book_repo
(
  book_id integer NOT NULL,
  student_id integer NOT NULL,
  email character varying NOT NULL,
  issue_date date DEFAULT now(),
  renew_date date DEFAULT now(),
  due integer DEFAULT 0,
  rate money,
  fine money,
  returned boolean DEFAULT false,
  id bigserial NOT NULL,
  CONSTRAINT student_book_repo_pk PRIMARY KEY (book_id,email,issue_date)
);