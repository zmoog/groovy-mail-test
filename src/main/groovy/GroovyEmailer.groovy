import javax.mail.*
import javax.mail.internet.*
import javax.mail.search.*
 
class GroovyEmailer {
 
    private config
 
    GroovyEmailer() {
        config = new ConfigSlurper().parse(new File('config.groovy').toURL())
    }

    void sendMail(String to, String subject, String message){
        def props = new Properties()
        //props.put "mail.smtps.auth", "true"
        props.put "mail.smtp.starttls.enable", "true"
        props.put "mail.smtp.auth", "true"
 
        def session = Session.getDefaultInstance props, null
 
        def msg = new MimeMessage(session)
 
        msg.setSubject(subject)
        msg.setText(message)
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to, to))
 
        def transport = session.getTransport "smtp"
 
        try {
         
            transport.connect(config.mailer.host, config.mailer.auth.username, config.mailer.auth.password)
            transport.sendMessage(msg, msg.getAllRecipients())

        } catch (Exception e) {
            println e
       }
    }

    void search(String subject) {
        def props = new Properties()
        props.put "mail.store.protocol", "imap"
        props.put "mail.imap.host", config.mailer.host
        props.put "mail.imap.port", config.mailer.imap.port

        def session = Session.getDefaultInstance props, null

        def store = session.getStore("imap")
        def inbox

        try {

            store.connect(config.mailer.host, config.mailer.auth.username, config.mailer.auth.password)

            inbox = store.getFolder('INBOX')
            inbox.open(Folder.READ_ONLY)

            def messages = inbox.search(
              new SubjectTerm(subject)
            )

            println("Found ${messages.length} messages:")

            messages.each { msg ->

                println(msg.class)
                println "${msg.sentDate.format('dd/MM/yyyy')},${msg.sender},${msg.subject}"

            }

        } finally {
            if (inbox) {
                inbox.close(false)
            }
            store.close()
        }

    }
}