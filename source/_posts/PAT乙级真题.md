---
title: PAT乙级真题
date: 2017-2-11 20:13:10
tags:
---
1001
```c++
#include <stdio.h>
#include <iostream>

using namespace std;

int main(){
    int n;
    scanf("%d", &n);
    int count = 0;
    while(n != 1){
        if(n % 2 != 0){
            n = (3 * n + 1) / 2;
            count ++;
        }
        else{
            n /= 2;
            count ++;
        }
    }
    printf("%d\n", count);
    return 0;
	cin>>n;

}
```
1005
```c++
#include<iostream>
using namespace std;
int main()
{
  int bai,shi,ge,n;
  cin>>n;
  ge=n%10;
  shi=(n-ge)/10%10;
  bai=(n-shi*10-ge)/100%10;
  if(bai!=0)
    for(int i=0;i<bai;i++){
      printf("B");
    }
    if(shi!=0)
      for(int i=0;i<shi;i++){
        printf("S");
      }
      if(ge!=0)
        for(int i=0;i<ge;i++){
          printf("G");
        }

}
```
1006
```c++
#include<iostream>
#include<stdio.h>
using namespace std;
int array[100000];
int main()
{
  int n;
  cin>>n;
  for(int i=2;i<n/2+1;i++){
    for(int j=2;i*j<=n;j++){
      array[i*j]=1;
    }
  }
  int tmp=2,count;
  for(int j=2;j<=n;j++){
    if(array[j]==0){
      if(j-tmp==2){
        count++;
        tmp=j;
      }
      else{
        tmp=j;
      }
    }
  }
  printf("%d",count);
}

```
1007
```c++
#include<iostream>
#include<stdio.h>
using namespace std;
int main()
{
  int n,m,a[100];
  cin>>n>>m;
  for(int i=0;i<n;i++){
    scanf("%d",&a[(i+m)%n]);
  }
  for(int i=0;i<n;i++){
    printf("%d ",a[i]);
  }
}
```
1014
```c++
#include<iostream>
#include<sstdio>
#include<sstring>
using namespace std;
int main()
{
  int n,lowline,eline;
  cin>>renshu>>lowline>>eline>>endl;
  int man[n][4];
  for(int i=0;i<n;i++){
    scanf("%d %d %d",man[i][0],man[i][1],man[i][2]);

  }  
}
```
1021
```c++
#include<iostream>
#include<sstdio>
#include<sstring>
using namespace std;
int main()
{
  string s;
  gets(s);
  int count[10];
  for(int i=0;i<s.size();i++){
    if(s[i]-48=0){
      count[0]++;
    }
    else if(s[i]-48=1){
      count[1]++;
    }
    else if(s[i]-48=2){
      count[2]++;
    }
    else if(s[i]-48=3){
      count[3]++;
    }
    else if(s[i]-48=4){
      count[4]++;
    }
    else if(s[i]-48=5){
      count[5]++;
    }
    else if(s[i]-48=6){
      count[6]++;
    }
    else if(s[i]-48=7){
      count[7]++;
    }
    else if(s[i]-48=8){
      count[8]++;
    }
    else if(s[i]-48=9){
      count[9]++;
    }
    for(int j=0;j<10;j++){
      printf("%d :%d",j,count[j]);
    }
  }
}
```
1023
```c++
#include<iostream>
#include<sstream>
#include<string.h>
#include<cmath>
using namespace std;
int main()
{
  string s;
  cin>>s;

  string num1,num2;
  for(int i=1;i<s.find('E');i++){
    num1[i]=s[i];
  }
  for(int i=s.find('E')+1;i<s.size();i++){
    num2[i]=s[i];
  }
  if(s[0]="+"&&s.find('E')+1=='+'){
    printf("%d",num1*pow(10,num2));
  }
  if(s[0]="+"&&s.find('E')+1=='-'){
    printf("%d",1/(num1*pow(10,num2)));
  }
  if(s[0]="-"&&s.find('E')+1=='+'){
    printf("-%d",num1*pow(10,num2));
  }
  if(s[0]="-"&&s.find('E')+1=='-'){
    printf("-%d",1/(num1*pow(10,num2)));
  }

}
```
1025
```c++
#include<iostream>
using namespace std;
int main()
{
  float c1,c2;
  cin>>c1>>c2;
  float time=c2-c1+50;
  int hour=time/360000;
  int minute=(time-hour*360000)/6000;
  int second=(time-hour*360000-minute*6000)/100;
  printf("%02f %02f %02f",hour,minute,second);

}
```
1056
```c++
#include<iostream>
#include<vector>
using namespace std;
int main()
{
  int n;
  scanf("%d",&n);
  vector<int> v(n);
  int sum;
  for(int i=0;i<n;i++){
    scanf("%d",&v[i]);
  }
  for(int i=0;i<n;i++){
    for(int j=i+1;j<n;j++){
      sum+=v[i]*10+v[j];
      sum+=v[j]*10+v[i];
    }
  }
  printf("%d",sum);
}

```
1057
```c++
#include<iostream>
#include<vector>
using namespace std;
int main()
{
   string s;
   gets(s);
   int sum;
   for(int i=0;i<s,size();i++){
     if(s[i]>='A'&&s[i]=<'Z'){
       sum=sum+s[i]-64;
     }
     else if(s[i]>='a'&&s[i]=<'z'){
       sum=sum+s[i]-96;
     }
     int count1,count0;
     while(sum!=0){//十进制转二进制   除2
       if(sum%2==0){
         count0++;
       }
       eles if(sum%2==1){
         count1++;
       }
       sum=sum/2;
    }
   }
}


```
1061
```c++
#include<iostream>
#include<vector>
using namespace std;
int main()
{
  int n,m;
  scanf("%d %d",&n,&m);
  vector<int> vatual(m);
  vector<int> key(m);
  int sum=0,g;
  for(int i=0;i<m;i++){
    scanf("%d",&vatual[i]);
  }
  for(int i=0;i<m;i++){
    scanf("%d",&key[i]);
  }
  for(int i=0;i<n;i++){

  for(int i=0;i<m;i++){
    scanf("%d",&g);
    if(g==key[i]){
      sum=sum+key[i];
    }
  }
}
    printf("%d\n",sum);

}

```
1063
```c++
#include<cmach>
#include<iostream>
using namespace std;
int main()
{
  int n,a,b;
  cin>>n;
  double mo;
  double max=0;
  for(i=0;i<n;i++){
    scanf("%f %f",&a.&b);
    mo=sqrt(a*a+b*b);
    max=(mo> max)?mo:max;
  }
  printf("%0.2lf",max);
}

```
1064
```c++
#include<iostream>
#include<sstdio>
#include<vector>
#include<string>
#include<algorithm>
using namespace std;
int main()
{
  int n;
  vector<int> v;
  string s;
  sum=0;
  scanf("%d",&n);
  for(int i=0;i<n;i++){
    cin>>s
    for(j=0;j<=s.size();j++){
      sum=sum+s[j]-'0';
    }
    vector<int>::iterator result=find(v.begin,v.end(),sum);
    if(result=v.end()){
      v.push_back(sum);
    }
  }
  sort(v.begin,v.end());
  printf("%d",v.size());
  for(i=0;i<v.size();i++){
    if(i==0){
      printf("%d",v[i]);
    }
    else{
      printf(" %d",v[i]);
    }
  }
}
```
1065
```c++
#include<sstdio>
#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;
int table[100000]
int main ()
{
  int n;
  int a,b;
  scanf("%d",&n);
  for(int i=0;i<n;i++){
    scanf("%d %d",&a,&b);
    table[a]=b;
    table[b]=a;
    }
    scanf("%d",&n);
    vector<int> v(n);
    vector<int> doge;
    for(int i=0;i<n;i++){
      scanf("%d",&v[i]);
    }
    for(int i=0;i<n;i++){
      vector<int>::iterator result=find(v.begin(),v.end(),table[v[i]]);
      if(result==v.end())
      doge.push_back(v[i]);
    }
    sort(doge.begin(),doge.end());
    for(int i=0;i<doge.size();i++){
      printf("%05d",doge[i]);
    }

}
```
