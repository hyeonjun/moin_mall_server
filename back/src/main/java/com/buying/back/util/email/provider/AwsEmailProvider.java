package com.buying.back.util.email.provider;

import static org.apache.commons.codec.CharEncoding.UTF_8;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.buying.back.util.email.provider.exception.AwsEmailException;
import com.buying.back.util.email.provider.exception.AwsEmailException.AwsExceptionCode;
import com.buying.back.util.email.template.HtmlEmailTemplate;
import com.buying.back.util.html.HtmlBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AwsEmailProvider implements EmailProvider {

  private final HtmlBuilder htmlBuilder;
  private final AmazonSimpleEmailService service;

  @Override
  public void send(HtmlEmailTemplate template) {
    try {
      SendEmailRequest request = new SendEmailRequest()
        .withSource(template.getFromAddress())
        .withDestination(getReceivers(template))
        .withMessage(getMessage(template));
      service.sendEmail(request);
    } catch (Exception e) {
      log.error("[ERROR] ", e);
      throw new AwsEmailException(AwsExceptionCode.NOT_SENT_AWS_EMAIL);
    }
  }

  private Destination getReceivers(HtmlEmailTemplate template) {
    return new Destination()
      .withToAddresses(template.getToAddressList());
  }

  private Message getMessage(HtmlEmailTemplate template) {
    return new Message()
      .withSubject(createContent(template.getSubject()))
      .withBody(new Body().withHtml(createContent(htmlBuilder.buildHtml(template))));
  }

  private Content createContent(String data) {
    return new Content().withData(data).withCharset(UTF_8);
  }
}
