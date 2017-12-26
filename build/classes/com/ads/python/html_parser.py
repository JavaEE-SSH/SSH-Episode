'''
Created on 2017年10月12日

@author: Administrator
'''
from bs4 import BeautifulSoup
from urllib.parse import urljoin


class HtmlParser(object):
    
    
    def _get_new_urls(self, page_url, soup):
        '''
        @summary: 获取需要的页面url
        @param page_url: 页面url-str
        @param html_cont: 页面内容-str
        @return: 获取的url set()
        '''
        new_urls = set()
        
        links = soup.find_all('a', class_='c-next-btn')
        
        for link in links:
            new_url = link['href']
            new_full_url = urljoin(page_url, new_url)#拼接成一个url
            
            print('new url: %s'%new_full_url)
            new_urls.add(new_full_url)
        
        return new_urls;
    
    def _get_new_data(self, page_url, soup):
        '''
        @summary: 获取需要的页面数据
        @param page_url: 页面url-str
        @param html_cont: 页面内容-str
        @return: 获取的数据字典
        '''
        res_data = {}
        #url
        res_data['url'] = page_url
        #class="j-r-list-c-desc"
        node = soup.find('div', class_='j-r-list-c-desc').find('h1')
        if len(node.get_text()) * 2 < 1000: # 只要指定长度以内的数据
            res_data['content'] = node.get_text()
        else:
            res_data['content'] = None;
        
        return res_data
    
    def parse(self, page_url, html_cont):
        '''
        @summary: 解析页面
        @param page_url: 页面url-str
        @param html_cont: 页面内容-str
        @return: new_urls 页面中的新url set(), new_data 获取的新数据字典
        '''
        if page_url is None or html_cont is None:
            return
        
        soup = BeautifulSoup(html_cont, 'html.parser')
        new_urls = self._get_new_urls(page_url, soup)
        new_data = self._get_new_data(page_url, soup)
        
        return new_urls, new_data



