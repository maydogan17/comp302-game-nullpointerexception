package domain.factories;

public interface ObjectFactory<T> {

	public abstract T createObject(ObjectType type);

	public abstract void setFactoryListener(FactoryListener fl);
}
