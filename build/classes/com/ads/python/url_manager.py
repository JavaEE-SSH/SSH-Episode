'''
Created on 2017年10月12日

@author: Administrator
'''


class UrlManager(object):
    def __init__(self):
        self.new_urls = set()
        self.old_urls = set()
    
    def add_new_url(self, url):
        '''
        @summary: 添加新的url
        @param url: str
        '''
        if url is None:
            return
        if url not in self.new_urls and url not in self.old_urls:
            self.new_urls.add(url)


    def add_new_urls(self, urls):
        '''
        @summary: 添加一个set的url
        @param urls: set()集合
        '''
        if urls is None or len(urls) == 0:
            return
        for url in urls:
            self.add_new_url(url)
    
    def has_new_url(self):
        '''
        @summary: 判断是否有带爬取的url
        '''
        return len(self.new_urls) != 0

    
    def get_new_url(self):
        '''
        @summary: 获取一个url，并从new_urls移到old_urls
        @return: url
        '''
        url = self.new_urls.pop()
        self.old_urls.add(url)
        return url
    
    
    
    
    
    
    
    



