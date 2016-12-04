import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
/**
 * Class：蛇
 * @author 默1451
 *
 */
public class Snake {
	//蛇头
	private Node head;
	//蛇尾
	private Node tail;
	//蛇的大小
	private int size;
	//第一节蛇
	private Node n = new Node(20,20,Dir.L);
	//主类
	private Yard y;
	/**
	 * 创造一个新蛇
	 * @param y 主类
	 */
	Snake(Yard y){
		head = n;
		tail = n;
		size = 1;
		this.y = y;
	}
	
	/**
	 * 添加一个尾巴
	 */
	public void addToTail(){
		Node node = null;
		//判断蛇尾方向
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
	 * 添加一个蛇头
	 */
	public void addToHead(){
		Node node = null;
		//判断舌头
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
	 * 画蛇
	 * @param g 窗口画笔
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
	 * 蛇移动
	 * 检查蛇是否死亡
	 * 检查蛇是否吃蛋
	 */
	private void move() {
		checkDead();
		addToHead();
		deleteFromTail();
		
	}

	/**
	 * 检查蛇是否死亡
	 */
	private void checkDead() {
		//出界
		if(head.x < 2 || head.y < 0 || head.x > Yard.ROWS - 1 || head.y > Yard.COLS - 1) {
			y.stop();
		}
		
		//碰到自己
		for(Node n = head.next; n != null ; n = n.next) {
			if(head.x == n.x && head.y == n.y) {
				y.stop();
			}
		}	
	}

	/**
	 * 蛇移动
	 */
	private void deleteFromTail() {
		tail = tail.prev;
		tail.next = null;
		
	}

	/**
	 * Class：蛇关节
	 * @author 默1451
	 *
	 */
	private class Node{
		//宽度
		int w = Yard.BLOCKS;
		//高度
		int h = Yard.BLOCKS;
		//x坐标
		int x;
		//y坐标
		int y;
		//移动方向
		Dir dir = Dir.L;
		//下一节
		Node next = null;
		//上一节
		Node prev = null;
		//构造一个蛇节关节
		Node(int x,int y,Dir dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		/**
		 * 画出蛇关节
		 * @param g 窗口画笔
		 */
		void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(Yard.BLOCKS * y, Yard.BLOCKS * x, w, h);
			g.setColor(c);
		}
	}
	/**
	 * 蛇吃蛋函数
	 * @param e Class：蛋
	 */
	public void eatEgg(Egg e) {
		if(this.getRec().intersects(e.getRec())) {
			e.reflesh();
			this.addToHead();
			size ++;
		}
	}
	/**
	 * 得到蛇所占位置
	 * @return 蛇所占位置
	 */
	public Rectangle getRec() {
		return new Rectangle(Yard.BLOCKS * head.y, Yard.BLOCKS * head.x, head.w, head.h);
	}

	/**
	 * 键盘监听，判断蛇移动方向
	 * @param e 键盘事件e
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
	 * 得到蛇的长度
	 * @return 蛇的长度
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 设置蛇的长度
	 * @param size 蛇的长度
	 */
	public void setSize(int size) {
		this.size = size;
	}

}
