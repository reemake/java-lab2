package calculator;

import org.junit.jupiter.api.Test;

import static calculator.RpnEvaluation.infixToPostfix;
import static calculator.RpnEvaluation.postfixEvaluation;
import static org.junit.jupiter.api.Assertions.*;

class RpnEvaluationTest {

    @Test
    void infixToPostfixTest() {
        String infix = "10*5+15-(4+1)^2-25";
        String expectedValue = "10 5 * 15 + 4 1 + 2 ^ - 25 -";
        String actualValue = infixToPostfix(infix);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void postfixEvaluationTest() {
        String postfix = "10 5 * 15 + 4 1 + 2 ^ - 25 -";
        double expectedValue = 15;
        double actualValue = postfixEvaluation(postfix);

        assertEquals(expectedValue, actualValue);
    }
}