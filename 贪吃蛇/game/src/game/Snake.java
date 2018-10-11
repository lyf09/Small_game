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
		//ʳ��
		private Point  point = new Point();
		//��
		private LinkedList<Point> list= new LinkedList<Point>(); 
		 //������̵ĳ�ʼֵ
		private  int key=37;
		//Ϊʳ����ߵ������ʼ��
		public void init(){
			//ʳ������
			point.setLocation(100, 100);
			//�ߵ����� 
			list.add(new Point(300,300));
			list.add(new Point(310,300));
			list.add(new Point(320,300));
			list.add(new Point(330,300));
			list.add(new Point(340,300));
			list.add(new Point(350,300));
			list.add(new Point(360,300));	
			//�����߳�
			new Thread(new MoveThread()).start();
		}
		//����JFrame����paint����
		@Override 
	public void paint(Graphics g) {
			Image img = createImage(500, 500);//����ͼƬ����
			Graphics g2 = img.getGraphics();//�������ʶ���
			g2.setColor(Color.WHITE); //���������ɫ��
			g2.fillRect(0, 0, 500, 500);//����������ʼ���Լ���С
			g2.translate(50, 50);//�ƶ�����λ��
			g2.setColor(Color.RED);//�ڻ����ߵ���ɫ��
			g2.drawRect(0, 0, 400, 400);//��һ�����Ρ���ʼ���Լ���С
			g2.setColor(Color.GREEN);//��һ������
		//�ߵĴ�С�����Ŷ��ٸ�������	
			/*	�������ߵ�����λ��(���������û��ʹ���)
			 * for (int i = 0; i < list.size(); i++) {
				g2.fillRect(list.get(i).x, list.get(i).y, 10, 10);
				}*/
			for(Point p:list){
				g2.fillRect(p.x, p.y, 10, 10);
			}	
		//��ʳ��
			g2.setColor(Color.RED);
			g2.fillRect(point.x, point.y, 10, 10);//λ�á���С
			g2.drawImage(img, 0, 0, 500, 500, this);//����ͼƬ�����
			
		}
		//Ϊʳ����ߵ�������г�ʼ�� 
		private void into() {
			point.setLocation(100,100);		
		}
		//��������
	public Snake() {
		this.setTitle("̰����");	//����
		this.setResizable(false);//���ܸı�
		this.setSize(500,  500);	//��С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�������Ͻ���С������󻯡��ر�
		this.setLocationRelativeTo(null);	//����
		this.setVisible(true);	//�ɼ�
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				/*���̵���������
				���׶�˼·������37��38��39��40.�ĸ�����
				Ŀ�ģ��ϵ�ʱ�����£����ʱ������
				������������õ�ǰ����������벻����ִ�еķ�������
				�Ĳ�������ֵ������2������key�ſ��Ա��ı�*/
				if (e.getKeyCode()>=37 && e.getKeyCode()<=40) {
					if (Math.abs(key-e.getKeyChar())!=2) {
						key=e.getKeyCode();
					}
					
				}
			}
		});
		init();
	}
	
	//�ڲ���ʵ���߳�
	class MoveThread implements Runnable{
		@Override
		public void run() {
		while(true){
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			
			//��ȡ�ߵĵ�һ����,ǰ��x�ͼ�һ��ֵ10����
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
			
			//�ж��ߵ�λ�ã�����������
			if(p.x<0||p.x>390||p.y<0||p.y>390||list.contains(p)){
				JOptionPane.showMessageDialog(null, "��Ϸ����");
				break;
			}
			list.addFirst(p);
			//���߳Ե�ʳ������������µ�ʳ�ﲢ���߱䳤
			if(p.equals(point)){
				//�����x��y�Ļ��Χ
				int x=(int)(Math.random()*40)*10;
				int y=(int)(Math.random()*40)*10;
				point.setLocation(x, y); 
			}else{
				//ǰ��ʱɾ�����һ����
				list.removeLast();
			}
			//���ķ���
			Snake.this.repaint();
		}	
		}
	}
	
	public static void main(String[] args) {
		new Snake();
	}
}
