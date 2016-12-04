import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
/**
 * Class：蛋
 * @author 默1451
 *
 */
public class Egg {
	//蛋所在的行数、列数
	private int row,col;
	//蛋的宽度
	private int w = Yard.BLOCKS;
	//蛋的高度
	private int h = Yard.BLOCKS;
	//随机蛋的位置
	private static Random ran = new Random();
	//蛋的颜色
	private Color ch = Color.GREEN;

	//通过指定位置构造一个蛋
	Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}
	//随机构造一个蛋
	Egg() {
		this(ran.nextInt(Yard.ROWS - 4) + 3,ran.nextInt(Yard.COLS - 2) + 1);
	}
	//刷新蛋
	public void reflesh() {
		this.setCol(ran.nextInt(Yard.COLS - 4) + 3);
		this.setRow(ran.nextInt(Yard.ROWS - 2) + 1);
	}
	//画蛋
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(ch);
		g.fillOval(Yard.BLOCKS * col, Yard.BLOCKS * row, w, h);
		g.setColor(c);
		//每被吃一次就变换一次蛋的颜色
		if(ch == Color.GREEN) {
			ch = Color.RED;
		}
		else {
			ch = Color.GREEN;
		}
	}
	/**
	 * 获取蛋所占面积
	 * @return 但所占面积
	 */
	public Rectangle getRec() {
		return new Rectangle(Yard.BLOCKS * col, Yard.BLOCKS * row, w, h);
	}

	/**
	 * 获取蛋所在的行数
	 * @return 蛋所在的行数
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 设置蛋所在的行数
	 * @param row 蛋所在的行数
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * 获取蛋所在的列数
	 * @return 蛋所在的列数
	 */
	public int getCol() {
		return col;
	}

	/**
	 * 设置蛋所在的列数
	 * @param col 蛋所在的列数
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	

}
