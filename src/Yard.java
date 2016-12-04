import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 贪吃蛇主函数
 * @author 默1451
 *
 */
public class Yard extends Frame {
	//方格行数
	public static final int ROWS = 30;
	//方格列数
	public static final int COLS = 30;
	//方格大小
	public static final int BLOCKS = 15;
	//判断蛇是否死亡
	private boolean flag = true;
	//建立蛇对象
	Snake s = new Snake(this);
	//建立蛋对象
	Egg e = new Egg();
	//双缓冲图片
	Image offScreenImage = null;

	/**
	 * 主函数
	 * @param args 未使用
	 */
	public static void main(String[] args) {
		new Yard().launch();
		
	}
	
	/**
	 * 建立主窗口
	 */
	public void launch(){
		//设置窗口初始位置
		this.setLocation(450,100);
		//设置窗口初始大小
		this.setSize(ROWS * BLOCKS, COLS * BLOCKS);
		//添加窗口监听
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				System.exit(-1);
			}			
		});
		//创建键盘监听
		this.addKeyListener(new KeyE());
		//设置可见
		this.setVisible(true);
		//启动窗口刷新线程
		new Thread(new PrintThread()).start();
	}

	/**
	 * 窗口刷新函数
	 */
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		//设置颜色--灰色
		g.setColor(Color.GRAY);
		//画出背景
		g.fillRect(0, 0, ROWS * BLOCKS, COLS * BLOCKS);
		//设置颜色--黑灰色
		g.setColor(Color.DARK_GRAY);
		//通过画线条画出颜色
		for(int i=1; i<ROWS ; i++){
			g.drawLine(0, BLOCKS * i, ROWS * BLOCKS, BLOCKS * i);
		}
		for(int i=1; i<COLS ; i++){
			g.drawLine(BLOCKS * i, 0, i * BLOCKS, BLOCKS * COLS);
		}
		//蛇吃蛋
		s.eatEgg(e);
		//画蛇
		s.draw(g);
		//画蛋
		e.draw(g);
		//设置颜色--黄色
		g.setColor(Color.YELLOW);
		//显示分数
		g.drawString("Socres:" + (s.getSize() - 1) * 5, 20, 60); 
		//显示游戏结束
		if(flag == false) {
			g.setFont(new Font("宋体",Font.BOLD,50));
			g.drawString("游戏结束", 125, 240);
		}		
		g.setColor(c);
	}
	
	/**
	 * 游戏结束函数
	 */
	public void stop() {
		flag = false;
	}
	
	/**
	 * 双缓冲机制
	 */
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null){
			offScreenImage = this.createImage(ROWS * BLOCKS, COLS * BLOCKS);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	/**
	 * 重画线程
	 * @author 默1451
	 *
	 */
	private class PrintThread implements Runnable{

		@Override
		public void run() {
			while(flag){
				repaint();
				try{
					Thread.sleep(150);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			repaint();
		}
		
	}
	/**
	 * 键盘监听类
	 * @author 默1451
	 *
	 */
	private class KeyE extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			s.keyPressed(e);
		}
		
	}

}
