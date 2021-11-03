package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class RpnEvaluationTest {

    RpnEvaluation calculator;

    @BeforeEach
    public void setUp() { calculator = new RpnEvaluation(); }

    @Test
    void infixToPostfixTest() {
        String infix = "10*5+15-(4+1)^2-25";
        String expectedValue = "10 5 * 15 + 4 1 + 2 ^ - 25 - ";
        String actualValue = calculator.infixToPostfix(infix);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void postfixEvaluationTest() {
        String postfix = "10 5 * 15 + 4 1 + 2 ^ - 25 -";
        double expectedValue = 15;
        double actualValue = calculator.postfixEvaluation(postfix);

        assertEquals(expectedValue, actualValue);
    }
}