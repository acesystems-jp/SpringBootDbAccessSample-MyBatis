package jp.co.acesystems.mybatissample.repository.datamodel;

public interface HavingSinglePk<T> extends Cloneable {
	public T getId();
	public Object clone();
}
