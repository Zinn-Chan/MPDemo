package com.itheima;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.List;

@SpringBootTest
class MpDemoApplicationTests {
	@Autowired
	private UserDao userDao;

	@Test
	void testSave(){
		User user=new User();
		user.setName("黑马程序员");
		user.setPassword("itheima");
		user.setAge(12);
		user.setTel("4006184000");
		userDao.insert(user);
		testGetAll();
	}

	@Test
	void testGetAll() {
//		QueryWrapper<User> wrapper=new QueryWrapper();
//		wrapper.lt("age",18);
//		wrapper.lambda().lt(User::getAge,10);
		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
//		lqw.lt(User::getAge,30);
//		lqw.gt(User::getAge,10);
		lqw.lt(User::getAge,30).gt(User::getAge,10);
		List<User> users = userDao.selectList(lqw);
		System.out.println(users);
	}

	@Test
	void testGetByPage(){
		IPage page=new Page(1,2);
		userDao.selectPage(page,null);
		System.out.println("当前页码值："+page.getCurrent());
		System.out.println("每页显示数："+page.getSize());
		System.out.println("一共多少页："+page.getPages());
		System.out.println("一共多少条数据："+page.getTotal());
		System.out.println("数据："+page.getRecords());
	}

}
