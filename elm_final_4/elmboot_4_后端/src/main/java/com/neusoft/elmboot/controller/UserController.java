package com.neusoft.elmboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@RestController
@RequestMapping("/UserController")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/getUserByIdByPass")
	public User getUserByIdByPass(User user) throws Exception {
		return userService.getUserByIdByPass(user);
	}

	@RequestMapping("/getUserById")
	public int getUserById(User user) throws Exception {
		return userService.getUserById(user.getUserId());
	}

	@RequestMapping("/saveUser")
	public int saveUser(User user) throws Exception {
		return userService.saveUser(user);
	}
	/*@RequestMapping("/updateUserImg")
	public int updateUserImg(String userId,MultipartFile file) {
		

         // 检查文件是否为空
         if (file.isEmpty()) {
             throw new IllegalArgumentException("上传的文件为空");
         }

         // 获取原始文件名
         String originalFilename = file.getOriginalFilename();
         System.out.println(originalFilename);

         // 保存文件到服务器或其他数据存储的逻辑
         // ...

         // 更新用户的userImg字段为文件路径或文件名
         return userService.updateUserImg(userId,originalFilename);
	}*/
	@RequestMapping("/updatePasswordByuserId")
	public int updatePasswordByuserId(String userId,String password) {
		return userService.updatePasswordByuserId(userId,password);
	}
}