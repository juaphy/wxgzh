/*  
Base64编码规则：  
1、将三个byte的数据，先后放入一个24bit的缓冲区中，先来的byte占高位；  
2、数据不足3byte的话，缓冲区中剩下的bit用0补足；  
3、然后，每次取出6个bit（因为2^6=64，即0到63），按照其值选择ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/中的字符作为编码后的输出；  
4、不断进行，直到全部输入数据转换完成；  
5、如果最后剩下两个输入数据，在编码结果后加1个=；如果最后剩下一个输入数据，编码结果后加2个=；如果没有剩下任何数据，则什么都不加，这样可以保证数据还原的正确性。  
注1：先对输入字符串进行单字节编码，否则，因为charCodeAt()对汉字等符号返回Unicode编码，其长度为16bit；因此，可将所有字符当做双字节处理，虽然增加了字节数量，但简化了双字节字符和单字节字符的识别  
注2：在JavaScript中，CJK ExtB（扩展字符平面2）中的字符均被当做两个字符，用4Byte编码，即字符"𠀀"~"𪛖"，其编码0xD840,0xDC00~0xD869~0xDED6，  
    例：语句：alert("𪛖".charCodeAt(0).toString(16)+" "+"𪛖".charCodeAt(1).toString(16));将显示：d869 ded6  
技巧：编码时处理源字节，如果字节总数模3余1，则可现在其后面添加2个为0的字节，如果模3余2，则添加1个为0的字节，然后在编码完成后将末尾的2个或1个A字符均替换为=字符；  
     解码时同样可以将末尾的=字符替换为A字符，由于A字符对应0，而0解码为空字符，故可不做任何处理(编码非字符类型的其它字节流，如图片、音视频等，则必须将末尾的0字节去除)。  
*/  
/**
 * @author 邹瑞金
 * @date 2013/10/14
 * 
 */
function unicodeToByte(str) //将Unicode字符串转换为UCS-16编码的字节数组  
{  
    var result=[];  
    for(var i=0;i<str.length;i++)  
        result.push(str.charCodeAt(i)>>8,str.charCodeAt(i)&0xff);  
    return result;  
}  
function byteToUnicode(arr) //将UCS-16编码的字节数组转换为Unicode字符串  
{  
    var result="";  
    for(var i=0;i<arr.length;i+=2)  
        result+=String.fromCharCode((arr[i]<<8)+arr[i+1]);  
    return result;  
}  
var map="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"; //Base64从0到63的对应编码字符集  
function encodeBase64(str)  
{  
    var buffer,result="",flag=0; //flag表示在字节数组剩余的个数  
    var arr=unicodeToByte(str);  
    flag=arr.length%3;  
    if(flag==1)  
        arr.push(0,0);  
    else if(flag==2)  
        arr.push(0);  
    for(var i=0;i<arr.length;i+=3) //此时arr.length一定能被3整除  
    {  
        buffer=(arr[i]<<16)+(arr[i+1]<<8)+arr[i+2];  
        result+=map.charAt(buffer>>18)+map.charAt(buffer>>12&0x3f)+map.charAt(buffer>>6&0x3f)+map.charAt(buffer&0x3f);  
    }  
    if(flag==1)  
        resultresult=result.replace(/AA$/g,"==");  
    else if(flag==2)  
        resultresult=result.replace(/A$/g,"=");  
    return result;  
}  
function decodeBase64(str)  
{  
    //逆向映射数字索引和Base64编码字符集（简单Hash）  
    var s="var base64={";  
    for(var i=0;i<64;i++)  
        s+="\""+map.charAt(i)+"\":"+i+",";  
    s+="\"=\":0};"; //将"="字符对应的编码定义为0，相当于将=字符转换为A字符  
    eval(s);  
    var buffer,result=[];  
    for(i=0;i<str.length;i+=4) //由于包含Base64末尾包含1个或2个=字符，故str.length一定能被4整除  
    {  
        buffer=(base64[str.charAt(i)]<<18)+(base64[str.charAt(i+1)]<<12)+(base64[str.charAt(i+2)]<<6)+base64[str.charAt(i+3)];  
        result.push(buffer>>16,buffer>>8&0xff,buffer&0xff);  
    }  
    if(/==$/g.test(str)) //如解码为字符串可不做该处理  
    {  
        result.pop();  
        result.pop();  
    }  
    else if(/=$/g.test(str))  
        result.pop();  
    return byteToUnicode(result);  
} 