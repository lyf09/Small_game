	package game;
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;	
	public class Snake extends JFrame{
		//食物
		private Point  point = new Point();
		//蛇
		private LinkedList<Point> list= new LinkedList<Point>(); 
		 //定义键盘的初始值
		private  int key=37;
		//为食物和蛇的坐标初始化
		public void init(){
			//食物坐标
			point.setLocation(100, 100);
			//蛇的坐标 
			list.add(new Point(300,300));
			list.add(new Point(310,300));
			list.add(new Point(320,300));
			list.add(new Point(330,300));
			list.add(new Point(340,300));
			list.add(new Point(350,300));
			list.add(new Point(360,300));	
			//开启线程
			new Thread(new MoveThread()).start();
		}
		//查找JFrame里面paint方法
		@Override 
	public void paint(Graphics g) {
			Image img = createImage(500, 500);//创建图片对象
			Graphics g2 = img.getGraphics();//创建画笔对象
			g2.setColor(Color.WHITE); //设置面板颜色白
			g2.fillRect(0, 0, 500, 500);//设置面板的起始点以及大小
			g2.translate(50, 50);//移动画笔位置
			g2.setColor(Color.RED);//在画出线的颜色红
			g2.drawRect(0, 0, 400, 400);//画一个矩形、起始点以及大小
			g2.setColor(Color.GREEN);//画一条绿蛇
		//蛇的大小代表着多少个正方形	
			/*	遍历出蛇的坐标位置(遍历集合用画笔工具)
			 * for (int i = 0; i < list.size(); i++) {
				g2.fillRect(list.get(i).x, list.get(i).y, 10, 10);
				}*/
			for(Point p:list){
				g2.fillRect(p.x, p.y, 10, 10);
			}	
		//画食物
			g2.setColor(Color.RED);
			g2.fillRect(point.x, point.y, 10, 10);//位置、大小
			g2.drawImage(img, 0, 0, 500, 500, this);//画在图片面板上
			
		}
		//为食物和蛇的坐标进行初始化 
		private void into() {
			point.setLocation(100,100);		
		}
		//创建界面
	public Snake() {
		this.setTitle("贪吃蛇");	//名称
		this.setResizable(false);//不能改变
		this.setSize(500,  500);	//大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//界面右上角最小化、最大化、关闭
		this.setLocationRelativeTo(null);	//居中
		this.setVisible(true);	//可见
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				/*键盘的上下左右
				本阶段思路，存在37、38、39、40.四个数字
				目的：上的时候不能下，左的时候不能右
				解决方案：利用当前方向的数字与不可以执行的方向数字
				的差做绝对值，不被2整除，key才可以被改变*/
				if (e.getKeyCode()>=37 && e.getKeyCode()<=40) {
					if (Math.abs(key-e.getKeyChar())!=2) {
						key=e.getKeyCode();
					}
					
				}
			}
		});
		init();
	}
	
	//内部类实现线程
	class MoveThread implements Runnable{
		@Override
		public void run() {
		while(true){
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			
			//获取蛇的第一个点,前行x就减一个值10像素
			Point p=list.getFirst().getLocation();
			switch (37) {
			case 37:
			p.x=p.x-10;
				break;
			case 38:
				p.y=p.y-10;
					break;
			case 39:
				p.x=p.x+10;
					break;
			case 40:
				p.y=p.y+10;
					break;
				}
			
			//判断蛇的位置，不让他出框
			if(p.x<0||p.x>390||p.y<0||p.y>390||list.contains(p)){
				JOptionPane.showMessageDialog(null, "游戏结束");
				break;
			}
			list.addFirst(p);
			//当蛇吃掉食物后，马上生成新的食物并且蛇变长
			if(p.equals(point)){
				//定义点x、y的活动范围
				int x=(int)(Math.random()*40)*10;
				int y=(int)(Math.random()*40)*10;
				point.setLocation(x, y); 
			}else{
				//前行时删除最后一个点
				list.removeLast();
			}
			//画的方法
			Snake.this.repaint();
		}	
		}
	}
	
	public static void main(String[] args) {
		new Snake();
	}
}
