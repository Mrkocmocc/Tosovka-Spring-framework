PGDMP                      |            tosovka    16.3    16.3 2    &           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            '           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            (           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            )           1262    16413    tosovka    DATABASE     {   CREATE DATABASE tosovka WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE tosovka;
                postgres    false            �            1255    16658    update_is_active()    FUNCTION     �   CREATE FUNCTION public.update_is_active() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.is_active := (NEW.event_date >= CURRENT_DATE);
    RETURN NEW;
END;
$$;
 )   DROP FUNCTION public.update_is_active();
       public          postgres    false            �            1259    16526    comments    TABLE     �   CREATE TABLE public.comments (
    comm_id bigint NOT NULL,
    event_id bigint NOT NULL,
    user_id bigint NOT NULL,
    comment_text character varying(255) NOT NULL,
    comment_date timestamp without time zone NOT NULL
);
    DROP TABLE public.comments;
       public         heap    postgres    false            �            1259    16525    comments_comm_id_seq    SEQUENCE     �   ALTER TABLE public.comments ALTER COLUMN comm_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.comments_comm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224            �            1259    16510    events    TABLE     _  CREATE TABLE public.events (
    event_id bigint NOT NULL,
    title character varying(100) NOT NULL,
    description character varying(1100) NOT NULL,
    event_date date NOT NULL,
    location character varying(100) NOT NULL,
    user_id bigint NOT NULL,
    type_id bigint NOT NULL,
    main_image bytea NOT NULL,
    is_active boolean NOT NULL
);
    DROP TABLE public.events;
       public         heap    postgres    false            �            1259    16509    events_event_id_seq    SEQUENCE     �   ALTER TABLE public.events ALTER COLUMN event_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.events_event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220            �            1259    16518    images    TABLE     �   CREATE TABLE public.images (
    image_id bigint NOT NULL,
    event_id bigint NOT NULL,
    image bytea NOT NULL,
    user_id bigint NOT NULL
);
    DROP TABLE public.images;
       public         heap    postgres    false            �            1259    16517    images_image_id_seq    SEQUENCE     �   ALTER TABLE public.images ALTER COLUMN image_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.images_image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    222            �            1259    16479    role    TABLE     i   CREATE TABLE public.role (
    role_id bigint NOT NULL,
    role_name character varying(255) NOT NULL
);
    DROP TABLE public.role;
       public         heap    postgres    false            �            1259    16478    role_role_id_seq    SEQUENCE     �   ALTER TABLE public.role ALTER COLUMN role_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    16585    type    TABLE     i   CREATE TABLE public.type (
    type_id bigint NOT NULL,
    type_name character varying(255) NOT NULL
);
    DROP TABLE public.type;
       public         heap    postgres    false            �            1259    16584    type_type_id_seq    SEQUENCE     �   ALTER TABLE public.type ALTER COLUMN type_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.type_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    228            �            1259    16471 	   user_data    TABLE     �   CREATE TABLE public.user_data (
    user_id bigint NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    role_id bigint NOT NULL
);
    DROP TABLE public.user_data;
       public         heap    postgres    false            �            1259    16470    user_data_user_id_seq    SEQUENCE     �   ALTER TABLE public.user_data ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_data_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �            1259    16534    visits    TABLE     x   CREATE TABLE public.visits (
    visit_id bigint NOT NULL,
    event_id bigint NOT NULL,
    user_id bigint NOT NULL
);
    DROP TABLE public.visits;
       public         heap    postgres    false            �            1259    16533    visits_visit_id_seq    SEQUENCE     �   ALTER TABLE public.visits ALTER COLUMN visit_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.visits_visit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    226                      0    16526    comments 
   TABLE DATA           Z   COPY public.comments (comm_id, event_id, user_id, comment_text, comment_date) FROM stdin;
    public          postgres    false    224   �9                 0    16510    events 
   TABLE DATA           }   COPY public.events (event_id, title, description, event_date, location, user_id, type_id, main_image, is_active) FROM stdin;
    public          postgres    false    220   �9                 0    16518    images 
   TABLE DATA           D   COPY public.images (image_id, event_id, image, user_id) FROM stdin;
    public          postgres    false    222   �9                 0    16479    role 
   TABLE DATA           2   COPY public.role (role_id, role_name) FROM stdin;
    public          postgres    false    218   �9       #          0    16585    type 
   TABLE DATA           2   COPY public.type (type_id, type_name) FROM stdin;
    public          postgres    false    228   /:                 0    16471 	   user_data 
   TABLE DATA           P   COPY public.user_data (user_id, username, password, email, role_id) FROM stdin;
    public          postgres    false    216   �:       !          0    16534    visits 
   TABLE DATA           =   COPY public.visits (visit_id, event_id, user_id) FROM stdin;
    public          postgres    false    226   �:       *           0    0    comments_comm_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.comments_comm_id_seq', 1, true);
          public          postgres    false    223            +           0    0    events_event_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.events_event_id_seq', 10, true);
          public          postgres    false    219            ,           0    0    images_image_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.images_image_id_seq', 9, true);
          public          postgres    false    221            -           0    0    role_role_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.role_role_id_seq', 2, true);
          public          postgres    false    217            .           0    0    type_type_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.type_type_id_seq', 5, true);
          public          postgres    false    227            /           0    0    user_data_user_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.user_data_user_id_seq', 5, true);
          public          postgres    false    215            0           0    0    visits_visit_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.visits_visit_id_seq', 9, true);
          public          postgres    false    225            x           2606    16532    comments comments_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (comm_id);
 @   ALTER TABLE ONLY public.comments DROP CONSTRAINT comments_pkey;
       public            postgres    false    224            t           2606    16516    events events_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (event_id);
 <   ALTER TABLE ONLY public.events DROP CONSTRAINT events_pkey;
       public            postgres    false    220            v           2606    16524    images images_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.images
    ADD CONSTRAINT images_pkey PRIMARY KEY (image_id);
 <   ALTER TABLE ONLY public.images DROP CONSTRAINT images_pkey;
       public            postgres    false    222            r           2606    16483    role role_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    218            |           2606    16589    type type_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.type
    ADD CONSTRAINT type_pkey PRIMARY KEY (type_id);
 8   ALTER TABLE ONLY public.type DROP CONSTRAINT type_pkey;
       public            postgres    false    228            p           2606    16477    user_data user_data_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.user_data
    ADD CONSTRAINT user_data_pkey PRIMARY KEY (user_id);
 B   ALTER TABLE ONLY public.user_data DROP CONSTRAINT user_data_pkey;
       public            postgres    false    216            z           2606    16538    visits visits_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.visits
    ADD CONSTRAINT visits_pkey PRIMARY KEY (visit_id);
 <   ALTER TABLE ONLY public.visits DROP CONSTRAINT visits_pkey;
       public            postgres    false    226            �           2620    16659    events trigger_update_is_active    TRIGGER     �   CREATE TRIGGER trigger_update_is_active BEFORE INSERT OR UPDATE ON public.events FOR EACH ROW EXECUTE FUNCTION public.update_is_active();
 8   DROP TRIGGER trigger_update_is_active ON public.events;
       public          postgres    false    229    220            �           2606    16544    images fk_events    FK CONSTRAINT     �   ALTER TABLE ONLY public.images
    ADD CONSTRAINT fk_events FOREIGN KEY (event_id) REFERENCES public.events(event_id) NOT VALID;
 :   ALTER TABLE ONLY public.images DROP CONSTRAINT fk_events;
       public          postgres    false    222    4724    220            �           2606    16549    comments fk_events    FK CONSTRAINT     �   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fk_events FOREIGN KEY (event_id) REFERENCES public.events(event_id) NOT VALID;
 <   ALTER TABLE ONLY public.comments DROP CONSTRAINT fk_events;
       public          postgres    false    220    4724    224            �           2606    16559    visits fk_events    FK CONSTRAINT     �   ALTER TABLE ONLY public.visits
    ADD CONSTRAINT fk_events FOREIGN KEY (event_id) REFERENCES public.events(event_id) NOT VALID;
 :   ALTER TABLE ONLY public.visits DROP CONSTRAINT fk_events;
       public          postgres    false    220    226    4724            }           2606    16628    user_data fk_role    FK CONSTRAINT     ~   ALTER TABLE ONLY public.user_data
    ADD CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES public.role(role_id) NOT VALID;
 ;   ALTER TABLE ONLY public.user_data DROP CONSTRAINT fk_role;
       public          postgres    false    4722    218    216            ~           2606    16623    events fk_type    FK CONSTRAINT     {   ALTER TABLE ONLY public.events
    ADD CONSTRAINT fk_type FOREIGN KEY (type_id) REFERENCES public.type(type_id) NOT VALID;
 8   ALTER TABLE ONLY public.events DROP CONSTRAINT fk_type;
       public          postgres    false    228    220    4732                       2606    16539    events fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public.events
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.user_data(user_id) NOT VALID;
 8   ALTER TABLE ONLY public.events DROP CONSTRAINT fk_user;
       public          postgres    false    4720    216    220            �           2606    16554    comments fk_users    FK CONSTRAINT     �   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES public.user_data(user_id) NOT VALID;
 ;   ALTER TABLE ONLY public.comments DROP CONSTRAINT fk_users;
       public          postgres    false    224    216    4720            �           2606    16564    visits fk_users    FK CONSTRAINT     �   ALTER TABLE ONLY public.visits
    ADD CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES public.user_data(user_id) NOT VALID;
 9   ALTER TABLE ONLY public.visits DROP CONSTRAINT fk_users;
       public          postgres    false    226    216    4720            �           2606    16569    images fk_users    FK CONSTRAINT     �   ALTER TABLE ONLY public.images
    ADD CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES public.user_data(user_id) NOT VALID;
 9   ALTER TABLE ONLY public.images DROP CONSTRAINT fk_users;
       public          postgres    false    216    222    4720                  x������ � �            x������ � �            x������ � �         !   x�3���q�wt����2�pB�]��b���� s��      #   F   x�3�0�¾{/�]�z��b��%@f�Ŧ;.l�����=\Ɯ&�Aj��{/캰�+F��� ��%�            x������ � �      !      x������ � �     