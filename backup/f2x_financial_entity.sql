--
-- PostgreSQL database dump
--

-- Dumped from database version 12.7
-- Dumped by pg_dump version 13.10

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: client; Type: TABLE; Schema: public; Owner: f2x_user
--

CREATE TABLE public.client (
    id character varying(255) NOT NULL,
    birthday date,
    creation_date timestamp(6) without time zone,
    email character varying(255),
    identification_number character varying(255),
    identification_type character varying(255),
    lastname character varying(255),
    modification_date timestamp(6) without time zone,
    name character varying(255),
    CONSTRAINT client_identification_type_check CHECK (((identification_type)::text = ANY ((ARRAY['CC'::character varying, 'CE'::character varying, 'PT'::character varying])::text[])))
);


ALTER TABLE public.client OWNER TO f2x_user;

--
-- Name: product; Type: TABLE; Schema: public; Owner: f2x_user
--

CREATE TABLE public.product (
    id character varying(255) NOT NULL,
    account_number character varying(255),
    balance double precision NOT NULL,
    creation_date timestamp(6) without time zone,
    excludegmf boolean NOT NULL,
    modification_date timestamp(6) without time zone,
    status character varying(255),
    type character varying(255),
    client_id character varying(255),
    CONSTRAINT product_status_check CHECK (((status)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying, 'CANCELLED'::character varying])::text[]))),
    CONSTRAINT product_type_check CHECK (((type)::text = ANY ((ARRAY['SAVING'::character varying, 'CHECKING'::character varying])::text[])))
);


ALTER TABLE public.product OWNER TO f2x_user;

--
-- Name: transaction; Type: TABLE; Schema: public; Owner: f2x_user
--

CREATE TABLE public.transaction (
    id character varying(255) NOT NULL,
    amount double precision NOT NULL,
    destination_account character varying(255),
    source_account character varying(255),
    transaction_date timestamp(6) without time zone,
    type character varying(255),
    CONSTRAINT transaction_type_check CHECK (((type)::text = ANY ((ARRAY['CONSIGNMENT'::character varying, 'WITHDRAWAL'::character varying, 'TRANSFER_ACCOUNTS'::character varying])::text[])))
);


ALTER TABLE public.transaction OWNER TO f2x_user;

--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: f2x_user
--

COPY public.client (id, birthday, creation_date, email, identification_number, identification_type, lastname, modification_date, name) FROM stdin;
e5b00e9c-0f08-43df-a698-2de3f2bb5835	2000-05-28	2024-03-09 22:41:10.924384	ing.danilomontoya@gmail.com	12345	CC	Montoya	2024-03-09 22:41:10.9244	Danilo S
930b5282-7eaa-4015-a5ea-38c5750dc777	2000-05-28	2024-03-09 23:44:16.727625	ing.danilomontoya@gmail.com	1234567	CC	Montoya	2024-03-10 10:56:47.628995	Sebastian
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: f2x_user
--

COPY public.product (id, account_number, balance, creation_date, excludegmf, modification_date, status, type, client_id) FROM stdin;
1e0f5fd4-2906-4a5e-8da6-d5f004d03d5d	5348218446	400	2024-03-10 15:08:32.899036	t	2024-03-10 15:13:17.624488	ACTIVE	SAVING	930b5282-7eaa-4015-a5ea-38c5750dc777
6c12bef1-ab20-4499-9a96-18487c1fc419	5363457210	2100	2024-03-10 15:08:28.844003	t	2024-03-10 15:14:17.003532	ACTIVE	SAVING	930b5282-7eaa-4015-a5ea-38c5750dc777
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: f2x_user
--

COPY public.transaction (id, amount, destination_account, source_account, transaction_date, type) FROM stdin;
456d238e-1f8c-4b7b-a59b-c1e1fdbdc43d	2000	5348218446	\N	2024-03-10 15:08:54.428742	CONSIGNMENT
9f4ad354-8c70-4de2-8fb8-83c495de4f6f	3000	5348218446	\N	2024-03-10 15:09:00.750465	CONSIGNMENT
872d42b8-51e3-4cad-96e4-ba2006af52be	2000	5348218446	\N	2024-03-10 15:09:05.478621	CONSIGNMENT
1f5d442e-57d1-479e-8f3f-98605281a797	3000	5363457210	5348218446	2024-03-10 15:10:04.557048	TRANSFER_ACCOUNTS
a377ee43-71df-4f2b-9326-1da241dc616c	1200	\N	5348218446	2024-03-10 15:10:51.06533	WITHDRAWAL
e282348b-aa0e-4c76-986c-28394ef1b02c	1100	\N	5348218446	2024-03-10 15:10:57.380131	WITHDRAWAL
6cfec343-952f-4e8a-ac20-5b0b416564f8	1100	\N	5348218446	2024-03-10 15:11:15.612391	WITHDRAWAL
eb2fe17b-2b08-4976-aff2-e6599bfdc49c	200	5363457210	5348218446	2024-03-10 15:13:17.631932	TRANSFER_ACCOUNTS
0cef7de5-72cd-4eee-903b-b92ad1c8a0d3	1100	\N	5363457210	2024-03-10 15:14:17.005425	WITHDRAWAL
\.


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: f2x_user
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: f2x_user
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: f2x_user
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);


--
-- Name: product uk_lqq4ot8t63vohyx7ilfe9weuh; Type: CONSTRAINT; Schema: public; Owner: f2x_user
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT uk_lqq4ot8t63vohyx7ilfe9weuh UNIQUE (account_number);


--
-- Name: product fk3g8nmhhbt7mwbf9r0g5qon8m0; Type: FK CONSTRAINT; Schema: public; Owner: f2x_user
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk3g8nmhhbt7mwbf9r0g5qon8m0 FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- PostgreSQL database dump complete
--

