package 三子棋;

import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Checkerboard checkerboard = new Checkerboard();
		XChessman xchessman = new XChessman();
		OChessman ochessman = new OChessman();
		System.out.println("'X'先手，'O'后手");
		Scanner in   = new Scanner(System.in);
		int i =-2;
		boolean f2 = true;
		int size =9;
		
		while(true){
			boolean  flag = false;
			while(!flag){
				i = in.nextInt();
				flag = xchessman.Playchess(i); // 根据xChess.xiaqi()方法的返回值，判断能不能够继续运行，不能则提示重新下		
			}
			
			if(checkerboard.print()){ // 只要print返回的不是TRUE ，继续往下走，如果是TRUE 就break掉
				break;
			}
			
			size--;
			if(size <= 0){
				System.out.println("平局");
				break;
			}
			
			flag = false;
			while(!flag){
				i = in.nextInt();
				flag = ochessman.Playchess(i); // 根据xChess.xiaqi()方法的返回值，判断能不能够继续运行，不能则提示重新下		
			}
			if(checkerboard.print()){
				break;
			}
			size--;
		}
	}

}
