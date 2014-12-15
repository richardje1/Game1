
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InputHandler implements KeyListener
{
	public List<Key> keys = new ArrayList<Key>();
	
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
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public void toggleKey(int keyCode, boolean isPressed)
	{
		if (keyCode == KeyEvent.VK_W)
		{
			up.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_W)
		{
			down.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_W)
		{
			left.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_W)
		{
			right.toggle(isPressed);
		}
	}
	public class Key
	{
		private boolean pressed = false;

		public boolean isPressed()
		{
			return pressed;
		}
		
		public void toggle(boolean isPressed)
		{
			pressed = isPressed;
		}
	}
}
