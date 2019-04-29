package org.arquillian.jpa.study2.dao;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.arquillian.jpa.study2.model.Bid;
import org.arquillian.jpa.study2.model.Category;
import org.arquillian.jpa.study2.model.Item;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@Ignore
public class SetDaoTest {

	
	@Inject 
	private CategoryDao categoryDao;

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
				.addAsWebInfResource(new File("src/main/webapp","beans.xml"))
				.addAsResource("META-INF/persistence.xml")
				.addClasses(Category.class,CategoryDao.class,Item.class,Bid.class);
				
		
	}
	
	
	private Category clothing;
	private Category furniture;
	private Category red;
	private Category blue;
	
	
	
	@Before
	public  void init() {
		
		clothing=new Category("clothing");
//		categoryDao.create(clothing);
		furniture=new Category("furniture");
//		categoryDao.create(furniture);
		red=new Category("red");
//		categoryDao.create(red);
		blue=new Category("blue");
//		categoryDao.create(blue);
		
		Set<Category>categories=new HashSet<>();
		categories.add(clothing);
		categories.add(red);
		categoryDao.create(categories);
		
		
		
		
	}
	
	@After
	public void fini() {
//		bidDao.delete(bidOnLaptop);
//		itemDao.delete(laptop);
//		bidDao.delete(bidOnScreen);
//		itemDao.delete(screen);
	}
	
	

	@Test
	public void should_be_deployed() {
		Assert.assertNotNull(categoryDao);
		
	}
	
//	@Test
	public void foo() {
		
	}
}
