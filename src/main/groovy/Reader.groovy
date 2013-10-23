class Reader {
 
    static void main(String[] args) {
 
        String subject = 'test'
 
        def emailer = new GroovyEmailer()
 
        emailer.search(subject)
    }
}