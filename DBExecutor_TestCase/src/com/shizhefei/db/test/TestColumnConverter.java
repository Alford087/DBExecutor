package com.shizhefei.db.test;

import android.test.AndroidTestCase;

import com.shizhefei.db.converters.ColumnConverterFactory;
import com.shizhefei.db.converters.DBType;
import com.shizhefei.db.converters.IColumnConverter;

/**
 * 
 * ��������ת����
 * 
 * @author ���ŷ�
 * 
 */
public class TestColumnConverter extends AndroidTestCase {
	public void testColumnConverter() throws Exception {
		boolean isOk = true;
		IColumnConverter converter = ColumnConverterFactory
				.getColumnConverter(isOk);
		Object value = converter.toSqlValue(isOk);
		System.out.println("ת����sql�����ݣ�" + value);
		Object re = converter.toJavaValue(value);
		System.out.println("ת����java�����ݣ�" + re);
	}

	public void testSelfColumnConverter() throws Exception {
		// ���ﶨ�����Լ���ת��������ע�ᵽ��ColumnConverterFactory��
		IColumnConverter self = new IColumnConverter() {

			@Override
			public String toSqlValue(Object value) {
				Person person = (Person) value;
				String s = person.name + "###" + person.age;
				return s;
			}

			@Override
			public Person toJavaValue(Object value) {
				String s = String.valueOf(value);
				String[] vas = s.split("###");
				String name = vas[0];
				int age = Integer.parseInt(vas[1]);
				return new Person(age, name);
			}

			@Override
			public DBType getDBType() {
				return DBType.TEXT;
			}
		};
		ColumnConverterFactory.regist(Person.class, self);
		// ���￪ʼ��ʾʹ�õĹ���
		Person person = new Person(11, "С��");
		IColumnConverter converter = ColumnConverterFactory
				.getColumnConverter(person);
		Object value = converter.toSqlValue(person);
		System.out.println("ת����sql�����ݣ�" + value);
		Object re = converter.toJavaValue(value);
		System.out.println("ת����java�����ݣ�" + re);
	}

	private static class Person {
		private int age;
		private String name;

		public Person(int age, String name) {
			super();
			this.age = age;
			this.name = name;
		}

	}
}
