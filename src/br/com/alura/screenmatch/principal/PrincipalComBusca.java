package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do filme que deseja buscar: ");
        var busca = scanner.nextLine();
        try {
            String endereco = "https://www.omdbapi.com/?t="+busca.replace(" ","+")+"&apikey=846994ef";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);

            // Deserializando o JSON para um objeto TituloOmdb
            // Esse builder faz com que o dado que chega como "Title" seja possivel interpretado
            // como "title", corrigindo o erro de "null"
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();

            TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            System.out.println(meuTituloOmdb);
            //try {
            Titulo meuTitulo = new Titulo(meuTituloOmdb);
            System.out.println("Titulo ja convertido:");
            System.out.println(meuTitulo);


        } catch (NumberFormatException e) {
            System.out.println("Aconteceu um erro:");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println("Algum erro de endereco na busca, verifique o endereco");
        }catch (ErroDeConversaoDeAnoException e){
            System.out.println(e.getMensagem());
        }

        System.out.println("Programa finalizou corretamente.");


    }
}
