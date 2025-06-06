package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {
	
	public String gerarToken(Usuario usuario) {

		try {
			var algoritimo = Algorithm.HMAC256("12345678");
			return JWT.create()
				.withIssuer("API Voll.med")
				.withSubject(usuario.getLogin())
				.withExpiresAt(dataExpiracao())
				.sign(algoritimo);
		} catch (JWTCreationException exception){
			throw new RuntimeException(
				"Erro ao gerar o token JWT", exception
			);
		}
	}
	
	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2)
			.toInstant(ZoneOffset.of("-03:00"));
	}

}
