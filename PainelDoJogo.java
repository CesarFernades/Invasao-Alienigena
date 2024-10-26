import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PainelDoJogo extends JPanel implements Runnable, KeyListener {

    private Jogador jogador;
    private List<Projetil> projeteis = new ArrayList<>();
    private List<Alienigena> alienigenas = new ArrayList<>();
    private boolean rodando = false;
    private Thread threadDoJogo;
    private Image fundo; // Campo para armazenar a imagem de fundo

    public PainelDoJogo() {
        jogador = new Jogador(375, 500);

        // Inicializa alienígenas
        for (int i = 0; i < 5; i++) {
            alienigenas.add(new Alienigena(i * 60, 30)); // Adiciona alienígenas na tela
        }

        fundo = new ImageIcon("fundo.jpg").getImage(); // Carrega a imagem de fundo

        addKeyListener(this);
        setFocusable(true);
        iniciar();
    }

    public synchronized void iniciar() {
        rodando = true;
        threadDoJogo = new Thread(this);
        threadDoJogo.start();
    }

    @Override
    public void run() {
        while (rodando) {
            atualizar();
            repaint(); // Redesenha o jogo
            try {
                Thread.sleep(16); // Aproximadamente 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void atualizar() {
        // Movimentação dos projéteis
        Iterator<Projetil> iteradorProjeteis = projeteis.iterator();
        while (iteradorProjeteis.hasNext()) {
            Projetil proj = iteradorProjeteis.next();
            proj.mover(); 
            if (proj.foraDaTela()) {
                iteradorProjeteis.remove(); // Remove projéteis que saíram da tela
            }
        }

        // Movimentação dos alienígenas
        for (Alienigena alien : alienigenas) {
            alien.mover();
        }

        // Verificar colisões
        Iterator<Alienigena> iteradorAlienigenas = alienigenas.iterator();
        while (iteradorAlienigenas.hasNext()) {
            Alienigena alien = iteradorAlienigenas.next();
            Iterator<Projetil> iteradorProjeteisInterno = projeteis.iterator(); // Iterador interno para projéteis
            while (iteradorProjeteisInterno.hasNext()) {
                Projetil proj = iteradorProjeteisInterno.next();
                if (alien.getLimites().intersects(proj.getLimites())) {
                    // Colisão detectada
                    iteradorAlienigenas.remove(); // Remove o alienígena
                    iteradorProjeteisInterno.remove(); // Remove o projétil
                    break; // Sai do loop interno para evitar erros de concorrência
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fundo, 0, 0, getWidth(), getHeight(), null); // Desenha a imagem de fundo
        jogador.desenhar(g); // Desenha o jogador
        for (Projetil proj : projeteis) {
            proj.desenhar(g); // Desenha os projéteis
        }
        for (Alienigena alien : alienigenas) {
            alien.desenhar(g); // Desenha os alienígenas
        }
    }

    // Eventos de teclas
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            jogador.moverEsquerda(); // Mover a nave para a esquerda
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            jogador.moverDireita(getWidth()); // Mover a nave para a direita
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Adiciona um novo projétil ao centro da nave
            projeteis.add(new Projetil(jogador.getX() + 22, jogador.getY()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Pode ser utilizado para liberar teclas se necessário
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não utilizado, mas necessário pela interface KeyListener
    }
}
