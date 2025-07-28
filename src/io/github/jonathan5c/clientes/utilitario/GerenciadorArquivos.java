package io.github.jonathan5c.clientes.utilitario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GerenciadorArquivos {
    public static final String PASTA_FOTOS = "C:\\Users\\jonat\\OneDrive\\Documentos\\fotosUsuarios";

    public static void persistirArquivo(String nomeArquivo, byte[] bytes) {
        try {
            Path path = Paths.get(PASTA_FOTOS + nomeArquivo);
            Files.write(path, bytes);
        } catch (IOException ex) {
            System.out.println("Ocorreu um erro " + ex.getMessage());
        }
    }
}
