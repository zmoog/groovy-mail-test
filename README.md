# Groovy Mail Test

Un piccola raccolta di script Groovy per testare JavaMail API.

## Dipendenze

Per eseguire gli script è necessario avere Java 6+. 

Groovy e Gradle stesso vengono scaricati dal [wrapper di Gradle][gradle-wrapper].


## Configurazione

Il principale file da personalizzare è il file ``config.groovy``:

> mailer {
>   auth {
>     username = 'test@test.it'
>     password = 'secret'
>   }
>   host = 'mx.test.it'
>   imap {
>     port = 143
>   }
> }

Nei file ``Reader.groovy`` e ``Sender.groovy`` è rispettivamente possibile personalizzare la stringa di ricerca nel soggetto e l'indirizzo di destinazione.


## Esecuzione

Test lettura mail:

  > ./gradlew runReader

Test invio mail:

  > ./gradlew runSender


Su Windows occorre utilizzare lo script ``gradlew.bat``.


## (Opzionale) Importare il certificato del server SMTP o IMAP

Da utilizzare solo se il server SMTP non ha un certificato riconosciuto (ad esempio è self-signed) è necessario importarlo nel TrushStore utilizzato dalla JVM per poter utilizzare il TLS o SMTPS.


Source: http://springinpractice.com/2012/04/29/fixing-pkix-path-building-issues-when-using-javamail-and-smtp


  > $ openssl s_client -connect mailer.test.it:25 -starttls smtp > test-smtp.cer


Poi rimuovere tutto lasciando solo la parte del certificato:

  > -----BEGIN CERTIFICATE-----
  > MIICvDCCAiWgAwIBAgIFE4CDFHIwDQYJKoZIhvcNAQEFBQAwgZoxCzAJBgNVBAYT
  > AlVTMQwwCgYDVQQIEwNOL0ExDDAKBgNVBAcTA04vQTEkMCIGA1UEChMbWmltYnJh
  > IENvbGxhYm9yYXRpb24gU2VydmVyMSQwIgYDVQQLExtaaW1icmEgQ29sbGFib3Jh
  > dGlvbiBTZXJ2ZXIxIzAhBgNVBAMTGm1haWxlci5hZHZhbHNvLm9saXZldHRpLml0
  > MB4XDTEzMTAwMzIwMTc1OVoXDTIzMTAwMTIwMTc1OVowgYwxCzAJBgNVBAYTAlVT
  > MQwwCgYDVQQIEwNOL0ExJDAiBgNVBAoTG1ppbWJyYSBDb2xsYWJvcmF0aW9uIFNl
  > cnZlcjEkMCIGA1UECxMbWmltYnJhIENvbGxhYm9yYXRpb24gU2VydmVyMSMwIQYD
  > VQQDExptYWlsZXIuYWR2YWxzby5vbGl2ZXR0aS5pdDCBnzANBgkqhkiG9w0BAQEF
  > AAOBjQAwgYkCgYEA0i4se8PNxpyFJngOxxu9F6SZok3GJGjpzBzNZtHK0dkKfzxj
  > w6N12236OqQmLYaDO2RNVicz8Db/UXngS/SGQefHXb84rOt8955bUTInAMs08HeD
  > SpycTx3bcAf+otiwYZ/ByCXHMSIi+IZg5CgwOZ7PAgHM1fnoe2jhnvW21HcCAwEA
  > AaMaMBgwCQYDVR0TBAIwADALBgNVHQ8EBAMCBeAwDQYJKoZIhvcNAQEFBQADgYEA
  > EYGRKcogRyyfM8z15ap9lX2x9gP8MqfsavB/1/UmA4xDTlMBu8W7biDHBCVLJcGb
  > XFhT2s4CcAjC0n/+GCvTvMLTMmnUZCzIZ+EsKcbQJB1EalmtIaRw7J7FE8vUbO62
  > NkoISEaDjSAUsBEKoBWW9NZas50+vYCYP+uhpN3RlXY=
  > -----END CERTIFICATE-----


Quindi importare il certificato nel truststore:

  > $ keytool -import -alias mailer.test.it -file mailer.test.it.cer -keystore cacerts



[gradle-wrapper]: http://www.gradle.org/docs/current/userguide/gradle_wrapper.html  "Chapter 61. The Gradle Wrapper"