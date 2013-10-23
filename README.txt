========
JavaMail 
========

Importare il certificato del server SMTP o IMAP
-----------------------------------------------

Source: http://springinpractice.com/2012/04/29/fixing-pkix-path-building-issues-when-using-javamail-and-smtp

::

  $ openssl s_client -connect mailer.test.it:25 -starttls smtp > test-smtp.cer


Poi rimuovere tutto lasciando solo la parte del certificato:

::

  -----BEGIN CERTIFICATE-----
  MIICvDCCAiWgAwIBAgIFE4CDFHIwDQYJKoZIhvcNAQEFBQAwgZoxCzAJBgNVBAYT
  AlVTMQwwCgYDVQQIEwNOL0ExDDAKBgNVBAcTA04vQTEkMCIGA1UEChMbWmltYnJh
  IENvbGxhYm9yYXRpb24gU2VydmVyMSQwIgYDVQQLExtaaW1icmEgQ29sbGFib3Jh
  dGlvbiBTZXJ2ZXIxIzAhBgNVBAMTGm1haWxlci5hZHZhbHNvLm9saXZldHRpLml0
  MB4XDTEzMTAwMzIwMTc1OVoXDTIzMTAwMTIwMTc1OVowgYwxCzAJBgNVBAYTAlVT
  MQwwCgYDVQQIEwNOL0ExJDAiBgNVBAoTG1ppbWJyYSBDb2xsYWJvcmF0aW9uIFNl
  cnZlcjEkMCIGA1UECxMbWmltYnJhIENvbGxhYm9yYXRpb24gU2VydmVyMSMwIQYD
  VQQDExptYWlsZXIuYWR2YWxzby5vbGl2ZXR0aS5pdDCBnzANBgkqhkiG9w0BAQEF
  AAOBjQAwgYkCgYEA0i4se8PNxpyFJngOxxu9F6SZok3GJGjpzBzNZtHK0dkKfzxj
  w6N12236OqQmLYaDO2RNVicz8Db/UXngS/SGQefHXb84rOt8955bUTInAMs08HeD
  SpycTx3bcAf+otiwYZ/ByCXHMSIi+IZg5CgwOZ7PAgHM1fnoe2jhnvW21HcCAwEA
  AaMaMBgwCQYDVR0TBAIwADALBgNVHQ8EBAMCBeAwDQYJKoZIhvcNAQEFBQADgYEA
  EYGRKcogRyyfM8z15ap9lX2x9gP8MqfsavB/1/UmA4xDTlMBu8W7biDHBCVLJcGb
  XFhT2s4CcAjC0n/+GCvTvMLTMmnUZCzIZ+EsKcbQJB1EalmtIaRw7J7FE8vUbO62
  NkoISEaDjSAUsBEKoBWW9NZas50+vYCYP+uhpN3RlXY=
  -----END CERTIFICATE-----


Quindi importare il certificato nel truststore:

::

  $ keytool -import -alias mailer.test.it -file mailer.test.it.cer -keystore cacerts
