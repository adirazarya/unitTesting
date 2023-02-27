package testingil.unittesting.examples.demos.d04.characterization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;

@ExtendWith(MockitoExtension.class)
public class CalculatorDisplayTestsCopy {

	private CalculatorDisplayCopy calculatorDisplayCopy = new CalculatorDisplayCopy();

	@Mock
	private PlasmaScreen plasmaScreenMock;

	// Ex1 - Calculator Tests

	@Test
	void whenUseSum_thenSumUpNumbers() {
		String a = "4";
		String b = "10";
		String expected = "14";
		calculatorDisplayCopy.press(a);
		calculatorDisplayCopy.press("+");
		calculatorDisplayCopy.press(b);
		calculatorDisplayCopy.press("=");
		String actual = calculatorDisplayCopy.getDisplay();

		assertEquals(expected, actual);
	}


	@Test
	void whenUseSub_thenSubNumbers() {
		String a = "4";
		String b = "10";
		String expected = "-6";
		calculatorDisplayCopy.press(a);
		calculatorDisplayCopy.press("-");
		calculatorDisplayCopy.press(b);
		calculatorDisplayCopy.press("=");
		String actual = calculatorDisplayCopy.getDisplay();

		assertEquals(expected, actual);
	}


	@Test
	void whenUseMultiply_thenMultiplyNumbers() {
		String a = "4";
		String b = "10";
		String expected = "40";
		calculatorDisplayCopy.press(a);
		calculatorDisplayCopy.press("*");
		calculatorDisplayCopy.press(b);
		calculatorDisplayCopy.press("=");
		String actual = calculatorDisplayCopy.getDisplay();

		assertEquals(expected, actual);
	}


	@Test
	void whenDivideByNotZero_thenDivideNumbers() {
		String a = "4";
		String b = "2";
		String expected = "2";
		calculatorDisplayCopy.press(a);
		calculatorDisplayCopy.press("/");
		calculatorDisplayCopy.press(b);
		calculatorDisplayCopy.press("=");
		String actual = calculatorDisplayCopy.getDisplay();

		assertEquals(expected, actual);
	}

	@Test
	void whenPressedMultipleZeroes_thenShowZero() {
		calculatorDisplayCopy.press("0");
		calculatorDisplayCopy.press("0");
		calculatorDisplayCopy.press("0");
		String actual = calculatorDisplayCopy.getDisplay();

		assertEquals("0", actual);
	}

	@Test
	void whenPressedMultipleZeroesAndNumber_thenShowNumber() {
		calculatorDisplayCopy.press("0");
		calculatorDisplayCopy.press("5");
		calculatorDisplayCopy.press("4");
		String actual = calculatorDisplayCopy.getDisplay();

		assertEquals("54", actual);
	}


	@Test
	void whenDivideByZero_thenDivideNumbers() {
		String a = "4";
		String b = "0";
		String expected = "E";
		calculatorDisplayCopy.press(a);
		calculatorDisplayCopy.press("/");
		calculatorDisplayCopy.press(b);
		calculatorDisplayCopy.press("=");
		String actual = calculatorDisplayCopy.getDisplay();

		assertEquals(expected, actual);
	}

	// Ex2- Data driven tests

	@ParameterizedTest
	@CsvFileSource(resources = "/sumTests.csv")
	void sumTests(String a, String b, String res) {
		calculatorDisplayCopy.press(a);
		calculatorDisplayCopy.press("+");
		calculatorDisplayCopy.press(b);
		calculatorDisplayCopy.press("=");
		String actual = calculatorDisplayCopy.getDisplay();

		assertEquals(actual, res);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/divideTests.csv")
	void DivideTests(String a, String b, String res) {
		calculatorDisplayCopy.press(a);
		calculatorDisplayCopy.press("/");
		calculatorDisplayCopy.press(b);
		calculatorDisplayCopy.press("=");
		String actual = calculatorDisplayCopy.getDisplay();

		assertEquals(actual, res);
	}

	// Ex4 - Mockito

	@Test
	void verifyPlasmaSetWhenGetDisplay() {
		calculatorDisplayCopy.plasmaScreen = plasmaScreenMock;

		calculatorDisplayCopy.press("3");
		calculatorDisplayCopy.press("+");
		calculatorDisplayCopy.press("5");
		calculatorDisplayCopy.press("=");
		calculatorDisplayCopy.getDisplay();

		Mockito.verify(plasmaScreenMock, atLeast(1)).show(any(String.class));
	}

	@Test
	void whenGetValidResult_thenPlasmaShowsNumber() {
		calculatorDisplayCopy.plasmaScreen = plasmaScreenMock;
		ArgumentCaptor<String> displayCaptor = ArgumentCaptor.forClass(String.class);

		calculatorDisplayCopy.press("3");
		calculatorDisplayCopy.press("+");
		calculatorDisplayCopy.press("5");
		calculatorDisplayCopy.press("=");
		String argumentSent = calculatorDisplayCopy.getDisplay();

		Mockito.verify(plasmaScreenMock).show(displayCaptor.capture());

		String plasmaRes = displayCaptor.getValue();
		assertEquals (argumentSent, plasmaRes);
	}

}
