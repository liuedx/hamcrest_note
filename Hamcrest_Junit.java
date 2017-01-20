junit:
assertEquals(expected, actual);


hamcrest:
assertThat(acutal, equalTo(expected));

Hamcrest Example:
@Test
public void test_failed() throws Exception {
	//Given
	Integer number = 7;
	
	//Then
	assertThat(number, greaterThan(10));
}

Hamcrest will give detailed information about the failture:
java.lang.AssertionError:
Expected: a value greater than <10>
     but: <7> was less than <10>
	 
//Bring in all the matchers with a statics import
import static org.hamcrest.Matchers.*

//However you run the risk of a naming clash if you do this because both Hamcrest and Mockito provide a static any() method so it is recommended to import each static method you use individually.

//Hamcrest Matchers Library broken down into 2 broad categories
//1. Matchers which work to test values(Simples)
//2. Matchers which work to combine other Matchers(Aggregate)

any()
//Matches any varialbe of the given type
@Test
	public void test_any() throws Exception{
		//Given
		String myString ="hello";
		
		//Then
		assertThat(myString, is(any(String.class)));
	}
	
anything()
//Matches anything
@Test
	public void test_anything() throws Exception{
		//Given
		String myString = "hello";
		Integer four = 4;
		
		//Then
		assertThat(myString, is(anything()));
		assertThat(four, is(anything()));
	}
	
arrayContaining()
//Various matchers for Arrays, length of the array must match the number of matchers, and their order is important.
//Does the array contain all given items in the order in which they are input to the matcher?

@Test
	public void test_arrayContaining_items() throws Exception{
		//Given
		String[] strings = {"why", "hello", "there"};
		
		//Then
		assertThat(strings, is(arrayContaining("why", "hello", "there")));
	}

//Does the array contain items which match the input list of matchers, in order?

@Test
	public void test_arrayContaining_list_of_matchers() throws Exception {
		// Given
		String[] strings = {"why", "hello", "there"};
		
		// Then
		java.util.List<org.hamcrest.Matcher<? super String>> itemMatchers = new ArrayList<>();
		itemMatchers.add(equalTo("why"));
		itemMatchers.add(equalTo("hello"));
		itemMatchers.add(endsWith("here"));
		assertThat(strings, is(arrayContaining(itemMatchers)));
	}
	
//Does the array contain items which match the input vararg matchers, in order?

@Test
	public void test_arrayContaining_matchers() throws Exception {
		// Given
		String[] strings = {"why", "hello", "there"};
		
		// Then
		assertThat(strings, is(arrayContaining(startsWith("wh"), equalTo("hello"), endsWith("here"))));
	}
	
arrayContainingInAnyOrder()
//Various matchers for Arrays, length of the array must match the number of matchers, but their order is not important.
//Does the array contain all the given items?

@Test
	public void test_arrayContainingInAnyOrder_items() throws Exception {
		// Given
		String[] strings = { "why", "hello", "there" };

		// Then
		assertThat(strings, is(arrayContainingInAnyOrder("hello", "there", "why")));
	}

//Does the array contain items which match the input collection of Matchers?
@Test
	public void test_arrayContainingInAnyOrder_collection_of_matchers() throws Exception {
		// Given
		String[] strings = { "why", "hello", "there" };

		// Then
		Set<org.hamcrest.Matcher<? super String>> itemMatchers = new HashSet<>();
		itemMatchers.add(equalTo("hello"));
		itemMatchers.add(equalTo("why"));
		itemMatchers.add(endsWith("here"));
		assertThat(strings, is(arrayContainingInAnyOrder(itemMatchers)));
	}

//Does the array contain items which match the input vararg matchers?
@Test
	public void test_arrayContainingInAnyOrder_matchers() throws Exception {
		// Given
		String[] strings = { "why", "hello", "there" };

		// Then
		assertThat(strings, is(arrayContainingInAnyOrder(endsWith("lo"), startsWith("the"), equalTo("why"))));
	}
	
arrayWithSize()
//Various matchers to check if an array is of a certain length.
//Does the input array have exactly the specified length?

@Test
	public void test_arrayWithSize_exact() throws Exception {
		// Given
		String[] strings = { "why", "hello", "there" };

		// Then
		assertThat(strings, is(arrayWithSize(3)));
	}
	
//Does the input array have a length which matches the specified matcher?
@Test
	public void test_arrayWithSize_matcher() throws Exception {
		// Given
		String[] strings = { "why", "hello", "there" };

		// Then
		assertThat(strings, is(arrayWithSize(greaterThan(2))));
	}
	
closeTo()
//Matcher which can be used with either Double or BigDecimal to check if a value is within a specified error margin of an expected value.

Double

@Test
	public void test_closeTo_double() throws Exception {
		// Given
		Double testValue = 6.3;

		// Then
		assertThat(testValue, is(closeTo(6, 0.5)));
	}

BigDecimal

@Test
	public void test_closeTo_bigDecimal() throws Exception {
		// Given
		BigDecimal testValue = new BigDecimal(324.0);

		// Then
		assertThat(testValue, is(closeTo(new BigDecimal(350), new BigDecimal(50))));
	}

comparesEqualTo()
//Creates a Comparable matcher which attempts to match the input matcher value using the compareTo() method of the input value. The matcher will match if the compareTo() method returns 0 for the input matcher value, otherwise it would not match.

@Test
	public void test_comparesEqualTo() throws Exception {
		// Given
		String testValue = "value";

		// Then
		assertThat(testValue, comparesEqualTo("value"));
	}
	
contains()
//Various matchers which can be used to check if an input Iterable contains values. The order of the values is important and the number of items in the Iterable must match the number of values being tested.
//Does the input list contain all of the values, in order?

@Test
	public void test_contains_items() throws Exception {
		// Given
		List<String> strings = Arrays.asList("why", "hello", "there");

		// Then
		assertThat(strings, contains("why", "hello", "there"));
	}
	
//Does the input list contain items which match all of the matchers in the input matchers list, in order?
@Test
	public void test_contains_list_of_matchers() throws Exception {
		// Given
		List<String> strings = Arrays.asList("why", "hello", "there");

		// Then
		List<org.hamcrest.Matcher<? super String>> matchers = new ArrayList<>();
		matchers.add(startsWith("wh"));
		matchers.add(endsWith("lo"));
		matchers.add(equalTo("there"));
		assertThat(strings, contains(matchers));
	}

//Does the input list contain only one item which matches the input matcher?
@Test
	public void test_contains_single_matcher() throws Exception {
		// Given
		List<String> strings = Arrays.asList("hello");

		// Then
		assertThat(strings, contains(startsWith("he")));
	}
	
//Does the input list contain items which match all of the matchers in the input vararg matchers, in order?
@Test
	public void test_contains_matchers() throws Exception {
		// Given
		List<String> strings = Arrays.asList("why", "hello", "there");

		// Then
		assertThat(strings, contains(startsWith("why"), endsWith("llo"), equalTo("there")));
	}
	
containsInAnyOrder()
//Various matchers which can be used to check if an input Iterable contains values. The order of the values is not important but the number of items in the Iterable must match the number of values being tested.
//Does the input list contain all of the values, in any order?

@Test
	public void test_containsInAnyOrder_items() throws Exception {
		// Given
		List<String> strings = Arrays.asList("why", "hello", "there");

		// Then
		assertThat(strings, containsInAnyOrder("hello", "there", "why"));
	}

//Does the input list contain items which match all of the matchers in the input matchers list, in any order?
@Test
	public void test_containsInAnyOrder_list_of_matchers() throws Exception {
		// Given
		List<String> strings = Arrays.asList("why", "hello", "there");

		// Then
		List<org.hamcrest.Matcher<? super String>> matchers = new ArrayList<>();
		matchers.add(equalTo("there"));
		matchers.add(startsWith("wh"));
		matchers.add(endsWith("lo"));		
		assertThat(strings, containsInAnyOrder(matchers));
	}

//Does the input list contain items which match all of the matchers in the input vararg matchers, in any order?
@Test
	public void test_containsInAnyOrder_matchers() throws Exception {
		// Given
		List<String> strings = Arrays.asList("why", "hello", "there");

		// Then
		assertThat(strings, containsInAnyOrder(endsWith("llo"), equalTo("there"), startsWith("why")));
	}

containsString()
//Matcher which matches if the String under test contains the given substring.

@Test
	public void test_containsString() throws Exception {
		// Given
		String testValue = "value";

		// Then
		assertThat(testValue, containsString("alu"));
	}
	
empty()
//Matcher which matches if an input Collections isEmpty() method returns true.
@Test
	public void test_empty() throws Exception {
		// Given
		Set<String> testCollection = new HashSet<>();

		// Then
		assertThat(testCollection, is(empty()));
	}

emptyArray()
//Matcher which matches if the input array has a length of 0.
@Test
	public void test_emptyArray() throws Exception {
		// Given
		String[] testArray = new String[0];

		// Then
		assertThat(testArray, is(emptyArray()));
	}
	
emptyCollegetionOf()
//Typesafe matcher which matches if the input collection is of the given type and is empty.
@Test
	public void test_emptyCollectionOf() throws Exception {
		// Given
		Set<String> testCollection = new HashSet<>();

		// Then
		assertThat(testCollection, is(emptyCollectionOf(String.class)));
	}
	
emptyIterable()
//Matcher which matches if the input Iterable has no values.

@Test
	public void test_emptyIterable() throws Exception {
		// Given
		Set<String> testCollection = new HashSet<>();

		// Then
		assertThat(testCollection, is(emptyIterable()));
	}

emptyIterableOf()
//Typesafe Matcher which matches if the input Iterable has no values and is of the given type.

@Test
	public void test_emptyIterableOf() throws Exception {
		// Given
		Set<String> testCollection = new HashSet<>();

		// Then
		assertThat(testCollection, is(emptyIterableOf(String.class)));
	}

endsWith	
//Matcher which matches if the input String ends with the given substring.

@Test
	public void test_endsWith() throws Exception {
		// Given
		String testValue = "value";

		// Then
		assertThat(testValue, endsWith("lue"));
	}
	
equalTo()
//Matcher which matches if the input value is logically equal to the given test value. Can also be used on Arrays in which case it will check the length of the Array and ensure that all the values in the input test array are logically equal to the values of the specified array.

Sigle value

@Test
	public void test_equalTo_value() throws Exception {
		// Given
		String testValue = "value";

		// Then
		assertThat(testValue, equalTo("value"));
	}
	
Array

@Test
	public void test_equalTo_array() throws Exception {
		// Given
		String[] testValues = { "why", "hello", "there" };

		// Then
		String[] specifiedValues = { "why", "hello", "there" };
		assertThat(testValues, equalTo(specifiedValues));
	}
	
equalToIgnoringCase()
//Matcher which matches if the input String value is equal to the specified String while ignoring case.

@Test
	public void test_equalToIgnoringCase() throws Exception {
		// Given
		String testValue = "value";

		// Then
		assertThat(testValue, equalToIgnoringCase("VaLuE"));
	}
	
equalToIgnoringWhiteSpace()
//Matcher which matches if the input String value is equal to the specified String while ignoring superfluous white space. All leading and trailing whitespace are ignored, and all remaining whitespace is collapsed to a single space.

@Test
	public void test_equalToIgnoringWhiteSpace() throws Exception {
		// Given
		String testValue = "this    is   my    value    ";

		// Then
		assertThat(testValue, equalToIgnoringWhiteSpace("this is my value"));
	}
	
eventFrom()
//Matcher which matches if an input EventObject is from the given Source. Can also accept an EventObeject of a specified subtype.

@Test
	public void test_eventFrom() throws Exception {
		// Given
		Object source = new Object();
		EventObject testEvent = new EventObject(source);

		// Then
		assertThat(testEvent, is(eventFrom(source)));
	}
	
//With subtype specified.
@Test
	public void test_eventFrom_type() throws Exception {
		// Given
		Object source = new Object();
		EventObject testEvent = new MenuEvent(source);

		// Then
		assertThat(testEvent, is(eventFrom(MenuEvent.class, source)));
	}
	
greaterThan()
//Matcher which matches if an input test value is greater than a specified value.

@Test
	public void test_greaterThan() throws Exception {
		// Given
		Integer testValue = 5;

		// Then
		assertThat(testValue, is(greaterThan(3)));
	}
	

greaterThanOrEqual()
//Matcher which matches if an input test value is greater than or equal to a specified value.

@Test
	public void test_greaterThanOrEqualTo() throws Exception {
		// Given
		Integer testValue = 3;

		// Then
		assertThat(testValue, is(greaterThanOrEqualTo(3)));
	}
	
hasEntry()
//Matchers which match if a given map contains an entry which matches the specified key and value, or matchers.

Actual values

@Test
	public void test_hasEntry() throws Exception {
		// Given
		Integer testKey = 1;
		String testValue = "one";
		
		Map<Integer, String> testMap = new HashMap<>();
		testMap.put(testKey, testValue);

		// Then
		assertThat(testMap, hasEntry(1, "one"));
	}
	
Matchers

@Test
	public void test_hasEntry_matchers() throws Exception {
		// Given
		Integer testKey = 2;
		String testValue = "two";
		
		Map<Integer, String> testMap = new HashMap<>();
		testMap.put(testKey, testValue);

		// Then
		assertThat(testMap, hasEntry(greaterThan(1), endsWith("o")));
	}
	
hasItem()
//Matchers which match if the input Iterable has at least one item that matches the specified value or matcher.

Actual Value

@Test
	public void test_hasItem() throws Exception {
		// Given
		List<Integer> testList = Arrays.asList(1,2,7,5,4,8);

		// Then
		assertThat(testList, hasItem(4));
	}
	
Matcher

@Test
	public void test_hasItem_matcher() throws Exception {
		// Given
		List<Integer> testList = Arrays.asList(1,2,7,5,4,8);

		// Then
		assertThat(testList, hasItem(is(greaterThan(6))));
	}
	

hasItemInArray()
//Matchers which match if the input Array has at least one item that matches the specified value or matcher.

Actual Value

@Test
	public void test_hasItemInArray() throws Exception {
		// Given
		Integer[] test = {1,2,7,5,4,8};

		// Then
		assertThat(test, hasItemInArray(4));
	}
	
Matcher

@Test
	public void test_hasItemInArray_matcher() throws Exception {
		// Given
		Integer[] test = {1,2,7,5,4,8};

		// Then
		assertThat(test, hasItemInArray(is(greaterThan(6))));
	}
	
hasItems()
//Matchers which match if the input Iterable has all of the specified values or matchers, in any order.

Actual Values

public void test_hasItems() throws Exception {
		// Given
		List<Integer> testList = Arrays.asList(1,2,7,5,4,8);

		// Then
		assertThat(testList, hasItems(4, 2, 5));
	}
	
Matchers

@Test
	public void test_hasItems_matcher() throws Exception {
		// Given
		List<Integer> testList = Arrays.asList(1,2,7,5,4,8);

		// Then
		assertThat(testList, hasItems(greaterThan(6), lessThan(2)));
	}
	




	
