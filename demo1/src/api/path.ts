
interface base {
    baseUrl: string,
    chengpin: string,
}

// 请求路径放在这里面
const base: base = {
    // 公共地址，一般不变的
    // 解决完跨域后，进行访问的时候前面的公共问题就不需要写在这里了
    baseUrl: "",
    // 一般改变的是子地址
    chengpin: "/admin/getAllAdmin",
}
export default base