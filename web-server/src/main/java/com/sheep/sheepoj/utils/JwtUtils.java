package com.sheep.sheepoj.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    public static final String SECRET = "sheepisbeautiful";
    public static final long EXPIRATION = 3600*24*14;
    public static final String iss="Twill";
    public static final String sub="SheepOJ";

    public static String createToken(String userId)
    {
        SignatureAlgorithm algorithm=SignatureAlgorithm.HS256;
        Date now=new Date(System.currentTimeMillis());
        Date expireDate=new Date(now.getTime()+EXPIRATION);
        Key key=new SecretKeySpec(DatatypeConverter.parseBase64Binary(SECRET),algorithm.getJcaName());

        Map<String,Object> claims= new HashMap<String,Object>();
        claims.put("userDetails",userId);

        return
        Jwts.builder().setId(userId)
                .setIssuedAt(now)
                .setIssuer(iss)
                .setAudience(sub)
                .setExpiration(expireDate)
                .signWith(algorithm,key)
                .compact();
    }

    //将jwt字符串转化为claim,如果该jwt字符串的签名不对，就会抛出异常
    public static Claims parseJWT(String jwt) {

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception exception) {
            return null;
        }
    }

    //判断一个jwt声明是否过期
    public static Boolean isTokenExpired(Claims claims) {
        return claims == null || claims.getExpiration().before(new Date());
    }


}
