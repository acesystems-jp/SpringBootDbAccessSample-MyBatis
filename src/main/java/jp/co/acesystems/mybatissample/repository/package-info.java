/**
 * データベースとのやり取りを受け持つパッケージ
 * Repository-他のパッケージで使用するクラス
 * Mapper-MyBatisを使用して直接データベースとやり取りするクラス。
 *  MapperはデータベースやMyBaisの仕様に引きずられるため外部からは参照しない。
 *  Repositoryからのみ参照する
 * DataModel-レコードを表現するクラス。
 */
package jp.co.acesystems.mybatissample.repository;
