import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Jogador {
    private int x, y;
    private int largura = 100, altura = 100;  // Tamanho da nave
    private int velocidade = 5;
    private Image imagem; // Adiciona um campo para armazenar a imagem

    public Jogador(int inicioX, int inicioY) {
        this.x = inicioX;
        this.y = inicioY;
        this.imagem = new ImageIcon("nave.png").getImage(); // Carrega a imagem
    }

    public void moverEsquerda() {
        x -= velocidade;
        if (x < 0) x = 0; // Impede que a nave saia da tela
    }

    public void moverDireita(int larguraDaTela) {
        x += velocidade;
        if (x + largura > larguraDaTela) x = larguraDaTela - largura;
    }

    public void desenhar(Graphics g) {
        g.drawImage(imagem, x, y, largura, altura, null); // Desenha a imagem em vez do retângulo
    }

    // Método para detecção de colisão
    public Rectangle getLimites() {
        return new Rectangle(x, y, largura, altura);
    }

    // Métodos para obter a posição da nave
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
