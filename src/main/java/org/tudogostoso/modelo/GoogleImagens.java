package org.tudogostoso.modelo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class GoogleImagens {
    private static final String API_KEY = "AIzaSyBZwU23Hdl_HVxhhcgJeO-7XKD5KVysBO4";
    private static final String CX = "702669bb08f8e4a1f";
    private static final String DIRETORIO_IMAGENS = "src/main/resources/org/tudogostoso/ImagensReceitas/";

    /**
     * Busca imagens no Google Custom Search API.
     *
     * @param consulta O termo de pesquisa para buscar imagens.
     * @param numImagens O número de imagens a ser retornado.
     * @return Uma lista de URLs de imagens.
     * @throws Exception Se houver um erro durante a chamada da API.
     */
    public static List<String> buscarImagens(String consulta, int numImagens) throws Exception {
        String consultaCodificada = URLEncoder.encode(consulta, "UTF-8");
        String urlString = "https://www.googleapis.com/customsearch/v1?q=" + consultaCodificada +
                "&cx=" + CX + "&key=" + API_KEY + "&searchType=image&num=" + numImagens;
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        JSONObject json = new JSONObject(content.toString());
        JSONArray items = json.getJSONArray("items");

        List<String> linksImagens = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            linksImagens.add(items.getJSONObject(i).getString("link"));
        }

        if (linksImagens.isEmpty()) {
            throw new Exception("Nenhuma imagem encontrada");
        }

        return linksImagens;
    }

    /**
     * Baixa uma imagem da URL fornecida e a salva em um diretório especificado.
     *
     * @param urlImagem URL da imagem a ser baixada.
     * @param nomeArquivo Nome do arquivo onde a imagem será salva.
     * @return Caminho do arquivo salvo.
     * @throws IOException Se ocorrer um erro durante o download ou salvamento da imagem.
     */
    public static String salvarImagem(String urlImagem, String nomeArquivo) throws IOException {
        URL url = new URL(urlImagem);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        // Verifica se a conexão foi bem-sucedida
        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Erro ao conectar com a URL: " + connection.getResponseMessage());
        }

        // Verifica o tipo de conteúdo da resposta HTTP
        String contentType = connection.getContentType();
        String formatoDeImagem = getFormatFromContentType(contentType);

        if (formatoDeImagem == null) {
            throw new IOException("Formato de imagem desconhecido");
        }

        File diretorio = new File(DIRETORIO_IMAGENS);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        File arquivo = new File(diretorio, nomeArquivo + "." + formatoDeImagem);
        try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
             FileOutputStream fileOutputStream = new FileOutputStream(arquivo)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        }

        connection.disconnect();

        // Verifica se o arquivo foi criado com sucesso
        if (!arquivo.exists()) {
            throw new IOException("Falha ao criar o arquivo: " + arquivo.getAbsolutePath());
        }

        return arquivo.getAbsolutePath();
    }

    /**
     * Obtém o formato da imagem a partir do tipo de conteúdo.
     *
     * @param contentType O tipo de conteúdo da imagem.
     * @return O formato da imagem (por exemplo, "jpg", "png").
     */
    private static String getFormatFromContentType(String contentType) {
        if (contentType.contains("jpeg")) {
            return "jpeg";
        } else if (contentType.contains("png")) {
            return "png";
        } else if (contentType.contains("gif")) {
            return "gif";
        } else {
            return null;
        }
    }
}
