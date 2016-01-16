package myfuel.Tools;


public class MailThread implements Runnable 
{
	String emailAddress;
	String subject;
	String content;
	
	public MailThread(String emailAddress, String subject, String content)
	{
		this.emailAddress = emailAddress;
		this.subject = subject;
		this.content = content;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		SendMailTLS.sendMail(emailAddress, subject, content);
	}
	
}
