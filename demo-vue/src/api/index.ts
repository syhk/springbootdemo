import axios from '../utils/request'
import path from './path'


const api = {

	getAll() {
		path.children = '/user/getAll';
		return axios.get(path.baseUrl.concat(path.children));
	},

	login(email: string, pwd: string) {
		path.children = '/user/login';
		return axios.post(path.baseUrl.concat(path.children), { email: email, pwd: pwd }); // k - v  形势
	},

	logout() {
		path.children = "/user/logout";
		return axios.post(path.baseUrl.concat(path.children));
	}
	,
	// 获取所有博客文章
	getAllblog() {
		path.children = "/blog/getAll";
		return axios.get(path.baseUrl.concat(path.children));
	}
}































export default api

