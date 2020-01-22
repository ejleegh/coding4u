package crud;

import java.util.Scanner;

import manager.Managable;

public interface Crudable {
	void readOnLine(Scanner keyin);
	boolean checkRemove(Scanner keyin);
	void update(Scanner keyin);
}
