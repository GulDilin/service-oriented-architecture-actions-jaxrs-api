package guldilin.util;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class ClientFactoryBuilder {
    private static Client client;

    public static Client getClient() {
        if (client != null) return client;
//        String keystoreLocation = System.getenv("KEYSTORE_LOCATION");
//        String keystorePassword = System.getenv("KEYSTORE_PASSWORD");
//        FileInputStream is = new FileInputStream(keystoreLocation);
//        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
//        keystore.load(is, keystorePassword.toCharArray());
//        client = ClientBuilder.newBuilder().trustStore(keystore).build();
        client = ClientBuilder.newBuilder().build();
//        client = new ResteasyClient();
        return client;
    }
}
