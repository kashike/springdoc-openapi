package test.org.springdoc.api.app69;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutingConfiguration {

	@Bean
	@RouterOperations({ @RouterOperation(path = "/api/user/index", method = RequestMethod.GET, beanClass = UserRepository.class, beanMethod = "getAllUsers", consumes = MediaType.APPLICATION_JSON_VALUE),
			@RouterOperation(path = "/api/user/byFirstName", method = RequestMethod.GET, beanClass = UserRepository.class, beanMethod = "getAllUsers", parameterTypes = {String.class} , consumes = MediaType.APPLICATION_JSON_VALUE),
			@RouterOperation(path = "/api/user/{id}", method = RequestMethod.GET, beanClass = UserRepository.class, beanMethod = "getUserById", consumes = MediaType.APPLICATION_JSON_VALUE),
			@RouterOperation(path = "/api/user/post", method = RequestMethod.POST, beanClass = UserRepository.class, beanMethod = "saveUser", consumes = MediaType.APPLICATION_JSON_VALUE),
			@RouterOperation(path = "/api/user/put/{id}", method = RequestMethod.PUT, beanClass = UserRepository.class, beanMethod = "putUser", consumes = MediaType.APPLICATION_JSON_VALUE),
			@RouterOperation(path = "/api/user/delete/{id}", method = RequestMethod.DELETE, beanClass = UserRepository.class, beanMethod = "deleteUser", consumes = MediaType.APPLICATION_JSON_VALUE) })
	public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
		return route(GET("/api/user/index").and(accept(MediaType.APPLICATION_JSON)), userHandler::getAll)
				.andRoute(GET("/api/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUser)
				.andRoute(POST("/api/user/post").and(accept(MediaType.APPLICATION_JSON)), userHandler::postUser)
				.andRoute(PUT("/api/user/put/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::putUser)
				.andRoute(DELETE("/api/user/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::deleteUser);
	}

}