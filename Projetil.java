import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Projetil {
    private int x, y;
    private int largura = 40, altura = 60;
    private int velocidade = 5;
    private Image imagem; // Adiciona um campo para armazenar a imagem

    public Projetil(int inicioX, int inicioY) {
        this.x = inicioX;
        this.y = inicioY;
        this.imagem = new ImageIcon("missel.png").getImage(); // Carrega a imagem
    }

    public void mover() {
        y -= velocidade; // O projétil se move para cima
    }

    public void desenhar(Graphics g) {
        g.drawImage(imagem, x, y, largura, altura, null); // Desenha a imagem em vez do retângulo
    }

    public boolean foraDaTela() {
        return y + altura < 0; // Verifica se o projétil saiu da tela
    }

    public Rectangle getLimites() {
        return new Rectangle(x, y, largura, altura);
    }
}
