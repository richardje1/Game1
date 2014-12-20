package ja.richard.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener 
{
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();

	public InputHandler(Game game)
	{
		game.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent e) 
	{
		toggleKey(e.getKeyCode(), true);
	}
	
	public void keyReleased(KeyEvent e) 
	{
		toggleKey(e.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent arg0) 
	{

	}
	
	public void toggleKey(int keyCode, boolean isPressed)
	{	
		if (keyCode == KeyEvent.VK_W)
		{
			up.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_S)
		{
			down.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_A)
		{
			left.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_D)
		{
			right.toggle(isPressed);
		}
	}

	public class Key
	{
		private boolean pressed = false;
		private int numTimesPressed = 0;
		
		public int getNumTimesPressed()
		{
			return numTimesPressed;
		}
		public void toggle (boolean isPressed)
		{
			pressed = isPressed;
		}
		public boolean isPressed()
		{
			return pressed;
		}
	}
}
