package helper;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import resource.DataBaseSchema;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceProvider {
	private static DataSourceProvider instance;
	private MysqlDataSource _dataSource = new MysqlDataSource();
	private DataModel _userActivityDataModel;
	
	public static DataSourceProvider getInstance() {
		if (instance == null) {
			instance = new DataSourceProvider();
		}
		return instance;
	}

	private DataSourceProvider() {
		_dataSource.setServerName("DATABASE PORT");
		_dataSource.setUser("ENTER DATABASE USER NAME");
		_dataSource.setPassword("DATABASE PASSWORD");
		_dataSource.setDatabaseName("DATABASE NAME");

		_userActivityDataModel = new MySQLJDBCDataModel(_dataSource,
				DataBaseSchema.UserActivityTable,
				DataBaseSchema.UserActivityTable_UserIdColumn,
				DataBaseSchema.UserActivityTable_EntityIdColumn,
				DataBaseSchema.UserActivityTable_Weight,
				DataBaseSchema.UserActivityTable_TimeStamp);
	}

	public DataModel getUserActivityModel() {
		return _userActivityDataModel;
	}
}
