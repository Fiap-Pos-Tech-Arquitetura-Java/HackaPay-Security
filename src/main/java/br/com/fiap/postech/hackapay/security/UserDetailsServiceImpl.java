package br.com.fiap.postech.hackapay.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Value("${hackapay.user.url}")
	String baseURI;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		RestClient restClient = RestClient.create();
		var uri = baseURI + "/findByLogin/{userName}";
		System.out.println("uri = " + uri);
		User user = restClient.get()
				.uri(uri, userName)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
					System.out.println("statusCode = "+ response.getStatusCode() + " statusText = "+ response.getStatusText());
					throw new IllegalArgumentException("Falha na busca do usuario pelo login: ");
				}).onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
					System.out.println("statusCode = "+ response.getStatusCode() + " statusText = "+ response.getStatusText());
					throw new IllegalArgumentException("Falha na busca do usuario pelo login 2 ..");
				})
				.body(User.class);
		if (user != null) {
			return new UserDetailsImpl(user);
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}
}
