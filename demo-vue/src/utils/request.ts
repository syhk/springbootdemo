import axios from "axios";
// 进行配置 允许 cookie 的携带
axios.defaults.withCredentials=true
// 携带 token 进行访问
axios.defaults.headers.common['Authorization'] ='${token}' 
import querystring from 'querystring';
const errorHandler = (status: number, info: string) => {
	switch (status) {
		case 400:
			console.log("客户端有误");
			break;
		case 500:
			console.log("服务器有误");
			break;
		default:
			console.log(info);
			break;
	}
}
// Accept 代表发送端希望接受的数据类型
//  Content-Type 代表发送端发送的数据格式
const instance = axios.create({
	timeout: 5000,
	headers: { 'Content-Type': 'application/json', 'Accept': '*/*' }
})
// 拦截器
// 发送数据之前 
instance.interceptors.request.use(
	config => {
		if (config.method === 'post' || config.method === 'delete' || config.method === 'patch') {
			// config.data = querystring.stringify(config.data);
			config.data = JSON.stringify(config.data);
			console.log(config.data);
			const token = localStorage.getItem('token');
			token && (config.headers.Authorization= token);
			// qs 和 Json 都是序列化  eg{name:'syhk', age:29}
			// qs 结果： name=syhk&age=
			//  JSON 结果： {"syhk&age=29"}
		}
		// config 包含所有的网络请求信息
		return config;
	},
	error => {
		return Promise.reject(error);
	}
)
// 获取数据之前 
instance.interceptors.response.use(
	response => {
		return response.status === 200 ? Promise.resolve(response) : Promise.reject(response);
	},
	error => {
		const { response } = error;
		errorHandler(response.status, response.info);
	}
)
export default instance;














