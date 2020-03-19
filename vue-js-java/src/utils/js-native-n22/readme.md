### js-native-n22使用指南 ###

[NPM](https://www.npmjs.com/package/js-native-n22)

#### 1. 安装 #### 
```
npm i js-native-n22
```

#### 2. 导入 ####
```
import native from 'js-native-n22'
``` 

#### 3. 使用 ####
```
native.api(data, (content) => {
    ...
}, (error) => {
    ...
})
```

#### API ####

##### 1. toast
请求参数:
参数 | 类型 | 枚举 | 含义
---|---|---|---
text | String | 无 | 提示信息
duration | int | 1:长,0:短 | 显示时长

响应参数: 无
    
API`toast`示例:
    
```js
native.toast({ text: '你好啊赛利亚', duration: 0 }, (content) => {
}, (error) => {
})
```
##### 2. device
请求参数: 无

响应参数:
参数 | 类型 | 枚举 | 含义
---|---|---|---
isDeviceRooted | boolean | 无 | 判断设备是否 rooted
isAdbEnabled | boolean | 无 | 判断设备 ADB 是否可用
sDKVersionName | String | 无 | 获取设备系统版本号
sDKVersionCode | int | 无 | 获取设备系统版本码
androidID | String | 无 | 获取设备 AndroidID
macAddress | String | 无 | 获取设备 MAC 地址
manufacturer | String | 无 | 获取设备厂商
model | String | 无 | 获取设备型号
aBIs | String[] | 无 | 获取设备 ABIs
isTablet | boolean | 无 | 判断是否是平板
isEmulator | boolean | 无 | 判断是否是模拟器
uniqueDeviceId | String | 无 | 获取唯一设备 ID
isSameDevice | boolean | 无 | 判断是否同一设备

API`device`示例:
```js
native.device((content) => {
    alert(JSON.stringify(content))
}, (error) => {
    alert(error)
})
```
##### 3. close
请求参数: 无

响应参数: 无
    
API`close`示例:
    
```js
native.close((content) => {
}, (error) => {
})
```