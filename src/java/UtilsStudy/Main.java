package UtilsStudy;

import com.google.common.collect.Lists;
import com.google.errorprone.annotations.Var;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2020年01月10日 17:14
 */
public class Main {

    @Test
    public void StringUtilsTest(){
        String name = "Jason";
        String name1 = "Jason";
        String name2 = "jason";

        //判断两个String是否相等
        System.out.println(StringUtils.equals(name, name1));
        System.out.println(StringUtils.equals(name, name2));
        //判断两个string是否相等,不比较大小写
        System.out.println(StringUtils.equalsIgnoreCase(name, name1));
        System.out.println(StringUtils.equalsIgnoreCase(name, name2));

        //判断多个String是否包含两个相等的
        System.out.println(StringUtils.equalsAny(name, name1, name2));
        System.out.println(StringUtils.equalsAnyIgnoreCase(name, name1, name2));

    }

    @Test
    public void ArrayUtilsTest(){
        TestUser testUser1 = new TestUser().setAge(23).setName("Jason");
        TestUser testUser2 = new TestUser().setAge(24).setName("Jason");
        TestUser testUser3 = new TestUser().setAge(23).setName("Jason");

        List<TestUser> userList = Lists.newArrayList(testUser1,testUser2);
        List<TestUser> userList2 = Lists.newArrayList(testUser3,testUser2);

        //对比两个list是否相等
        System.out.println(ArrayUtils.contains(new List[]{userList}, userList2));
        System.out.println(userList.contains(userList2));
    }

}
