package jp.co.acesystems.mybatissample.repository.datamodel;

/**
 * 単一PK項目を持つテーブルのレコードであることを示すInterface
 * @author U0268
 *
 * @param <T> PK項目の型
 */
public interface HavingSinglePk<T> extends Cloneable {
	/**
	 * PK項目
	 * @return
	 */
	public T getId();
	
	/**
	 * ディープコピー
	 * @return
	 */
	public Object clone();
}
