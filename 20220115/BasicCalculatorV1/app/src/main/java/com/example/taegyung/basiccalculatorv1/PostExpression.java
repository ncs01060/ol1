package com.example.taegyung.basiccalculatorv1;

import android.widget.Toast;

import java.util.Stack;

/**
 * Created by TaeGyung on 2021-12-29.
 */

public class PostExpression {

    String postExpression = "";

    // 연산자 우선순위 정의하는 메소드
    public int getOpPriority(char op) {

        if (op == '*' || op == '/' || op == '%') {
            return 3;
        }
        else if (op == '+' || op == '-'){
            return 2;
        }
        else if(op == '('){
            return 1;
        }

        return -1;
    }

    // 연산자 우선순위 실제로 비교하는 메소드
    public boolean compareToPriority(char op1, char op2) {

        int opPriority1 = getOpPriority(op1);
        int opPriority2 = getOpPriority(op2);

        if(opPriority1 >= opPriority2) {
            return true;
        }
        else {
            return false;
        }
    }

    public String setSpace( String exp ) {

        String res = "";

        // 두자리 수 계산을 위해 공백  추가
        // 만일 문자열 앞에 이미 공백이 추가되어 있으면 추가하지 않는다
        if ( !exp.endsWith(" ")) {
            res = " ";
        }

        return res;
    }

    // 중위 표현식 => 후위 표현식으로 변환하는 메소드
    // 컴퓨터은 후위 표현식만 알수 있다.( 연산자 우선순위가 반영된 표현식 )
    // 알고리즘
//    1. 피연산자는(숫자이면) 그대로 후위표현식 문자열 변수에 저장합니다. (postExpression)
//    2. 연산자는 문자열 변수에 공백(" ")을 추가하고 스택이 비어있으면 자신을 바로 push합니다.
//    3. 연산자의 우선순위가 높으면 push , 스택의 연산자가 높으면 낮은 것이 나올때까지 pop해서 후위표현식 문자열 변수에 저장합니다.
//    4. 단, 여는 괄호는 닫는 괄호가 아니면 pop하지 않는다.
//    4. 닫는 괄호가 나오면 여는 괄호가 나올 때까지 꺼내서 후위표현식 문자열 변수에 저장합니다.
//    5. 마지막에 도착했는데도 스택에 값이 남아있다면 스택에서 차례로 꺼내서 후위표현식 문자열 변수에 저장합니다.

    // PostExpresion 메소드 만들기
    public String middleToRear(String exp) {
        Stack<Character> stack = new Stack<>();
        String postExpression = "";
        int cnt = 0;

        // 문자열이 끝에 도달할때 까지 체크하여 후위표현식으로 변환한다.
        while (cnt <  exp.length()) {

            char chrExp = exp.charAt(cnt);
            char chrPreExp = ' ';

            if (cnt -1 >= 0) {
                chrPreExp = exp.charAt(cnt - 1);
            }

            // 숫자이면 (피연산자이면) 후위표현식 문자열에 저장
            // 두자리 이상일 경우도 반영
            if (chrExp >= '0' && chrExp <= '9' || chrExp == '.' ) {

                if (chrPreExp == '+' || chrPreExp == '-' || chrPreExp == '*' || chrPreExp == '/' ||  chrPreExp == '%' || chrPreExp == '(') {

                    // 두자리 수 계산을 위해 공백  추가
                    postExpression += setSpace(postExpression);
                }

                postExpression += chrExp;

            }
            else {
                // 연산자이면
                // 두자리이상 숫자를 위해 공백을 하나 추가한다.
                postExpression += setSpace(postExpression);

                // 닫는 괄호가 나오면 여는 괄호가 나올 때까지 꺼내서 출력합니다.
                if (chrExp == ')') {
                    while (true) {
                        char chrPeek = (char)stack.pop();
                        if (chrPeek == '(') {
                            break;
                        }
                        // 두자리이상 숫자를 위해 연산자 앞에 공백을 추가한다.
                        postExpression += setSpace(postExpression);
                        postExpression += chrPeek;
                    }
                }
                // 연산자는 스택이 비어있으면 자신을 바로 추가합니다.
                else if (chrExp == '+' || chrExp == '-' || chrExp == '*' || chrExp == '/' ||  chrExp == '%' || chrExp == '(') {
                    // 연산자가 높으면 push , 연산자가 낮으면 스택의 연산자를 계속 꺼낸다
                    // 연산자 우선순위가 낮아 꺼내는 부분
                    // stack.peek() > chrExp 이면 true , 아니면 false
                    while (stack.isEmpty() == false && compareToPriority(stack.peek(), chrExp) == true ) {

                        // 두자리수 숫자를 위해 공백을 추가한다.
                        postExpression += setSpace(postExpression);
                        postExpression += stack.pop();
                    }
                    // 연산자 우선순위가 높아 스택에 저장
                    stack.push(chrExp);
                }
            }
            cnt++;
        }

        // 문자열을 끝까지 조사했는데도 스택에 값이 남아있으면 모두 꺼내서 후위표현식 문자열의 끝에 붙인다.
        while (stack.isEmpty() == false) {
            // 두자리이상 숫자를 위해 연산자 앞에 공백을 추가한다.
            postExpression += setSpace(postExpression);
            postExpression += stack.pop();
        }

        return postExpression;
    }


    // 알고리즘 : 후위 표현식 계산하기
    //후위 표현식을 왼쪽부터 한 글자씩 읽어서
    //피연산자이면, 스택에 push
    //연산자를 만나면 스택에서 pop → (1), 또 pop → (2)
    //(1) 연산 (2) 을 계산 이 결과를 스택에 push
    //수식의 끝에 도달하면 스택에서 pop → 이것이 계산 결과
    public double postCalculator(String expression) {

        // 중위 표현식을 후위 표현식으로 변환
        // 두자리이상 수를 위해 각각 숫자와 연산자는 공백으로 구분되어 있다.
        String exp = middleToRear(expression);

        Stack<Double> stack = new Stack<>();
        int cnt = 0;

        try {
            // 공백을 기준으로 나눈다
            String[] res = exp.split(" ");

            while (cnt < res.length) {

                if (res[cnt].equals("+") || res[cnt].equals("%") || res[cnt].equals("/")) {

                    // 연산자를 만나면 스택에서 pop → (1), 또 pop → (2)
                    double op2 = stack.pop();
                    double op1 = stack.pop();

                    // (1) 연산 (2) 을 계산 이 결과를 스택에 push
                    if (res[cnt].equals("+")) {
                        stack.push(op1 + op2);
                    } else if (res[cnt].equals("%")) {
                        stack.push(op1 % op2);
                    } else if (res[cnt].equals("/")) {
                        stack.push(op1 / op2);
                    }

                } else {
                    stack.push(Double.parseDouble(res[cnt]));
                }

                cnt++;

            }

        }
        catch ( Exception e) {
            // 에러 출력
            return -1;
        }

        return stack.pop();

    }
}
