package manager;
import java.util.Scanner;

public interface Managable {
	void read(Scanner scan);
	void print();
	boolean compare(String kwd);
	boolean compare(int n);
	String getName();
}
