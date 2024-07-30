package study.project;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class project4 extends JFrame {

  static class Card {
    int month;
    String type;

    Card(int month, String type) {
      this.month = month;
      this.type = type;
    }

    @Override
    public String toString() {
      return month + "월 - " + type;
    }
  }

  public project4() {
    setTitle("Hanafuda Cards");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    add(new HanafudaPanel());
  }

  class HanafudaPanel extends JPanel {
    Card[] deck;

    HanafudaPanel() {
      deck = new Card[48];
      String[] types = {"광", "끗", "피"};

      int index = 0;
      for (int month = 1; month <= 12; month++) {
        for (int i = 0; i < 4; i++) {
          if ((month == 1 || month == 8) && i == 1) {
            deck[index++] = new Card(month, "끗");
          } else if (i == 0) {
            deck[index++] = new Card(month, types[i]);
          } else {
            deck[index++] = new Card(month, types[2]);
          }
        }
      }
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      int cardWidth = 60;
      int cardHeight = 90;
      int padding = 10;
      int x = padding;
      int y = padding;

      for (Card card : deck) {
        g.drawRect(x, y, cardWidth, cardHeight);
        g.drawString(card.toString(), x + 5, y + 20);
        x += cardWidth + padding;
        if (x + cardWidth + padding > getWidth()) {
          x = padding;
          y += cardHeight + padding;
        }
      }
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      project4 frame = new project4();
      frame.setVisible(true);
    });
  }
}
