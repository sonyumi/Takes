package Calculator;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator extends WindowAdapter implements ActionListener {
	private Frame cal; // 메인프레임
	private TextField subView; // 작은 화면
	private TextField mainView; // 큰 화면
	private Button[] bt = new Button[20]; // 버튼
	private String[] num = new String[999]; // 사용자가 입력하는 피연산자 저장
	private String[] op = new String[998]; // 사용자가 입력하는 연산자 저장

	public Calculator() {

		cal = new Frame("Calculator"); // 프레임 생성
		subView = new TextField(); // 서브 텍스트필드 생성
		mainView = new TextField(); // 메인 텍스트필드 생성

		for (int i = 0; i < bt.length; i++) {
			bt[i] = new Button(); // 버튼 생성
		}

	}

	void setCalculator() {
		Toolkit tk = Toolkit.getDefaultToolkit(); // 디폴트값의 툴킷 리턴
		Dimension screenSize = tk.getScreenSize(); // 높이값 넓이값 설정
		Font f = new Font("Gothic", Font.PLAIN, 50); // 큰 폰트
		Font fs = new Font("Gothic", Font.PLAIN, 20); // 작은 폰트
		cal.setSize(350, 530); // 계산기 프레임 사이즈
		cal.setLocation(screenSize.width / 2 - 175, screenSize.height / 2 - 250); // 계산기 실행 위치
		cal.addWindowListener(this); // 윈도우 닫기 창 구현 리스너
		subView.setBounds(10, 40, 330, 40); // 작은 텍스트필드 위치 , 크기 설정
		subView.setFont(fs); // 작은 텍스트필드 폰트

		mainView.setBounds(10, 71, 330, 80); // 큰 텍스트필드 위치 , 크기 설정
		mainView.setFont(f); // 큰 텍스트필드 폰트

		mainView.setEditable(false); // 메인 텍스트필드 키보드 타이핑 잠구기
		subView.setEditable(false); // 서브 텍스트필드 키보드 타이핑 잠구기

		cal.setLayout(null); // Layout 스타일
		cal.add(mainView);
		cal.add(subView);
		cal.setVisible(true);

		// **botton setting

		// 버튼 cal 프레임과 연결 및 리스너 구현, 폰트 설정
		for (int i = 0; i < bt.length; i++) {
			cal.add(bt[i]);
			bt[i].addActionListener(this);
			bt[i].setFont(fs);
		}
		// 제일 윗줄 1~4 버튼 크기, 위치지정
		for (int i = 0, x = 9, y = 163; i < 4; i++, x += 83) {
			bt[i].setBounds(x, y, 82, 70);
		}
		// 두번째줄 1~4 버튼 크기, 위치지정
		for (int i = 4, x = 9, y = 234; i < 8; i++, x += 83) {
			bt[i].setBounds(x, y, 82, 70);
		}
		// 세번째줄 1~4 버튼 크기, 위치지정
		for (int i = 8, x = 9, y = 305; i < 12; i++, x += 83) {
			bt[i].setBounds(x, y, 82, 70);
		}
		// 네번째줄 1~4 버튼 크기, 위치지정
		for (int i = 12, x = 9, y = 376; i < 16; i++, x += 83) {
			bt[i].setBounds(x, y, 82, 70);
		}
		// 다섯번째 줄 1~4 버튼 크기, 위치 지정
		for (int i = 16, x = 9, y = 442; i < 20; i++, x += 83) {
			bt[i].setBounds(x, y, 82, 70);
		}

		// 버튼에 숫자 및 기호 세팅
		for (int i = 4, j = 7; i < 7; i++, j++) {
			String s = "";
			s += j;
			bt[i].setLabel(s);
		}
		for (int i = 8, j = 4; i < 11; i++, j++) {
			String s = "";
			s += j;
			bt[i].setLabel(s);
		}
		for (int i = 12, j = 1; i < 15; i++, j++) {
			String s = "";
			s += j;
			bt[i].setLabel(s);
		}

		bt[0].setLabel("<");
		bt[1].setLabel("Ｃ");
		bt[2].setLabel("ＣＥ");
		bt[3].setLabel("/");
		bt[7].setLabel("*");
		bt[11].setLabel("-");
		bt[15].setLabel("+");
		bt[16].setLabel("±");
		bt[17].setLabel("0");
		bt[18].setLabel(".");
		bt[19].setLabel("=");

		// ** button setting 끝
	}

	public void actionPerformed(ActionEvent e) {
		// 버튼 눌렸을때 이벤트 발생
		String st = e.getActionCommand();

		// 버튼이 숫자일경우
		if (st.equals("0") || st.equals("1") || st.equals("2") || st.equals("3") || st.equals("4") || st.equals("5")
				|| st.equals("6") || st.equals("7") || st.equals("8") || st.equals("9")) {
			int i = Integer.parseInt(st); // 조건체크를 위한 변환
			if (i >= 0 || i < 10) {
				mainView.setText(mainView.getText() + st);
			}
		}

		// 버튼이 연산자일경우
		if (st.equals("+") || st.equals("-") || st.equals("*") || st.equals("/")) {
			if (mainView.getText().length() == 0) {
				if (st.equals("-")) {
					// 숫자 입력 전 첫 입력이 -일경우
					num[0] = "0";
					op[0] = "-";
					subView.setText("0-");
					return;

				} else if (st.equals("*")) {
					// 숫자 입력 전 첫 입력이 *일경우
					num[0] = "0";
					op[0] = "*";
					subView.setText("0*");
					return;

				} else if (st.equals("/")) {
					// 숫자 입력 전 첫 입력이 /일경우
					num[0] = "0";
					op[0] = "/";
					subView.setText("0/");
					return;
				} else if (st.equals("+")) {
					// 숫자 입력 전 첫 입력이 +일경우
					num[0] = "0";
					op[0] = "+";
					subView.setText("0+");
					return;
				}
			}
			subView.setText(subView.getText() + mainView.getText() + st); // subView text값 세팅
			String ss = mainView.getText();
			for (int i = 0; i < num.length; i++) {
				if (num[i] == null && op[i] == null) {
					num[i] = ss; // 피연산자를 문자열 배열에 저장
					op[i] = st; // 연산자를 문자열 배열에 저장
					break;
				}
			}

			mainView.setText(""); // Main 텍스트 화면 클리어
		}
		if (st.equals("ＣＥ")) { // mainView의 값을 지워줌
			mainView.setText("");
		}
		if (st.equals("±")) { // mainView의 값을 음수는 양수로, 양수는 음수로 변환
			if (mainView.getText().indexOf("-") == 0) {
				mainView.setText(mainView.getText().replaceFirst("-", ""));

			} else {
				mainView.setText("-" + mainView.getText());
			}
		}
		if (st.equals("<")) { // backspace 기능 구현
			if (mainView.getText().length() == 0) { // mainView에 값이 없으면 실행되지 않음
				return;
			} else { // mainView의 값의 마지막 문자열을 지움
				mainView.setText(mainView.getText().substring(0, mainView.getText().length() - 1));
			}
		}
		// 이퀄 연산 (사칙연산 순서대로 연산)
		if (st.equals("=")) {
			subView.setText("");
			String ss = mainView.getText();

			// 배열의 마지막에 마지막으로 들어온 텍스트 배치
			for (int i = 0; i < num.length; i++) {
				if (num[i] == null) {
					num[i] = ss;
					break;
				}
			}

			// "=" if 안에서의 지역변수지정
			BigDecimal x2; // 입력되는 피연산자 (큰 수를 계산하기위한 BigDecimal)
			BigDecimal x3; // 입력되는 피연산자
			String x1; // 연산 후의 값

			// 문자열 배열로 연산 (연산이 끝난 연산자,피연산자는 null로 초기화)
			try {
				// *, / 의 연산처리 (사칙연산 순서 기준)
				for (int i = 0; i < op.length; i++) {
					if (op[i] != null) { // [ *,/ ]먼저 연산
						if (op[i].equals("*")) {
							x2 = new BigDecimal(num[i]);
							x3 = new BigDecimal(num[i + 1]);
							x1 = x2.multiply(x3).toString(); // (BigDecimal에서 제공하는 연산메소드로 곱셈 수행)
							num[i + 1] = x1;
							num[i] = null;// 계산한 피연산자는 null로 바꿔줌
							op[i] = null;// 계산한 연산자는 null로 바꿔줌
							continue;
						}
						if (op[i].equals("/")) { // [ *,/ ]먼저 연산
							x2 = new BigDecimal(num[i]); // (BigDecimal에서 제공하는 연산메소드로 나눗셈 수행)
							x3 = new BigDecimal(num[i + 1]);
							x1 = x2.divide(x3, 4, RoundingMode.HALF_UP).toString();// 4번째자리에서 반올림
							num[i + 1] = x1;
							num[i] = null;// 계산한 피연산자는 null로 바꿔줌
							op[i] = null;// 계산한 연산자는 null로 바꿔줌
							continue;
						}
					}
				}
				// result 값 세팅
				BigDecimal result = null;
				if (num[0] != null) {
					// 첫번째 숫자가 null이 아닌경우 result로 값 이전 후 null값 세팅
					result = new BigDecimal(num[0]);
					num[0] = null;
				} else {
					// 첫번째 숫자가 null인 경우 null값이 아닌 제일 빠른 인덱스 값을 result로 값 이전후 null로 세팅
					for (int i = 0; i < num.length; i++) {
						if (num[i] != null) {
							result = new BigDecimal(num[i]);
							num[i] = null;
							break;
						}
					}
				}

				// +, - 의 연산 처리
				for (int i = 0; i < op.length; i++) {
					if (op[i] != null) { // null이 아닌 값 계산
						if (op[i].equals("+")) {
							for (int j = i; j < num.length; j++) {
								if (num[j] == null) {
									continue;
								} else {
									x3 = new BigDecimal(num[j]);
									result = result.add(x3);
									op[i] = null;
									num[j] = null;
									break;
								}
							}
							continue;
						}

						if (op[i].equals("-")) {
							for (int j = i; j < num.length; j++) {
								if (num[j] == null) {
									continue;
								} else {
									x3 = new BigDecimal(num[j]);
									result = result.subtract(x3);
									op[i] = null;
									num[j] = null;
									break;
								}
							}
							continue;
						}

					}
				}

				String sResult = result.toString();
				mainView.setText(sResult);
			} catch (NumberFormatException e1) {
				// 익셉션 뜰경우 (연산자가 피연산자 없이 여러번 눌린경우
				// 또는 null값에서 "=" 연산을 수행하는 경우)
				mainView.setText("");
			} catch (ArithmeticException e2) {
				// 사용자가 0으로 나눌 경우
				mainView.setText("0으로 나눌 수 없습니다.");
			}

			// '='연산 후 스트링 배열(피연산자, 연산자) 초기화
			for (int i = 0; i < num.length; i++) {
				if (num[i] != null) {
					num[i] = null;
				} else {
					break;
				}
				if (op[i] != null) {
					op[i] = null;
				}
			}
		}
		if (st.equals("Ｃ")) {
			// 초기화 버튼 ("Ｃ") 입력시 스트링 배열(피연산자, 연산자) 초기화
			for (int i = 0; i < num.length; i++) {
				if (num[i] != null) {
					num[i] = null;
				} else {
					break;
				}
				if (op[i] != null) {
					op[i] = null;
				}
			}
			// 메인, 서브 화면 초기화
			subView.setText("");
			mainView.setText("");
		}
		if (st.equals(".")) {
			if (mainView.getText().length() == 0) {
				// main textfield에 아무것도 없이 .만 입력한경우
				mainView.setText("0" + ".");
			} else { // main textfield 에 한자리 이상의 값에 소수점을 추가하는경우
				if (mainView.getText().indexOf(".") > 0) {
					// 이미 소숫점이 있을경우
					return;
				} else { // 소숫점이 없을경우 추가
					mainView.setText(mainView.getText() + ".");
				}
			}
		}
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	public static void main(String[] args) {
		Calculator ct = new Calculator();
		ct.setCalculator();
	}
}