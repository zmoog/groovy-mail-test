class Sender {
 
    static void main(String[] args) {
 
        String to = 'test@test.it'
        String subject = 'test'
        String message = 'This is a test message.'
 
        def emailer = new GroovyEmailer()
 
        emailer.sendMail(to, subject, message)
    }
}
