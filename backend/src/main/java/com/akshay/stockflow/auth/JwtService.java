package com.akshay.stockflow.auth;
import io.jsonwebtoken.Jwts; import io.jsonwebtoken.io.Decoders; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import javax.crypto.SecretKey; import java.time.Instant; import java.util.Date;
@Service public class JwtService {
private final SecretKey key; private final long expirationMs;
public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration-ms:3600000}") long expirationMs){this.key=Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));this.expirationMs=expirationMs;}
public String generate(UserAccount user){Instant now=Instant.now();return Jwts.builder().subject(user.getUsername()).claim("role",user.getRole().name()).issuedAt(Date.from(now)).expiration(Date.from(now.plusMillis(expirationMs))).signWith(key).compact();}
public String subject(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();}
public long expirationSeconds(){return expirationMs/1000;}
public boolean valid(String token,UserAccount user){try{return subject(token).equalsIgnoreCase(user.getUsername())&&Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getExpiration().after(new Date());}catch(Exception e){return false;}}
}
