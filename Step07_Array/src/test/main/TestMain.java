package test.main;

import java.util.Random;
import java.util.Scanner;

public class TestMain {
	public static void main(String[] args) {
		
		int point = 1000;
		Scanner scan = new Scanner(System.in);
		String[] items = {"cherry", "apple","banana","melon","7"};
		// Random한 숫자를 얻어내기 위한 객체
		Random ran = new Random();
		
		// for 문 안에서 얻어낸 랜덤한 숫자 3개를 저장할 배열 객체 생성
		int[] nums = new int[3];

		while(true) {
			// 만일 point가 0이면
			if(point == 0) {
				break; // 반복물 loop 탈출
			}
			
			System.out.println("Enter를 치세요.");
			// Enter를 칠 때까지 블로킹되는 메소드 호출
			scan.nextLine();
			// point를 10 감소시키면서 
			point -= 10;
			
			for(int i = 0; i < 3; i++) {
				// 0~4 사이의 랜덤한 숫자를 하나 얻어내서
				int ranNum = ran.nextInt(5);
				// 배열의 인덱스로 활용해서 문자열 출력하기
				System.out.print(items[ranNum]);
				if( i < 2) {
					System.out.print(" | ");
				}
				// 배열에 얻어낸 랜덤한 숫자를 저장하기
				nums[i] = ranNum;
			}		
			// 개행
			System.out.println();
			// 3개가 모두 같은지 여부
			boolean isAllEqual = nums[0] == nums[1] && nums[1] == nums[2];
			// 2개가 같은지 여부
		    boolean isTwoEqual = nums[0] == nums[1] || nums[1] == nums[2] || nums[0] == nums[2];
		    /*
		     *  점수는 10, 5, 0 
		     */
		    if(isAllEqual) {
		    	point += 10;
		    	System.out.println("점수는 "+point +" 입니다.");
		    }else if(isTwoEqual) {
		    	point += 5;
		    	System.out.println("점수는 "+point +" 입니다.");
		    }else {
		    	point += 0;
		    	System.out.println("점수는 "+point +" 입니다.");

		    }
			
			// 원하는 작업 수행...
			System.out.println("수행중...");
		}
		
		System.out.println("main 메소드가 종료 됩니다.");
	}
}
