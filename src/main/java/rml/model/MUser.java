package rml.model;


import lombok.Data;

@Data
public class MUser {
    private String id;

    private String name;

    private Integer age;

    private String address;

}

//
//    CREATE TABLE `MUSER` (
//        `ID`  varchar(255) NOT NULL ,
//        `NAME`  varchar(255) NULL ,
//        `AGE`  INT(255) NULL ,
//        `ADDRESS`  varchar(255) NULL
//        )
//        ;