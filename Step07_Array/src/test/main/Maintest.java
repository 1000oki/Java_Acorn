package test.main;

import java.util.Random;

public class Maintest {
	public static void main(String[] args) {
		String[] items = {"cherry", "apple","banana","melon","7"};
		
		Random ran = new Random();
		
		/* 1. run 했을 때 
		 *    cherry, apple, banana, melon, 7
		 *    5개의 문자열 중에서 1개가 랜덤하게 출력되게 해 보세요.
		 */
		int ranNum = ran.nextInt(5);
		String answer = items[ranNum];
		 System.out.println(answer);
		 
		/* 2. run했을 때 
		 *    5개의 문자열 중에서 3개가 한 줄에 한 번에 랜덤하게 출력되게 해 보세요.
		 */
		 
		 String[] answer1 = new String[3];
		 for(int i = 0; i < answer1.length; i++) {
			 int ranNum1 = ran.nextInt(5);
			 answer1[i] = items[ranNum1];
		 }
	
		 System.out.println("3개 모두 같을 때 2점\r\n"
		 		+ "2개만 같을 때 1점\r\n"
		 		+ "같지 않을 때 0점");
		 System.out.println(answer1[0] + " | "+answer1[1]+" | "+answer1[2]);
		 
		 /* 3. 3개 모두 같을 때 2점
		  *    2개만 같을 때 1점
		  *    같지 않을 때 0점
		  */
		 int sum = 0;
		if(answer1[0]==answer1[1] && answer1[0]==answer1[2] && answer1[1]==answer1[2]) {
			sum += 2;
		}else if( answer1[0] == answer1[1] && answer1[0] != answer1[2] ) {
			sum += 1;
		}else if( answer1[0] == answer1[2] && answer1[0] != answer1[1] ) {
			sum += 1;
		}else if( answer1[1] == answer1[2] && answer1[0] != answer1[1] ) {
			sum += 1;
		}else {
			sum = 0;
		}
		System.out.println("점수 : " + sum); 

	}
}
