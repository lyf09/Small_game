package ������;

public class Chessman {
	public String name;
	public boolean Playchess(int index){
		int Row = index/3;
		int Col = index%3;
		if(index<9&&index>-1&&Checkerboard.strs[Row][Col] == " "){
			Checkerboard.strs[Row][Col] = name; // ��Ӧ���������Ӧ�ĸ�����
			return true;
		}else{
			System.out.println("����������");
			return false;
		}
	}
	

}
