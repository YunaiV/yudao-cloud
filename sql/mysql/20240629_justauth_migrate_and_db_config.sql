alter table system_social_client
add `redirect_uri` varchar(255) DEFAULT NULL,
add `ignore_check_redirect_uri` bit(1) NOT NULL COMMENT '忽略校验redirectUri参数',
add `ignore_check_state` bit(1) NOT NULL COMMENT '忽略校验state参数'