package com.cslg.graduation.dto;

import lombok.Data;

/**
 * @author 若竹流风
 * @version 1.0
 * @className UserDTO
 * @description TODO
 * @date 2020/3/26 10:27
 */
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String sex;
    private String hname;
}
