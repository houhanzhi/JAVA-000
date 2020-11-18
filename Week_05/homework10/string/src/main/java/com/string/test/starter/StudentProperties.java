package com.string.test.starter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 来自猩猩的攻城狮hhz
 * @date 2020-11-18 11:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "student")
public class StudentProperties {

    private int id;

    private  String name;
}
