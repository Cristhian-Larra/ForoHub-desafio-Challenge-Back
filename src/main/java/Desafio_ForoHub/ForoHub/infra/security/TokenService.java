package Desafio_ForoHub.ForoHub.infra.security;

import Desafio_ForoHub.ForoHub.domain.usuario.Usuario;
import Desafio_ForoHub.ForoHub.domain.usuario.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private String apiSecret;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generarToken(Usuario usuario) {
        apiSecret = usuario.getPassword();
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("forohub_api")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al generar el token");
        }
    }

    @Transactional
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("token nulo");
        }
        DecodedJWT verifier = null;
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            Long userId = decodedJWT.getClaim("id").asLong();
            Usuario usuario = usuarioRepository.getReferenceById(userId);
            apiSecret = usuario.getPassword();
            System.out.println(apiSecret);
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("forohub_api")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }
        if (verifier == null || verifier.getSubject() == null) {
            throw new RuntimeException("Verifier inválido");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
