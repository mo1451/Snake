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
 * ̰����������
 * @author Ĭ1451
 *
 */
public class Yard extends Frame {
	//��������
	public static final int ROWS = 30;
	//��������
	public static final int COLS = 30;
	//�����С
	public static final int BLOCKS = 15;
	//�ж����Ƿ�����
	private boolean flag = true;
	//�����߶���
	Snake s = new Snake(this);
	//����������
	Egg e = new Egg();
	//˫����ͼƬ
	Image offScreenImage = null;

	/**
	 * ������
	 * @param args δʹ��
	 */
	public static void main(String[] args) {
		new Yard().launch();
		
	}
	
	/**
	 * ����������
	 */
	public void launch(){
		//���ô��ڳ�ʼλ��
		this.setLocation(450,100);
		//���ô��ڳ�ʼ��С
		this.setSize(ROWS * BLOCKS, COLS * BLOCKS);
		//��Ӵ��ڼ���
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				System.exit(-1);
			}			
		});
		//�������̼���
		this.addKeyListener(new KeyE());
		//���ÿɼ�
		this.setVisible(true);
		//��������ˢ���߳�
		new Thread(new PrintThread()).start();
	}

	/**
	 * ����ˢ�º���
	 */
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		//������ɫ--��ɫ
		g.setColor(Color.GRAY);
		//��������
		g.fillRect(0, 0, ROWS * BLOCKS, COLS * BLOCKS);
		//������ɫ--�ڻ�ɫ
		g.setColor(Color.DARK_GRAY);
		//ͨ��������������ɫ
		for(int i=1; i<ROWS ; i++){
			g.drawLine(0, BLOCKS * i, ROWS * BLOCKS, BLOCKS * i);
		}
		for(int i=1; i<COLS ; i++){
			g.drawLine(BLOCKS * i, 0, i * BLOCKS, BLOCKS * COLS);
		}
		//�߳Ե�
		s.eatEgg(e);
		//����
		s.draw(g);
		//����
		e.draw(g);
		//������ɫ--��ɫ
		g.setColor(Color.YELLOW);
		//��ʾ����
		g.drawString("Socres:" + (s.getSize() - 1) * 5, 20, 60); 
		//��ʾ��Ϸ����
		if(flag == false) {
			g.setFont(new Font("����",Font.BOLD,50));
			g.drawString("��Ϸ����", 125, 240);
		}		
		g.setColor(c);
	}
	
	/**
	 * ��Ϸ��������
	 */
	public void stop() {
		flag = false;
	}
	
	/**
	 * ˫�������
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
	 * �ػ��߳�
	 * @author Ĭ1451
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
	 * ���̼�����
	 * @author Ĭ1451
	 *
	 */
	private class KeyE extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			s.keyPressed(e);
		}
		
	}

}
