import axios from '../utils/request'
import path from './path'


const api = {

	getAll() {
		path.children = '/user/getAll';
		return axios.get(path.baseUrl.concat(path.children));
	},

	login(email: string, pwd: string) {
		path.children = '/user/login1';
		return axios.post(path.baseUrl.concat(path.children), { email: email, pwd: pwd }); // k - v  形势
	}
}































export default api

