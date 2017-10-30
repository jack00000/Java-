---
title: 剑指offer_1
date: 2017-9-22 18:00
categories: 剑指offer

---

1.为CMyString添加赋值运算符函数
```c++
class CMyString{
public:
  CMyString(char* pData=NULL);
  CMyString(const CMyString& str);
  ~CMyString(void);
private:
  char* m_pData;

}

```



1.1 解题思路<\h3>
- 是否把返回值的类型声明为该类型的引用，并在函数结束前返回实例的自身的引用（*this)
- 是否把传入的参数的类型声明为常量引用
- 是否释放实例自身已有的内存
- 是否判断传入的参数和当前实例（*this）是不是同一个实例


1.2经典揭发（初级）
```c++
CMyString& CMyString::operator=(const CMyString &str){
  if(this==&str)
      return *this;
  delete []m_pData;
  m_pData=NULL;
  m_pData=new char[strlen(str.m_pData)+1];
  strcpy(m_pData,str.m_pData);
  return *this;    
}
```
1.3 考虑异常安全的方法（高级）
```c++
CMyString& CMyString::operator=(const CMyString &str) {

	if (this!- &str) {
		CMyString strTemp(str);
		char* pTemp = strTemp.m_pData;
		m_pData = pTemp;
	}
	return *this;
}
```
