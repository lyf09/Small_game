package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Snack1 {

	public static void main(String[] args) {
		       JFrame jf = new JFrame();
		       Snack1 sg = new Snack1();
		       sg.init(jf, 800, 600);
		       
		       final SnakePanel sp = new SnakePanel();
		       //���ó�ʼ����ͼ�ķ���
		       sp.initMap();
		       //���ó�ʼ���ߵķ���
		       sp.initSnake();
		       //��������ʳ��ķ���
		       sp.createFood();
		       
		       sp.move();
		       
		       jf.add(sp);
		       
		       jf.addKeyListener(new KeyAdapter() {
		           @Override
		        public void keyReleased(KeyEvent e) {
		            int keyCode = e.getKeyCode();
		            char ch = e.getKeyChar();            
		            System.out.println("keyCode="+keyCode+",keyChar="+ch);
		            //�õ���ͷ������
		            Point snakeHead = sp.snake.getFirst();
		            switch (keyCode) {
		            case KeyEvent.VK_LEFT:    
		                sp.setDirection(SnakePanel.LEFT);
		                break;
		            case KeyEvent.VK_RIGHT:
		                sp.setDirection(SnakePanel.RIGHT);
		            case 102: //С���̵�����6
		            case 54:  //����̵�����6
		                
		                break;
		            case KeyEvent.VK_UP:
		                sp.setDirection(SnakePanel.UP);
		                break;
		            case KeyEvent.VK_DOWN:
		                sp.setDirection(SnakePanel.DOWN);
		                break;
		            default:
		                break;
		            }
		              //�ٶ���Ӧ�Ŀ��Ϊ40��,��Ӧ���Ǻ��������x
		            final int X_WIDTH = 40;
		          //�ٶ���Ӧ�ĸ߶�Ϊ30��,��Ӧ�������������y
		            final int Y_HEIGHT = 30;
		            //ײǽ�ˣ���Ϸ����-----����� X_WIDTH-1���� == 0�����߻����ǽ�ڣ�������
		            if(snakeHead.x == X_WIDTH-2|| snakeHead.x == 1|| snakeHead.y == Y_HEIGHT-2||snakeHead.y == 1)
		            {
		                String message = "��������!";
		                JOptionPane.showMessageDialog(sp, message);
		                System.exit(0);
		            }
		            else{
		                sp.move();
		            }
		            
		        }
		    });
	 }
		    
    public void init(JFrame frame,int formWidth,int formHeight){
		               //���õ�ǰ����ɼ�,Ĭ�ϲ��ɼ�
		                frame.setVisible(true);
		                //���õ�ǰ����Ŀ�͸�
		                frame.setSize(formWidth+14, formHeight+35);
		                frame.setTitle("�ҵ�̰ʳ��....");
		                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		                //ͨ��Dimension��Ķ���dim���Ի�ȡ����Ļ�Ŀ�͸�
		                int screenWidth = dim.width;
		                int screenHeight = dim.height;
		                System.out.println("��ǰ��Ļ�ķֱ���Ϊ:"+screenWidth+"*"+screenHeight);
		            
		                
		                int x = (screenWidth-formWidth)/2;
		                int y = (screenHeight-formHeight)/2;;
		                //���õ�ǰ��������ڴ���������λ��,��x�������ֵ��y�������ֵ
		                frame.setLocation(x, y);
		                
		                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    }

}

	class SnakePanel extends JPanel{
		    private static final long serialVersionUID = 1L;
		/*    int x_width = 40;
		    int y_height = 30;*/
		    
		    //�ٶ���Ӧ�Ŀ��Ϊ40��,��Ӧ���Ǻ��������x
		    public static final int X_WIDTH = 40;
		  //�ٶ���Ӧ�ĸ߶�Ϊ30��,��Ӧ�������������y
		    public static final int Y_HEIGHT = 30;
		    /*
		     *
		     * ����һ��30*40��ά���� ,��ʾ��30��40��
		     * �����ж�Ӧ����y�������ֵ
		     * �ж�Ӧ����x�������ֵ
		     */
		    boolean[][]map = new boolean[Y_HEIGHT][X_WIDTH];
		    /*
		     * ��ʼ����ͼ�ķ���:
		     * ��һ�С����һ�С���һ�С����һ�б�ʾ��Ϸ�е�ǽ��
		     * [1,28]*[1,38]��ʾ�����߻�ĵ�ͼ
		     * �����ǽ����ֵΪtrue����������Ϊfalse
		     * 
		     */
		    void initMap(){
		        for (int i = 0; i < map.length; i++) {
		            for (int j = 0; j < map[i].length; j++) {
		                if(i==0 || i==map.length-1 || j==0 || j == map[i].length-1)
		                    map[i][j] = true;
		                //else
		                //    map[i][j] = false;
		            }
		        }
		    }
		    
		    LinkedList<Point> snake = new LinkedList<Point>();
		    /*
		     * �������Լ������ߵ�����ֵ
		     * �ٶ���
		     * �ߵ���������Ϊ3,��ͷһ��,����2��
		     * ��ͷˮƽ��ֱ������ʾ��������������40/2-1,����30/2-1
		     * 
		     * ��ʼ���ߵ�����ֵ�ķ�������������Ϣ������LinkedList������
		     */
		    void initSnake(){
		        int x = X_WIDTH/2-1; //19  //ˮƽ���еĺ�������x
		        int y = Y_HEIGHT/2-1; //14 //��ֱ���е���������y
		        for (int i = 0; i < 3; i++) {
		            snake.add(new Point(x-i,y));
		        }        
		    }
		    
		    
		    Point food;  //ʳ�������
		    /**
		     * �������ʳ�������
		     * ����:
		     * 1.ʳ�ﲻ����ǽ����
		     * ������ɵ�ʳ�������ȡֵ��Χ [1,38] * [1,28]
		     * Ҳ����x����ֵȡֵΪ [1,38]֮��
		     * y����ֵȡֵΪ[1,28]֮��
		     * 
		     * Math.ramdom()
		     * ����
		     * Random random = new Random();
		     * random.nextInt(int value)
		     * 
		     */
		    public void createFood(){
		        Random random = new Random();
		        
		        while (true) {
		            int x = random.nextInt(X_WIDTH);
		            int y = random.nextInt(Y_HEIGHT);
		            System.out.println("x:"+x+",y:"+y);
		            //x��ʾ���Ƕ�ά�����е���,y��ʾ���Ƕ�ά�����е���,�����ڶ�ά�������ȴ�y�ڴ�x,�����к���
		            if (!map[y][x]) {
		                food = new Point(x, y);
		                break;
		            }
		        }
		        
		    }
		    
		    //�Զ������������ĸ�����ĳ���
		    public static final int LEFT = 1;
		    public static final int RIGHT = -1;
		    public static final int UP = 2;
		    public static final int DOWN = -2;
		    
		    
		    //�ٶ��������˶�
		    int currentDirection = RIGHT;
		    
		    /*
		     * ����˶�����͵�ǰ����Ӧ�ķ�����ӵ���0
		     * ����Ϊ������180�ȵ�ͷ��������,������Ӧ��
		     * ��ʾ������ͽ���ǰ���򻻳��˶�����
		     * 
		     * 
		     */
		    public void setDirection(int newDirection){
		        System.out.println("--------->newDirection="+newDirection);
		        if (currentDirection + newDirection == 0) {
		            String message = "�����û�������180�ȵ�ͷ!";
		            System.out.println(message);
		            JOptionPane.showMessageDialog(this, message, "����", JOptionPane.WARNING_MESSAGE);
		        }else
		        this.currentDirection = newDirection;
		        
		    }
		    
		    /*
		     * �ж��߳Ե�ʳ��ķ���
		     * 
		     */
		    public boolean eatFood(){
		        Point snakeHead = snake.getFirst();
		        boolean isEat = false;
		        //����Ե���ʳ��
		        if(snakeHead.x == food.x&& snakeHead.y == food.y){  
		             isEat = true;
		        }
		        
		        return isEat;
		    }
		    /*
		     * ͨ�����̰����ϡ��¡����Ҽ�
		     * �ͽ����ƶ��ķ���������趨,֮���Ӧ�ø��ݶ�Ӧ���ƶ������ƶ�
		     * ����:������������������,���᲻��   
		     * ����:�����������ӷ�����,���᲻��
		     * ����:������������������,���᲻��
		     * ����:�����������ӷ�����,���᲻��
		     * 
		     * ע��:��Ҫ��--��++
		     * 
		     */
		    public void move(){
		        System.out.println("----------move()------------");
		        Point snakeHead = snake.getFirst();
		        switch (currentDirection) {
		        case LEFT:
		            snake.addFirst(new Point(snakeHead.x-1,snakeHead.y));
		            break;
		        case RIGHT:
		            snake.addFirst(new Point(snakeHead.x+1,snakeHead.y));        
		            break;
		        case UP:
		            snake.addFirst(new Point(snakeHead.x,snakeHead.y-1));        
		            break;
		        case DOWN:
		            snake.addFirst(new Point(snakeHead.x,snakeHead.y+1));        
		            break;
		        default:
		            break;
		        }
		        
		        /*
		         * ��ֻ���ڳԵ�ʳ���ʱ��Ż�����һ��,�����߳����ֲ���
		         * ���������������ƶ�����������һֱ��������LinkedList
		         * ������û�гԵ�ʳ���ʱ��Ӧ�ý���������һ���Ƴ���
		         * ����ͨ��LinkedList�����е�removeLast()���
		         * 
		         * 
		         */
		        if(eatFood()){
		            createFood();//�ٴ�����ʳ��
		            
		        }else{//�Ƴ�����β��
		        	//��������
		        	//snake.removeFirst();
		        	//��ȷ�ķ���
		            snake.removeLast();        
		        }
		        
		        repaint();
		    }
		    
		   
		    
		    //��ͼ�е���С��Ԫ��Ϊ20*20��������
		    int cell_width = 20;  //��ǰ��Ԫ��Ŀ�
		    int cell_height = 20;  //��ǰ��Ԫ��ĸ�
		    @Override
		    public void paint(Graphics g) {    
		        super.paint(g);
		        //���ѭ�����Ƶ�����������ǰ�����������y
		        for (int i = 0; i < map.length; i++) {
		            //�ڲ�ѭ�����Ƶ�����������ǰ����ĺ�����x
		            for (int j = 0; j < map[i].length; j++) {
		                //�����ǽ�ڣ�������ǽ�ڵ���ɫΪ���ɫ
		                  if(map[i][j])    
		                       g.setColor(Color.DARK_GRAY);
		                  else
		                      //����ǻ����������Ϊ��ɫ
		                      g.setColor(Color.WHITE);
		                  //�ڶ�Ӧ��panel�ϻ���30*40����1200����������(20*20)�ĵ�Ԫ��Ӷ��������ǵ���Ϸ����
		                  g.fill3DRect(j*cell_width, i*cell_height, cell_width, cell_height, true);
		            }
		        }
		          //������ͷ
		          Point snakeHead = snake.getFirst();
		         g.setColor(Color.RED);
		          g.fill3DRect(snakeHead.x*cell_width, snakeHead.y*cell_height, cell_width, cell_height, true);
		        //��������
		          g.setColor(Color.GREEN);
		        for (int i = 1; i < snake.size(); i++) {
		            Point snakeBody = snake.get(i);
		             g.fill3DRect(snakeBody.x*cell_width, snakeBody.y*cell_height, cell_width, cell_height, true);
		        }
		        
		        //����ʳ��
		         g.setColor(Color.PINK);
		         g.fill3DRect(food.x*cell_width, food.y*cell_height, cell_width, cell_height, true);
		    }
		    
		    

}

