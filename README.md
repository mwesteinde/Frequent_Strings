**Exercise 8 / CPEN 221 / Fall 2019**

Frequent Strings
=========

### General Instructions

+ You should try to complete the assigned tasks within 75 minutes.
+ Take your time to read the question.

### Submission Instructions

+ Submit your work to the Github classroom repository that was created for your.
+ **Do not alter the directory/folder structure. You should retain the structure as in this repository.**
+ Do not wait until the last minute to push your work to Github. It is a good idea to push your work at intermediate points as well. _I would recommend that you get your Git and Github workflow set up at the start._

### Honour Code

By submitting your work to Github you agree to the following:

+ You did not consult with any other person in completing the activity.
+ You did not aid any other person in the class in completing their activity.
+ If you consulted any external sources, such as resources available on the World Wide Web, in completing the activity then you have cited the source. (You do not need to cite class notes or Sun/Oracle Java documentation.)
+ You are not aware of any infractions of the honour code for this activity.

> Violations of this honour code will be treated as a case of academic misconduct and will dealt with under UBC policies governing such issues. A consequence of this may be to nullify this exam for everyone that submits work for grading!

### Question: Frequent Strings
> The skeleton source code for this question is in the package `frequentstrings`. You have to implement the required method in the class `FrequentStrings`. You may import the provided code as a Gradle project in Eclipse.

A `FrequentStrings` type allows us to store `Strings`s (including duplicates) and provide some operations on the collection.

Here are the essential operations that a `FrequentStrings` supports:

1. **Creators**
	1. Create an empty `FrequentStrings` object.
	2. Create a `FrequentStrings` object using an array of `Strings`s as initial values.

2. **Mutators**
	1. Add a given `String`.
	2. Remove exactly one occurrence of a given `String`.

3. **Observers**
	1. Check if a `String` is in the object.
	2. Return a count of the number of occurrences of a `String` in the object.
	3. Return the mode (the most frequent `String`) in the object. If multiple `String`s have the same highest count, then any one `String` can be returned. If the object has no `String` then an `EmptyObjectException` must be thrown.
	3. Return a sorted `List` of **distinct** `String`s in the object. Changes to the returned `List` should not affect the `FrequentStrings` object.
	4. Verify equality: two `FrequentStrings` are equal if and only if each `String` occurs the same number of times in each object.
	5. A suitable hash code operation.
	6. Similarity: Two `FrequentStrings` objects are similar if they contain the same set of `String`s (although counts may be different)

#### Specifications

```
// Create an empty FrequentStrings object
FrequentStrings()

// Create a FrequentStrings object using the elements in
// the provided array
// requires: seedArray is not null
FrequentStrings(String[] seedArray)

// add s to the FrequentStrings object
void add(String s)

// remove exactly one occurrence of s
// from the FrequentStrings object
// if s does not exist in the object then do nothing
void remove(String s)

// return true if this FrequentStrings object
// contains s and false otherwise
boolean contains(String s)

// get the count the occurrences of String s in the object
// if s is not in the object then return 0
int getCount(String s)

// get the String that is mode of the Strings in the object
// the mode is the most frequent string
// if multiple Strings have the same and highest frequency
// then return any such String
// if the object has no Strings then throw an EmptyObjectException
String getMode() throws EmptyObjectException

// return a sorted List of distinct Strings in the object
// Changes to the returned List should not mutate
// the FrequentStrings object
// and the returned List should be empty
// if the object has no Strings
List<String> stringsSorted()

// are two FrequentStrings objects similar?
// true if the two objects contain the same Strings
// and false otherwise
// the objects need not have the same counts for the Strings
boolean similar(FrequentStrings other)
```

Although not listed above, `equals()` and `hashCode()` should be implemented.

#### Test Cases

```java
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
	}
	catch (EmptyObjectException e) {
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
```

### What Should You Implement / Guidelines

+ You should implement all the methods that are indicated with `TODO`.
+ Passing the provided tests is the minimum requirement. Use the tests to identify cases that need to be handled. Passing the provided tests is *not sufficient* to infer that your implementation is complete and that you will get full credit. Additional tests will be used to evaluate your work. The provided tests are to guide you.
+ You can implement additional helper methods if you need to but you should keep these methods `private` to the appropriate classes.
+ You do not need to implement new classes **unless asked to**.
+ You can use additional standard Java libraries by importing them.
+ Do not throw new exceptions unless the specification for the method permits exceptions.

### Answers to FAQs

#### Can I consult Java documentation and other Internet-based sources?

Yes, you can. The point of this test is not to demonstrate mastery over syntax but that you can solve a problem in a reasonable amount of time with reasonable resources.

*If you find useful information online outside the official Java documentation and the course material, you must cite the source. You should do so by adding comments in your source code.*

Naturally you are expected to adhere to all of the course and UBC policies on academic integrity.

#### Isn't one hour and a bit more too short to produce a working implementation?

The questions are straightforward, and these are not very different from what one might sometimes encounter on a job interview (for example). The difference is that you get less time during an interview (10-15 minutes) with no access to additional resources. So the time allotted is reasonable in that regard and I am expecting that everyone will be able to clear this bar. The goal is that it is possible to say, at a minimal level, what everyone who completes this course can achieve.

#### Why am I not guaranteed full credit if my implementation passes all the provided tests?

It is easy to develop an implementation that passes the provided tests and not much else. A good-faith implementation that passes all the provided tests is very likely to pass other tests too.
