import javax.swing.JFrame;


public class JanelaDoJogo extends JFrame {
   public JanelaDoJogo() {
       this.setTitle("Invasão Alienígena");
       this.setSize(800, 600);  // Tamanho da janela
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.add(new PainelDoJogo());  // Adiciona o painel onde o jogo será desenhado
       this.setVisible(true);
   }
   public static void main(String[] args) {
       new JanelaDoJogo();
   }
}