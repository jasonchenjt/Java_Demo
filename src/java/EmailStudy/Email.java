package EmailStudy;

import java.io.FileOutputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2019年11月21日 12:23
 */
public class Email implements Runnable{

    public void run(){
        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.host", "smtp.qq.com");
//        prop.setProperty("mail.smtp.host", "smtp-std.hkpo.hksarg");
        prop.setProperty("mail.smtp.auth", "true");
        prop.put("mail.smtp.port","25");
        prop.setProperty("mail.debug", "true");
        try{
            //使用JavaMail发送邮件的5个步骤
            //1、创建session
            Session session = Session.getInstance(prop);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            ts.connect("smtp.qq.com", "1465738672", "rgsggmkhibpfbaed");
            //4、创建邮件
            Message message = createSimpleMail(session);
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //发送纯文本邮件
    public static MimeMessage createSimpleMail(Session session)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("1465738672@qq.com"));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("1465738672@qq.com"));
        //邮件的标题
//        message.setSubject("邮件发送测试","text/html;charset=UTF-8");
        message.setSubject(MimeUtility.encodeText("中文邮件标题", "utf-8", "B"));
        //邮件的文本内容
//        message.setContent("请查收!\n  test by jason", "text/html;charset=UTF-8");
        message.setText("请查收!<br>  test by jason","UTF-8");
        //返回创建好的邮件对象
        return message;
    }

    //编辑带图片邮件
    public static MimeMessage createImageMail(Session session) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("1465738672@qq.com"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("546846575@qq.com"));
        //邮件标题
        message.setSubject("带图片的邮件");

        // 准备邮件数据
        // 准备邮件正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("这是一封邮件正文带图片<img src='cid:picture1'/>的邮件", "text/html;charset=UTF-8");
        // 准备图片数据
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("src\\0.jpg"));
        image.setDataHandler(dh);
        image.setContentID("picture1");
        // 描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(image);
        mm.setSubType("related");

        message.setContent(mm);
        message.saveChanges();
        //将创建好的邮件写入到E盘以文件的形式进行保存
        message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
        //返回创建好的邮件
        return message;
    }

    //编辑带图片邮件
    public static MimeMessage createhtmlMail(Session session) throws Exception {
        //创建邮件
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("1465738672@qq.com"));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("546846575@qq.com"));
        //邮件标题
        message.setSubject("带图片的邮件");

        // 准备邮件数据
        // 准备邮件正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("这是一封邮件正文带图片<br>的邮件", "text/html;charset=UTF-8");
        // 描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);

        message.setContent(mm);
        message.saveChanges();
        //将创建好的邮件写入到E盘以文件的形式进行保存
//        message.writeTo(new FileOutputStream("E:\\ImageMail.eml"));
        //返回创建好的邮件
        return message;
    }

}
