package com.example.nhahang.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	@Autowired
	  private JavaMailSender javaMailSender;
	public void sendOtpEmail(String email, String verificationCode) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true, "utf-8");

        String emailContent = "<div>"
        		  + "<p>Mã OTP cua ban la: " + verificationCode + "</p>"
        		  + "<p>Gmail cua ban la: " + email + "</p>"
                + "<a href=\"http://localhost:8080/api/user/verify-account?email=" + email + "&otp=" + verificationCode + "\" target=\"_blank\">Verify OTP HERE</a>"
                + "</div>";
        

        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP Để LOGIN");
        mimeMessageHelper.setText(emailContent, true);

        javaMailSender.send(mimeMessage);
    }
    public void sendStatus(String email, String status) throws MessagingException {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true, "utf-8");

      String emailContent = "<div>"
            +"<p> Cập nhật trạng thái đơn hàng: " + status +"</p>"
              + "</div>";
      

      mimeMessageHelper.setTo(email);
      mimeMessageHelper.setSubject("Status Order");
      mimeMessageHelper.setText(emailContent, true);

      javaMailSender.send(mimeMessage);
  }

  public void sendCancel(String email, String status) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true, "utf-8");

    String emailContent = "<div>"
          +"<p> Cập nhật trạng thái đặt bàn: " + status +"</p>"
          +"<p> thông tin liên hệ: 0858453639 " + "</p>"
            + "</div>";
    

    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("Status dat ban");
    mimeMessageHelper.setText(emailContent, true);

    javaMailSender.send(mimeMessage);
}

	}