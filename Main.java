import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main extends JComponent {

    public static void main(String[] args) {
        new Main();
    }

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int ITERATIONS = 100;

    private BufferedImage buffer;

    public Main() {

        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        renderMandelbrotSet();


        JFrame frame = new JFrame("Mandelbrot Set", null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        
    }

    public void addNotify() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void renderMandelbrotSet(){

        for(int x = 0;x < WIDTH; x++){
            for(int y = 0; y < HEIGHT; y++){
                int color = calculatePoint((x - WIDTH/2f)/100 , (y - HEIGHT/2f)/100);

                buffer.setRGB(x, y, color);
            }
        }
    }

    public int calculatePoint(float x, float y){

        float cx = x;
        float cy = y;

        int i = 0;

        for(; i < ITERATIONS; i++){

            float nx = x*x - y*y + cx;
            float ny = 2 * x * y + cy;
            x = nx;
            y = ny;

            if(x*x + y*y > 4) break;
        }

        if(i == ITERATIONS) return 0x00000000;
        return 0xFFFFFFFF;
    }

    public void paint(Graphics g) { 
        g.drawImage(buffer, 0, 0, null);
    }


}