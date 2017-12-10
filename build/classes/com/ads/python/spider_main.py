# coding=utf-8
'''
Created on 2017年10月12日

@author: 卫亮旭
@summary: 爬虫main模块
'''
import url_manager, html_downloader, html_parser, html_outputer
import sys
import os


class SpiderMain(object):
    def __init__(self, old_urls):
        self.urls = url_manager.UrlManager()  # url管理器
        self.downloader = html_downloader.HtmlDownloader()  # 网页下载器
        self.parser = html_parser.HtmlParser()  # 页面解析器
        self.outputer = html_outputer.HtmlOutputer()  # 输出器
        self.urls.old_urls = old_urls
    
    def crawl(self, root_url):
        count = 1
        self.urls.add_new_url(root_url)  # 添加初始url
        new_url = ''
        
        try:
            while self.urls.has_new_url():  # url循环
                new_url = self.urls.get_new_url()  # 从url管理其中获取一个新的url
                print('crawl %d: %s' % (count, new_url))  # 输出爬取页数
                html_cont = self.downloader.download(new_url)  # 获取url的网页内容
                new_urls, new_data = self.parser.parse(new_url, html_cont)  # 获取新的urls和新的数据
                self.urls.add_new_urls(new_urls)  # 保存新的url
                self.outputer.collect_data(new_data)  # 保存新的数据
                
                if count == 100:#限定爬取数量
                    break
                count += 1
        except:  # 异常处理
            self.urls.add_new_url(new_url)
            print('crawl failed')
            info = sys.exc_info()
            print(info[0], ':', info[1])
        
        self.outputer.output_data(self.urls)  # 输出，收集好的数据

if __name__ == '__main__':
    if os.path.exists(r'E:\Python\Data') == False:
        os.makedirs(r'E:\Python\Data')
    # 确保指定文件存在
    open(r'E:\Python\Data\new_url.dat', 'a').close()
    open(r'E:\Python\Data\old_urls.dat', 'a').close()
    
    # 获取初始化数据
    with open(r'E:\Python\Data\new_url.dat', 'r') as fout:
        root_url = fout.readline()
        if root_url is None or root_url == '':
            root_url = 'http://www.budejie.com/detail-26502141.html'
        print('url:%s' % root_url)
        
    # 获取已经访问的url
    with open(r'E:\Python\Data\old_urls.dat', 'r') as fout:
        old_urls = set()
        for url in fout:
            print('old_urls:%s' % url.replace('\n', ''))
            old_urls.add(url.replace('\n', ''))
    print('***********************开始爬取数据************************')
    # 执行爬虫
    obj_spider = SpiderMain(old_urls)
    obj_spider.crawl(root_url)
