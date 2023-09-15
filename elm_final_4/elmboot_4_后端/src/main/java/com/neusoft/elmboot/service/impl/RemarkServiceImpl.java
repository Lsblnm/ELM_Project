package com.neusoft.elmboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.BusinessMapper;
import com.neusoft.elmboot.mapper.RemarkMapper;
import com.neusoft.elmboot.po.Remark;
import com.neusoft.elmboot.service.RemarkService;
import com.neusoft.elmboot.util.CommonUtil;

@Service
public class RemarkServiceImpl implements RemarkService{

	 @Autowired
	 private RemarkMapper remarkMapper;
	
	@Override
	public List<Remark> listRemarksByBussinessId(Integer businessId) {
		
		return remarkMapper.listRemarksByBussinessId(businessId);
	}

	@Override
	public int saveRemarks(Remark remark) {
		remark.setRemarkDate(CommonUtil.getCurrentDate());
		return remarkMapper.saveRemarks(remark);
	}
	
	@Override
	public List<Remark> removeAllRemarksByUserId(String userId) {
		return remarkMapper.removeAllRemarksByUserId(userId);
	}
	
	@Override
	public List<Remark> removeOneRemarksByUserId(String userId, int remarkId) {
		return remarkMapper.removeOneRemarksByUserId(userId, remarkId);
	}
	
}
