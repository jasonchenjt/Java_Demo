package DateTimeStudy;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2020年01月10日 9:40
 */
public class Main {

    public static void main(String[] args) {
        //解析日期
        String dateStr= "2020年01月10日";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        LocalDate date= LocalDate.parse(dateStr, formatter);
        System.out.println(date);

        //日期转换为字符串
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss a");
        String nowStr = now .format(format);
        System.out.println("逆转"+format.format(now));
        System.out.println(nowStr);
    }


    @Test
    public void Test(){

        //巨坑的MM, 大写MM表示月份,小写mm表示分钟,不可胡乱使用.
        // 注意使用大写的YYYY: 当计算年末时间时,如12月31日,使用YYYY会先判断该日所在的week是否属于2019年,如果不属于就会自动加一年,变成2020/12/31
        //注意使用大写的DD:大写的DD表示计算当前日期为该年份的第几日,只能计算前99日
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("YYYY/MM/dd");
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter format3 = DateTimeFormatter.ofPattern("yyyy/MM/DD");

        LocalDate date1 = LocalDate.of(2020, Month.JANUARY,01);
        LocalDate date2 = LocalDate.of(2019, Month.DECEMBER,31);
        LocalDate date3 = LocalDate.of(2019, 4,10);

        System.out.println( date1+"-->"+format1.format(date1)); //2020-01-01-->2020/01/01
        System.out.println(date1+"-->"+format2.format(date1)); //2020-01-01-->2020/01/01
        System.out.println(date2+"-->"+format1.format(date2)); //2019-12-31-->2020/12/31 (注意此处的变化)
        System.out.println(date2+"-->"+format2.format(date2)); //2019-12-31-->2019/12/31
        System.out.println(date2+"-->"+format3.format(date3)); //2019-12-31-->2019/12/31
    }

    private static void tryit(int Y, int M, int D, String pat) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pat);
        LocalDate dat = LocalDate.of(Y,M,D);
        String str = fmt.format(dat);
        System.out.printf("Y=%04d M=%02d D=%02d " +
                "formatted with " +
                "\"%s\" -> %s\n",Y,M,D,pat,str);
    }
    @Test
    public void Test2(){
        tryit(2020,01,20,"MM/DD/YYYY");
        tryit(2020,01,21,"DD/MM/YYYY");
        tryit(2020,01,22,"YYYY-MM-DD");
        tryit(2020,03,17,"MM/DD/YYYY");
        tryit(2020,03,18,"DD/MM/YYYY");
        tryit(2020,03,19,"YYYY-MM-DD");
    }
}
