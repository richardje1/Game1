package ja.richard.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable
{

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Game";

    private JFrame frame;

    public boolean running = false;
    public int tickCount = 0;
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    
    //6 shades of each primary color [RGB]
    private int[] colours = new int[6*6*6];
    
    //Screen Object field
    public InputHandler input;

    public Game()
    {
        // setting size to be static (non - changing)
        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        // making frame, name changes with the final
        frame = new JFrame(NAME);

        // when someone exits, stop running
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // sets layout
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    
    public void init()
    {
    	int index = 0;
    	for (int r = 0; r < 6; r++){
    		for (int g = 0; g < 6; g++){
    			for (int b = 0; b < 6; b++){
    				int rr = r * 255/5;
    				int gg = g * 255/5;
    				int bb = b * 255/5;
    				
    				colours[index++] = rr << 16 | gg << 8 | bb; 
    			}
    		}
    	}
    	//Screen object declaration Screen(WIDTH, HEIGHT, new SpriteSheet(...));
    	input = new InputHandler(this);
    }
    
    private synchronized void start()
    {
        running = true;
        new Thread(this).start();


    }
    private synchronized void stop()
    {
        running = false;

    }
    @Override
    public void run()
    {
        // accurate time
        long lastTime = System.nanoTime();
        // how many nano seconds for 60 updates
        double naPerTick = 1000000000D / 60D;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / naPerTick;
            lastTime = now;
            boolean shouldRender = true;

            while (delta >= 1)
            {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }
            // prevent overload
            try
            {
                Thread.sleep(2);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (shouldRender)
            {
                frames++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000)
            {
                lastTimer += 1000;
                System.out.println(frames + "," + ticks);
                frames = 0;
                ticks = 0;
            }
        }


    }
    public void tick()
    {
        tickCount++;
        
        if(input.up.isPressed())
        {
        	//screen moves according to pressed key screen.yOffset--
        }
        if(input.down.isPressed())
        {
        	//screen.yOffset++
        }
        if(input.left.isPressed())
        {
        	//screen.xOffset--
        }
        if(input.right.isPressed())
        {
        	//screen.xOffset++
        }
    }
    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.BLACK);
        g.drawRect(0,  0,  getWidth(), getHeight());
        g.dispose();
        bs.show();
    }
    
    public static void main(String[] args)
    {
        new Game().start();

    }


}
