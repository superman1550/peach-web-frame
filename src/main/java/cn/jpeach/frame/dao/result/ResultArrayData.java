package cn.jpeach.frame.dao.result;

import java.io.Serializable;
import java.util.List;

public class ResultArrayData implements Serializable {
	private static final long serialVersionUID = -5010122139396325585L;
	private List<ResultRowData> originalrows;
	private List<ResultRowData> deletesRows;

	public List<ResultRowData> getOriginalrows() {
		return originalrows;
	}

	public void setOriginalrows(List<ResultRowData> originalrows) {
		this.originalrows = originalrows;
	}

	public List<ResultRowData> getDeletesRows() {
		return deletesRows;
	}

	public void setDeletesRows(List<ResultRowData> deletesRows) {
		this.deletesRows = deletesRows;
	}

}
