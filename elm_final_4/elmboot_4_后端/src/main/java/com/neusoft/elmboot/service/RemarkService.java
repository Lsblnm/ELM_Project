package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.po.Business;
import com.neusoft.elmboot.po.Remark;

public interface RemarkService {
	
	 public List<Remark> listRemarksByBussinessId(Integer businessId);
	 public int saveRemarks(Remark remark);
	 public List<Remark> removeAllRemarksByUserId(String userId);
	 public List<Remark> removeOneRemarksByUserId(String userId, int remarkId);
}
