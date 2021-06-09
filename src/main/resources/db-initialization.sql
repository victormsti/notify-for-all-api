CREATE TABLE user_app (
	    id  bigserial not null,
		username varchar(255) not null,
        email varchar(100) not null,
		password varchar(255) not null,
        company_name varchar(100),
		phone varchar(100),
		address varchar(255),
		automatic_login boolean,
        primary key (id)
);

CREATE TABLE app (
        id  bigserial not null,
        id_user_app int8 not null,
		identifier_name varchar(255) not null,
		enable_notification_web_push boolean not null,
		enable_notification_email boolean not null,
		enable_notification_sms boolean not null,
		primary key (id),
		foreign key (id_user_app) references user_app(id)
);


create table web_push_user_app_configuration(
	id  bigserial not null,
    id_app int8 not null,
	site_messaging_name_web_push varchar(100),
    site_address_messaging_web_push varchar(100),
    site_icon_web_push varchar(255),
    permission_text_message_web_push text,
    permit_text_button_web_push varchar(255),
    deny_text_button_web_push varchar(255),
    notification_title_web_push varchar(255),
    welcome_text_message_web_push text,
    enable_destiny_link_web_push boolean,
    destiny_link_address_web_push varchar(255),
	primary key (id),
	foreign key (id_app) references app(id)
);

create table email_user_app_configuration(
	id  bigserial not null,
    id_app int8 not null,
	smtp_service_name_email varchar(100),
    port_email varchar(50),
    login_email varchar(100),
    password_email varchar(255),
    template_email text,
	primary key (id),
	foreign key (id_app) references app(id)
);

create table sms_user_app_configuration(
	id  bigserial not null,
    id_app int8 not null,
	provider_sms varchar(255),
    login_sms varchar(255),
    password_sms varchar(255),
	primary key (id),
	foreign key (id_app) references app(id)
);

CREATE TABLE notification (
        id  bigserial not null,
        id_app int8 not null,
        channel_type varchar(30) not null,
        id_web_push_user_app_configuration int8 null,
		id_email_user_app_configuration int8 null,
		id_sms_user_app_configuration int8 null,
		notification_origin varchar(50) not null,
		sender_phone_number varchar(50),
		receiver_phone_number varchar(50),
		sender_name_email varchar(100),
        sender_email varchar(100),
        recipient_name_email varchar(100),
        recipient_email varchar(100),
		text_email text,
		text_sms text,
		read_confirmation boolean,
		send_date date not null,
		received_date date,
		primary key (id),
		foreign key (id_app) references app(id),
        foreign key (id_web_push_user_app_configuration) references web_push_user_app_configuration(id),
		foreign key (id_email_user_app_configuration) references email_user_app_configuration(id),
		foreign key (id_sms_user_app_configuration) references sms_user_app_configuration(id)
);

--drop table app cascade;
--drop table notification cascade;
--drop table user_app cascade;
--drop table web_push_user_app_configuration cascade;
--drop table email_user_app_configuration cascade;
--drop table sms_user_app_configuration cascade;
