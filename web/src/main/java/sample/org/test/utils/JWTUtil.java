package sample.org.test.utils;

// JWTUtil.java

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.Payload;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.util.Date;
import java.util.List;

public class JWTUtil {
    private static final String SECRET_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqGKukO1De7zhZj6+H0qtjTkVxwTCpvKe4eCZ0FPqri0cb2JZfXJ/DgYSF6vUpwmJG8wVQZKjeGcjDOL5UlsuusFncCzWBQ7RKNUSesmQRMSGkVb1/3j+skZ6UtW+5u09lHNsj6tQ51s1SPrCBkedbNf0Tp0GbMJDyR4e9T04ZZwIDAQAB"; // Replace with a secure secret key
    private static final long EXPIRATION_TIME = 86400000; // 24 hours

    static {
        final String configDir = System.getProperty("jboss.server.config.dir");
        System.out.println("configDir:  " + configDir);
        final Path keyStore = Path.of(configDir, "yourKeystore.jks");
        char[] password = "password".toCharArray();
        String alias = "yourAlias";
        PrivateKey pk = null;
        try (InputStream in = Files.newInputStream(keyStore)) {
            final KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(in, password);
            Key key = ks.getKey(alias, password);
            if (key instanceof PrivateKey) {
                pk = (PrivateKey) key;
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
        privateKey = pk;
    }

    private static final PrivateKey privateKey;
    private static final int TOKEN_VALIDITY = 14400;
    private static final String ISSUER = "is";
    private static final String AUDIENCE = "au";

    public static String generateToken(String username) throws NoSuchAlgorithmException {
        /*Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject("sub")
                .setAudience("as.example.com")
                .setIssuer("api.example.com")
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();*/

        // Sample claims for the JWT
        String subject = "1234567890";
        final String[] roles = {"user", "admin"};
        final JsonArrayBuilder rolesBuilder = Json.createArrayBuilder(List.of(roles));
        final JsonObjectBuilder claimsBuilder = Json.createObjectBuilder()
                .add("sub", subject)
                .add("iss", ISSUER)
                .add("aud", AUDIENCE)
                .add("groups", rolesBuilder.build())
                .add("exp", ((System.currentTimeMillis() / 1000) + TOKEN_VALIDITY));

        // Generate Bearer token using RS256 algorithm
        String token = Jwts.builder()
                .setPayload(claimsBuilder.build().toString())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();

        System.out.println("Bearer Token: " + token);
        return token;
    }

    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static KeyPair generateRsaKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }
}