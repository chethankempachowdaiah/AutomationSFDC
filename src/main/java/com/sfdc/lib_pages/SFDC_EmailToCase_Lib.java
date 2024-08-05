package com.sfdc.lib_pages;

import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;

/**
 * @author priyanka.acharya, Date 10/FEB/2020
 * 
 *         For Email to case testing, This utility will initiate the email for
 *         case creation in the respective QA environment
 *
 */
public class SFDC_EmailToCase_Lib extends MyListener {

	public static String username = InputData.emailToCaseCustomerID;
	public static String password = InputData.emailToCaseCustomerPassword;
	public static String hostname = InputData.emailToCaseCustomerHost;
	public static String recipient = InputData.emailToCaseCustomerRecipientID;

	public static String subjectLine = "QA Urgent_Email to case Automated Test "
			+ AdditionalUtilities.currentSystemTime("yyyy.MM.dd.HH.mm.ss");
	public static String mailBody = "As QA Automation Script, Testing Email to Case.This should create a case in given env.";

	public static String caseID;

	/**
	 * Set up Email Properties
	 * 
	 * Authenticate user
	 * 
	 * Create object of MimeMessage class
	 * 
	 * Set the from address
	 * 
	 * Set the recipient address
	 * 
	 * Add the subject link
	 * 
	 * Create object to add multimedia type content
	 * 
	 * Set the body of email
	 * 
	 * Create object of MimeMultipart class
	 * 
	 * add body part
	 * 
	 * set the content and send the email
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * 
	 * 
	 */
	public static void initiateEmail() throws IOException, InterruptedException {

		// Set up Email Properties
		Properties props = new Properties();
		props.put("mail.smtp.host", hostname);

		// Authenticate user
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress(username));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

			// Add the subject link
			message.setSubject(subjectLine);

			// Create object to add multimedia type content
			BodyPart messageBodyPart = new MimeBodyPart();

			// Set the body of email
			messageBodyPart.setText(mailBody);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part
			multipart.addBodyPart(messageBodyPart);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			reportStatusPass(
					" Email with subject line: " + subjectLine + " has been sent successfully for case creation", true,
					false);
			sf.seleU.wait(10000);

		} catch (MessagingException e) {
			reportStatusFail("Error in sending email to Rep", true);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
