package alkemy.disney.service.impl;

import alkemy.disney.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class EmailServiceImpl implements EmailService {

    private final Environment environment;

    @Value("${alkemy.icons.email.sender}")
    private String emailSender;

    @Value("${alkemy.icons.email.enabled}")
    private boolean enable;

    @Autowired
    public EmailServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void sendWelcomeTo(String to) {
        if (enable) {
            String apiKey = environment.getProperty("EMAIL_API_KEY");
            Email fromEmail = new Email(emailSender);
            Email toEmail = new Email(to);
            Content content = new Content("text/plain", "Welcome to Alkemy Disney");
            String subject = "Alkemy Disney";
            Mail mail = new Mail(fromEmail, subject, toEmail, content);
            sendEmail(mail, apiKey);
        }
    }

    private void sendEmail(@NotNull Mail mail,
                           @NotNull String apiKey) {
        try {
            SendGrid sendGrid = new SendGrid(apiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

}
