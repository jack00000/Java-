---
title: PAT知识点
date: 2017-2-12 12：00
tags:
---

vector
```
vecotr<int>  // 声明向量容器 TM就是指针组 大小不确定
vector<int> v(n);//见乙级1065
vector<int>::iterator//定义向量迭代器
vector<int>::iterator result=find(v.begin(),v.end(),table[v[i]]);// 见1065
v.push_back(v[i]);//在v的末尾增加一个值为v[i]的元素
举个例子

for(vector<int>::iterator it=v.begin();it!=v.end();it++)
     cout<<*it<<endl;
就把里面的内容都输出了
```
函数algorithm
```
以很好的平均效率排序	sort()
sort(doge.begin(),doge.end());//见1065
排序，并维持相同元素的原有顺序	stable_sort()
将序列的前一部分排好序	partial_sort()
复制的同时将序列的前一部分排好序	partial_sort_copy()
在序列中找出某个值的第一次出现的位置	find()
find(v.begin(),v.end(),table[v[i]]);//table[v[i]]为要找的元素    见乙级1065

```
qsort
```
qsort（即，quicksort）主要根据你给的比较条件给一个快速排序，主要是通过指针移动实现排序功能。排序之后的结果仍然放在原来数组中。

参数意义如下:

第一个参数 base 是 需要排序的目标数组名（或者也可以理解成开始排序的地址，因为可以写&s[i]这样的表达式）

第二个参数 num 是 参与排序的目标数组元素个数

第三个参数 width 是单个元素的大小（或者目标数组中每一个元素长度），推荐使用sizeof(s[0]）这样的表达式

第四个参数 compare 就是让很多人觉得非常困惑的比较函数啦
int compare(const void *a_t, const void *b_t){
    int *a = (int *)a_t, *b = (int *)b_t;
    if(a[3] != b[3]){
        return a[3] - b[3]; //type升序
    }
    else if(a[1] + a[2] != b[1] + b[2]){
        return (b[1] + b[2]) - (a[1] + a[2]); //总分降序
    }
    else if(a[1] != b[1]){
        return b[1] - a[1]; //德分降序
    }
    else{
        return a[0] - b[0]; //学号升序
    }
}
int main(){
qsort(&sTable[0], n, sizeof(sTable[0]), compare);//数组名 元素个数 单个元素大小 比较函数
}
```
