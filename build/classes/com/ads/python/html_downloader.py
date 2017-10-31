'''
Created on 2017年10月12日

@author: Administrator
'''
import urllib.request


class HtmlDownloader(object):
    
    
    def download(self, url):
        '''
        @summary: 下载指定url网页
        @param url: str
        @return: 下载好的网页
        '''
        if url is None:
            return
        # 根据当前URL创建请求包
        req = urllib.request.Request(url)
        # 添加头信息，伪装成浏览器访问
        req.add_header('User-Agent', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36')
        # 发起请求
        response = urllib.request.urlopen(req)
        
        if response.getcode() != 200:
            return
        
        return response.read()
    
    



