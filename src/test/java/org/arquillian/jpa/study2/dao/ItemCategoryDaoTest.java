package org.arquillian.jpa.study2.dao;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

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
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ItemCategoryDaoTest {

	@Inject
	private BidDao bidDao;

	@Inject
	private ItemDao itemDao;

	@Inject
	private CategoryDao categoryDao;

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class).addAsWebInfResource(new File("src/main/webapp", "beans.xml"))
				.addAsResource("META-INF/persistence.xml")
				.addClasses(Item.class, Bid.class, ItemDao.class, BidDao.class, Category.class, CategoryDao.class);

	}

	private static Item laptop;
	private static Item screen;
	private static Bid bidOnLaptop;
	private static Bid bidOnScreen;

	private static Item dress;
	private static Item chair;
	private static Category clothing;
	private static Category furniture;
	private static Category red;
	private static Category blue;

	private static boolean isInit = false;

	//// @Before
	// public void init_() {
	// laptop=new Item("laptop");
	// itemDao.create(laptop);
	// bidOnLaptop=new Bid();
	// bidDao.create(bidOnLaptop, laptop);
	// screen=new Item("screen");
	// itemDao.create(screen);
	// bidOnScreen=new Bid();
	// bidDao.create(bidOnScreen, screen);
	//
	// clothing=new Category("clothing");
	// categoryDao.create(clothing);
	// furniture=new Category("furniture");
	// categoryDao.create(furniture);
	// red=new Category("red");
	// categoryDao.create(red);
	// blue=new Category("blue");
	// categoryDao.create(blue);
	//
	// Set<Category>categories=new HashSet<>();
	// categories.add(clothing);
	// categories.add(red);
	// dress=new Item("dress");
	// itemDao.create(dress, categories);
	//
	// categories=new HashSet<>();
	// categories.add(furniture);
	// categories.add(blue);
	// chair=new Item("chair");
	// itemDao.create(chair, categories);
	//
	//
	//
	// }
	//// @Before
	// public void init__() {
	//
	// clothing=new Category("clothing");
	// furniture=new Category("furniture");
	// red=new Category("red");
	// blue=new Category("blue");
	//
	// dress=new Item("dress");
	// dress.addCategory(clothing);
	// dress.addCategory(red);
	// itemDao.create(dress);
	//
	// chair=new Item("chair");
	// chair.addCategory(furniture);
	// chair.addCategory(blue);
	// itemDao.create(chair);
	//
	//
	//
	// }
	//

	// works with
	// class Item{@ManyToMany(cascadetype=CascadeType.ALL)Set<Categories>categories}
	// of,
	// class Item{@ManyToMany(cascadetype={CascadeType.MERGE,CascadeType.PERSIST})categories}
//	 @Before
	public void initc() {
		if (isInit == false) {
			isInit = true;

			clothing = new Category();
			clothing.setName("clothing");
			furniture = new Category();
			furniture.setName("furniture");
			red = new Category();
			red.setName("red");
			blue = new Category();
			blue.setName("blue");

			dress = new Item();
			dress.setName("dress");
			chair = new Item();
			chair.setName("chair");
			
			dress.addCategory(clothing);
			dress.addCategory(red);
			chair.addCategory(furniture);
			chair.addCategory(blue);
			
			itemDao.create(dress);
			itemDao.create(chair);
		}
	}
	
	// class Item{ @ManyToMany Set<Category>categories}
	// class Category{@ManyToMany(mappedBy="categories")Set<Item>items}
	// OK,
//	@Before
	public void init() {
		if (isInit == false) {
			isInit = true;

			clothing = new Category();
			clothing.setName("clothing");
			furniture = new Category();
			furniture.setName("furniture");
			red = new Category();
			red.setName("red");
			blue = new Category();
			blue.setName("blue");

			categoryDao.create(clothing);
			categoryDao.create(furniture);
			categoryDao.create(red);
			categoryDao.create(blue);

			dress = new Item();
			dress.setName("dress");
			dress.addCategory(clothing);
			dress.addCategory(red);
			
			itemDao.create(dress);

			chair = new Item();
			chair.setName("chair");
			chair.addCategory(furniture);
			chair.addCategory(blue);
			
			itemDao.create(chair);

		}

	}
	
	// class Item{ @ManyToMany Set<Category>categories}
	// class Category{@ManyToMany(mappedBy="categories")Set<Item>items}
	// ERR	,
//	@Before
	public void inita() {
		if (isInit == false) {
			isInit = true;

			clothing = new Category();
			clothing.setName("clothing");
			furniture = new Category();
			furniture.setName("furniture");
			red = new Category();
			red.setName("red");
			blue = new Category();
			blue.setName("blue");

			dress = new Item();
			dress.setName("dress");
			dress.addCategory(clothing);
			dress.addCategory(red);
			
			itemDao.create(dress);

			chair = new Item();
			chair.setName("chair");
			chair.addCategory(furniture);
			chair.addCategory(blue);
			
			itemDao.create(chair);
			
			categoryDao.create(clothing);
			categoryDao.create(furniture);
			categoryDao.create(red);
			categoryDao.create(blue);

		}

	}
	
	// class Item{ @ManyToMany Set<Category>categories}
	// class Category{@ManyToMany(mappedBy="categories")Set<Item>items}
	@Before
	public void initb() {
		if (isInit == false) {
			isInit = true;

			clothing = new Category();
			clothing.setName("clothing");
			furniture = new Category();
			furniture.setName("furniture");
			red = new Category();
			red.setName("red");
			blue = new Category();
			blue.setName("blue");

			dress = new Item();
			dress.setName("dress");
			chair = new Item();
			chair.setName("chair");
			
			dress.addCategory(clothing);
			dress.addCategory(red);
			chair.addCategory(furniture);
			chair.addCategory(blue);
// of,
//			clothing.addItem(dress);
//			red.addItem(dress);
//			furniture.addItem(chair);
//			blue.addItem(chair);
			
			categoryDao.create(clothing);
			categoryDao.create(furniture);
			categoryDao.create(red);
			categoryDao.create(blue);
			
			itemDao.create(dress);
			itemDao.create(chair);

		}

	}
	
	
	// class Item{@ManyToMany(mappedBy="items")Set<Category>categories}
	// class Category{@ManyToMany Set<Item>items}
//	@Before
	public void init2() {
		if (isInit == false) {
			isInit = true;

			clothing = new Category();
			clothing.setName("clothing");
			furniture = new Category();
			furniture.setName("furniture");
			red = new Category();
			red.setName("red");
			blue = new Category();
			blue.setName("blue");

			dress = new Item();
			dress.setName("dress");
			dress.addCategory(clothing);
			dress.addCategory(red);
			
			itemDao.create(dress);

			chair = new Item();
			chair.setName("chair");
			chair.addCategory(furniture);
			chair.addCategory(blue);
			
			itemDao.create(chair);
			
			categoryDao.create(clothing);
			categoryDao.create(furniture);
			categoryDao.create(red);
			categoryDao.create(blue);

			

		}

	}
	
	// werkt met 
	// class Item{@ManyToMany(mappedBy="items")Set<Category>categories}
	// class Category{@ManyToMany Set<Item>items}
//	@Before
	public void init2a() {
		if (isInit == false) {
			isInit = true;

			clothing = new Category();
			clothing.setName("clothing");
			furniture = new Category();
			furniture.setName("furniture");
			red = new Category();
			red.setName("red");
			blue = new Category();
			blue.setName("blue");

			dress = new Item();
			dress.setName("dress");

			chair = new Item();
			chair.setName("chair");
			
			
			clothing.addItem(dress);
			red.addItem(dress);
			furniture.addItem(chair);
			blue.addItem(chair);
// of,			
//			dress.addCategory(clothing);
//			dress.addCategory(red);
//			chair.addCategory(furniture);
//			chair.addCategory(blue);
			
			itemDao.create(dress);
			itemDao.create(chair);
			
			categoryDao.create(clothing);
			categoryDao.create(furniture);
			categoryDao.create(red);
			categoryDao.create(blue);

			

		}

	}


	@After
	public void fini() {
		// bidDao.delete(bidOnLaptop);
		// itemDao.delete(laptop);
		// bidDao.delete(bidOnScreen);
		// itemDao.delete(screen);
	}

	@Test
	public void should_be_deployed() {
		Assert.assertNotNull(bidDao);
		Assert.assertNotNull(itemDao);
		Assert.assertNotNull(categoryDao);
		System.out.println("test class inst: " + this);
	}

	@Test
	public void size_collections() {
		Assert.assertEquals(2, dress.getCategories().size());
		Assert.assertEquals(2, chair.getCategories().size());
		Assert.assertEquals(1, red.getItems().size());
		for (Item item : red.getItems()) {
			Assert.assertEquals("dress", item.getName());
			System.out.println("!!!!!!!!!!!!!! " + item + "hash: " + item.hashCode() + ", dress:" + dress + "hash: "
					+ dress.hashCode() + "instanceof Item: " + (dress instanceof Item));
			Assert.assertTrue(item.equals(dress));
			Set<Item> items = new HashSet<>();
			items.add(item);
			Assert.assertTrue(items.contains(item));
			Assert.assertTrue(red.getItems().contains(item));
		}

		Assert.assertTrue(red.getItems().contains(dress));
		Assert.assertTrue(blue.getItems().contains(chair));
		System.out.println("test class inst: " + this);

	}

	@Test
	public void are_persisted() {
		Assert.assertNotNull(dress.getId());
		System.out.println(dress.getId());
		System.out.println("test class inst: " + this);

	}
}
