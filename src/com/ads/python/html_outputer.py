'''
Created on 2017年10月12日

@author: Administrator
'''
import time
import os
from idlelib.IOBinding import encoding


class HtmlOutputer(object):
    def __init__(self):
        self.data = []
    
    def collect_data(self, data):
        '''
        @summary: 收集数据
        @param data: 单个数据-str
        '''
        if data is None:
            return
        self.data.append(data)
    
    def output_data(self, urls):
        #保存数据到文件中
        if os.path.exists(r'E:\Python\Data\episode') == False:
            os.makedirs(r'E:\Python\Data\episode')
        file_name = time.strftime('%Y%m%d', time.localtime(time.time()))
        with open(r'E:\Python\Data\episode\%s.dat'%file_name, 'a', encoding='utf-8') as fout:
            for data in self.data:
                #replace(u'\xa0', u' ')防止网页中的&nbsp;转换异常
                fout.write(data['content'].replace(u'\xa0', u' ')+'\n')
        print(r'episode保存完成(E:\Python\Data\episode\%s.dat)'%file_name)
    
        #保存访问过的url 到文件中
        with open(r'E:\Python\Data\old_urls.dat', 'w', encoding='utf-8') as fout:
            old_urls = urls.old_urls;
            for url in old_urls:
                fout.write(url+'\n')
        print(r'old_urls保存完成(E:\Python\Data\old_urls.dat)')
    
        #保存一个新的url
        with open(r'E:\Python\Data\new_url.dat', 'w', encoding='utf-8') as fout:
            if urls.has_new_url():
                fout.write(urls.get_new_url())
        print(r'new_url保存完成(E:\Python\Data\new_url.dat)')
    



