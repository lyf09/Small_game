package ������;

import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Checkerboard checkerboard = new Checkerboard();
		XChessman xchessman = new XChessman();
		OChessman ochessman = new OChessman();
		System.out.println("'X'���֣�'O'����");
		Scanner in   = new Scanner(System.in);
		int i =-2;
		boolean f2 = true;
		int size =9;
		
		while(true){
			boolean  flag = false;
			while(!flag){
				i = in.nextInt();
				flag = xchessman.Playchess(i); // ����xChess.xiaqi()�����ķ���ֵ���ж��ܲ��ܹ��������У���������ʾ������		
			}
			
			if(checkerboard.print()){ // ֻҪprint���صĲ���TRUE �����������ߣ������TRUE ��break��
				break;
			}
			
			size--;
			if(size <= 0){
				System.out.println("ƽ��");
				break;
			}
			
			flag = false;
			while(!flag){
				i = in.nextInt();
				flag = ochessman.Playchess(i); // ����xChess.xiaqi()�����ķ���ֵ���ж��ܲ��ܹ��������У���������ʾ������		
			}
			if(checkerboard.print()){
				break;
			}
			size--;
		}
	}

}
