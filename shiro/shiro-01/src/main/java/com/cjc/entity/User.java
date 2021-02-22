package com.cjc.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2021/2/22
 * Time: 16:34
 * To change this template use File | Settings | File Templates.
 **/
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private String username;
    private String password;
    private Set<String> permsSet;
}
