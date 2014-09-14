package com.shizhefei.db.test;

import java.util.Map;
import java.util.Set;

import android.test.AndroidTestCase;

import com.shizhefei.db.DBExecutor;
import com.shizhefei.db.annotations.Check;
import com.shizhefei.db.annotations.Column;
import com.shizhefei.db.annotations.Id;
import com.shizhefei.db.annotations.NotNull;
import com.shizhefei.db.annotations.Table;
import com.shizhefei.db.annotations.Transient;
import com.shizhefei.db.annotations.Unique;
import com.shizhefei.db.sql.SqlFactory;
import com.shizhefei.db.table.TableFactory;

/**
 * ���Ա������
 * 
 * @author Administrator
 * 
 */
public class TestConfig extends AndroidTestCase {
	private DBExecutor db;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		db = DBExecutor.getInstance(getContext());
	}

	public void testCreateSql() {
		com.shizhefei.db.table.Table table = TableFactory.getTable(QQQQ.class);
		String sql = SqlFactory.createTable(table);
		System.out.println("create table sql:" + sql);
	}

	public void testTableName() throws Exception {
		String tableName = TableFactory.getTableName(QQQQ.class);
		System.out.println("tableName:" + tableName);
	}

	public void testColumnName() throws Exception {
		com.shizhefei.db.table.Table table = TableFactory.getTable(QQQQ.class);
		Map<String, com.shizhefei.db.table.Column> columns = table.getColumns();
		Set<String> columnNames = columns.keySet();
		for (String name : columnNames) {
			System.out.print(name + ",");
		}
		System.out.println();
	}

	public void testCheck() {
		int id = 1;
		String kkkk = "С��";
		int age = 11;
		int cardId = 5899;
		String ddd = "";
		QQQQ qqqq = new QQQQ(id, kkkk, age, cardId, ddd);

		assertTrue(db.insert(qqqq));
		assertTrue(db.deleteById(QQQQ.class, 1));

		qqqq.age = -11;
		assertFalse(db.insert(qqqq));
		assertTrue(db.deleteById(QQQQ.class, 1));
	}

}

@Table(name = "Person_Table")
class QQQQ {
	/**
	 * <pre>
	 * ���ø��ֶ�Ϊid�ֶΣ�������������
	 * ##########
	 *  Ĭ�ϵ����������������������д����
	 *  @Id
	 *  private int id;
	 * </pre>
	 */
	@Id(autoIncrement = false)
	int id;
	/**
	 * �����Զ��������
	 */
	@Column(column = "name")
	String kkkk;
	/**
	 * ���check����
	 */
	// sql��ôдcheck ���ʽ����߾���ôд
	@Check(value = "age>0")
	int age;
	/**
	 * ���ֶε�ֵΨһ
	 */
	@Unique
	int cardId;
	/**
	 * ����Ϊ��
	 */
	@NotNull
	String ddd;
	// @Transient��transient ��static�������ֶβ��������ݿ����������
	@Transient
	private String MMMMMM;
	private transient String UUUUUUU;
	private static String LLLLL;

	public QQQQ(int id, String kkkk, int age, int cardId, String ddd) {
		super();
		this.id = id;
		this.kkkk = kkkk;
		this.age = age;
		this.cardId = cardId;
		this.ddd = ddd;
	}

}
