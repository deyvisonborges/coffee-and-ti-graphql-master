package br.com.coffeeandit.graphql;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class GraphqlClient {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {


        var client = HttpClient.newHttpClient();
        var uri = new URI("http://localhost:8080/graphql/");
        var request = HttpRequest.newBuilder(uri).
                POST(HttpRequest.BodyPublishers.ofFile(Paths.get("query.json")))
                .header("accept", MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", MediaType.APPLICATION_JSON_VALUE).
                build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Files.write(Paths.get("resultado.json"), response.body().getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);

    }
}
