import axios from '../utils/request'
import path from './path'
import {Ref} from 'vue'
const api = {
	getAll() {
		path.children = '/user/getAll';
		return axios.get(path.baseUrl.concat(path.children));
	},
	login(email: string, pwd: string) {
		path.children = '/user/login';
		return axios.post(path.baseUrl.concat(path.children), { email: email, pwd: pwd }); // k - v  形势
	},
	// 测试后端接口是否可行
	loginredis(email: string, pwd: string) {
		path.children = '/user/loginredis';
		return axios.post(path.baseUrl.concat(path.children), { email: email, pwd: pwd }); // k - v  形势
	},
	// 把当前登录用户传递上去
	logout(email:string) {
		path.children = "/user/logout";
		return axios.post(path.baseUrl.concat(path.children),{email:email});
	}
	,
	// 获取所有博客文章
	getAllblog() {
		path.children = "/blog/getAll";
		return axios.get(path.baseUrl.concat(path.children))
	},
	// 上传博客文章
	putblog(title: string, context: string) {
		path.children = "/blog/putblog";
		return axios.put(path.baseUrl.concat(path.children), { title: title, context: context });
	},
	// 获取首页 4 篇文章
	getfourblog() {
		path.children = "/blog/getfour";
		return axios.get(path.baseUrl.concat(path.children));
	},
	// 根据文章 id 获取对应的文章
	getOneByid(id: number) {
		path.children = "/blog/getOneById";
		return axios.get(path.baseUrl.concat(path.children), { params: { id: id } });
	},
	// 分页获取文章
	getpagingblog(page: number, pagesize: number) {
		path.children = "/blog/page";
		return axios.get(path.baseUrl.concat(path.children), { params: { page: page, pagesize: pagesize } });
	},
	// 注册
	register(name:string,email:string,pwd:string){
		path.children = "/user/register";
		return axios.post(path.baseUrl.concat(path.children),{name:name,email:email,pwd:pwd});
	},
	// 获取博客文章总数量
	getblogcount() {
		path.children = "/blog/getAllCount";
		return axios.get(path.baseUrl.concat(path.children));
	},
	// 通过 id 参数获取用户名
	getusername(id: number) {
		path.children = "/user/getusername";
		return axios.get(path.baseUrl.concat(path.children), { params: { id: id } });
	},
	//通过文章 id 获取评论
	getRemark(id: number) {
		path.children = "/remark/getReamByid";
		return axios.get(path.baseUrl.concat(path.children), { params: { id: id } });
	},
	// 上传用户数据:头像，name,签名
	// updateUser(headpic:string,name:string,qianming:string){
	// 	path.children="/user/updateUser";
	// 	return axios.post(path.baseUrl.concat(path.children),{headpic:headpic,name:name,qianming:qianming})
	// },
	// 通过 email 获取用户信息
	getUserByEmail(email: string) {
		path.children = "/user/getUserByEmail";
		return axios.get(path.baseUrl.concat(path.children), { params: { email: email } });
	},
	// 上传用户数据：头像，姓名，签名，性别，生日
	updateUser(headpic:string,name:string,qianming:string,sex:number,birthday:string,email:string){
		path.children="/user/updateUser";
		return axios.post(path.baseUrl.concat(path.children),{avatarUrl:headpic,name:name,qm:qianming,gender:sex,birthday:birthday,email:email})	
	},
	// 获取登录状态
	getUserLoginstatus(){
		path.children="/user/getLoginStatus";
		return axios.get(path.baseUrl.concat(path.children));
	},
	// 获取所有博客文章
	getallblog(){
		path.children="/blog/getall";
		return axios.get(path.baseUrl.concat(path.children));
	},
	// 获取头像连接,上传 email 参数获取
	getavatarUrl(email:string)
	{
		path.children="/user/avatar";
		return  axios.get(path.baseUrl.concat(path.children),{params:{email:email}});
	},
	// 暂时只提交三个参数
	submitremark(articleId:number,id:number,context:string,high:number,low:number)
	{
		path.children="/remark/submitremark";
		return axios.post(path.baseUrl.concat(path.children),{articleId:articleId,id:id,context:context,high:high,low:low});
	},
	// 获取用户 id
	getUserIdByEmail(email:string)
	{
		path.children="/user/getId";
		return axios.get(path.baseUrl.concat(path.children),{params:{email:email}});
	},
	// 搜索文章
	search(keyword:string)
	{
		path.children="/blog/search";
		return axios.get(path.baseUrl.concat(path.children),{params:{keyword:keyword}});
	},
	// 获取所有视频信息
	getallvideo()
	{
		path.children="/video/getall";
		return axios.get(path.baseUrl.concat(path.children));
	}
	

	
}
export default api