package UtilsStudy;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2020年01月10日 17:23
 */
@Data
@Accessors(chain = true)
public class TestUser {
    private Integer id;
    private String name;
    private int age;
}
