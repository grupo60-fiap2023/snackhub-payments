package com.alura.fiap.infrastructure.configuration;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"development", "test"})
public class MongoClientConnection {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://marlonfcosta90:1HhW07spwd7nwnqx@cluster0.onm2dkj.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        // Configurar a API do servidor MongoDB
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        // Configurar as configurações do cliente MongoDB
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Criar um novo cliente e conectar ao servidor
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Enviar um ping para confirmar uma conexão bem-sucedida
                MongoDatabase database = mongoClient.getDatabase("admin");
                Document pingCommand = new Document("ping", 1);
                Document pingResult = database.runCommand(pingCommand);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                System.out.println("Ping result: " + pingResult);
            } catch (MongoException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

