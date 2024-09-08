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

        ImageInputStream iis = ImageIO.createImageInputStream(arquivo);

        Iterator<ImageReader> iteradores = ImageIO.getImageReaders(iis);

        if (!iteradores.hasNext()) {
            throw new IOException("problema com o formato da imagem");
        }

        ImageReader leitor = iteradores.next();

        // Obtém o formato do arquivo de imagem
        String formatoDeImagem = leitor.getFormatName();

        iis.close();

        // caminho original do arquivo
        BufferedImage imagem = ImageIO.read(arquivo);

        // Cria o diretório, se necessário
        File diretorioSaida = new File("src/main/resources/org/tudogostoso/ImagensReceitas/");
        if (!diretorioSaida.exists()) {
            diretorioSaida.mkdirs();
        }

        File arquivoSaida = new File("src/main/resources/org/tudogostoso/ImagensReceitas/" + nomeArquivo + "." + formatoDeImagem);

        // Salva a imagem no formato desejado
        ImageIO.write(imagem, formatoDeImagem, arquivoSaida);

        return "src/main/resources/org/tudogostoso/ImagensReceitas/" + nomeArquivo + "." + formatoDeImagem;
    }
    public void excluir(String caminhoImagem) throws IOException {
        File arquivo = new File(caminhoImagem);
        arquivo.delete();
    }
}
