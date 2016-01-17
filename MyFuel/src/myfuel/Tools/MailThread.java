package myfuel.Tools;

/**
 * Mail Thread Class, using the static class SendMailTLS for sending one mail 
 * with the specific address,content and subject.
 *
 */
public class MailThread implements Runnable 
{
	/**
	 * Destination Email address value.
	 */
	String emailAddress;
	/**
	 * Message subject.
	 */
	String subject;
	/**
	 * Message content.
	 */
	String content;
	
	/**
	 * Create new Mail Thread, with specific message details.
	 * @param emailAddress- Destination Email address value.
	 * @param subject- Message subject.
	 * @param content - Message content.
	 */
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
