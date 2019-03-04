/**
 * 配置编译环境和线上环境之间的切换
 * 
 * baseUrl: 域名地址
 * routerMode: 路由模式
 * dataSources：数据源
 */

let baseUrl = ''; 
let routerMode = 'hash';
let dataSources='local';//local=本地，其他值代表非本地


if (process.env.NODE_ENV == 'development') {
	baseUrl='';

}else if(process.env.NODE_ENV == 'production'){
	baseUrl = '';
}

export {
	baseUrl,
	routerMode,
	dataSources,
}