package ������;

public class Checkerboard {
	static String[][] strs = {{" "," "," "},{" "," "," "},{" "," "," "}};
	public boolean print(){
		for(int i=0 ;i<3; i++){
			for(int j=0;j<3;j++){
				System.out.print(strs[i][j]);
				System.out.print("|");
			}
			System.out.println();
			System.out.print("------");
			System.out.println();
		}
		return winner();
	}
	public boolean winner() {
		if(strs[0][0].equals(strs[0][1])&&strs[0][1].equals(strs[0][2])&&strs[0][2].equals("O")
				||strs[1][0].equals(strs[1][1])&&strs[1][1].equals(strs[1][2])&&strs[1][2].equals("O")
				||strs[2][0].equals(strs[2][1])&&strs[2][1].equals(strs[2][2])&&strs[2][2].equals("O")
				||strs[0][0].equals(strs[1][0])&&strs[1][0].equals(strs[2][0])&&strs[2][0].equals("O")
				||strs[0][1].equals(strs[1][1])&&strs[1][1].equals(strs[2][1])&&strs[2][1].equals("O")
				||strs[0][2].equals(strs[1][2])&&strs[1][2].equals(strs[2][2])&&strs[2][2].equals("O")
				||strs[0][0].equals(strs[1][1])&&strs[1][1].equals(strs[2][2])&&strs[2][2].equals("O")
				||strs[0][2].equals(strs[1][1])&&strs[1][1].equals(strs[2][0])&&strs[2][0].equals("O")){
			System.out.println("O���ʤ��");
			return true;
			
		} else if(strs[0][0].equals(strs[0][1])&&strs[0][1].equals(strs[0][2])&&strs[0][2].equals("X")
				||strs[1][0].equals(strs[1][1])&&strs[1][1].equals(strs[1][2])&&strs[1][2].equals("X")
				||strs[2][0].equals(strs[2][1])&&strs[2][1].equals(strs[2][2])&&strs[2][2].equals("X")
				||strs[0][0].equals(strs[1][0])&&strs[1][0].equals(strs[2][0])&&strs[2][0].equals("X")
				||strs[0][1].equals(strs[1][1])&&strs[1][1].equals(strs[2][1])&&strs[2][1].equals("X")
				||strs[0][2].equals(strs[1][2])&&strs[1][2].equals(strs[2][2])&&strs[2][2].equals("X")
				||strs[0][0].equals(strs[1][1])&&strs[1][1].equals(strs[2][2])&&strs[2][2].equals("X")
				||strs[0][2].equals(strs[1][1])&&strs[1][1].equals(strs[2][0])&&strs[2][0].equals("X")){
			System.out.println("X���ʤ��");
			return true;
		} 
		
		
		return false; // û�����ʤ������Ϸ���� ����false
	}
	

}
