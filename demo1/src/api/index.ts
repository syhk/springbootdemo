import axios from '../utils/http'
import base from './path'
// import path from './path'


const api = {
    // 所有的网络请求方法都可以放在这里面
    getChengpin() {
        // str1.concat(str2) 拼接两个字符串
        base.baseUrl = ""
        return axios.get(base.baseUrl.concat(base.chengpin))
    }
}

export default api 