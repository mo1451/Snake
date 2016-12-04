import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
/**
 * Class����
 * @author Ĭ1451
 *
 */
public class Egg {
	//�����ڵ�����������
	private int row,col;
	//���Ŀ��
	private int w = Yard.BLOCKS;
	//���ĸ߶�
	private int h = Yard.BLOCKS;
	//�������λ��
	private static Random ran = new Random();
	//������ɫ
	private Color ch = Color.GREEN;

	//ͨ��ָ��λ�ù���һ����
	Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}
	//�������һ����
	Egg() {
		this(ran.nextInt(Yard.ROWS - 4) + 3,ran.nextInt(Yard.COLS - 2) + 1);
	}
	//ˢ�µ�
	public void reflesh() {
		this.setCol(ran.nextInt(Yard.COLS - 4) + 3);
		this.setRow(ran.nextInt(Yard.ROWS - 2) + 1);
	}
	//����
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(ch);
		g.fillOval(Yard.BLOCKS * col, Yard.BLOCKS * row, w, h);
		g.setColor(c);
		//ÿ����һ�ξͱ任һ�ε�����ɫ
		if(ch == Color.GREEN) {
			ch = Color.RED;
		}
		else {
			ch = Color.GREEN;
		}
	}
	/**
	 * ��ȡ����ռ���
	 * @return ����ռ���
	 */
	public Rectangle getRec() {
		return new Rectangle(Yard.BLOCKS * col, Yard.BLOCKS * row, w, h);
	}

	/**
	 * ��ȡ�����ڵ�����
	 * @return �����ڵ�����
	 */
	public int getRow() {
		return row;
	}

	/**
	 * ���õ����ڵ�����
	 * @param row �����ڵ�����
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * ��ȡ�����ڵ�����
	 * @return �����ڵ�����
	 */
	public int getCol() {
		return col;
	}

	/**
	 * ���õ����ڵ�����
	 * @param col �����ڵ�����
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	

}
