package com.example.demo;

import com.example.demo.cache.MemoryCache;
import com.example.demo.model.entity.Object;
import com.example.demo.model.entity.ObjectItem;
import com.example.demo.service.IObjectService;
import com.example.demo.service.impl.Status;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@SpringBootTest(classes = SpringDemoApplication.class)
@ActiveProfiles("local")
class SpringDemoApplicationTests {

	@Resource
	private IObjectService objectService;

	private static final Logger logger = LoggerFactory.getLogger(SpringDemoApplicationTests.class);
	private static final int objectId = 1;
	@Test
	void createTest() {
		try {
			Object object = createDummyObjects(objectId);
			String result = objectService.createObject(object);
			Assertions.assertEquals(result, Status.INSERT_SUCCESSFULLY);
		} catch (Exception e) {
			logger.error(String.format("createTest failed {}" + e));
		}
	}

	@Test
	public void getObjectByIdTest() {
		try {
			Object object = objectService.getObjectById(objectId);
			Assertions.assertNotNull(object);
		} catch (Exception e) {
			logger.error(String.format("getObjectByIdTest failed {}" + e));
		}
	}

	@Test
	public void updateOrderTest() {
		Object object = objectService.getObjectById(objectId);
		if (Objects.isNull(object)) {
			return;
		}
		object.setName(object.getName() + "1");
		try {
			Boolean result = objectService.updateObject(object);
			Assertions.assertEquals(result, true);
			Assertions.assertEquals(object, objectService.getObjectById(objectId));
		} catch (Exception e) {
			logger.error(String.format("updateOrderTest failed {}" + e));
		}
	}

	@Test
	public void getCacheOrderByIdTest() {
		Object object = objectService.getObjectById(objectId);
		if (object == null) {
			return;
		}
		try {
			Object cacheObject = MemoryCache.getInstance().getObjectInCache(objectId);
			Assertions.assertNotNull(object);
		} catch (Exception e) {
			logger.error(String.format("getCacheOrderByIdTest failed {}" + e));
		}
	}

	public Object createDummyObjects(Integer id) {
		Object object = new Object();
		object.setId(id);
		object.setName("DummyObject1");

		List<ObjectItem> objectItems = new LinkedList<>();
		ObjectItem orderItem1 = new ObjectItem();
		orderItem1.setItemId(id * 100 + 1);
		orderItem1.setItemName("Item1");

		ObjectItem orderItem2 = new ObjectItem();
		orderItem2.setItemId(id * 100 + 2);
		orderItem2.setItemName("Item2");

		ObjectItem orderItem3 = new ObjectItem();
		orderItem3.setItemId(id * 100 + 3);
		orderItem3.setItemName("Item3");
		objectItems.add(orderItem1);
		objectItems.add(orderItem2);
		objectItems.add(orderItem3);
		object.setObjectItems(objectItems);
		return object;
	}

}
