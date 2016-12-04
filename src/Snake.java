import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
/**
 * Class����
 * @author Ĭ1451
 *
 */
public class Snake {
	//��ͷ
	private Node head;
	//��β
	private Node tail;
	//�ߵĴ�С
	private int size;
	//��һ����
	private Node n = new Node(20,20,Dir.L);
	//����
	private Yard y;
	/**
	 * ����һ������
	 * @param y ����
	 */
	Snake(Yard y){
		head = n;
		tail = n;
		size = 1;
		this.y = y;
	}
	
	/**
	 * ���һ��β��
	 */
	public void addToTail(){
		Node node = null;
		//�ж���β����
		switch(tail.dir){
		case L :
			node = new Node(tail.x,tail.y + 1,tail.dir);
			break;
		case R :
			node = new Node(tail.x,tail.y - 1,tail.dir);
			break;
		case U :
			node = new Node(tail.x + 1,tail.y,tail.dir);
			break;
		case D :
			node = new Node(tail.x - 1,tail.y,tail.dir);
			break;
		}
		tail.next = node;
		node.prev = tail;
		tail = node;	
	}
	/**
	 * ���һ����ͷ
	 */
	public void addToHead(){
		Node node = null;
		//�ж���ͷ
		switch(head.dir){
		case L :
			node = new Node(head.x,head.y - 1,head.dir);
			break;
		case R :
			node = new Node(head.x,head.y + 1,head.dir);
			break;
		case U :
			node = new Node(head.x - 1,head.y,head.dir);
			break;
		case D :
			node = new Node(head.x + 1,head.y,head.dir);
			break;
		}
		node.next = head;
		head.prev = node;
		head = node;
	}
	/**
	 * ����
	 * @param g ���ڻ���
	 */
	public void draw(Graphics g)
	{
		if(size < 0) return;
		move();
		for(Node node = head;node.next != null;node = node.next){
			node.draw(g);
		}
		tail.draw(g);	
	}
	
	/**
	 * ���ƶ�
	 * ������Ƿ�����
	 * ������Ƿ�Ե�
	 */
	private void move() {
		checkDead();
		addToHead();
		deleteFromTail();
		
	}

	/**
	 * ������Ƿ�����
	 */
	private void checkDead() {
		//����
		if(head.x < 2 || head.y < 0 || head.x > Yard.ROWS - 1 || head.y > Yard.COLS - 1) {
			y.stop();
		}
		
		//�����Լ�
		for(Node n = head.next; n != null ; n = n.next) {
			if(head.x == n.x && head.y == n.y) {
				y.stop();
			}
		}	
	}

	/**
	 * ���ƶ�
	 */
	private void deleteFromTail() {
		tail = tail.prev;
		tail.next = null;
		
	}

	/**
	 * Class���߹ؽ�
	 * @author Ĭ1451
	 *
	 */
	private class Node{
		//���
		int w = Yard.BLOCKS;
		//�߶�
		int h = Yard.BLOCKS;
		//x����
		int x;
		//y����
		int y;
		//�ƶ�����
		Dir dir = Dir.L;
		//��һ��
		Node next = null;
		//��һ��
		Node prev = null;
		//����һ���߽ڹؽ�
		Node(int x,int y,Dir dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		/**
		 * �����߹ؽ�
		 * @param g ���ڻ���
		 */
		void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(Yard.BLOCKS * y, Yard.BLOCKS * x, w, h);
			g.setColor(c);
		}
	}
	/**
	 * �߳Ե�����
	 * @param e Class����
	 */
	public void eatEgg(Egg e) {
		if(this.getRec().intersects(e.getRec())) {
			e.reflesh();
			this.addToHead();
			size ++;
		}
	}
	/**
	 * �õ�����ռλ��
	 * @return ����ռλ��
	 */
	public Rectangle getRec() {
		return new Rectangle(Yard.BLOCKS * head.y, Yard.BLOCKS * head.x, head.w, head.h);
	}

	/**
	 * ���̼������ж����ƶ�����
	 * @param e �����¼�e
	 */ 
	public void keyPressed(KeyEvent e) {
		int keyValue = e.getKeyCode();
		switch(keyValue){
		case KeyEvent.VK_LEFT :
			if(head.dir != Dir.R) {
				head.dir = Dir.L;
			}			
			break;
		case KeyEvent.VK_RIGHT :
			if(head.dir != Dir.L) {
				head.dir = Dir.R;
			}
			
			break;
		case KeyEvent.VK_UP :
			if(head.dir != Dir.D) {
				head.dir = Dir.U;
			}
			
			break;
		case KeyEvent.VK_DOWN :
			if(head.dir != Dir.U) {
				head.dir = Dir.D;
			}
			
			break;
		}		
	}

	/**
	 * �õ��ߵĳ���
	 * @return �ߵĳ���
	 */
	public int getSize() {
		return size;
	}

	/**
	 * �����ߵĳ���
	 * @param size �ߵĳ���
	 */
	public void setSize(int size) {
		this.size = size;
	}

}
