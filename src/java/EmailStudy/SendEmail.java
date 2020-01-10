package EmailStudy;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2019年11月21日 14:55
 */

public class SendEmail {

    public static void main(String[] args) {
        new Thread(new Email()).start();
    }
}
