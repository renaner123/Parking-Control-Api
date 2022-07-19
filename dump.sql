PGDMP     /                    z            parking-control-db    14.4    14.4     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16395    parking-control-db    DATABASE     t   CREATE DATABASE "parking-control-db" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Portuguese_Brazil.1252';
 $   DROP DATABASE "parking-control-db";
                postgres    false            �            1259    16896 	   car_model    TABLE     +  CREATE TABLE public.car_model (
    id integer NOT NULL,
    brand_car character varying(70) NOT NULL,
    color_car character varying(70) NOT NULL,
    license_plate_car character varying(7) NOT NULL,
    model_car character varying(70) NOT NULL,
    car_model_id uuid,
    parking_spot_id uuid
);
    DROP TABLE public.car_model;
       public         heap    postgres    false            �            1259    16412    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �            1259    16901    tb_parking_spot    TABLE     Z  CREATE TABLE public.tb_parking_spot (
    id uuid NOT NULL,
    apartment character varying(30) NOT NULL,
    block character varying(30) NOT NULL,
    parking_spot_number character varying(10) NOT NULL,
    registration_date timestamp without time zone NOT NULL,
    responsible_name character varying(130) NOT NULL,
    car_model_id integer
);
 #   DROP TABLE public.tb_parking_spot;
       public         heap    postgres    false            �          0    16896 	   car_model 
   TABLE DATA                 public          postgres    false    210   �       �          0    16901    tb_parking_spot 
   TABLE DATA                 public          postgres    false    211   �       �           0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 49, true);
          public          postgres    false    209            a           2606    16900    car_model car_model_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.car_model
    ADD CONSTRAINT car_model_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.car_model DROP CONSTRAINT car_model_pkey;
       public            postgres    false    210            e           2606    16905 $   tb_parking_spot tb_parking_spot_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.tb_parking_spot
    ADD CONSTRAINT tb_parking_spot_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.tb_parking_spot DROP CONSTRAINT tb_parking_spot_pkey;
       public            postgres    false    211            g           2606    16909 ,   tb_parking_spot uk_678owtycsgr3anxf3qw4s9r8u 
   CONSTRAINT     v   ALTER TABLE ONLY public.tb_parking_spot
    ADD CONSTRAINT uk_678owtycsgr3anxf3qw4s9r8u UNIQUE (parking_spot_number);
 V   ALTER TABLE ONLY public.tb_parking_spot DROP CONSTRAINT uk_678owtycsgr3anxf3qw4s9r8u;
       public            postgres    false    211            c           2606    16907 &   car_model uk_ecemfyng5xgna0a0wcx7fakjy 
   CONSTRAINT     n   ALTER TABLE ONLY public.car_model
    ADD CONSTRAINT uk_ecemfyng5xgna0a0wcx7fakjy UNIQUE (license_plate_car);
 P   ALTER TABLE ONLY public.car_model DROP CONSTRAINT uk_ecemfyng5xgna0a0wcx7fakjy;
       public            postgres    false    210            j           2606    16915 +   tb_parking_spot fk3kp04une6dg7u69lrmy9i1wda    FK CONSTRAINT     �   ALTER TABLE ONLY public.tb_parking_spot
    ADD CONSTRAINT fk3kp04une6dg7u69lrmy9i1wda FOREIGN KEY (car_model_id) REFERENCES public.car_model(id);
 U   ALTER TABLE ONLY public.tb_parking_spot DROP CONSTRAINT fk3kp04une6dg7u69lrmy9i1wda;
       public          postgres    false    210    3169    211            h           2606    16910 %   car_model fka6512klytfdlsuvvv5n0vgbus    FK CONSTRAINT     �   ALTER TABLE ONLY public.car_model
    ADD CONSTRAINT fka6512klytfdlsuvvv5n0vgbus FOREIGN KEY (car_model_id) REFERENCES public.tb_parking_spot(id);
 O   ALTER TABLE ONLY public.car_model DROP CONSTRAINT fka6512klytfdlsuvvv5n0vgbus;
       public          postgres    false    211    3173    210            i           2606    16920 %   car_model fkab60k3rqpku1m6b6jhpii8dea    FK CONSTRAINT     �   ALTER TABLE ONLY public.car_model
    ADD CONSTRAINT fkab60k3rqpku1m6b6jhpii8dea FOREIGN KEY (parking_spot_id) REFERENCES public.tb_parking_spot(id);
 O   ALTER TABLE ONLY public.car_model DROP CONSTRAINT fkab60k3rqpku1m6b6jhpii8dea;
       public          postgres    false    210    3173    211            �   �   x�����0��n)Hh)&�:x��]ǜK�S�4���-�:�9/ۗ����ca�I�8;~-�k�l��0����@m	e��c��l$mO gh c��fj|��jG��m{��`��!ʃ�m�ՙ�a%�!\��$��rT����(Oc���nf����/n%�c�z�g-�먭67E㴭?Z[Ż�,�;��%.ן������H#O�      �   _  x���QK�0���}��mBSrI.i�Ȕ�L��ג�R)v���������%���ˏ[�ߖ�-[���l��&�c��~�j����G�h(c~J�.uc�B�ǯ�]�T��.�!cC�l��Ǧ�*�c:�i�;4�MU�wS�~�v=��j�}<��/��b�k�
(r��v
x�q��b���Ka�%��ܤ��rpL�R�\^h��p�:߱MO}[�S���~����-@��c��5�"�
����3�d~<���*��A��
׷W5�W�r�r�j��q�T4щ䴺��^���D�ke�W�x[rH����J3��Tx�iGH�I+/���P
[J�;0��U������-�     