package frequentstrings;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class FrequentStringsTest {

	@Test
	public void test1() {
		FrequentStrings fs = new FrequentStrings(new String[] { "b", "b", "b", "c", "a", "a" });
		assertEquals(Arrays.asList("a", "b", "c"), fs.stringsSorted());
		try {
			assertEquals("b", fs.getMode());
		} catch (Exception e) {
			fail("No exception expected");
		}
	}

	@Test
	public void test2() {
		FrequentStrings fs = new FrequentStrings(new String[] { "a", "a", "b", "c", "b", "b" });
		fs.remove("b");
		assertEquals(Arrays.asList("a", "b", "c"), fs.stringsSorted());
	}

	@Test
	public void test3() {
		FrequentStrings fs = new FrequentStrings();
		fs.add("shrdlu");
		assertTrue(fs.contains("shrdlu"));
	}

	@Test
	public void test4() {
		FrequentStrings fs = new FrequentStrings();
		fs.add("shrdlu");
		fs.add("shrdlu");
		assertEquals(2, fs.getCount("shrdlu"));
		fs.remove("shrdlu");
		assertTrue(fs.contains("shrdlu"));
		assertEquals(1, fs.getCount("shrdlu"));
	}

	@Test
	public void test5() {
		FrequentStrings fs1 = new FrequentStrings();
		fs1.add("etaoin");
		fs1.add("shrdlu");
		FrequentStrings fs2 = new FrequentStrings();
		fs2.add("shrdlu");
		fs2.add("etaoin");
		assertTrue(fs1.equals(fs2));
		assertTrue(fs2.equals(fs1));
		assertTrue(fs1.similar(fs2));
		assertTrue(fs1.hashCode() == fs2.hashCode());
	}

	@Test
	public void test6() {
		FrequentStrings fs1 = new FrequentStrings();
		fs1.add("etaoin");
		fs1.add("shrdlu");
		FrequentStrings fs2 = new FrequentStrings();
		fs2.add("shrdlu");
		fs2.add("etaoin");
		fs2.add("shrdlu");
		assertTrue(!fs1.equals(fs2));
		assertTrue(fs2.similar(fs1));
	}

	@Test
	public void test7() {
		FrequentStrings fs = new FrequentStrings(
				new String[] { "lorem", "ipsum", "lorem", "ipsum", "dolor", "et", "summit", "ipsum" });
		try {
			assertEquals("ipsum", fs.getMode());
		} catch (Exception e) {
			fail("No exception expected");
		}
		assertEquals(Arrays.asList("dolor", "et", "ipsum", "lorem", "summit"), fs.stringsSorted());
	}

	@Test
	public void test8() {
		FrequentStrings fs = new FrequentStrings();
		try {
			fs.getMode();
			fail("Should have seen an exception");
		} catch (EmptyObjectException e) {
			// success; empty object should throw exception with getMode
			assertTrue(true);
		}
	}

	@Test
	public void test9() {
		FrequentStrings fs1 = new FrequentStrings();
		fs1.add("abc");
		FrequentStrings fs2 = new FrequentStrings();
		fs2.add("abc");
		fs2.add("abc");
		assertTrue(fs1.similar(fs2));
		fs1.remove("abc");
		assertTrue(!fs1.contains("abc"));
		assertTrue(!fs1.similar(fs2));
	}
}
