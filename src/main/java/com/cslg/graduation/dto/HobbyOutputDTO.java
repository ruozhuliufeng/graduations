package com.cslg.graduation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HobbyOutputDTO {
    private Integer id;
    private String name;
    private String sname;//阶段名称
    private String cname;//分类名称

    public HobbyOutputDTO(Integer id, String name, String sname) {
        this.id = id;
        this.name = name;
        this.sname = sname;
    }
}
