package com.gdtel.common.base.entity;

import lombok.Data;

/**
 * Created by gdxieyue@gmail.com
 * 2017-06-14 11:29
 */
@Data
public class UserAuth {

    private String branchId;
    private String loginId;
    private String loginType;
    private String mobile;
    private String token;
    private String isLogin;
    private String avatarId;
    private String loginTime;
    private String name;
    private String fromIp;
    private String state;
    private String id;
    private String shortName;
    private String email;
    private String[] roles;
    private String orgNames;
    private String orgShortNames;
}
