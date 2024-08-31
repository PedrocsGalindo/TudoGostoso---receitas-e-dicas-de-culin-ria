package org.tudogostoso.repositorios;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class RepositorioImagens {

    public String salvar(File arquivo, String nomeArquivo) throws IOException{

        // Cria um ImageInputStream a partir do arquivo de imagem
        ImageInputStream iis = ImageIO.createImageInputStream(arquivo);

        // Obtém o iterador de ImageReaders que podem ler o arquivo
        Iterator<ImageReader> iteradores = ImageIO.getImageReaders(iis);

        if (!iteradores.hasNext()) {
            throw new IOException("problema com o formato da imagem");
        }

        // Obtém o primeiro ImageReader disponível
        ImageReader leitor = iteradores.next();

        // Obtém o formato do arquivo de imagem
        String formatoDeImagem = leitor.getFormatName();

        // Fecha o ImageInputStream
        iis.close();

        // caminho original do arquivo
        BufferedImage imagem = ImageIO.read(arquivo);
        // Earquivo de destino
        File arquivoSaida = new File("src/main/resources/org/tudogostoso/ImagensReceitas/" + nomeArquivo + "." + formatoDeImagem);

        // Salva a imagem no formato desejado
        ImageIO.write(imagem, formatoDeImagem, arquivoSaida);

        return "src/main/resources/org/tudogostoso/ImagensReceitas/" + nomeArquivo + "." + formatoDeImagem;
    }
}
