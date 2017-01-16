package spring.hotel.utils.cert;

import javax.net.ssl.*;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * Conventional cert configuration:
 * 
 * <pre>
 *    -Djavax.net.ssl.trustStore=d:/dev/tut-spring-boot-oauth2-master/github/sso-keystore2 -Djavax.net.ssl.trustStorePassword=changeit -Djavax.net.ssl.keyStore=d:/dev/tut-spring-boot-oauth2-master/github/sso-keystore2 -Djavax.net.ssl.keyStorePassword=changeit
 * </pre>
 *
 * @author Richard_Pal
 *
 */
public class CertUtils {

  private static KeyStore certTrustStore = null;

  public static void trustCertificatesByKeyStore() {
    try {
      TrustManagerFactory trustManagerFactory = TrustManagerFactory
          .getInstance(TrustManagerFactory.getDefaultAlgorithm());
      KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

      try (InputStream keyStoreStream = CertUtils.class.getResourceAsStream("sso-keystore.jks")) {
        keyStore.load(keyStoreStream, "changeit".toCharArray());
      }

      trustManagerFactory.init(keyStore);
      TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustManagers, null);
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void trustAllCertificates() {

    try {
      TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
          return null;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
          // No need to implement.
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
          // No need to implement.
        }
      } };

      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

    } catch (Exception ex) {
      throw new UnableToTrustAllCertificatesException(ex);
    }
  }

  static class UnableToTrustAllCertificatesException extends RuntimeException {
    public UnableToTrustAllCertificatesException(Throwable t) {
      super(t);
    }
  }

}
