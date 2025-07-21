package br.com.alura.screenmatch.exercicios.etapa2;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PessoaExer {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        String json = """
                {
                    "Nome": "João",
                    "Idade": 30,
                    "Cidade": "São Paulo"
                }
                """;
        Pessoa pessoa =gson.fromJson(json, Pessoa.class);
        System.out.println(pessoa);
    }
}
