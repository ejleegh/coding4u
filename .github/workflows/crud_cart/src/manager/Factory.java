package manager;

public interface Factory<E extends Managable> {
	E create();
}
