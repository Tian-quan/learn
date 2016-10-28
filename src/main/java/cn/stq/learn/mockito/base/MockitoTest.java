package cn.stq.learn.mockito.base;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import cn.stq.learn.utils.TestUtil;

/**
 * @see http://waylau.com/mockito-quick-start/
 * @update
 * @create 2016年10月26日 下午6:07:26
 * @author tianquan.shi<tianquan.shi@msxf.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

	@Test
	public void test(){
		mockList.add("1");
		TestUtil.outAsJSON(mockList);
	}
	/**
	 * 测试调用实际的方法
	 */
	@Test
	public void test012CallRealMethod() {
		List<String> arr = mock(ArrayList.class);
		when(arr.get(Mockito.anyInt())).thenReturn("ok");
		when(arr.get(100)).thenCallRealMethod();
		
		System.out.println(arr.get(99));
		//DO 调用实际方法,会抛出异常.
		System.out.println(arr.get(100));
	}

	/**
	 * 测试桩回调
	 */
	@Test
	public void test011mockAnswer() {
		when(mockList.get(Mockito.anyInt())).thenAnswer(new Answer<String>() {

			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				if ((int) invocation.getArgument(0) > 10) {
					throw new ArrayIndexOutOfBoundsException();
				}
				return "ok";
			}
		});

		System.out.println(mockList.get(1));
		System.out.println(mockList.get(11));
	}

	/**
	 * 测试连续打桩
	 */
	@Test
	public void test010thenReturn() {
		when(mockList.get(Mockito.anyInt())).thenReturn("0") // 第一次调用返回
				.thenReturn("1");// 第二次调用返回

		System.out.println(mockList.get(1));
		System.out.println(mockList.get(2));
	}

	/**
	 * mock注解
	 */
	@Test
	public void test009mockAnnotation() {
		System.out.println(mockList.get(1));
	}

	@Mock
	private List<String> mockList;

	@Before
	public void initMocks() {
		// 初始化<code>@Mock</code>注解
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 测试验证没有调用
	 */
	@Test
	public void test007NoInteraction() {
		List<String> mockOne = Mockito.mock(List.class);
		List<String> mockTwo = Mockito.mock(List.class);
		List<String> mockThree = Mockito.mock(List.class);
		// using mocks - only mockOne is interacted
		mockOne.add("one");

		// ordinary verification
		verify(mockOne).add("one");

		// verify that method was never called on a mock
		verify(mockOne, Mockito.never()).add("two");

		// verify that other mocks were not interacted
		Mockito.verifyZeroInteractions(mockTwo, mockThree);
	}

	/**
	 * 有序的验证
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test005Order() {
		// A. Single mock whose methods must be invoked in a particular order
		List<String> singleMock = mock(List.class);

		// using a single mock
		singleMock.add("was added first");
		singleMock.add("was added second");

		// create an inOrder verifier for a single mock
		InOrder inOrder = Mockito.inOrder(singleMock);

		// following will make sure that add is first called with "was added
		// first, then with "was added second"
		inOrder.verify(singleMock).add("was added first");
		inOrder.verify(singleMock).add("was added second");

		// B. Multiple mocks that must be used in a particular order
		List firstMock = mock(List.class);
		List secondMock = mock(List.class);

		// using mocks
		firstMock.add("was called first");
		secondMock.add("was called second");

		// create inOrder object passing any mocks that need to be verified in
		// order
		InOrder inOrder2 = Mockito.inOrder(firstMock, secondMock);

		// following will make sure that firstMock was called before secondMock
		inOrder2.verify(firstMock).add("was called first");
		inOrder2.verify(secondMock).add("was called second");

		// Oh, and A + B can be mixed together at will
	}

	/**
	 * 调用次数测试
	 */
	@Test
	public void test004VerificationMode_incokeTimes() {
		// DO mock
		List<String> mockList = Mockito.mock(List.class);
		// DO invoke
		mockList.size();
		mockList.size();

		// DO verify invoke times
		Mockito.verify(mockList, Mockito.times(2)).size();// 两次
		Mockito.verify(mockList, Mockito.atLeastOnce()).size();// 至少一次
		Mockito.verify(mockList, Mockito.atLeast(2)).size();// 至少两次
		Mockito.verify(mockList, Mockito.atMost(10)).size();// 至多两次
	}
	/**
	 * 延迟验证时间
	 */
	@Test
	public void test004VerificationMode_delay(){
		//DO 验证3s内调用方法
		mockList.add("1");
		verify(mockList,Mockito.timeout(3000)).add("1");
		System.out.println("verify add in in 3000ms");
		
		//DO 3s后验证.
		mockList.clear();
		verify(mockList,Mockito.after(2000)).clear();
		System.out.println("verify clear after 2000ms.");
	}

	/**
	 * 参数匹配测试
	 */
	@Test
	public void test003MockParamMatch() {
		// DO mock and matcher
		List<String> mockList = mock(List.class);
		ArgumentMatcher<List<String>> size2ArgMatcher = argList -> argList.size() == 2;
		ArgumentMatcher<String> stringMatcher = strArg -> StringUtils.equals("1", strArg);

		// DO stubbing
		when(mockList.addAll(Mockito.argThat(size2ArgMatcher))).thenReturn(true);
		when(mockList.add(Mockito.argThat(stringMatcher))).thenReturn(true);

		// DO invoke
		mockList.addAll(Arrays.asList("a", "b"));
		mockList.add("1");
		mockList.add("2");
		// DO verify
		Mockito.verify(mockList).addAll(Mockito.argThat(size2ArgMatcher));
		Mockito.verify(mockList).add(Mockito.argThat(stringMatcher));
	}

	/*
	 * 桩测试
	 */
	@Test
	public void test002MockListStub() {
		// You can mock concrete classes, not just interfaces
		LinkedList<String> mockedList = mock(LinkedList.class);

		// stubbing
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenThrow(new RuntimeException());
		when(mockedList.get(-1)).thenThrow(new IllegalArgumentException("不支持的参数"));
		// following prints "first"
		System.out.println(mockedList.get(0));

		// following throws runtime exception
		// System.out.println(mockedList.get(1));

		// System.out.println(mockedList.get(-1));

		// following prints "null" because get(999) was not stubbed
		System.out.println(mockedList.get(999));

		// Although it is possible to verify a stubbed invocation, usually it's
		// just redundant
		// If your code cares what get(0) returns, then something else breaks
		// (often even before verify() gets executed).
		// If your code doesn't care what get(0) returns, then it should not be
		// stubbed. Not convinced? See here.
		verify(mockedList).get(1);
	}

	@Test
	public void test001MockList() {
		// DO mock
		List mockedList = Mockito.mock(List.class);

		// DO run
		mockedList.add("1");

		// DO verify
		Mockito.verify(mockedList).add("1");
	}

}
