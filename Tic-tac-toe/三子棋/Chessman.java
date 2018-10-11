package 三子棋;

public class Chessman {
	public String name;
	public boolean Playchess(int index){
		int Row = index/3;
		int Col = index%3;
		if(index<9&&index>-1&&Checkerboard.strs[Row][Col] == " "){
			Checkerboard.strs[Row][Col] = name; // 对应的棋子填到对应的格子里
			return true;
		}else{
			System.out.println("错误，重新下");
			return false;
		}
	}
	

}
