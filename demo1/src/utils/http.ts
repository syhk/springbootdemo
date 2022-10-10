import axios, { AxiosInstance, AxiosRequestConfig } from 'axios'
import querystring from 'querystring'

// 封闭网络请求

// querystring post 请求需要额外处理，转换参数格式  querystring.stringify({})
const instance: AxiosInstance = axios.create({
    // 网络请求的公共配置
    timeout: 5000,
})

// 请求拦截
instance.interceptors.request.use(

    config => {
        if (config.method === "post") {
            config.data = querystring.stringify(config.data);
        }
        // config 包含着网络请求的所有信息
        return config;
    },
    error => {
        return Promise.reject(error);
    }
)

// 获取数据之前 
// 响应拦截
instance.interceptors.response.use(

    response => {
        return response.status === 200 ? Promise.resolve(response) : Promise.reject(response)
    },
    error => {
        const { response } = error;
        //错误处理才是重要的
        errorHandler(response.status, response.info)
    }
)

// 请求状态码错误处理
const errorHandler = (status: number, info: string) => {
    switch (status) {

        case 400:
            console.log('客户端有错误!');
            break;
        case 500:
            console.log("服务端错误！");
            break;
        default:
            console.log(info);
            break;
    }
}

export default instance;