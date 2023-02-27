package testingil.unittesting.examples.demos.d04.characterization;

public class CalculatorDisplayCopy {
	String display = "";
	int lastArgument = 0;
	int result = 0;
	Boolean newArgument = false;
	Boolean shouldReset = true;

	OperationType lastOperation;

	PlasmaScreen plasmaScreen = new PlasmaScreen();

	public void press(String key) {
		if (key.equals("+")) {
			lastOperation = OperationType.Plus;
			lastArgument = Integer.parseInt(display);
			newArgument = true;
		} else if (key.equals("/")) {
				lastOperation = OperationType.Div;
				lastArgument = Integer.parseInt(display);
				newArgument = true;
		} else if (key.equals("*")) {
			lastOperation = OperationType.Mult;
			lastArgument = Integer.parseInt(display);
			newArgument = true;
		} else if (key.equals("-")) {
			lastOperation = OperationType.Sub;
			lastArgument = Integer.parseInt(display);
			newArgument = true;
		} else if (key.equals("=")) {
				int currentArgument = Integer.parseInt(display);
				if (lastOperation == OperationType.Plus) {
					display = Integer.toString(lastArgument + currentArgument);
				}
				if (lastOperation == OperationType.Div) {
					if (currentArgument == 0) {
						display = "Division By Zero Error";
					} else {
						display = Integer.toString(lastArgument / currentArgument);
					}
				}
				if (lastOperation == OperationType.Sub) {
					display = Integer.toString(lastArgument - currentArgument);
				}
				if (lastOperation == OperationType.Mult) {
					display = Integer.toString(lastArgument * currentArgument);
				}
				shouldReset = true;
			} else {
				if (shouldReset) {
					display = "";
					shouldReset = false;
				}
				if (newArgument) {
					display = "";
					newArgument = false;
				}
				display += key;
			}
	}

	public String getDisplay() {
		if (display.equals("")) {
			plasmaScreen.show("0");
			return "0";
		}
		if (display.length() > 5) {
			plasmaScreen.show("E");
			return "E";
		}

		display = filterZeroes(display);
		plasmaScreen.show(display);
		return display;
	}

	public String filterZeroes(String num) {
		int removeIndex = 0;

		for (int i = 0; i < display.length(); i++) {
			if (display.charAt(i) != '0') {
				removeIndex = i;
				break;
			}
		}

		if (Integer.parseInt(num) == 0) {
			return "0";
		}
		return num.substring(removeIndex);
	}

}