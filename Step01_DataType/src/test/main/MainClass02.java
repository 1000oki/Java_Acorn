package test.main;

public class MainClass02 {
	public static void main(String[] args) {
		System.out.println("main 메소드가 시작되었습니다.");

		// 국어점수
		int kor = 95;
		// 영어점수
		int eng = 100;
		
		// 국어 점수와 영어 점수의 평균을 구해서 변수에 담고 그 결과를 콘솔창에 출력해 보세요.
		double avg = (kor+eng)/2f; 
		/*	정수끼리 연산하면 결과는 정수만 나옴.
		*	정수와 실수와 연산하면 실수가 나옴.
		*/
		System.out.println(avg);
	}
}
