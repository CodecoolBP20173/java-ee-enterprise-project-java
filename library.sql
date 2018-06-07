--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.12
-- Dumped by pg_dump version 9.5.12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_with_oids = false;

--
-- Name: author; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.author (
    id integer NOT NULL,
    birthyear integer,
    deathyear integer,
    easternnameorder boolean,
    firstname character varying(255),
    lastname character varying(255),
    title character varying(255)
);


--
-- Name: author_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.author_id_seq OWNED BY public.author.id;


--
-- Name: book; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.book (
    id integer NOT NULL,
    language character varying(255),
    location character varying(255),
    publicationyear integer NOT NULL,
    title character varying(255),
    publisher_id integer,
    translationof_id integer
);


--
-- Name: book_author; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.book_author (
    book_id integer NOT NULL,
    author_id integer NOT NULL
);


--
-- Name: book_bookinstance; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.book_bookinstance (
    book_id integer NOT NULL,
    bookinstances_id integer NOT NULL
);


--
-- Name: book_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.book_id_seq OWNED BY public.book.id;


--
-- Name: bookinstance; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.bookinstance (
    id integer NOT NULL,
    place_id integer
);


--
-- Name: bookinstance_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.bookinstance_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: bookinstance_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.bookinstance_id_seq OWNED BY public.bookinstance.id;


--
-- Name: place; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.place (
    id integer NOT NULL,
    name character varying(255)
);


--
-- Name: place_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.place_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: place_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.place_id_seq OWNED BY public.place.id;


--
-- Name: publisher; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.publisher (
    id integer NOT NULL,
    name character varying(255)
);


--
-- Name: publisher_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.publisher_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: publisher_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.publisher_id_seq OWNED BY public.publisher.id;


--
-- Name: translator; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.translator (
    book_id integer NOT NULL,
    translator_id integer NOT NULL
);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.author ALTER COLUMN id SET DEFAULT nextval('public.author_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.book ALTER COLUMN id SET DEFAULT nextval('public.book_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.bookinstance ALTER COLUMN id SET DEFAULT nextval('public.bookinstance_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.place ALTER COLUMN id SET DEFAULT nextval('public.place_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.publisher ALTER COLUMN id SET DEFAULT nextval('public.publisher_id_seq'::regclass);


--
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.author VALUES (1, NULL, NULL, NULL, 'Chris', 'McChesney', NULL);
INSERT INTO public.author VALUES (2, NULL, NULL, NULL, 'Sean', 'Covey', NULL);
INSERT INTO public.author VALUES (3, NULL, NULL, NULL, 'Jim', 'Huling', NULL);
INSERT INTO public.author VALUES (4, NULL, NULL, false, 'Laszlo', 'Bock', NULL);
INSERT INTO public.author VALUES (5, NULL, NULL, NULL, 'Péter', 'Lázár A.', NULL);
INSERT INTO public.author VALUES (6, NULL, NULL, NULL, 'Ádám', 'Nagy', NULL);


--
-- Name: author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.author_id_seq', 6, true);


--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.book VALUES (1, 'ENGLISH', 'London', 2012, 'The 4 Disciplines of Execution', 1, NULL);
INSERT INTO public.book VALUES (2, 'HUNGARIAN', 'Szekszárd', 2015, 'A google-titok', 2, NULL);
INSERT INTO public.book VALUES (3, 'HUNGARIAN', 'Budapest', 2015, 'Ifjúságügy-Módszertár 100 nonformális módszer és szituáció megoldása', 3, NULL);


--
-- Data for Name: book_author; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.book_author VALUES (1, 1);
INSERT INTO public.book_author VALUES (1, 2);
INSERT INTO public.book_author VALUES (1, 3);
INSERT INTO public.book_author VALUES (2, 4);
INSERT INTO public.book_author VALUES (3, 6);


--
-- Data for Name: book_bookinstance; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.book_id_seq', 3, true);


--
-- Data for Name: bookinstance; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: bookinstance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.bookinstance_id_seq', 1, false);


--
-- Data for Name: place; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.place VALUES (1, 'BP');


--
-- Name: place_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.place_id_seq', 1, true);


--
-- Data for Name: publisher; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.publisher VALUES (1, 'Simon & Schuster');
INSERT INTO public.publisher VALUES (2, 'Bookline');
INSERT INTO public.publisher VALUES (3, 'ISZT Alapítvány');


--
-- Name: publisher_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.publisher_id_seq', 3, true);


--
-- Data for Name: translator; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.translator VALUES (2, 5);


--
-- Name: author_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);


--
-- Name: book_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);


--
-- Name: bookinstance_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.bookinstance
    ADD CONSTRAINT bookinstance_pkey PRIMARY KEY (id);


--
-- Name: place_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.place
    ADD CONSTRAINT place_pkey PRIMARY KEY (id);


--
-- Name: publisher_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.publisher
    ADD CONSTRAINT publisher_pkey PRIMARY KEY (id);


--
-- Name: uk_1ikv32vgv7lrjg66i4dl0nhgh; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.book_bookinstance
    ADD CONSTRAINT uk_1ikv32vgv7lrjg66i4dl0nhgh UNIQUE (bookinstances_id);


--
-- Name: fk293fxbj65hy1wl3sq8t4k260j; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.book_author
    ADD CONSTRAINT fk293fxbj65hy1wl3sq8t4k260j FOREIGN KEY (book_id) REFERENCES public.book(id);


--
-- Name: fk2xybwfqhsistxbgm3n0giho6v; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.translator
    ADD CONSTRAINT fk2xybwfqhsistxbgm3n0giho6v FOREIGN KEY (book_id) REFERENCES public.book(id);


--
-- Name: fk3vm8aisswhl9bs8h455hlg9qn; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.book_bookinstance
    ADD CONSTRAINT fk3vm8aisswhl9bs8h455hlg9qn FOREIGN KEY (bookinstances_id) REFERENCES public.bookinstance(id);


--
-- Name: fk9ailmieh3bu4egif9mv28on9q; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.book_author
    ADD CONSTRAINT fk9ailmieh3bu4egif9mv28on9q FOREIGN KEY (author_id) REFERENCES public.author(id);


--
-- Name: fkb7vi3wn2i15c0qmwhvuhvy0uh; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.book_bookinstance
    ADD CONSTRAINT fkb7vi3wn2i15c0qmwhvuhvy0uh FOREIGN KEY (book_id) REFERENCES public.book(id);


--
-- Name: fkhd1gk8nkde9ooutl0i6mojy1g; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkhd1gk8nkde9ooutl0i6mojy1g FOREIGN KEY (translationof_id) REFERENCES public.book(id);


--
-- Name: fkiilll13nv2mjoeo2afq5vjb8x; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.translator
    ADD CONSTRAINT fkiilll13nv2mjoeo2afq5vjb8x FOREIGN KEY (translator_id) REFERENCES public.author(id);


--
-- Name: fkk4ksshhvniyo9wr87dwelg51q; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.bookinstance
    ADD CONSTRAINT fkk4ksshhvniyo9wr87dwelg51q FOREIGN KEY (place_id) REFERENCES public.place(id);


--
-- Name: fkrb2njmkvio5mhe42empuaiphu; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkrb2njmkvio5mhe42empuaiphu FOREIGN KEY (publisher_id) REFERENCES public.publisher(id);


--
-- PostgreSQL database dump complete
--

