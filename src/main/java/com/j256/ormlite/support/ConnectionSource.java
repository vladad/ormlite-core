package com.j256.ormlite.support;

import java.sql.SQLException;

import com.j256.ormlite.db.DatabaseType;

/**
 * A reduction of the SQL DataSource so we can implement its functionality outside of JDBC.
 * 
 * @author graywatson
 */
public interface ConnectionSource {

	/**
	 * Return a database connection suitable for read-only operations. After you are done, you should call
	 * {@link #releaseConnection(DatabaseConnection)}.
	 */
	public DatabaseConnection getReadOnlyConnection() throws SQLException;

	/**
	 * Return a database connection suitable for read or write operations. After you are done, you should call
	 * {@link #releaseConnection(DatabaseConnection)}.
	 */
	public DatabaseConnection getReadWriteConnection() throws SQLException;

	/**
	 * Release a database connection previously returned by {@link #getReadOnlyConnection()} or
	 * {@link #getReadWriteConnection()}.
	 */
	public void releaseConnection(DatabaseConnection connection) throws SQLException;

	/**
	 * Save this connection as part of a transaction. Between this call and the
	 * {@link #clearTransactionConnection(DatabaseConnection)}, all connections returned by
	 * {@link #getReadOnlyConnection()} and {@link #getReadWriteConnection()} should return this connection since all
	 * operations within a transaction must operate on the same connection.
	 */
	public void saveTransactionConnection(DatabaseConnection connection) throws SQLException;

	/**
	 * Clear the saved transaction connection. New transactions will be returned and released.
	 */
	public void clearTransactionConnection(DatabaseConnection connection) throws SQLException;

	/**
	 * Close any outstanding database connections.
	 */
	public void close() throws SQLException;

	/**
	 * Return the DatabaseTypre associated with this connection.
	 */
	public DatabaseType getDatabaseType();
}
