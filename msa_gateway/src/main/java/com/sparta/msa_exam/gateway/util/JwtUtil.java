package com.sparta.msa_exam.gateway.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Log4j2
public class JwtUtil {

    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    @Value("${service.jwt.secret-key}")
    private String secretKey;

/*    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    private Key key;

    // Base64 -> HMAC 키 생성
    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // JWT Token 생성
    public String createJwtToken(String id, String role) {

        return BEARER +
                Jwts.builder()
                        .claim("id", id)
                        .claim("role", role)
                        .issuer(issuer)
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                        .signWith(key, SignatureAlgorithm.HS512)
                        .compact();
    }*/

    // Header 에서 JWT Token 추출
    public String getJwtTokenFromHeader(ServerWebExchange exchange) {

        String authHeader = exchange.getRequest().getHeaders().getFirst(AUTHENTICATION_HEADER);

        // 베어러 자르기
        if(authHeader != null && authHeader.startsWith(BEARER)){
            return authHeader.substring(7);
        }

        return null;
    }

    // JWT Token 검증
    public boolean validateToken(String token, ServerWebExchange exchange){
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key)
                    .build().parseSignedClaims(token);
            log.info("#####payload :: " + claimsJws.getPayload().toString());
            Claims claims = claimsJws.getBody();
            exchange.getRequest().mutate()
                    .header("X-Id", claims.get("id").toString()) // 변수 이름 일치해야함.
                    .header("X-Role", claims.get("role").toString())
                    .build();
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

}
