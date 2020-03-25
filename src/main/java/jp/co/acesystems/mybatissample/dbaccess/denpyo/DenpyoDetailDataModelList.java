package jp.co.acesystems.mybatissample.dbaccess.denpyo;

import java.util.ArrayList;
import java.util.List;

public class DenpyoDetailDataModelList {
	private List<DenpyoDetailDataModel> list;
	
	DenpyoDetailDataModelList() {
		this.list = new ArrayList<>();
	}
	
	
	DenpyoDetailDataModelList(List<DenpyoDetailDataModel> list) {
		this.list = list;
	}
	
	public int size() {
		return this.list.size();
	}


	public List<DenpyoDetailDataModel> getList() {
		return list;
	}
}
