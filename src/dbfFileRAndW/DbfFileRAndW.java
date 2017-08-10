package dbfFileRAndW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFUtils;
import com.linuxense.javadbf.DBFWriter;

public class DbfFileRAndW {
	public static void main(String[] args) {
		DbfFileRAndW dbfFileRAndW = new DbfFileRAndW();
		try {
			dbfFileRAndW.testWriteAndReadAgain();
		} catch (DBFException | IOException e) {
			e.printStackTrace();
		}
	}

	public void testWriteAndReadAgain() throws DBFException, IOException {
		// let us create field definitions first
		// we will go for 3 fields
		//
		DBFField fields[] = new DBFField[3];

		fields[0] = new DBFField();
		fields[0].setName("emp_code");
		fields[0].setType(DBFDataType.CHARACTER);
		fields[0].setLength(10);

		fields[1] = new DBFField();
		fields[1].setName("emp_name");
		fields[1].setType(DBFDataType.CHARACTER);
		fields[1].setLength(20);

		fields[2] = new DBFField();
		fields[2].setName("salary");
		fields[2].setType(DBFDataType.NUMERIC);
		fields[2].setLength(12);
		fields[2].setDecimalCount(2);
		DBFWriter writer = null;
		DBFReader reader = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File f = new File("d:/emp.dbf");
		f.createNewFile();
		try {
			// 开始写
			fos = new FileOutputStream(f);
			writer = new DBFWriter(fos);
			writer.setFields(fields);

			// now populate DBFWriter
			//

			Object rowData[] = new Object[3];
			rowData[0] = "1000";
			rowData[1] = "John";
			rowData[2] = new Double(5000.00);

			writer.addRecord(rowData);

			rowData = new Object[3];
			rowData[0] = "1001";
			rowData[1] = "Lalit";
			rowData[2] = new Double(3400.00);

			writer.addRecord(rowData);

			rowData = new Object[3];
			rowData[0] = "1002";
			rowData[1] = "Rohit";
			rowData[2] = new Double(7350.00);

			writer.addRecord(rowData);

			DBFUtils.close(writer);
			System.out.println("The dbf file product success!");

			// 开始读
			fis = new FileInputStream(f);
			reader = new DBFReader(fis);
			Object[] objects = null;
			for (; (objects = reader.nextRecord()) != null;) {
				System.out.println(Arrays.toString(objects));
			}
			DBFUtils.close(reader);

		} finally {
			DBFUtils.close(reader);
			DBFUtils.close(writer);
		}
	}

}
