package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;

import common.source.Log;

public class StoredProcComplianceGetGaps extends BaseEntity {
	private String rowNumber;
	private String colA;
	private String colB;
	private String colC;
	private String colD;
	private String colE;
	private String colF;
	private String colG;
	private String colH;
	private String colI;
	private String colJ;
	private String colK;
	private String colL;

	public StoredProcComplianceGetGaps() {
		super();
	}

	public String getRowNumber() {
		String returnVal = (rowNumber != null) ? rowNumber : "";
		return returnVal;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getColA() {
		String returnVal = (colA != null) ? colA : "";
		return returnVal;
	}

	public void setColA(String colA) {
		this.colA = colA;
	}

	public String getColB() {
		String returnVal = (colB != null) ? colB : "";
		return returnVal;
	}

	public void setColB(String colB) {
		this.colB = colB;
	}

	public String getColC() {
		String returnVal = (colC != null) ? colC : "";
		return returnVal;
	}

	public void setColC(String colC) {
		this.colC = colC;
	}

	public String getColD() {
		String returnVal = (colD != null) ? colD : "";
		return returnVal;
	}

	public void setColD(String colD) {
		this.colD = colD;
	}

	public String getColE() {
		String returnVal = (colE != null) ? colE : "";
		return returnVal;
	}

	public void setColE(String colE) {
		this.colE = colE;
	}

	public String getColF() {
		String returnVal = (colF != null) ? colF : "";
		return returnVal;
	}

	public void setColF(String colF) {
		this.colF = colF;
	}

	public String getColG() {
		String returnVal = (colG != null) ? colG : "";
		return returnVal;
	}

	public void setColG(String colG) {
		this.colG = colG;
	}

	public String getColH() {
		String returnVal = (colH != null) ? colH : "";
		return returnVal;
	}

	public void setColH(String colH) {
		this.colH = colH;
	}

	public String getColI() {
		String returnVal = (colI != null) ? colI : "";
		return returnVal;
	}

	public void setColI(String colI) {
		this.colI = colI;
	}

	public String getColJ() {
		String returnVal = (colJ != null) ? colJ : "";
		return returnVal;
	}

	public void setColJ(String colJ) {
		this.colJ = colJ;
	}

	public String getColK() {
		String returnVal = (colK != null) ? colK : "";
		return returnVal;
	}

	public void setColK(String colK) {
		this.colK = colK;
	}

	public String getColL() {
		String returnVal = (colL != null) ? colL : "";
		return returnVal;
	}

	public void setColL(String colL) {
		this.colL = colL;
	}

	public ArrayList<StoredProcComplianceGetGaps> get(String reportId) {
		ArrayList<StoredProcComplianceGetGaps> objReportList = load(reportId);
		return objReportList;
	}

	public static ArrayList<StoredProcComplianceGetGaps> getReportGaps(String reportId) {
		ArrayList<StoredProcComplianceGetGaps> objStoredProcComplianceGetGaps = new StoredProcComplianceGetGaps().get(reportId);
		return objStoredProcComplianceGetGaps;
	}

	private StoredProcComplianceGetGaps loadFrom(ResultSet resultSet) {
		StoredProcComplianceGetGaps objGap = new StoredProcComplianceGetGaps();
		try {
			objGap.setRowNumber(resultSet.getString("RowNumber"));
			objGap.setColA(resultSet.getString("ColA"));
			objGap.setColB(resultSet.getString("ColB"));
			objGap.setColC(resultSet.getString("ColC"));
			objGap.setColD(resultSet.getString("ColD"));
			objGap.setColE(resultSet.getString("ColE"));
			objGap.setColF(resultSet.getString("ColF"));
			objGap.setColG(resultSet.getString("ColG"));
			objGap.setColH(resultSet.getString("ColH"));
			objGap.setColI(resultSet.getString("ColI"));
			objGap.setColJ(resultSet.getString("ColJ"));
			objGap.setColK(resultSet.getString("ColK"));
			objGap.setColL(resultSet.getString("ColL"));

		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}

		return objGap;
	}

	public ArrayList<StoredProcComplianceGetGaps> load(String reportId) {
		ArrayList<StoredProcComplianceGetGaps> objGapList = new ArrayList<StoredProcComplianceGetGaps>();

		try {
			CallableStatement cs = connection.prepareCall("{CALL Compliance_Investigation_Assessment_GetGAPData(?)}");
			cs.setString(1, reportId);
			boolean isRS = cs.execute();
			boolean keepLooping = true;

			while (keepLooping) {
				isRS = cs.getMoreResults();

				// This checks to see if there are no more resultsets.
				if (isRS == false && cs.getUpdateCount() == -1) {
					break;
				}
				if (!isRS) {
					continue;
				}
				resultSet = cs.getResultSet();
				if (resultSet == null) {
					continue;
				} else {
					// No reason to loop anymore once the non-empty resultset is found.
					keepLooping = false;
				}
				while (resultSet.next()) {
					StoredProcComplianceGetGaps objGap = loadFrom(resultSet);
					objGapList.add(objGap);
				}
				resultSet.close();

			}
			cs.close();

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objGapList;
	}

}
