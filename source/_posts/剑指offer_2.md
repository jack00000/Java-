---
title: 剑指offer_2
date: 2017-9-23 18:00
categories: 剑指offer

---

1.设计一个类，我们只能生成该类的一个实例（Singleton模式）

1.2.解法1：利用静态构造函数
```c++
public sealed class Singleton4 {
	private Singleton4() {

	}
	private static Singleton4 instance = new Singleton4();
	public static Singleton4 Instance{
		get{
		return instance;
}
	}
}
```

1.3 解法2：实现按需创建实例
```c++
public sealed class Singleton5 {
	Singleton5() {

	}
	public static Singleton5 Instance{
		get{
		return Nested.instance;

}
	}
		class Nested {
	static Nested() {

	}
	internal satic readonly Singleton5 instance = new Singleto5();
}
}

```
