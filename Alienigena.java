import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Alienigena {

    private int x, y;
    private int largura = 100, altura = 100;
    private int velocidade = 2;
    private Image imagem; // Adiciona um campo para armazenar a imagem

    public Alienigena(int inicioX, int inicioY) {
        this.x = inicioX;
        this.y = inicioY;
        this.imagem = new ImageIcon("alien.png").getImage(); // Carrega a imagem
    }

    public void mover() {
        x += velocidade;
        if (x <= 0 || x + largura >= 800) { // Verifica as bordas da tela
            velocidade = -velocidade; // Inverte a direção
            y += altura; // Desce um pouco
        }
    }